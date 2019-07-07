package fr.bikach.ouiscnf;

import fr.bikach.ouiscnf.xspeedIt.PackagingBoxes;

public class XspeedItApp {

    public static void main( String[] args ) {

        PackagingBoxes packagingBoxes = new PackagingBoxes();

        String result = packagingBoxes.optimize("23961111235");

        System.out.println(result);
    }
}
