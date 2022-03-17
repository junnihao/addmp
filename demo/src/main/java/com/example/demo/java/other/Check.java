package com.example.demo.java.other;

public class Check {

    public static void main(String[] args){
        Check c  = new Check() ;
        //System.out.println("  ->"+c.verifyUpcCode("362000190066")) ;
        // System.out.println("  ->"+c.verifyUpcCode("0210114523009")) ;
        System.out.println("  ->"+c.verifyUpcCode("197300734038")) ;
    }

    private boolean verifyUpcCode(final String code) {
        // reference link  http://www.gs1.org/how-calculate-check-digit-manually
        try {
            if (code == null || code.length() == 1) {
                return false;
            }

            try {
                Long.getLong(code);
            } catch (Exception ex) {
                return false;
            }

            String bytesToCheck = code.substring(0, code.length() - 1);
            int checkByte = Integer.valueOf(code.substring(code.length() - 1));

            int[] m = {3, 1};
            int j = 0;
            long sum = 0;

            for (int i = bytesToCheck.length() - 1; i >= 0; i--) {
                sum += Integer.valueOf(bytesToCheck.substring(i, i + 1)) * m[(j++) % 2];
            }
            long checkSum = 10 - (sum % 10);
            if (checkSum == 10) {    // NOPMD
                checkSum = 0;
            }
            System.out.println(" checkSum =" + checkSum) ;
            System.out.println(" checkByte =" + checkByte) ;
            return checkSum == checkByte;
        } catch (Exception ex) {    // NOPMD
        }
        return false;
    }
}
