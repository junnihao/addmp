package com.example.demo.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class AaestReport {


	public static void main(String[] args){
		AaestReport ar = new AaestReport() ;
		ArrayList<TransactionSearchReq> data = new ArrayList<TransactionSearchReq>();

		for(int i = 0 ;i<=57;i++){
			TransactionSearchReq tr = new TransactionSearchReq() ;

			tr.setDepartmentName("Contemporary Marketing Center");
			tr.setVirtualAccountNo("120462070042");
			tr.setSubAccountEnglishName("HKU-FINANCE AND ENTERPRISES OFFICE X");
			tr.setTransactionCurrency("HKD");
			tr.setTransactionDate("20210115");
			tr.setNetAmtHkd(new BigDecimal(1000.0));
			tr.setRemark("FPS/MISS LUIPUI SEE/FRN2020-5");
			data.add(tr) ;
		}

		TransactionSearchReq tr2 = new TransactionSearchReq() ;
		tr2.setDepartmentName("Contemporary XYZ");
		tr2.setVirtualAccountNo("120462070042");
		tr2.setSubAccountEnglishName("HKU-FINANCE AND ENTERPRISES OFFICE");
		tr2.setTransactionCurrency("HKD");
		tr2.setTransactionDate("20210115");
		tr2.setNetAmtHkd(new BigDecimal(1.0));
		tr2.setRemark("FPS/MISS LUIPUI SEE/FRN2020-5 FPS/MISS LUIPUI SEE/FRN2020-5FPS/MISS LUIPUI SEE/FRN2020-5FPS/MISS LUIPUI SEE/FRN2020-5FPS/MISS LUIPUI SEE/FRN2020-5");
		data.add(tr2) ;


		TransactionSearchReq tr3 = new TransactionSearchReq() ;
		tr3.setDepartmentName("Contemporary Marketing Center");
		tr3.setVirtualAccountNo("120462070046");
		tr3.setSubAccountEnglishName("HKU-FINANCE AND ENTERPRISES OFFICE");
		tr3.setTransactionCurrency("HKD");
		tr3.setTransactionDate("20210115");
		tr3.setNetAmtHkd(new BigDecimal(1.0));
		tr3.setRemark("FPS/MISS LUIPUI SEE/FRN2020-5");
		data.add(tr3) ;


		JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		String reportFilePath = "D:\\iReportFiles\\WHCL\\trans-account.jasper";
		FileOutputStream outputStream = null ;

		JasperReport report = null;
		try {
			report = (JasperReport)JRLoader.loadObject(reportFilePath);

			Map<String, Object> parameter = new HashMap<String,Object>() ;
			parameter.put("transactionDateFrom", "2020/01/01") ;
			parameter.put("transactionDateTo", "2020/12/31") ;

			// parameter.put("accountNoNetAmtTotalMap", ar.getAccountNoNetAmtTotalMap(data)) ;
			//parameter.put("accountNoPageTotalMap", ar.getAccountNoPageTotalMap(data,parameter)) ;
			ar.setReportParameter(data,parameter) ;

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameter, dataSource);

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);

			outputStream = new FileOutputStream("D:\\iReportFiles\\WHCL\\trans-account.pdf");
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} /*finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}

    /* public Map<String,BigDecimal> getAccountNoNetAmtTotalMap(ArrayList<TransactionSearchReq> data){
    	Map<String,BigDecimal> accountNoNetAmtTotalMap = new HashMap<String,BigDecimal>() ;
    	Set<String> virtualAccountNosSet = new HashSet<String>() ;
    	for(TransactionSearchReq tr:data){
    		virtualAccountNosSet.add(tr.getVirtualAccountNo()) ;
    	}

    	for(String virtualAccountNo:virtualAccountNosSet){
    		BigDecimal groupNetAmt = new BigDecimal(0) ;
    		for(TransactionSearchReq tr:data){
    			if(virtualAccountNo.equals(tr.getVirtualAccountNo())){
    				groupNetAmt = groupNetAmt.add(tr.getNetAmtHkd());
    			}
        	}
    		accountNoNetAmtTotalMap.put(virtualAccountNo, groupNetAmt) ;
    	}
    	return accountNoNetAmtTotalMap ;
    } */

	public void setReportParameter(ArrayList<TransactionSearchReq> data,Map<String, Object> reportParameter){
		Map<String,Integer> accountNoPageTotalMap = new HashMap<String,Integer>() ;
		Set<String> virtualAccountNosSet = new HashSet<String>() ;
		for(TransactionSearchReq tr:data){
			virtualAccountNosSet.add(tr.getVirtualAccountNo()) ;
		}

		for(String virtualAccountNo:virtualAccountNosSet){
			Integer recordNumber = 0 ;
			StringBuffer departmentNames = new StringBuffer("") ;
			for(TransactionSearchReq tr:data){
				if(virtualAccountNo.equals(tr.getVirtualAccountNo())){
					recordNumber = recordNumber+1 ;
					if(!(departmentNames.toString()).contains(tr.getDepartmentName())){
						if("".equals(departmentNames.toString())){
							departmentNames = departmentNames.append(tr.getDepartmentName()) ;
						}else{
							departmentNames = departmentNames.append(" , ").append(tr.getDepartmentName()) ;
						}
					}
				}
			}

			reportParameter.put(virtualAccountNo,departmentNames.toString()) ;

			int remainRecord = recordNumber - (recordNumber/28)*28 ;
			Integer pageTotal = 0 ;
			if(remainRecord<=23){
				pageTotal = (recordNumber/28)+1 ;
			}else{
				pageTotal = (recordNumber/28)+2 ;
			}
			accountNoPageTotalMap.put(virtualAccountNo,pageTotal);
			//logger.info(" virtualAccountNo total page is:"+ pageTotal);
		}
		//return accountNoPageTotalMap ;
		reportParameter.put("accountNoPageTotalMap", accountNoPageTotalMap) ;
	}
}
