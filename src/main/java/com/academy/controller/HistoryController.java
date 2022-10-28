package com.academy.controller;

import com.academy.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryController {

    private final JournalService journalService;

    @Autowired
    public HistoryController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping(value = "/history")
    public String showPatientsHistory(@RequestParam Integer id, Model model) {
        var journal = journalService.findAllByPatientId(id);
        model.addAttribute("journal", journal);
        return "history";
    }
}
