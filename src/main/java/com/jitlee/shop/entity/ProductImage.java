package com.jitlee.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ProductImage {

    @Id @GeneratedValue
    @Column(name = "PRODUCTIMAGE_ID")
    private Long id;
    private String filename;

    private boolean thumbnail;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item itemProductImg;
}
