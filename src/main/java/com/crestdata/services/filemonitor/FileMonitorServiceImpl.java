package com.crestdata.services.filemonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMonitorServiceImpl implements FileMonitorService {


    @Override
    public String monitorFileForSpecificWord(String fileName, String regex) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            Pattern pattern = Pattern.compile(regex);
            // read line by line
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find())
                {
                    counter++;
                }
            }
            String data = fileName + " - > " + counter;
            return data;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return fileName;
    }
}
