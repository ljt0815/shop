package com.jitlee.shop.controller;

import com.jitlee.shop.entity.Item;
import com.jitlee.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;

    @PostMapping("/order/{id}")
    public String orderForm(@PathVariable Long id,
                            @RequestParam int quantity,
                            Model model) {

        Item item = itemService.find(id);
        model.addAttribute("item", item);
        model.addAttribute("quantity", quantity);
        return "orderForm";
    }

}
