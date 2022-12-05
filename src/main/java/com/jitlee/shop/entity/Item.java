package com.jitlee.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> CategoryItems = new ArrayList<>();

    @OneToMany(mappedBy = "itemContentImg")
    private List<ContentImage> ContentImages;

    @OneToMany(mappedBy = "itemProductImg")
    private List<ProductImage> productImages;

    private String name;
    @Column(length = 1000)
    private String content;
    private int price;
    private int stockQuantity;
}
