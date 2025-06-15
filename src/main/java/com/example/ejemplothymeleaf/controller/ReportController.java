package com.example.ejemplothymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ejemplothymeleaf.repository.ProductRepository;
import com.example.ejemplothymeleaf.service.impl.ReportService;



@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    @Autowired
    ProductRepository repository;

    @GetMapping("/view")
    public String viewReportPage(Model model) {
        List<com.example.ejemplothymeleaf.model.Product> products = repository.findAll();
        model.addAttribute("products", products);
        return "report-view";
    }

}