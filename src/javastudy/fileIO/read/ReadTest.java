package javastudy.fileIO.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/javastudy/fileIO/read/readTest.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            for (String s : split) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
