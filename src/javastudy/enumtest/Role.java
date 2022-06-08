package javastudy.enumtest;

public enum Role {
    USER("user"),
    ADMIN("admin"),
    BLOCKED("blocked");


    private final String ROLE;

    Role(String role) {
        ROLE = role;
    }
}
