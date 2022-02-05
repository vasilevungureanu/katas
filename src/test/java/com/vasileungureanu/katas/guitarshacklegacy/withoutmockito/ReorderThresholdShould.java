package com.vasileungureanu.katas.guitarshacklegacy.withoutmockito;

import com.vasileungureanu.katas.guitarshacklegacy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Reorder Threshold should")
public class ReorderThresholdShould {
    private static final String DUMMY_BASE_URL = "";

    private ReorderThreshold reorderThreshold;
    private SalesHistory salesHistory;
    private Date startDate;
    private Date endDate;
    private Product product;

    private Date fetchSalesTotalCalledWithRightStartDate;
    private Date fetchSalesTotalCalledWithRightEndDate;

    @BeforeEach
    public void setUp() {
        fetchSalesTotalCalledWithRightStartDate = null;
        fetchSalesTotalCalledWithRightEndDate = null;

        RESTService dummyRestService = new RESTService(DUMMY_BASE_URL);
        salesHistory = new SalesHistory(dummyRestService) {
            @Override
            public SalesTotal fetchSalesTotal(Product product, Date startDate, Date endDate) {
                fetchSalesTotalCalledWithRightStartDate = startDate;
                fetchSalesTotalCalledWithRightEndDate = endDate;

                return new SalesTotal();
            }
        };

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.APRIL, 25);
        Date currentTime = calendar.getTime();
        calendar.set(2020, Calendar.APRIL, 25);
        startDate = calendar.getTime();
        calendar.set(2020, Calendar.MAY, 25);
        endDate = calendar.getTime();

        CurrentSystemTime currentSystemTime = new CurrentSystemTime() {
            @Override
            public Date get() {
                return currentTime;
            }
        };

        reorderThreshold = new ReorderThreshold(salesHistory, currentSystemTime);
        product = new Product(811, 10, 14);
    }


    @Test
    @DisplayName("ensure that the sales total begins on the same day as the last year")
    public void ensureSalesTotalBeginsSameDayLastYear() {
        reorderThreshold.calculate(product);

        assertThat(startDate).isEqualTo(fetchSalesTotalCalledWithRightStartDate);
    }

    @Test
    @DisplayName("ensure that the sales total ends after 30 days from the start date")
    public void ensureSalesTotalEnds30daysAfterStartDate() {
        reorderThreshold.calculate(product);

        assertThat(endDate).isEqualTo(fetchSalesTotalCalledWithRightEndDate);
    }
}