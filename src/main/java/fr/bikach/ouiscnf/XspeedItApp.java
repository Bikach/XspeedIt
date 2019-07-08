package fr.bikach.ouiscnf;

import fr.bikach.ouiscnf.xspeedIt.BoxCounter;

public class XspeedItApp {

    public static void main( String[] args ) {

        BoxCounter boxCounter = new BoxCounter();

        String result = boxCounter.optimize("2396127733662374631983745327366328743229874325311235");

        System.out.println(result);
    }
}
