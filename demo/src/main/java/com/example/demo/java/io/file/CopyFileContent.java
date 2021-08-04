package com.example.demo.java.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class CopyFileContent {
   public static void writeToDat(String path) {
		File file = new File(path);
		try {
			File f = new File("D:/analysisLog/2/28-total.log");
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
			BufferedWriter writer = new BufferedWriter(write);


		    BufferedReader bw = new BufferedReader(new FileReader(file));
		    String line = null;
		    long count  = 0L ;
		    //因为不知道有几行数据，所以先存入list集合中
	    	while((line = bw.readLine()) != null){
	    		count = count+1 ;
	    		//if(count>105550 && count<=110000) writer.write(line+"\n");
	    		if(line.contains("00701904856")) writer.write(line+"\n");
		    }
		    bw.close();
		    writer.flush();
		    writer.close();
		    write.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
   }

   public static void main(String[] args) {
       String path = "D:/analysisLog/aeon-2021-07-28.log";
       writeToDat(path);
   }
}
