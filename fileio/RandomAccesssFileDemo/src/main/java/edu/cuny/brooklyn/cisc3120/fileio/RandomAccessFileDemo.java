package edu.cuny.brooklyn.cisc3120.fileio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo 
{
    public static void main( String[] args )
    {
        if (args.length < 1) {
            System.out.println("Usage: RandomAccessFileDemo position");
            return;
        }
        
        RandomAccessFileDemo demo = new RandomAccessFileDemo();
        try {
            int number = demo.readIntegerFile("files/intlist.bin", Integer.parseInt(args[0]));
            System.out.format("The number #%d is %d.%n", Integer.parseInt(args[0]), number);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find files/intlist.bin.");
        } catch (NumberFormatException e) {
            System.err.format("Expect an integer on the Command Line, but %s is not an integer.", args[0]);
        } catch (IOException e) {
            System.err.println("File to read files/intlist.bin.");
        }
    }
    
    public int readIntegerFile(String filename, int n) throws IOException {
        int number = 0;
        try (RandomAccessFile randAccessFile = new RandomAccessFile(filename, "r")) {
            System.out.format("The file has %d bytes%n", randAccessFile.length());
            System.out.format("The file pointer is at byte %d.%n", randAccessFile.getFilePointer());
            randAccessFile.seek(Integer.SIZE/8*n);
            System.out.format("The file pointer is at byte %d after the seek.%n", randAccessFile.getFilePointer());            
            number = randAccessFile.readInt();
            System.out.format("The file pointer is at byte %d after the read.%n", randAccessFile.getFilePointer());                        
        }
        return number;
    }
    
    
}
