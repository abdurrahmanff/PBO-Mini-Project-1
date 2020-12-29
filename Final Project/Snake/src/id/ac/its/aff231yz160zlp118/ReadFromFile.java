package id.ac.its.aff231yz160zlp118;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ReadFromFile {
    public static int readScore(String filename) {
        Scanner input = null;
        int score;
        try {
            input = new Scanner(Paths.get(filename));
        } catch (IOException ioException) {
            score = 0;
        }
        try {
            score = input.nextInt();
        } catch (NoSuchElementException | IllegalStateException stateException) {
            score = 0;
        }
        if(input != null)
            input.close();
        return score;
    }
}
