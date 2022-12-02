package com.jitlee.shop.controller;

import com.jitlee.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${my.imgConnectPath}")
    private String connectPath;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemService.itemList());
        model.addAttribute("connectPath", connectPath);
        return "index";
    }
}
