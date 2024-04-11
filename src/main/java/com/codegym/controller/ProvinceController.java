package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;

import com.codegym.service.impl.CustomerService;
import com.codegym.service.impl.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")

public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        Iterable<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "create province sucessfully");
        return "redirect:/provinces";
    }

    @GetMapping("/update/{id}")
        public ModelAndView updateForm(@PathVariable long id){
        Optional<Province> province = provinceService.findById(id);
        if(province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        }else{
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Update province sucessfully");
        return "redirect:/provinces";
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable long id) {
        Optional<Province> provinceOptional = provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(provinceOptional.get());
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;

    }
}
