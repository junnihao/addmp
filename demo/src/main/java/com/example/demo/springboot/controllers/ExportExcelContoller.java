package com.example.demo.springboot.controllers;

import com.example.demo.springboot.dto.SalesSummaryForPayment;
import com.example.demo.springboot.dto.SalesSummaryForProduct;
import com.example.demo.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ekbuyReport/salesSummary")
public class ExportExcelContoller {

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void payment(@RequestParam("fromDate") String fromDate,
                              @RequestParam("toDate") String toDate,
                              HttpServletResponse response){
        log.info("fromDate = " + fromDate);
        log.info("toDate = " + toDate);
        List<String> title = Arrays.asList("ORDER NO","ORDER DATE","PAYMENT STATUS","PAYMENT TYPE","MEMBER NO","MEMBER NAME","SOLD QTY","ORDERED AMOUNT","RECEIVED AMOUNT","TOTAL COST * Shipping cost is not included");
        List<String> headers = Arrays.asList("orderNo","orderDate","paymentStatus","paymentType","memberNo","memberName","soldQty","orderAmount","receivedAmount","totalCostNoShipCost");

        String paymentFileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "EKBUY-Sales-Summary-Report-Payment-" + paymentFileSuffix ;
        SalesSummaryForPayment orderDto = new SalesSummaryForPayment() ;
        orderDto.setOrderNo("ORD0000001");
        List<SalesSummaryForPayment> list = new ArrayList<SalesSummaryForPayment>() ;
        list.add(orderDto) ;

        ExcelUtil.createReport(response,fileName,title,list,headers);
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void product(@RequestParam("fromDate") String fromDate,
                              @RequestParam("toDate") String toDate,
                              HttpServletResponse response){
        log.info("fromDate = " + fromDate);
        log.info("toDate = " + toDate);
        List<String> title = Arrays.asList("ORDER NO","ORDER DATE","PAYMENT STATUS","Brand","SKU","ORIGINAL PRICE",
                                           "UNIT SOLD PRICE","UNIT COST","SOLD QTY","TOTAL SOLD AMOUNT","TOTAL COST");
        List<String> headers = Arrays.asList("orderNo","orderDate","paymentStatus","brand","sku","originalPrice",
                                             "unitSoldPrice","unitCost","soldQty","totalSoldAmount","totalCost");

        String productFileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "EKBUY-Sales-Summary-Report-Product-" + productFileSuffix ;
        SalesSummaryForProduct orderDto = new SalesSummaryForProduct() ;
        orderDto.setOrderNo("ORD0000001");
        List<SalesSummaryForProduct> list = new ArrayList<SalesSummaryForProduct>() ;
        list.add(orderDto) ;

        ExcelUtil.createReport(response,fileName,title,list,headers);
    }
}
