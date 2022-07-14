package javastudy.comparing;

public class CompareToNull {
    public static void main(String[] args) {
        Option option1 = Option.TODAY;
        Option option2 = null;
        System.out.println(option1.equals(Option.TODAY)); // true
        System.out.println(option2 == option1); // false
        System.out.println(option2 == null); // true
        // System.out.println(option2.equals(option1)); // NullPointerException
        System.out.println(option1.equals(option2)); // false
    }

    enum Option {
        TEMP, TODAY, WEEKLY;
    }
}
