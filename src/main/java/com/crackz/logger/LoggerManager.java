package com.crackz.logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoggerManager {
    private final Map<String, LogReader> logReaders = new HashMap<>();

    public LoggerManager(String directoryPath) {
        Map<String, String> logPaths = findLogPaths(directoryPath);
        for (Map.Entry<String, String> entry : logPaths.entrySet()) {
            logReaders.put(entry.getKey(), new LogReader(entry.getValue()));
        }
    }

    public void startMonitoring() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::printNewLogEntries, 0, 1, TimeUnit.SECONDS);
    }

    private void printNewLogEntries() {
        for (Map.Entry<String, LogReader> entry : logReaders.entrySet()) {
            List<String> newEntries = entry.getValue().readNewEntries();
            for (String logEntry : newEntries) {
                System.out.println(entry.getKey() + ": " + logEntry);
            }
        }
    }

    private Map<String, String> findLogPaths(String directoryPath) {
        Path dir = Paths.get(directoryPath);
        LogFileFinder finder = new LogFileFinder();
        try {
            Files.walkFileTree(dir, EnumSet.noneOf(FileVisitOption.class), 1, finder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finder.getLogPaths();
    }

    private class LogFileFinder extends SimpleFileVisitor<Path> {
        private final Map<String, String> logPaths = new HashMap<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(".txt")) {
                for (String logType : Arrays.asList("Event", "Combat", "Friends")) {
                    if (file.toString().contains(logType)) {
                        logPaths.put(logType, file.toString());
                        break;
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }

        public Map<String, String> getLogPaths() {
            return logPaths;
        }
    }
}
