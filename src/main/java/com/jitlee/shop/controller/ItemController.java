package com.jitlee.shop.controller;

import com.jitlee.shop.entity.Item;
import com.jitlee.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Value("${my.imgConnectPath}")
    private String connectPath;

    @GetMapping("/admin/registerProduct")
    public String registerProduct() {
        return "registerProduct";
    }

    @PostMapping("/admin/registerProduct")
    public String procRegister(@RequestParam(value = "files", required = false) List<MultipartFile> files,
                               @RequestParam(value = "images", required = false) String[] images,
                               @RequestParam(value = "thumbId", required = false) Integer thumbId,
                               Item item) throws Exception{

        itemService.save(item);
        if (images != null) {
            itemService.contentImageSave(item.getId(), images);
        }
        if (!(files == null || files.isEmpty())) {
            itemService.productImageSave(item.getId(), files);
        }
        if (thumbId != null) {
            itemService.changeThumbnail(item.getId(), thumbId);
        }
        return "redirect:/";
    }

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        Item item = itemService.find(id);
        if (item != null) {
            model.addAttribute("item", item);
            model.addAttribute("connectPath", connectPath);
        }
        return "itemDetail";
    }

    @GetMapping("/admin/registerProduct/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        Item item = itemService.find(id);
        if (item != null) {
            model.addAttribute("item", item);
            model.addAttribute("connectPath", connectPath);
        }
        return "editItem";
    }

    @PostMapping("/admin/registerProduct/{id}")
    public String editItemProc(@RequestParam(value = "files", required = false) List<MultipartFile> files,
                               @RequestParam(value = "images", required = false) String[] images,
                               @RequestParam(value = "thumbId", required = false) Integer thumbId,
                               @PathVariable Long id,
                               Item item) throws IOException {
        if (images != null) {
            itemService.contentImageSave(item.getId(), images);
        }
        if (!(files == null || files.isEmpty())) {
            itemService.productImageSave(item.getId(), files);
        }
        Item findItem = itemService.find(id);
        findItem.setName(item.getName());
        findItem.setContent(item.getContent());
        if (thumbId != null) {
            itemService.changeThumbnail(id, thumbId);
        }
        itemService.save(findItem);
        return "redirect:/item/"+id;
    }
}
