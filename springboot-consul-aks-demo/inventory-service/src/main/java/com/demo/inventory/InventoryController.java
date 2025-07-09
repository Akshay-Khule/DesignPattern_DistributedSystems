package com.example.inventory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/{productId}")
    public String getInventoryStatus(@PathVariable int productId) {
        return productId % 2 == 0 ? "Out of Stock" : "Available";
    }
}
