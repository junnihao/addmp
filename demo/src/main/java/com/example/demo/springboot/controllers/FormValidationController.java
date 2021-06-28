package com.example.demo.springboot.controllers;

import com.example.demo.springboot.form.PersonForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.validation.Valid;

@Slf4j
@Controller
public class FormValidationController extends WebMvcConfigurationSupport {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/personValidationFromResult").setViewName("personValidationFromResult");
    }

    @GetMapping("/personForm")
    public String showForm(PersonForm personForm) {
        log.info("display page .........................." ) ;
        return "persionValidationForm";
    }

    @PostMapping("/personForm")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
         log.info("input name is :" + personForm.getName()) ;
        if (bindingResult.hasErrors()) {
            return "persionValidationForm";
        }
        return "redirect:/personValidationFromResult";
    }
}
