package com.wxprocedure.controller;

import com.wxprocedure.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<String> all(){
        return cardService.all();
    }

    @PostMapping("/search/{match}")
    public List<String> filter(@PathVariable("match") String match) {
        return cardService.filter(match);
    }
}
