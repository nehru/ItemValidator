package com.example.itemvalidator;

import com.example.itemvalidator.common.ErrorCodes;
import com.example.itemvalidator.response.Response;
import com.example.itemvalidator.service.ItemValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.itemvalidator.request.ItemRequest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ItemValidatorApplicationTests {

    @Autowired
    ItemValidationService itemValidationService;

    @Test
    public void testValidateSiteIdWithValidSiteId() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setSiteId(44);
        Response response = itemValidationService.ValidateSiteId(itemRequest);

        Assertions.assertNull(response);
    }

    @Test
    public void testValidateSiteIdWithInvalidSiteId() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setSiteId(447);
        Response response = itemValidationService.ValidateSiteId(itemRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ErrorCodes.ResponseCode.INVALID_SITE_ID, response.getErrorId());
        Assertions.assertEquals(ErrorCodes.ResponseMessage.INVALID_SITE_ID.text, response.getErrorMessage());
    }

    @Test
    public void testValidateTitleLengthLessThan85() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setTitle("The title of the car");

        Response response = itemValidationService.ValidateSiteId(itemRequest);
        Assertions.assertNull(response);
    }

    @Test
    public void testValidateTitleLengthGreaterThan85() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setTitle("The title of the car which exceeds 85 character and The title of the car which exceeds 85 character and");

        Response response = itemValidationService.ValidateTitleLength(itemRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ErrorCodes.ResponseCode.INCORRECT_TITLE_LENGTH, response.getErrorId());
        Assertions.assertEquals(ErrorCodes.ResponseMessage.INCORRECT_TITLE_LENGTH.text, response.getErrorMessage());
    }

    @Test
    public void testValidateItemSpecificsWithValidNumerOfItems() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "honda");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);

        Response response = itemValidationService.ValidateItemSpecifics(itemRequest);
        Assertions.assertNull(response);
    }

    @Test
    public void testValidateItemSpecificsWithInValidNumerOfItems() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        itemRequest.setItemSpecifics(map);

        Response response = itemValidationService.ValidateItemSpecifics(itemRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ErrorCodes.ResponseCode.INCORRECT_ITEMS_IN_SPECIFICS, response.getErrorId());
        Assertions.assertEquals(ErrorCodes.ResponseMessage.INCORRECT_ITEMS_IN_SPECIFICS.text, response.getErrorMessage());
    }

    @Test
    public void testNormalizeSpecificsWithNullInSpecifics() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setItemSpecifics(null);

        itemRequest = itemValidationService.NormalizeSpecifics(itemRequest);
        Assertions.assertNotNull(itemRequest);
        Assertions.assertEquals(itemRequest.getItemSpecifics().size(),0);
    }

    @Test
    public void testNormalizeSpecificsWithValidValuesInSpecifics() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "honda");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);

        itemRequest = itemValidationService.NormalizeSpecifics(itemRequest);
        Assertions.assertNotNull(itemRequest);
        Assertions.assertEquals(itemRequest.getItemSpecifics().size(),3);
        Assertions.assertEquals(itemRequest.getItemSpecifics().get("color"), "Green");
    }

    @Test
    public void testValidateModelInSpecificsWithModelPresent() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "honda");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);
        Response response = itemValidationService.ValidateModelInSpecifics(itemRequest);
        Assertions.assertNull(response);
//        Assertions.assertEquals(ErrorCodes.ResponseCode.INCORRECT_TITLE_LENGTH, response.getErrorId());
//        Assertions.assertEquals(ErrorCodes.ResponseMessage.INCORRECT_TITLE_LENGTH.text, response.getErrorMessage());
    }

    @Test
    public void testValidateModelInSpecificsWithModelWithEmptyValue() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);
        Response response = itemValidationService.ValidateModelInSpecifics(itemRequest);
        Assertions.assertNotNull(response);
         Assertions.assertEquals(ErrorCodes.ResponseCode.MODEL_MISSING, response.getErrorId());
         Assertions.assertEquals(ErrorCodes.ResponseMessage.MODEL_MISSING.text, response.getErrorMessage());
    }

    @Test
    public void testValidateModelInSpecificsWithNoModel() {
        ItemRequest itemRequest = new ItemRequest();

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);
        Response response = itemValidationService.ValidateModelInSpecifics(itemRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ErrorCodes.ResponseCode.MODEL_MISSING, response.getErrorId());
        Assertions.assertEquals(ErrorCodes.ResponseMessage.MODEL_MISSING.text, response.getErrorMessage());
    }
}