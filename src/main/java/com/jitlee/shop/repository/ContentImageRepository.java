package com.jitlee.shop.repository;

import com.jitlee.shop.entity.ContentImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentImageRepository extends JpaRepository<ContentImage, Long> {
    Optional<ContentImage> findByFilename(String filename);
}
