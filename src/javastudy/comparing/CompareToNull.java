package javastudy.comparing;

public class CompareToNull {
    public static void main(String[] args) {
        Option option1 = Option.TODAY;
        Option option2 = null;
        System.out.println(option1.equals(Option.TODAY));
        System.out.println(option2==null);
    }

    static enum Option {
        TEMP, TODAY, WEEKLY;
    }
}
