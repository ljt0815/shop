package com.jitlee.shop.service;

import com.jitlee.shop.entity.ContentImage;
import com.jitlee.shop.repository.ContentImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentImageService {

    private final ContentImageRepository contentImageRepository;

    @Transactional
    public ContentImage findImage(String filename) {
        ContentImage contentImage = contentImageRepository.findByFilename(filename).orElseGet(()->{
            return null;
        });
        return contentImage;
    }

    @Transactional
    public void save(ContentImage contentImage) {
        contentImageRepository.save(contentImage);
    }
}
