package javastudy.comparing.blanktest;

public class StringIsBlank {
    public static void main(String[] args) {
        String noSpace = "";
        String space = " ";
        System.out.println("noSpace = " + noSpace.isBlank()); // true
        System.out.println("space = " + space.isBlank()); // true
        /**
         * Returns true if the string is empty or contains only white space codepoints, otherwise false.
         * Returns true if the string is empty or contains only white space codepoints, otherwise false
         */
    }
}
