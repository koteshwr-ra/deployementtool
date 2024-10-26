package com.example.sroslibrary.utils;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter {
    public boolean accept(File file) {
        if (!file.getName().endsWith(".png") && !file.getName().endsWith(".jpg") && !file.getName().endsWith(".PNG") && !file.getName().endsWith(".JPG")) {
            return false;
        }
        return true;
    }
}
