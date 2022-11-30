package com.jitlee.shop.controller;

import com.jitlee.shop.entity.Item;
import com.jitlee.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/registerProduct")
    public String registerProduct() {
        return "registerProduct";
    }

    @PostMapping("/item/registerProduct")
    public String procRegister(@RequestParam("files") MultipartFile file, Item item) throws Exception{
        System.out.println(item.getName());
        System.out.println(item.getPrice());
        System.out.println(item.getStockQuantity());
        System.out.println(item.getContent());

        String filePath = "D:\\uploadedImage\\" + file.getOriginalFilename();

        File dest = new File(filePath);
        file.transferTo(dest);

        return "redirect:/item/registerProduct";
    }
}
