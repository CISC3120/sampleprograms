package edu.cuny.brooklyn.cisc3120.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteFileCopierTryWithResources 
{
    public static void main( String[] args ) throws IOException
    {
        // since both FileInputStream & FileOutputStream implement AutoCloseable
        // we can use try-with-resources
        try (FileInputStream in = new FileInputStream("files/kaynmay.txt");
                FileOutputStream out = new FileOutputStream("files/copyofkaynmay.txt")) {
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("copied files/kaynmay.txt to files/copyofkaynmay.txt byte by byte");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
