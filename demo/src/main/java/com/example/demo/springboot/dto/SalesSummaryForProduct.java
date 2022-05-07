package com.example.demo.springboot.dto;

import lombok.Data;

@Data
public class SalesSummaryForProduct {
    public String orderNo ;
    public String orderDate ;
    public String paymentStatus;
    public String brand ;
    public String sku;
    public Double originalPrice;
    public Double unitSoldPrice;
    public Double unitCost;
    public Long soldQty;
    public Double totalSoldAmount ;
    public Double totalCost;
}
