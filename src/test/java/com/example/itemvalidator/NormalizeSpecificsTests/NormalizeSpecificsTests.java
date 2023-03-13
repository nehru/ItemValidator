package com.example.itemvalidator.NormalizeSpecificsTests;

import com.example.itemvalidator.service.NormalizeSpecifics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class NormalizeSpecificsTests {

    @Autowired
    NormalizeSpecifics normalizeSpecifics;

    private Map<String, String> cache;

    @BeforeEach
    public void setUp(){
        cache = normalizeSpecifics.getCache();
    }

    @Test
    public void testNomalizetoNull(){
        String result = normalizeSpecifics.nomalize(null);
        Assertions.assertEquals(result, "");
    }

    @Test
    public void testNomalizeWithEmptyString(){
        String result = normalizeSpecifics.nomalize("");
        Assertions.assertEquals(result, "");
    }

    @Test
    public void testNomalizeToUpperCase(){
        String result = normalizeSpecifics.nomalize("APPLICATION TO VALIDATE DATA");
        Assertions.assertEquals(result, "Application to validate data");
    }

    @Test
    public void testNomalizeToLowerCase(){
        String result = normalizeSpecifics.nomalize("application to validate data");
        Assertions.assertEquals(result, "Application to validate data");
    }

    @Test
    public void testNomalizeToLowerAndUpperCase(){
        String result = normalizeSpecifics.nomalize("application To VALIDATE data");
        Assertions.assertEquals(result, "Application to validate data");
    }

    @Test
    public void testNomalizeToCacheHit(){
        cache.put("application to validate data", "Application to validate data");
        String result = normalizeSpecifics.nomalize("application to validate data");
        Assertions.assertEquals(result, "Application to validate data");
    }

    @Test
    public void testNomalizeToCacheMiss(){
        String result = normalizeSpecifics.nomalize("application to validate data");
        Assertions.assertEquals(result, "Application to validate data");
        Assertions.assertEquals(cache.get("application to validate data"), "Application to validate data");
    }

}
