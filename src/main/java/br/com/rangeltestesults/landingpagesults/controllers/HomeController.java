package br.com.rangeltestesults.landingpagesults.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rangeltestesults.landingpagesults.model.Conta;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home (Conta conta) {
        return "landingPage";
    }
    
    
}
