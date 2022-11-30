package com.jitlee.shop.service;

import com.jitlee.shop.entity.Item;
import com.jitlee.shop.entity.Member;
import com.jitlee.shop.entity.RoleType;
import com.jitlee.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item find(Long id) {
        Item item = itemRepository.findById(id).orElseGet(()->{
            return null;
        });
        return item;
    }
}
