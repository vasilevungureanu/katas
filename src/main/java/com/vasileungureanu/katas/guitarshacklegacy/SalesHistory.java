package com.vasileungureanu.katas.guitarshacklegacy;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesHistory {
    private final RESTService salesService;

    public SalesHistory(RESTService salesService) {
        this.salesService = salesService;
    }

    public RESTService getSalesService() {
        return salesService;
    }

    SalesTotal fetchSalesTotal(Product product, Date startDate, Date endDate) {
        DateFormat format = new SimpleDateFormat("M/d/yyyy");
        Map<String, Object> params = new HashMap<>() {{
            put("productId", product.getId());
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};
        String result = getSalesService().sendRequest(params);
        return new Gson().fromJson(result, SalesTotal.class);
    }
}