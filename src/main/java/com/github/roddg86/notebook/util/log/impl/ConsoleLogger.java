package com.github.roddg86.notebook.util.log.impl;

import com.github.roddg86.notebook.util.log.Logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String text) {
        System.err.println(LocalDateTime.now() + ": " + text);
    }
}
