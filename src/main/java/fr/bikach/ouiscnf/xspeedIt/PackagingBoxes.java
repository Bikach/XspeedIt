package fr.bikach.ouiscnf.xspeedIt;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PackagingBoxes {

    private static final int MAX_BOX_SIZE = 10;
    private static final String SEPARATOR = "/";
    public static final int EMPTY = 0;

    private Deque<String> temporaryStock;
    private StringBuilder boxes;
    private int boxSize = EMPTY;

    PackagingBoxes() {
        this.temporaryStock = new ArrayDeque<>();
        this.boxes = new StringBuilder();
    }
    //temporaryStock.add(sequenceItems.get(ITEM));

    String optimize(String items) {
        Deque<String> currentStock = toDeque(items);
        boxes.append(currentStock.peekFirst());
        boxSize += Integer.valueOf(currentStock.removeFirst());

        if (isEmpty(currentStock, temporaryStock)) return boxes.toString();

        boxSize += Integer.valueOf(currentStock.getFirst());

        if ((boxSize > MAX_BOX_SIZE)) {
            boxes.append(SEPARATOR);
            boxSize = EMPTY;
        }

        return optimize(String.join("",currentStock));

    }

    private boolean isEmpty(Deque<String> currentStock, Deque<String> temporaryStock) {
        return currentStock.peekFirst() == null && temporaryStock.peekFirst() == null;
    }

    private ArrayDeque<String> toDeque(String items) {
        return Stream.of(items.split(""))
                .collect(Collectors
                        .toCollection(ArrayDeque::new));
    }
}
