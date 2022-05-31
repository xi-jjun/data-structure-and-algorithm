package javastudy.comparing.equalsoverride;

public class Node implements Comparable<Node> {
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

    @Override
    public int compareTo(Node o) {
        return this.value.compareTo(o.value);
    }
}
