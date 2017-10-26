package edu.cuny.brooklyn.cisc3120.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteFileCopier 
{
    public static void main( String[] args ) throws IOException
    {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            in = new FileInputStream("files/kaynmay.txt");
            out = new FileOutputStream("files/copyofkaynmay.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("copied files/kaynmay.txt to files/copyofkaynmay.txt byte by byte");            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
