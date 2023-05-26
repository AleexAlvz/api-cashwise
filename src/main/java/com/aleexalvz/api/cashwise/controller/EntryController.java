package com.aleexalvz.api.cashwise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @GetMapping
    public String entry() {
        return "Entry point";
    }
}
