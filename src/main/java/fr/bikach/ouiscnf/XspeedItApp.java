package fr.bikach.ouiscnf;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class XspeedItApp {

    public static void main( String[] args ) {

        Deque<String> list1 = new ArrayDeque<>();
        List<String> list2 = new ArrayList<>();

        list1.addFirst("premier");


        System.out.println(list1.removeFirst());
        System.out.println(list1.peekFirst());


    }
}
