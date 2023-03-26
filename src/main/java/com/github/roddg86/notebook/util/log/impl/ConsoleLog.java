package com.github.roddg86.notebook.util.log.impl;

import com.github.roddg86.notebook.util.log.Log;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ConsoleLog implements Log {
    @Override
    public Logger log(String text) {
        System.err.println(LocalDateTime.now() + ": " + text);
        return null;
    }
}
