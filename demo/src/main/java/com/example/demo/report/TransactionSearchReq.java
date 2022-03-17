package com.example.demo.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionSearchReq implements Serializable {
	private static final long serialVersionUID = -7171469571123003370L;

	private String departmentName;
	private String transactionDate;
	private String source;
	private String virtualAccountNo;
	private String subAccountEnglishName;
	private String transactionCurrency;
	private BigDecimal netAmtHkd;
	private String remark;

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getVirtualAccountNo() {
		return virtualAccountNo;
	}
	public void setVirtualAccountNo(String virtualAccountNo) {
		this.virtualAccountNo = virtualAccountNo;
	}
	public String getSubAccountEnglishName() {
		return subAccountEnglishName;
	}
	public void setSubAccountEnglishName(String subAccountEnglishName) {
		this.subAccountEnglishName = subAccountEnglishName;
	}
	public String getTransactionCurrency() {
		return transactionCurrency;
	}
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}
	public BigDecimal getNetAmtHkd() {
		return netAmtHkd;
	}
	public void setNetAmtHkd(BigDecimal netAmtHkd) {
		this.netAmtHkd = netAmtHkd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
