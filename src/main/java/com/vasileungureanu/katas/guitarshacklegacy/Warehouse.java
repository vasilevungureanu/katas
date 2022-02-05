package com.vasileungureanu.katas.guitarshacklegacy;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final RESTService productService;

    public Warehouse(RESTService productService) {
        this.productService = productService;
    }

    public Product getProduct(int productId) {
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String result = productService.sendRequest(params);
        return new Gson().fromJson(result, Product.class);
    }
}