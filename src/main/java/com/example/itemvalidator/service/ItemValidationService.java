package com.example.itemvalidator.service;

import com.example.itemvalidator.common.ErrorCodes;
import com.example.itemvalidator.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.itemvalidator.request.ItemRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemValidationService {

    @Autowired
    NormalizeSpecifics normalizeSpecifics;

    public Response Validate(ItemRequest itemRequest){
        Response response = null;
        ItemRequest itemRequestLocal = itemRequest;

        response = ValidateSiteId(itemRequestLocal);
        if(response != null){
            return response;
        }

        response = ValidateModelInSpecifics(itemRequest);
        if(response != null){
            return response;
        }

        itemRequestLocal = NormalizeSpecifics(itemRequestLocal);

        response = ValidateItemSpecifics(itemRequestLocal);
        if(response != null){
            return response;
        }

        response = ValidateTitleLength(itemRequestLocal);
        if(response != null){
            return response;
        }

        return new Response(ErrorCodes.ResponseCode.VALIDATION_PASS, ErrorCodes.ResponseMessage.VALIDATION_PASS.text);
    }

    public Response ValidateSiteId(ItemRequest itemRequest){
        Response response = null;

        int siteId = itemRequest.getSiteId();
        if(siteId < 0 || siteId > 100){
            response = new Response(ErrorCodes.ResponseCode.INVALID_SITE_ID, ErrorCodes.ResponseMessage.INVALID_SITE_ID.text);
        }

        return response;
    }


    public Response ValidateTitleLength(ItemRequest itemRequest){
        Response response = null;

        if(itemRequest.getTitle().length() > 85){
            response = new Response(ErrorCodes.ResponseCode.INCORRECT_TITLE_LENGTH, ErrorCodes.ResponseMessage.INCORRECT_TITLE_LENGTH.text);
        }

        return response;
    }

    public Response ValidateItemSpecifics(ItemRequest itemRequest) {
        Response response = null;
        int numberOfItemSpecifics = itemRequest.getItemSpecifics().size();
        if(numberOfItemSpecifics < 2 || numberOfItemSpecifics > 10){
            response = new Response(ErrorCodes.ResponseCode.INCORRECT_ITEMS_IN_SPECIFICS, ErrorCodes.ResponseMessage.INCORRECT_ITEMS_IN_SPECIFICS.text);
        }
        return response;
    }

    public ItemRequest NormalizeSpecifics(ItemRequest itemRequest){
        Map<String, String> nomalizedSpecificsMap = new HashMap<>();

        if(itemRequest.getItemSpecifics() != null){
            itemRequest.getItemSpecifics().forEach((key,value) -> nomalizedSpecificsMap.put(key, normalizeSpecifics.nomalize(value)));
        }
        itemRequest.setItemSpecifics(nomalizedSpecificsMap);

        return itemRequest;
    }

    public Response ValidateModelInSpecifics(ItemRequest itemRequest) {
        Response response = null;

        if (itemRequest.getItemSpecifics() != null && (itemRequest.getItemSpecifics().containsKey("model") && itemRequest.getItemSpecifics().get("model").length() == 0)) {
            response = new Response(ErrorCodes.ResponseCode.MODEL_WITH_EMPTY_STRING, ErrorCodes.ResponseMessage.MODEL_WITH_EMPTY_STRING.text);
        }else if(!itemRequest.getItemSpecifics().containsKey("model")){
            response = new Response(ErrorCodes.ResponseCode.MODEL_MISSING, ErrorCodes.ResponseMessage.MODEL_MISSING.text);
        }

        return response;
    }
}
