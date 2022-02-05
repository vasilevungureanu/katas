package com.vasileungureanu.katas.guitarshacklegacy;

public class StockMonitor {
    private final Alert alert;
    private final ReorderThreshold reorderThreshold;
    private final Warehouse warehouse;

    public StockMonitor(Alert alert, ReorderThreshold reorderThreshold, Warehouse warehouse) {
        this.alert = alert;
        this.reorderThreshold = reorderThreshold;
        this.warehouse = warehouse;
    }

    public void productSold(int productId, int quantity) {
        Product product = warehouse.getProduct(productId);
        int unsoldStock = product.getStock() - quantity;
        if (unsoldStock <= reorderThreshold.calculate(product))
            alert.send(product);
    }
}