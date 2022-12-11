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

    @OneToMany(mappedBy = "itemContentImg", cascade = CascadeType.REMOVE)
    private List<ContentImage> ContentImages = new ArrayList<>();

    @OneToMany(mappedBy = "itemProductImg", cascade = CascadeType.REMOVE)
    private List<ProductImage> productImages = new ArrayList<>();

    private String thumbnail;
    private String name;
    @Column(length = 1000)
    private String content;
    private int price;
    private int stockQuantity;

}
