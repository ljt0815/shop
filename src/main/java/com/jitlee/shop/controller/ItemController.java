package com.jitlee.shop.controller;

import com.jitlee.shop.entity.ContentImage;
import com.jitlee.shop.entity.Item;
import com.jitlee.shop.entity.ProductImage;
import com.jitlee.shop.repository.ItemRepository;
import com.jitlee.shop.service.ContentImageService;
import com.jitlee.shop.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ContentImageService contentImageService;
    private final ProductImageService productImageService;
    @Value("${my.externalStorage}")
    private String externalStorage;
    @Value("${my.externalTmpStorage}")
    private String externalTmpStorage;

    @GetMapping("/item/registerProduct")
    public String registerProduct() {
        return "registerProduct";
    }

    @PostMapping("/item/registerProduct")
    public String procRegister(@RequestParam(value = "files", required = false) MultipartFile file,
                               @RequestParam(value = "images", required = false) String[] images,
                               Item item) throws Exception{

        itemRepository.save(item);
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


}
