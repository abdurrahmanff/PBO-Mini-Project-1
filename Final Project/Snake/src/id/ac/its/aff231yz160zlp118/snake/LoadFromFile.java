package id.ac.its.aff231yz160zlp118.snake;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadFromFile {
    public static HighScore loadScore() {
        HighScore ret = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("data/highscorestats.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ret = (HighScore) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException exception) {
            System.out.println("Error when read file.");
            ret = new HighScore();
        } catch (ClassNotFoundException exception) {
            System.out.println("Couldn't find class");
            ret = new HighScore();
        }
        return ret;
    }
}
