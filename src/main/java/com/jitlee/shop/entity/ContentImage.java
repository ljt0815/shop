package com.jitlee.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ContentImage {

    @Id @GeneratedValue
    @Column(name = "CONTENTIMAGE_ID")
    private Long id;
    private String filename;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item itemContentImg;
}
