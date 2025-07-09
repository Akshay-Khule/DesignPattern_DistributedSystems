package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable int id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("inventory-service");
        String inventoryStatus = "Unavailable";

        if (!instances.isEmpty()) {
            String url = instances.get(0).getUri() + "/inventory/" + id;
            try {
                inventoryStatus = new java.util.Scanner(new java.net.URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
            } catch (Exception e) {
                inventoryStatus = "Error contacting inventory-service";
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("productId", id);
        response.put("productName", "Keyboard");
        response.put("inventoryStatus", inventoryStatus);

        return response;
    }
}
