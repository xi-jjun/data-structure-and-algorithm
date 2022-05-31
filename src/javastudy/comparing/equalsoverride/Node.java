package javastudy.comparing.equalsoverride;

public class Node {
    String value;
    int code;

    public Node(String value, int code) {
        this.value = value;
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        return other.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return code;
//        return super.hashCode();
    }
}
