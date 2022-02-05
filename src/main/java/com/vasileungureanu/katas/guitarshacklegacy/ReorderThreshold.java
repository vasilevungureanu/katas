package com.vasileungureanu.katas.guitarshacklegacy;

public class ReorderThreshold {
    private final SalesHistory salesHistory;
    private final CurrentSystemTime currentTime;

    public ReorderThreshold(SalesHistory salesHistory, CurrentSystemTime currentSystemTime) {
        this.salesHistory = salesHistory;
        this.currentTime = currentSystemTime;
    }

    int calculate(Product product) {
        DateRange dateRange = new DateRange(currentTime);
        SalesTotal total = salesHistory.fetchSalesTotal(product, dateRange.getStartDate(), dateRange.getEndDate());
        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }
}