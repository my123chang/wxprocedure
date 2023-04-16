package com.wxprocedure.controller;

import com.wxprocedure.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/search/{match}")
    public List<String> filter(@PathVariable("match") String match) {
        return cardService.filter(match);
    }
}
