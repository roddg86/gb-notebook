package com.github.roddg86.notebook.util.log.impl;

import com.github.roddg86.notebook.Main;
import com.github.roddg86.notebook.util.log.Log;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FileLog implements Log {

    @Override
    public Logger log(String className) {
        try (FileInputStream ins = new FileInputStream("src/main/java/com/github/roddg86/notebook/util/log/impl/log.config")) { //полный путь до файла с конфигами
            LogManager.getLogManager().readConfiguration(ins);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return Logger.getLogger(className);
    }
}
