package com.tss.model.repositary;

import java.io.*;

public class Repositary {

    public static <T> void saveToFile(T object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
