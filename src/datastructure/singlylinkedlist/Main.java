package datastructure.singlylinkedlist;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList list = new KjjSinglyLinkedList();
        SinglyLinkedList list2 = new KjjSinglyLinkedList();
        list.add(352);
        list.add(1);
        list.add(6);
        list.add(4);
        list2.add(99);

        list2.show();
        list.show();

        System.out.println("list.find(1) = " + list.find(1));

        list.add(25);

        list.show();

        System.out.println("list.find(3) = " + list.find(3));
//        System.out.println("list.find(14) = " + list.find(14));

        list.delete(4);
        list.show();
    }

}
