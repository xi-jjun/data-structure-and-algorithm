package javastudy.comparing.equalsoverride;

public class NodeTest {
    public static void main(String[] args) {
        Node node1 = new Node("node1", 100);
        Node node2 = new Node("node1", 200);
        Node node3 = new Node("node3", 100);
        Node node4 = new Node("node1", 100);

        System.out.println(node1.equals(node2)); // true
        System.out.println(node1 == node2); // false
        System.out.println(node1 == node3); // false
        System.out.println(node1 == node4); // false
        System.out.println(node1); // javastudy.comparing.equalsoverride.Node@64
        System.out.println(node3); // javastudy.comparing.equalsoverride.Node@64
        System.out.println(node4); // javastudy.comparing.equalsoverride.Node@64
    }
}
