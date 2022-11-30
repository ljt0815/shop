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

    private String itemImages;
    private String name;
    private String content;
    private int price;
    private int stockQuantity;
}
