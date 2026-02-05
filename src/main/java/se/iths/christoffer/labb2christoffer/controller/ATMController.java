package se.iths.christoffer.labb2christoffer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.christoffer.labb2christoffer.service.ATMService;

@Controller
@RequestMapping("/balance")
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public String getBalance(Model model) {
        int balance = atmService.getBalance();
        model.addAttribute("balance", balance);
        return "balance";

    }
}
