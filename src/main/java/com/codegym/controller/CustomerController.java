package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;

import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping( "/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return provinceService.findAll();
    }
    @GetMapping()
    public ModelAndView listCustomer(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Pageable modifiedPageable = PageRequest.of(pageable.getPageNumber(), 5, pageable.getSort());
        Page<Customer> customers = customerService.findAll(modifiedPageable);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer,RedirectAttributes redirectattributes){
        customerService.save(customer);
        redirectattributes.addFlashAttribute("message","Create new customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer",customer.get());
            return modelAndView;
        }else{
            System.out.println("hello");
            return new ModelAndView("error_404");
        }

    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("customer") Customer customer , RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message","Update customer successfully");
        return "redirect:/customers";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes ){
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("message"," Delete customer successfully");
        return "redirect:/customers";
    }

}
