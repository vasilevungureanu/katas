package com.vasileungureanu.katas.guitarshacklegacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Stock Monitor should")
public class StockMonitorShould {
    private Alert alert;
    private StockMonitor stockMonitor;

    @BeforeEach
    public void setUp() {
        alert = mock(Alert.class);

        RESTService salesService = mock(RESTService.class);
        when(salesService.sendRequest(any())).thenReturn("{\"total\": 60}");

        RESTService productService = mock(RESTService.class);
        when(productService.sendRequest(any())).thenReturn("{\"id\": 811, \"leadTime\": 14, \"stock\": 30}");

        ReorderThreshold reorderThreshold = new ReorderThreshold(new SalesHistory(salesService), new CurrentSystemTime());
        Warehouse warehouse = new Warehouse(productService);
        stockMonitor = new StockMonitor(alert, reorderThreshold, warehouse);
    }

    @Test
    @DisplayName("send an alert when current stock is below the order threshold")
    public void sendAlertWhenCurrentStockIsBelowOrderThreshold() {
        stockMonitor.productSold(811, 2);

        verify(alert).send(any());
    }

    @Test
    @DisplayName("not send an alert when current stock is above the order threshold")
    public void notSendAlertWhenCurrentStockIsAboveOrderThreshold() {
        stockMonitor.productSold(811, 1);

        verifyNoMoreInteractions(alert);
    }
}