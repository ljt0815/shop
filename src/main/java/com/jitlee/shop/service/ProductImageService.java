package com.jitlee.shop.service;

import com.jitlee.shop.entity.ProductImage;
import com.jitlee.shop.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Transactional
    public ProductImage findImage(String filename) {
        ProductImage productImage = productImageRepository.findByFilename(filename).orElseGet(()->{
            return null;
        });
        return productImage;
    }

    @Transactional
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }
}
