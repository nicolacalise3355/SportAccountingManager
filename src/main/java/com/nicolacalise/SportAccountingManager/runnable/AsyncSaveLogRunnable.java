package com.nicolacalise.SportAccountingManager.runnable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AsyncSaveLogRunnable implements Runnable{

    private Object lock;
    private String filePath;
    private String message;

    public AsyncSaveLogRunnable(Object lock, String filePath, String message) {
        this.lock = lock;
        this.filePath = filePath;
        this.message = message;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(this.message);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
