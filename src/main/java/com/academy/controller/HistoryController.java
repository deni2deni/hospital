package com.academy.controller;

import com.academy.service.JournalService;
import com.academy.utils.DateFormatUtil;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HistoryController {

    private final JournalService journalService;
    private final SecurityUtil securityUtil;
    private final DateFormatUtil dateFormatUtil;

    @GetMapping(value = "/history")
    public String showPatientsHistory(@RequestParam Integer id, Model model) {
        model.addAttribute("journal", journalService.findAllByPatientId(id));
        model.addAttribute("patientId", id);
        model.addAttribute("formatter", dateFormatUtil);
        return "history";
    }

    @GetMapping(value = "/myHistory")
    public String showCurrentPatientsHistory(Model model){
        model.addAttribute("journal", journalService.findAllByPatientUsername(securityUtil.getUsername()));
        model.addAttribute("formatter", dateFormatUtil);
        return "history";
    }
}
