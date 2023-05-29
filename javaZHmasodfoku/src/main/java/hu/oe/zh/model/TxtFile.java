package hu.oe.zh.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtFile {
    private final BufferedWriter bufferedWriter;

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public TxtFile() throws IOException {
        //new File("megoldasok.txt");
        //new FileWriter(new File("megoldasok.txt").toString(), true);
        bufferedWriter = new BufferedWriter(new FileWriter(new File("megoldasok.txt").toString(), true));
    }
}
