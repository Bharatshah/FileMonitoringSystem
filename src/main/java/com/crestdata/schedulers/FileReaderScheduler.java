package com.crestdata.schedulers;

import com.crestdata.services.filemonitor.FileMonitorService;
import com.crestdata.services.filemonitor.FileMonitorServiceImpl;
import com.crestdata.services.filewrite.FileWriterService;
import com.crestdata.services.filewrite.FileWriterServiceImpl;

import java.util.TimerTask;

public class FileReaderScheduler extends TimerTask {
   String fileName = "";
   String regex = "";
   String outPutFile = "";
   public FileReaderScheduler(String fileName, String regex, String outPutFile){
      this.fileName = fileName;
      this.regex = regex;
      this.outPutFile = outPutFile;
   }
   public void run() {
      try {
         FileMonitorService fileMonitorService = new FileMonitorServiceImpl();
         FileWriterService fileWriterService = new FileWriterServiceImpl();

         String outPut = fileMonitorService.monitorFileForSpecificWord(this.fileName, this.regex);
         fileWriterService.writeDataToFile(outPut, "search_results.log");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}