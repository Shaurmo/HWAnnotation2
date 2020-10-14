package com.gmail.shaurmo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(filePath = "src\\test.txt")
public class TextContainer {
    private String str;

    public TextContainer(String str) {
        this.str = str;
    }

    @Saver
    public void saveToFile(String filePath) {
        File file = new File(filePath);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
