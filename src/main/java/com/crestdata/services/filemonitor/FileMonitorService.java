package com.crestdata.services.filemonitor;

public interface FileMonitorService {
    String monitorFileForSpecificWord(String fileName, String regex);
}
