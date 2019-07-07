package fr.bikach.ouiscnf.xspeedIt;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PackagingBoxes {

    private static final int MAX_BOX_SIZE = 10;
    private static final int MEDIUM_BOX_SIZE = 5;
    private static final String SEPARATOR = "/";
    private static final int EMPTY = 0;
    private static final String GREATER_THAN_MEDIUM_SIZE = "more than 5";
    private static final String SMALLER_THAN_MEDIUM_SIZE = "less than 5";

    private StringBuilder boxes;
    private int boxSize = EMPTY;

    public PackagingBoxes() {
        this.boxes = new StringBuilder();
    }

    public String optimize(String articles) {
        Deque<Integer> smallerStock = toArrayDeque(articles, SMALLER_THAN_MEDIUM_SIZE);
        Deque<Integer> greaterStock = toArrayDeque(articles, GREATER_THAN_MEDIUM_SIZE);
        boxSize += getFirstArticle(smallerStock, greaterStock);

        while (!isEmpty(smallerStock, greaterStock)){
            int currentArticle = getCurrentArticle(smallerStock);
            if (currentArticle != EMPTY){
                boxes.append(currentArticle);
                smallerStock.remove(currentArticle);
                addSeparatorIsNotEmpty(smallerStock);
                addArticleIsNotEmpty(greaterStock);
            }else if (boxSize + smallerStock.peekFirst() > MAX_BOX_SIZE){
                    boxes.append(SEPARATOR);
                    addArticleIsNotEmpty(greaterStock);
            }else {
                boxSize += smallerStock.peekFirst();
                boxes.append(smallerStock.removeFirst());
            }
        }
        return boxes.toString();
    }

    private ArrayDeque<Integer> toArrayDeque(String articles, String position){
        return articles
                .chars()
                .mapToObj(article -> Character.getNumericValue((char)article))
                .sorted()
                .filter(is(position))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Predicate<Integer> is(String position){
        if (position.equals(GREATER_THAN_MEDIUM_SIZE))
            return article -> article > MEDIUM_BOX_SIZE;
        return article -> article <= MEDIUM_BOX_SIZE;
    }

    private int getFirstArticle(Deque<Integer> smallerMediumStock, Deque<Integer> largerMediumStock) {
        if(largerMediumStock.isEmpty()) {
            boxes.append(smallerMediumStock.peekLast());
            return smallerMediumStock.removeLast();
        }
        boxes.append(largerMediumStock.peekLast());
        return largerMediumStock.removeLast();
    }

    private boolean isEmpty(Deque<Integer> smallerStock, Deque<Integer> greaterStock){
        return smallerStock.isEmpty() && greaterStock.isEmpty();
    }

    private Integer getCurrentArticle(Deque<Integer> smallerStock) {
        return smallerStock.stream()
        .filter(article -> article + boxSize == MAX_BOX_SIZE)
        .findAny().orElse(EMPTY);
    }

    private void addSeparatorIsNotEmpty(Deque<Integer> smallerStock) {
        if(!smallerStock.isEmpty())
            boxes.append(SEPARATOR);
    }

    private void addArticleIsNotEmpty(Deque<Integer> greaterStock) {
        if (!greaterStock.isEmpty()){
            boxSize = greaterStock.peekLast();
            boxes.append(greaterStock.removeLast());
        }else
            boxSize = EMPTY;
    }
}
