/*package com.example.demo.report;

import com.hku.onlinepayment.domain.type.ReportType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class PdfService {

    //wkhtmltopdf在系统中的路径
    @Value("${toPdf}")
    private String toPdfTool;

    // 共享目录
    @Value("${upload.path}")
    String uploadFilePath;

    @Value("${apiServer}")
    String apiServer;

    @Value("${coa.report}")
    String report;
*/
    /**
     * 生成pdf(文件名带后缀)
     * @param invNo
     * @param fileName
     * @return
     */
    // key 用于加密
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public String recepit(String invNo, String fileName) {
        String subPath = "receipt";
        boolean recepitFlag = convert(apiServer + subPath + "?key=hjfkgjfjdhsssskkksl&token="+invNo, uploadFilePath +File.separator + subPath + File.separator + fileName, false);
        if (recepitFlag) {
            log.info("生成pdf成功，路径:{}", uploadFilePath +File.separator + subPath + File.separator + fileName);
            return fileName;
        } else {
            log.error("生成pdf失败，未打印，invNO:{}", invNo);
            return null;
        }
    }

    public String report(ReportType reportType, String batchNo, String fileName, String destPath) {
//        String subPath = uploadToSFTP? report: report+"_backup";
//        String destPath = uploadFilePath +File.separator + subPath + File.separator + fileName;
        boolean reprotFlag = convert(apiServer + "report?key=hjfkgjfjdhsssskkksl&token="+batchNo + "&type=" + reportType + "&fileName=" + fileName, destPath, true);
        if (reprotFlag) {
            log.info("生成pdf成功，路径:{}", destPath);
            return destPath;
        } else {
            log.error("生成pdf失败，未打印，batchNo:{}", batchNo);
            return null;
        }
    }
*/
    /**
     * html转pdf
     *
     * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

private boolean convert(String srcPath, String destPath, boolean horizontal) {
        File file = new File(destPath);
        File parent = file.getParentFile();
        String space = " ";

        //如果pdf保存路径不存在，则创建路径
        if (!parent.exists())
            parent.mkdirs();
        StringBuilder cmd = new StringBuilder();
        cmd.append(toPdfTool)
                .append(space)
                .append("--footer-right").append(space).append("\"Page:[page] of [topage]\"").append(space)
                .append("--footer-font-size").append(space).append("7").append(space)
                .append("--disable-smart-shrinking").append(space)
                .append("--enable-local-file-access").append(space)
                .append("--load-media-error-handling").append(space).append("ignore").append(space)
                .append("--load-error-handling").append(space).append("ignore").append(space);
        if (horizontal) {
            cmd.append("--orientation").append(space).append("landscape").append(space);
        }
        cmd
                .append("\""+srcPath+"\"").append(space)
                .append(destPath);

        log.info("pdf create : {}",cmd.toString());

        boolean result = true;
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        } catch (Exception e) {
            result = false;
            log.error(e.getMessage(),e);
        }

        return result;
    }

    private class HtmlToPdfInterceptor extends Thread {
        private InputStream is;

        public HtmlToPdfInterceptor(InputStream is) {
            this.is = is;
        }

        public void run() {
            try (InputStreamReader isr = new InputStreamReader(is, "utf-8");
                 BufferedReader br = new BufferedReader(isr);){
                String line;
                while ((line = br.readLine()) != null) {
                    log.debug(line); //输出内容
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
    }

}
*/
