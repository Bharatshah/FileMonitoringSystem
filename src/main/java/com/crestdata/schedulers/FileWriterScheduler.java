package com.crestdata.schedulers;

import com.crestdata.services.filewrite.FileWriterService;
import com.crestdata.services.filewrite.FileWriterServiceImpl;

import java.util.TimerTask;

public class FileWriterScheduler extends TimerTask {
   String fileName = "";
   public FileWriterScheduler(String fileName){
      this.fileName = fileName;
   }
   public void run() {
      try {
         FileWriterService fileWriterService = new FileWriterServiceImpl();
         fileWriterService.writeRandomStringToFile(this.fileName);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}