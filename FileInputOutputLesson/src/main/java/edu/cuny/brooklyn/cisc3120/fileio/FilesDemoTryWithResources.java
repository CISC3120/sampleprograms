package edu.cuny.brooklyn.cisc3120.fileio;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemoTryWithResources {
    private static String[] limericks = {
            "There once was an old man from Esser,",
            "Who's knowledge grew lesser and lesser.",
            "       It at last grew so small,",
            "       He knew nothing at all,",
            "And now he's a College Professor."
    };
    
    public static void main(String[] args) {
        FilesDemoTryWithResources demo = new FilesDemoTryWithResources();
        
        demo.writeNewFile();
    }
    
    private void writeNewFile() {
        Path path = Paths.get("files", "demo1", "file1.txt");
        
        if (Files.exists(path)) {
            System.out.format("%s exists. Exit...%n", path);
        }
        
        System.out.format("Great, the path is available to use because %s does not exists.%n", path);
        
        if (Files.exists(path.getParent())) {
            System.out.format("%s exists. Move forward ...%n", path.getParent());
        } else {
            System.out.format("%s does not exists. Attempt to create ...%n", path.getParent());
            System.out.println(Files.isDirectory(path.getParent()));
            if (Files.isDirectory(path.getParent().getParent()) && Files.isWritable(path.getParent().getParent())) {
                try {
                    Files.createDirectory(path.getParent());
                    System.out.format("Created directory %s%n",  path);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.format("Unexpected error when creating %s%n", path.getParent());
                }
            } else {
                System.out.format("Either %s is not a directory or it is not writable. Exit ..."
                        , path.getParent().getParent());
            }
        }
        
        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path))) {
            for (String line: limericks) {
                printWriter.println(line);
            }
            System.out.format("Wrote to %s%n", path);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.format("Cannot write to %s due to I/O error.", path);
        }
    }

}
