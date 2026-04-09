package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/java")
    public String java() { return "java"; }

    @GetMapping("/python")
    public String python() { return "python"; }

    @GetMapping("/html")
    public String html() { return "html"; }

    @GetMapping("/javascript")
    public String js() { return "javascript"; }
}
