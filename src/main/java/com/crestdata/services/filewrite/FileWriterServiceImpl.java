package com.crestdata.services.filewrite;

import com.crestdata.utils.FileWriterUtil;
import com.crestdata.utils.PseudoRandomStringCollector;

import java.io.IOException;

public class FileWriterServiceImpl implements FileWriterService {


    @Override
    public void writeRandomStringToFile(String fileName) {
        try {
            PseudoRandomStringCollector.generator(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeDataToFile(String data, String fileName) throws IOException {
        FileWriterUtil.writeDataToFile(data, fileName);
    }
}
