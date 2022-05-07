package com.example.demo.springboot.dto;

import lombok.Data;

@Data
public class SalesSummaryForPayment {
    public String orderNo ;
    public String orderDate ;
    public String paymentStatus;
    public String paymentType;
    public String memberNo;
    public String memberName;
    public long soldQty;
    public Double orderAmount;
    public Double receivedAmount;
    public Double totalCostNoShipCost;
}
