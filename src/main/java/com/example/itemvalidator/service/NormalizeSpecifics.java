package com.example.itemvalidator.service;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NormalizeSpecifics {
    private Map<String, String> cache = new ConcurrentHashMap<>();

    public String nomalize(String itemSpecific) {
        if (cache.containsKey(itemSpecific)) {
            return cache.get(itemSpecific);
        }

        String normalizeditemSpecific = null;
        if (itemSpecific != null && itemSpecific.length() > 0) {
            normalizeditemSpecific = itemSpecific.substring(0, 1).toUpperCase() + itemSpecific.substring(1).toLowerCase();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cache.put(itemSpecific, normalizeditemSpecific);

            return normalizeditemSpecific;
        }
        return "";
    }
}
