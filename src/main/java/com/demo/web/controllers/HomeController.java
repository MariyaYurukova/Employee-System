package com.demo.web.controllers;


import com.demo.domain.entities.Employee;
import com.demo.domain.models.binding.CreateNewEmployee;
import com.demo.domain.models.service.EmployeeServiceModel;
import com.demo.domain.models.views.EmployeeDetailsViewModel;
import com.demo.domain.models.views.EmployeeListViewModel;
import com.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HomeController extends BaseController {



    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;


    @Autowired
    public HomeController(ModelMapper modelMapper, EmployeeService employeeService) {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

//    @GetMapping("/")
//    public ModelAndView index(ModelAndView modelAndView) {
//        List<EmployeeListViewModel> employees = this.employeeService.getAllEmployees()
//                .stream()
//                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
//                .collect(Collectors.toList());
//        modelAndView.addObject("employee", employees);
//
//        return super.view("index", modelAndView);
//
//    }




    @GetMapping("/")
    public String index(Model model) {

        List<EmployeeListViewModel> employees = this.employeeService.getAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
               .collect(Collectors.toList());

        model.addAttribute("employee", employees);
        return findPaginated(1, model);


    }




    @GetMapping("/addNewEmployee")
    public ModelAndView showNewEmployee(ModelAndView modelAndView, @ModelAttribute(name = "model") CreateNewEmployee model) {
        modelAndView.addObject("model", model);
        return super.view("add-employee", modelAndView);
    }


    @PostMapping("/addNewEmployee")
    public ModelAndView addConfirm(@ModelAttribute(name = "model") CreateNewEmployee model, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("add-employee", modelAndView);
        }

        EmployeeServiceModel employeeServiceModel = this.modelMapper.map(model, EmployeeServiceModel.class);
        this.employeeService.saveEmployee(employeeServiceModel);

        return super.redirect("/");
    }


    @GetMapping("/details/{id}")
    public ModelAndView detailsCitizen(@PathVariable String id, ModelAndView modelAndView) {
        EmployeeDetailsViewModel citizen = this.modelMapper.map(this.employeeService.getEmployeeById(id), EmployeeDetailsViewModel.class);

        modelAndView.addObject("model", citizen);

        return super.view("details", modelAndView);
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteProductConfirm(@PathVariable String id) {

        this.employeeService.deleteEmployeeById(id);

        return super.redirect("/");
    }


    @GetMapping("/update/{id}")
    public ModelAndView updateEmployee(@PathVariable String id, ModelAndView modelAndView) {
        EmployeeServiceModel employeeServiceModel = this.employeeService.getEmployeeById(id);
        CreateNewEmployee model = this.modelMapper.map(employeeServiceModel, CreateNewEmployee.class);


        modelAndView.addObject("employee", model);
        modelAndView.addObject("employeeId", id);

        return super.view("update-employee", modelAndView);
    }


    @PostMapping("/update/{id}")
    public ModelAndView editEmployeeConfirm(@PathVariable String id, @ModelAttribute CreateNewEmployee model) {
        this.employeeService.updateEmployee(id, this.modelMapper.map(model, EmployeeServiceModel.class));

        return super.redirect("/details/" + id);
    }





    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {


       Page < Employee > page = employeeService.findPaginated(pageNo, 5);
       //List < Employee > listEmployees = page.getContent();
        List<EmployeeListViewModel> employees = this.employeeService.findPaginated(pageNo,5)
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("employee", employees);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", employees);
        return "index";
    }

}











