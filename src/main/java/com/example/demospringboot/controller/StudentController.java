package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController // Kết hợp @Controller và @ResponseBody
@RequestMapping("/api/students")
public class StudentController {

}
