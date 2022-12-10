package com.jitlee.shop.service;

import com.jitlee.shop.entity.*;
import com.jitlee.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ContentImageService contentImageService;
    private final ProductImageService productImageService;
    @Value("${my.externalStorage}")
    private String externalStorage;
    @Value("${my.externalTmpStorage}")
    private String externalTmpStorage;
    @Value("${my.imgConnectPath}")
    private String connectPath;

    @Transactional
    public Item find(Long id) {
        Item item = itemRepository.findById(id).orElseGet(()->{
            return null;
        });
        return item;
    }

    @Transactional
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    public void contentImageSave(Long id, String[] images) throws IOException {
        Item item = this.find(id);
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

    public void productImageSave(Long id, List<MultipartFile> files) throws IOException {
        Item item = this.find(id);
        for(MultipartFile file : files) {
            String originFilename = file.getOriginalFilename();
            if (originFilename.lastIndexOf(".") == -1) {
                break ;
            }
            String extension = originFilename.substring(originFilename.lastIndexOf("."));
            String filePath = UUID.randomUUID() + extension;
            File dest = new File(externalStorage + filePath);
            file.transferTo(dest);
            ProductImage productImage = new ProductImage();
            productImage.setFilename(filePath);
            productImage.setItemProductImg(item);
            productImageService.save(productImage);
        }
    }

    @Transactional
    public void changeThumbnail(Long id, int thumbId) {
        Item item = this.find(id);
        List<ProductImage> productImages = item.getProductImages();
        if (productImages.isEmpty()) {
            item.setThumbnail("https://dummyimage.com/450x300/dee2e6/6c757d.jpg");
        }
        else if (thumbId < productImages.size()) {
            ProductImage productImage = productImages.get(thumbId);
            String filename = productImage.getFilename();
            item.setThumbnail(connectPath+filename);
        }
        else {
            item.setThumbnail(productImages.get(0).getFilename());
        }
    }
}
