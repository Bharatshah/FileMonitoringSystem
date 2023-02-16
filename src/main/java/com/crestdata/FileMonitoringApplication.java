package com.crestdata;

import com.crestdata.schedulers.FileReaderScheduler;
import com.crestdata.schedulers.FileWriterScheduler;
import com.crestdata.utils.FileWriterUtil;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FileMonitoringApplication {
   public static void main(String[] args) throws IOException {

      String outputFileName = "search_results.log";
      List<String> dataFiles = new ArrayList<>();
      dataFiles.add( "RandomDataFile-1.txt");
      dataFiles.add( "RandomDataFile-2.txt");


      Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
      System.out.print("Enter CRON TIME in Mils to monitorfile- ");
      long cronPeriodMonitorFile = 10000;
      try{
          cronPeriodMonitorFile= sc.nextLong();
      } catch (Exception e) {
         e.printStackTrace();
      }

      System.out.print("Now, Enter String you wanted to Match - ");
      String regex = "\\bCDS\\b";
      try{
         regex= "\\b" + sc.next() + "\\b";
      } catch (Exception e) {
         e.printStackTrace();
      }

      System.out.print("Do you wanted to clear the data files? Type 1 for YES and 0 for NO - ");
      int deleteDataFiles = 0;
      try{
         deleteDataFiles = sc.nextInt();
      } catch (Exception e) {
         e.printStackTrace();
      }

      if(deleteDataFiles == 1) {
         for (String dataFile :
                 dataFiles) {
           FileWriterUtil.clearFile(dataFile);
         }
         FileWriterUtil.clearFile(outputFileName);
      }


      Timer timer = new Timer();
      long cronPeriodWriteToFile = 2000;

      writeRandomStringInFile(dataFiles, timer, cronPeriodWriteToFile);

      // wait for some time for files to be generated
      try {
         TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }



      try {
         startMonitoringFiles(outputFileName, dataFiles, timer, cronPeriodMonitorFile, regex);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    *
    * @param outputFileName
    * @param dataFiles
    * @param timer
    */
   private static void startMonitoringFiles(String outputFileName, List<String> dataFiles, Timer timer, long period, String regex) throws IOException {

      // Monitor
      List<TimerTask> monitorTasks = new ArrayList<>();
      getMonitorTasks(monitorTasks, regex, dataFiles, outputFileName);
      monitorTasks.parallelStream().forEach(task   -> timer.scheduleAtFixedRate(task,new Date(),period));

   }

   /**
    *
    * @param dataFiles
    * @param timer
    */
   private static void writeRandomStringInFile(List<String> dataFiles, Timer timer, long period) {
      List<TimerTask> tasks = new ArrayList<>();
      getWriteTasks(tasks, dataFiles);

      // scheduling the task at the specified time at fixed-rate - took advantage of parallel execution
      tasks.parallelStream().forEach(task   -> timer.scheduleAtFixedRate(task,new Date(),period));
   }

   /**
    *
    * @param monitorTasks
    * @param regex
    * @param dataFiles
    * @param outputFileName
    */
   static void getMonitorTasks(List<TimerTask> monitorTasks, String regex, List<String> dataFiles, String outputFileName) {
      for (String dataFile :
              dataFiles) {
         monitorTasks.add(new FileReaderScheduler(dataFile, regex, outputFileName));
      }
   }

   /**
    *
    * @param tasks
    * @param dataFiles
    */
   static void getWriteTasks(List<TimerTask> tasks, List<String> dataFiles) {
      for (String dataFile :
              dataFiles) {
         tasks.add(new FileWriterScheduler(dataFile));
      }
   }
}