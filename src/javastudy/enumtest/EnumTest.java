package javastudy.enumtest;

public class EnumTest {
    public static void main(String[] args) {
        Role role = Role.USER;
        System.out.println(Role.USER);
        Role role1 = Role.valueOf("USER");
        for (Role value : Role.values()) {
            System.out.println("value = " + value);
        }
    }
}
