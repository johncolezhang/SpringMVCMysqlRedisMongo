package com.johncole.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by johncole on 2017/6/21.
 */
public class FileUtil {
    public static File transMFtoFile(String path, String fileName, MultipartFile mFile) {
        File file = new File(path, fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            mFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
