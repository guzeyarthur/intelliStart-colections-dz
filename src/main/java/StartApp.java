import mylist.MyArrayList;
import mylist.MyLinkedList;
import mylist.MyStack;
import mymap.MyHashMap;
import myqueue.MyQueue;

import java.util.HashMap;

public class StartApp {
    public static void main(String[] args) {
        MyHashMap<Integer, String> arr = new MyHashMap<>();
        arr.put(1, "a1");
        System.out.println(arr.put(1, "a5"));
        arr.put(2, "a2");
        arr.put(3, "a3");
        arr.put(4, "a4");
        arr.put(5, "a5");
        arr.put(6, "a6");
        arr.put(7, "a7");
        arr.put(8, "a8");
        arr.put(9, "a9");
        arr.put(11, "a11");
        arr.put(10, "a10");
        arr.put(12, "a12");
        arr.put(13, "a13");
        arr.put(14, "a14");
        arr.put(15, "a15");
        arr.put(16, "a16");
        arr.put(17, "a17");
        arr.put(18, "a18");

        System.out.println(arr.size());
        System.out.println();
        System.out.println(arr.get(1));
        System.out.println();
        System.out.println(arr.remove(1));
        System.out.println(arr.get(1));
        System.out.println(arr.get(17));


    }
}
