package edu.cuny.brooklyn.cisc3120.streams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharFileCopier {
    public static void main(String[] args) throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("files/kaynmay.txt");
            outputStream = new FileWriter("files/copyofkaynmay.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
            System.out.println("copied files/kaynmay.txt to files/copyofkaynmay.txt char by char");     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
