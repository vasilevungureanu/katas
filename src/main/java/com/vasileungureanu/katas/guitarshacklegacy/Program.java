package com.vasileungureanu.katas.guitarshacklegacy;

public class Program {
    private static final StockMonitor monitor;

    static {
        final RESTService salesService = new RESTService("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales");
        final RESTService productService = new RESTService("https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product");
        ReorderThreshold reorderThreshold = new ReorderThreshold(new SalesHistory(salesService), new CurrentSystemTime());
        Warehouse warehouse = new Warehouse(productService);
        Alert alert = new Alert(new Console());
        monitor = new StockMonitor(alert, reorderThreshold, warehouse);
    }

    public static void main(String[] args) {
        int productId = Integer.parseInt(args[0]);
        int quantity = Integer.parseInt(args[1]);

        monitor.productSold(productId, quantity);
    }
}