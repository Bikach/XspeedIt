package fr.bikach.ouiscnf.xspeedIt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PackagingBoxes {

    private static final int FIRST_ITEM = 0;
    private static final int SECOND_ITEM = 1;
    private static final int MAX_BOX_SIZE = 10;
    private static final String SEPARATOR = "/";


    String optimize(String items) {
        List<String> sequenceItems = getSequenceItems(items);
        StringBuilder boxes = new StringBuilder();
        boxes.append(sequenceItems.get(FIRST_ITEM));

        if (sequenceItems.size() > SECOND_ITEM) {
            int sizeItems = Integer.valueOf(sequenceItems.get(FIRST_ITEM)) + Integer.valueOf(sequenceItems.get(SECOND_ITEM));
            if (sizeItems > MAX_BOX_SIZE) boxes.append(SEPARATOR);
            boxes.append(sequenceItems.get(SECOND_ITEM));
        }

        return boxes.toString();
    }

    private ArrayList<String> getSequenceItems(String items) {
        return Stream.of(items.split(""))
                .collect(Collectors
                        .toCollection(ArrayList::new));
    }
}
