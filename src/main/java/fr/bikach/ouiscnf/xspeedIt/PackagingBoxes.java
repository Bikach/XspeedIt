package fr.bikach.ouiscnf.xspeedIt;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PackagingBoxes {

    private static final int MAX_BOX_SIZE = 10;
    private static final String SEPARATOR = "/";

    private Deque<String> temporaryStock;
    private StringBuilder boxes;

    PackagingBoxes() {
        this.temporaryStock = new ArrayDeque<>();
        boxes = new StringBuilder();
    }
    //temporaryStock.add(sequenceItems.get(ITEM));

    String optimize(String items) {
        Deque<String> currentStock = toDeque(items);
        boxes.append(currentStock.removeFirst());

        if (isEmpty(currentStock, temporaryStock))
            return boxes.toString();

        String oldItem = currentStock.peekFirst();

        int boxSize = computeCurrentSizeBox(currentStock.getFirst(), oldItem);

        if (boxSize > MAX_BOX_SIZE) boxes.append(SEPARATOR);

        return optimize(String.join("",currentStock));

    }

    private boolean isEmpty(Deque<String> currentStock, Deque<String> temporaryStock) {
        return currentStock.peekFirst() == null && temporaryStock.peekFirst() == null;
    }

    private int computeCurrentSizeBox(String oldItem, String currentItem) {
        return Integer.valueOf(oldItem) + Integer.valueOf(currentItem);
    }

    private ArrayDeque<String> toDeque(String items) {
        return Stream.of(items.split(""))
                .collect(Collectors
                        .toCollection(ArrayDeque::new));
    }
}
