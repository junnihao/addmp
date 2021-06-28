package com.example.demo.springboot.controllers;

import com.example.demo.springboot.form.FormProcessGreeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class FormProcessGreetingController {
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new FormProcessGreeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute FormProcessGreeting greeting,Model model) {
        log.info(">>>"+greeting.getId());
        log.info(">>>"+greeting.getContent());
        model.addAttribute("greeting", greeting);
        return "greetingResult";
    }
}
