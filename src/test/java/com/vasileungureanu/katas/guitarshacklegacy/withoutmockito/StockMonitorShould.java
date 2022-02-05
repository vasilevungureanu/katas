package com.vasileungureanu.katas.guitarshacklegacy.withoutmockito;

import com.vasileungureanu.katas.guitarshacklegacy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Stock Monitor should")
public class StockMonitorShould {
    private static final String DUMMY_BASE_URL = "";

    private StockMonitor stockMonitor;

    private boolean sendCalled;

    @BeforeEach
    public void setUp() {
        sendCalled = false;

        Console console = new Console();
        Alert alert = new Alert(console) {
            @Override
            public void send(Product product) {
                sendCalled = true;
            }
        };

        RESTService salesService = new RESTService(DUMMY_BASE_URL) {
            @Override
            public String sendRequest(Map<String, Object> params) {
                return "{\"total\": 60}";
            }
        };

        RESTService productService = new RESTService(DUMMY_BASE_URL) {
            @Override
            public String sendRequest(Map<String, Object> params) {
                return "{\"id\": 811, \"leadTime\": 14, \"stock\": 30}";
            }
        };

        ReorderThreshold reorderThreshold = new ReorderThreshold(new SalesHistory(salesService), new CurrentSystemTime());
        Warehouse warehouse = new Warehouse(productService);
        stockMonitor = new StockMonitor(alert, reorderThreshold, warehouse);
    }

    @Test
    @DisplayName("send an alert when current stock is below the order threshold")
    public void sendAlertWhenCurrentStockIsBelowOrderThreshold() {
        stockMonitor.productSold(811, 2);

        assertThat(sendCalled).isTrue();
    }

    @Test
    @DisplayName("not send an alert when current stock is above the order threshold")
    public void notSendAlertWhenCurrentStockIsAboveOrderThreshold() {
        stockMonitor.productSold(811, 1);

        assertThat(sendCalled).isFalse();
    }
}