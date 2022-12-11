package com.jitlee.shop.controller.api;

import com.jitlee.shop.dto.ResponseDto;
import com.jitlee.shop.entity.Item;
import com.jitlee.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @DeleteMapping("/admin/deleteItem/{id}")
    public ResponseDto<Integer> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
