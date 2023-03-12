package com.example.itemvalidator.E2E;

import com.example.itemvalidator.common.ErrorCodes;
import com.example.itemvalidator.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.itemvalidator.request.ItemRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class EndToEndTesting {

    @Value("${server.port}")
    private String port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void testValidateItem(){
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.setSiteId(56);
        itemRequest.setCategoryId(874);
        itemRequest.setTitle("New car with blue color");
        itemRequest.setCondition("used");
        itemRequest.setPrice(586.00);
        itemRequest.setQuantity(1);
        itemRequest.setImageURLs(Arrays.asList("http://abc.com/diagram1.png", "http://abc.com/diagram2.png"));

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "honda");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);
        itemRequest.setDescription("Used car sales");

        ResponseEntity<Response>  response = testRestTemplate.postForEntity("http://localhost:" + port + "/validate", itemRequest, Response.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response.getHeaders());
    }

    @Test
    public void testValidateItemWithIncorrectSiteId(){
        ItemRequest itemRequest = new ItemRequest();

        itemRequest.setSiteId(-1);
        itemRequest.setCategoryId(874);
        itemRequest.setTitle("New car with blue color");
        itemRequest.setCondition("used");
        itemRequest.setPrice(586.00);
        itemRequest.setQuantity(1);
        itemRequest.setImageURLs(Arrays.asList("http://abc.com/diagram1.png", "http://abc.com/diagram2.png"));

        Map<String, String> map = new HashMap<>();
        map.put("color", "green");
        map.put("model", "honda");
        map.put("need repair", "no");
        itemRequest.setItemSpecifics(map);
        itemRequest.setDescription("Used car sales");

        ResponseEntity<Response>  response = testRestTemplate.postForEntity("http://localhost:" + port + "/validate", itemRequest, Response.class);
        System.out.println(response.getHeaders());
        Assertions.assertEquals(response.getBody().getErrorId(), ErrorCodes.ResponseCode.INVALID_SITE_ID);
        Assertions.assertEquals(response.getBody().getErrorMessage(), ErrorCodes.ResponseMessage.INVALID_SITE_ID.text);
    }

}
