package javastudy.fileIO.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonReadTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/javastudy/fileIO/json/file.json"));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

    }
}
