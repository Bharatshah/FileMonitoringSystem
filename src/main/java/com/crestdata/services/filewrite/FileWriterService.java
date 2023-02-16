package com.crestdata.services.filewrite;

import java.io.IOException;

public interface FileWriterService {
    void writeRandomStringToFile(String fileName);
    void writeDataToFile(String data, String fileName) throws IOException;
}
