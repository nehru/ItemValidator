package com.example.itemvalidator.controller;

import com.example.itemvalidator.common.ErrorCodes;
import com.example.itemvalidator.response.Response;
import com.example.itemvalidator.service.ItemValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/health")
    Response GetHealth(){
        return new Response(ErrorCodes.ResponseCode.HEALTH_CHECK, ErrorCodes.ResponseMessage.HEALTH_CHECK.text);
    }
}
