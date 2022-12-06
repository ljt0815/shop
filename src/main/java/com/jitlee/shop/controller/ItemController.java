package com.jitlee.shop.controller;

import com.jitlee.shop.entity.ContentImage;
import com.jitlee.shop.entity.Item;
import com.jitlee.shop.entity.ProductImage;
import com.jitlee.shop.repository.ItemRepository;
import com.jitlee.shop.service.ContentImageService;
import com.jitlee.shop.service.ItemService;
import com.jitlee.shop.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ContentImageService contentImageService;
    private final ProductImageService productImageService;
    @Value("${my.externalStorage}")
    private String externalStorage;
    @Value("${my.externalTmpStorage}")
    private String externalTmpStorage;
    @Value("${my.imgConnectPath}")
    private String connectPath;

    @GetMapping("/admin/registerProduct")
    public String registerProduct() {
        return "registerProduct";
    }

    @PostMapping("/admin/registerProduct")
    public String procRegister(@RequestParam(value = "files", required = false) MultipartFile file,
                               @RequestParam(value = "images", required = false) String[] images,
                               Item item) throws Exception{

        itemService.save(item);
        if (images != null) {
            for (String img : images) {
                File tmp = new File(externalTmpStorage + img);
                File copyImg = new File(externalStorage + img);
                if (tmp.exists()) {
                    Files.copy(tmp.toPath(), copyImg.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    ContentImage contentImage = new ContentImage();
                    contentImage.setFilename(img);
                    contentImage.setItemContentImg(item);
                    contentImageService.save(contentImage);
                } else {
                    copyImg.delete();
                }
            }
        }
        if (!(file == null || file.isEmpty())) {
            String originFileName = file.getOriginalFilename();
            String extension = originFileName.substring(originFileName.lastIndexOf("."));
            String filePath = UUID.randomUUID() + extension;
            File dest = new File(externalStorage + filePath);
            file.transferTo(dest);
            ProductImage productImage = new ProductImage();
            productImage.setFilename(filePath);
            productImage.setItemProductImg(item);
            productImageService.save(productImage);
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

    @PutMapping("/admin/registerProduct/{id}")
    public String editItemProc(@PathVariable Long id, Model model) {
        Item item = itemService.find(id);
        if (item != null) {
            model.addAttribute("item", item);
            model.addAttribute("connectPath", connectPath);
        }
        return "editItem";
    }
}
