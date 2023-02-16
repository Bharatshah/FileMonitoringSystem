package com.crestdata.utils;

import com.crestdata.services.filewrite.FileWriterService;
import com.crestdata.services.filewrite.FileWriterServiceImpl;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileWriterUtil {

    /**
     * This method is used to write pseudo random string from pseudoRandomStringCollector to the specified file name
     * @param rc - pseudoRandomStringCollector
     * @param fileName - file path to write random strings
     * @throws IOException
     */
    public static void writeRandomStingToFile(PseudoRandomStringCollector<Object> rc, String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        java.io.FileWriter writer = new java.io.FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(writer);
        for (int length = 0; length <= 10; length += 1) {
            br.write((String) rc.next());
            br.write(" ");
        }
        br.close();
        writer.close();
    }

    public static void writeDataToFile(String data, String fileName) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        File file = new File(fileName);
        file.createNewFile();
        java.io.FileWriter writer = new java.io.FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(writer);
        br.write(dtf.format(now) + " : " +data);
        br.write("\n");
        br.close();
        writer.close();
    }

    public static void clearFile(String filePath) throws IOException {
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();
    }
}