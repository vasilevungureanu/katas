package com.vasileungureanu.katas.guitarshacklegacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("Reorder Threshold should")
public class ReorderThresholdShould {
    private ReorderThreshold reorderThreshold;
    private SalesHistory salesHistory;
    private Date startDate;
    private Date endDate;
    private Product product;

    @BeforeEach
    public void setUp() {
        salesHistory = mock(SalesHistory.class);
        when(salesHistory.fetchSalesTotal(any(), any(), any())).thenReturn(new SalesTotal());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.APRIL, 25);
        Date currentTime = calendar.getTime();
        calendar.set(2020, Calendar.APRIL, 25);
        startDate = calendar.getTime();
        calendar.set(2020, Calendar.MAY, 25);
        endDate = calendar.getTime();

        CurrentSystemTime currentSystemTime = mock(CurrentSystemTime.class);
        when(currentSystemTime.get()).thenReturn(currentTime);

        reorderThreshold = new ReorderThreshold(salesHistory, currentSystemTime);
        product = new Product(811, 10, 14);
    }


    @Test
    @DisplayName("ensure that the sales total begins on the same day as the last year")
    public void ensureSalesTotalBeginsSameDayLastYear() {
        reorderThreshold.calculate(product);

        verify(salesHistory).fetchSalesTotal(any(), eq(startDate), any());
    }

    @Test
    @DisplayName("ensure that the sales total ends after 30 days from the start date")
    public void ensureSalesTotalEnds30daysAfterStartDate() {
        reorderThreshold.calculate(product);

        verify(salesHistory).fetchSalesTotal(any(), any(), eq(endDate));
    }
}