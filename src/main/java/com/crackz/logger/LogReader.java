package com.crackz.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    private final String filePath;
    private long lastReadPosition = 0;

    public LogReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readNewEntries() {
        List<String> newEntries = new ArrayList<>(); // No initial capacity.

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r");
             BufferedReader reader = new BufferedReader(new InputStreamReader(new RandomAccessFileInputStream(raf)))) {

            raf.seek(lastReadPosition);

            String line;
            while ((line = reader.readLine()) != null) {
                newEntries.add(line);
            }

            lastReadPosition = raf.getFilePointer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newEntries;
    }


    private static class RandomAccessFileInputStream extends java.io.InputStream {
        private final RandomAccessFile raf;

        public RandomAccessFileInputStream(RandomAccessFile raf) {
            this.raf = raf;
        }

        @Override
        public int read() throws IOException {
            return raf.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return raf.read(b, off, len);
        }
    }
}
