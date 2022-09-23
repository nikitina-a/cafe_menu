package com.example.cafe_menu.utils;


import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class FileOrDirectoryEraser {


    public void delete(String filePath) {

        File directory = new File(filePath);
        while (true) {
            var files = directory.listFiles();
            if (files.length != 0) {
                Arrays.stream(files)
                        .forEach(x -> deleteDirectoryOrFile(x));
            }
        }
    }

    private static boolean deleteDirectoryOrFile(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectoryOrFile(file);
            }
        }

        return directoryToBeDeleted.delete();
    }

}



