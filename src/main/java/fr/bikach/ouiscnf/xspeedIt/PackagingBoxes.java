package fr.bikach.ouiscnf.xspeedIt;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PackagingBoxes {

    private static final int MAX_BOX_SIZE = 10;
    private static final int MEDIUM_BOX_SIZE = 5;
    private static final String SEPARATOR = "/";
    private static final int ZERO = 0;
    private static final String GREATER_THAN_MEDIUM_SIZE = "more than 5";
    private static final String SMALLER_THAN_MEDIUM_SIZE = "less than 5";

    private StringBuilder boxes;
    private int boxSize = 0;

    public PackagingBoxes() {
        this.boxes = new StringBuilder();
    }

    public String optimize(String articles) {
        Deque<Integer> smallerArticles = toArrayDeque(articles, SMALLER_THAN_MEDIUM_SIZE);
        Deque<Integer> greaterArticles = toArrayDeque(articles, GREATER_THAN_MEDIUM_SIZE);
        boxSize += getFirstArticle(smallerArticles, greaterArticles);
        while (isNotEmpty(smallerArticles, greaterArticles))
            fillABox(smallerArticles, greaterArticles);
        return boxes.toString();
    }

    private ArrayDeque<Integer> toArrayDeque(String articles, String size){
        return articles
                .chars()
                .mapToObj(article -> Character.getNumericValue((char)article))
                .sorted()
                .filter(isRange(size))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Predicate<Integer> isRange(String size){
        if (size.equals(GREATER_THAN_MEDIUM_SIZE))
            return article -> article > MEDIUM_BOX_SIZE;
        return article -> article <= MEDIUM_BOX_SIZE;
    }

    private void fillABox(Deque<Integer> smallerArticles, Deque<Integer> greaterArticles) {
        Optional<Integer> currentArticle = getArticleThatWithBoxingIsEqualToMaxSize(smallerArticles);
        if (currentArticle.isPresent()){
            boxes.append(currentArticle.get());
            smallerArticles.remove(currentArticle.get());
            addSeparatorIsNotEmpty(smallerArticles);
            addArticleIsNotEmpty(greaterArticles);
        }else if (isBoxSizeIsGreaterThanMaxSize(smallerArticles)){
                boxes.append(SEPARATOR);
                addArticleIsNotEmpty(greaterArticles);
        }else {
            boxSize += smallerArticles.peekFirst();
            boxes.append(smallerArticles.removeFirst());
        }
    }

    private int getFirstArticle(Deque<Integer> smallerArticles, Deque<Integer> greaterArticles) {
        if(greaterArticles.isEmpty()) {
            boxes.append(smallerArticles.peekLast());
            return smallerArticles.removeLast();
        }
        boxes.append(greaterArticles.peekLast());
        return greaterArticles.removeLast();
    }

    private boolean isNotEmpty(Deque<Integer> smallerArticles, Deque<Integer> greaterArticles){
        return !(smallerArticles.isEmpty() && greaterArticles.isEmpty());
    }

    private Optional<Integer> getArticleThatWithBoxingIsEqualToMaxSize(Deque<Integer> smallerArticles) {
        return smallerArticles.stream()
        .filter(article -> article + boxSize == MAX_BOX_SIZE)
        .findAny();
    }

    private void addSeparatorIsNotEmpty(Deque<Integer> smallerArticles) {
        if(!smallerArticles.isEmpty())
            boxes.append(SEPARATOR);
    }

    private void addArticleIsNotEmpty(Deque<Integer> greaterArticles) {
        if (!greaterArticles.isEmpty()){
            boxSize = greaterArticles.peekLast();
            boxes.append(greaterArticles.removeLast());
        }else boxSize = ZERO;
    }

    private boolean isBoxSizeIsGreaterThanMaxSize(Deque<Integer> smallerArticles) {
        return boxSize + smallerArticles.peekFirst() > MAX_BOX_SIZE;
    }
}
