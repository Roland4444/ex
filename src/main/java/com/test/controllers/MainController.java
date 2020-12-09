package com.test.controllers;

import com.test.TableCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title","title" );
        return "index";
    }

    @GetMapping("/table")
    public String table(Model model) {
        return "table";
    }

    @PostMapping("/table")
    public String table(@RequestParam int number, Model model) throws IOException {
        if (number<= 0)
            return "index";

        TableCreator.createTable(number);
        return "table";
    }



}
