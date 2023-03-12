package com.example.itemvalidator.controller;

import com.example.itemvalidator.response.Response;
import com.example.itemvalidator.service.ItemValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.itemvalidator.request.ItemRequest;

@RestController
public class ItemValidationController {

    @Autowired
    ItemValidationService itemValidationService;

    @PostMapping("/validate")
    Response validateItems(@RequestBody ItemRequest createItemRequest){

        Response response = itemValidationService.Validate(createItemRequest);
        return response;
    }
}
