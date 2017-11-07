package edu.cuny.brooklyn.cisc3120.fileio;

import java.nio.charset.StandardCharsets;

public class NumberConversion {
    /* assume ASCII, i.e., the locale is en_US */
    public static int binaryToAny(int n, int base, byte[] digits) {
        int remainder;
        int i = 0;
        do {
            remainder = n % base;
            n = n / base;
            digits[i] = (byte)remainder;
            i ++;
        } while (n > 0);
        return i;
    }
    
    public static byte[] byteToAscii(byte[] digits, int length) {
        int startOfZero = 48;
        int startOfA = 65;
        byte[] asciiBytes = new byte[length];
        for (int i=0; i<length; i++) {
            asciiBytes[asciiBytes.length - 1 - i] 
                    = (byte)((int)digits[i] + ((int)digits[i]<=9?startOfZero:startOfA));
        }
        return asciiBytes;
    }
    
    public static String binaryToAnyBaseString(int n, int base) {
        byte[] digits = new byte[32];
        int ndigits = binaryToAny(n, base, digits);
        byte[] asciiBytes = byteToAscii(digits, ndigits);
        return new String(asciiBytes, StandardCharsets.US_ASCII);
    }
    
    public static void main(String[] args) {
        System.out.println(binaryToAnyBaseString(302, 10));
    }
}
