package com.demo.controller;

import com.demo.model.Customer;
import com.demo.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView showListCustomer(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers",customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("create");
        customerService.save(customer);
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message", "Customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer!= null){
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer")Customer customer){
        ModelAndView modelAndView = new ModelAndView("edit");
        customerService.save(customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers",customerService.findAll());
        return modelAndView;
    }
}
