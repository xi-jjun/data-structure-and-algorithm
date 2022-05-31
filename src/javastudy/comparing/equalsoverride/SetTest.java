package javastudy.comparing.equalsoverride;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        Set<Node> set = new TreeSet<>();
        Node node1 = new Node("a", 100);
        Node node2 = new Node("a", 200);
        Node node3 = new Node("b", 100);
        Node node4 = new Node("caa", 100);
        Node node5 = new Node("fgg", 100);
        Node node6 = new Node("fdf", 100);
        set.add(node3);
        set.add(node2);
        set.add(node4);
        set.add(node6);
        set.add(node1);
        set.add(node5);

        for (Node node : set) {
            System.out.println("node.value = " + node.value);
        }
    }
}
