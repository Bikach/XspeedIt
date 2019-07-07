package fr.bikach.ouiscnf;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class XspeedItApp {

    public static void main( String[] args ) {
      int test = 122;

      /*

        Queue<Integer> ascendngStoc = String.valueOf(test)
                .chars()
                .mapToObj(c -> Character.getNumericValue((char)c))
                .filter(items -> items > 5)
                .collect(Collectors.toCollection(() -> {
                    return new PriorityQueue<>(10, reverseOrder());
                }));


        System.out.println(ascendngStoc);*/

      Deque<Integer> ascending = String.valueOf(test)
              .chars()
              .mapToObj(c -> Character.getNumericValue((char)c))
              .sorted()
              .collect(Collectors.toCollection(ArrayDeque::new));

      int articles = ascending.stream().max(Integer::compareTo).orElse(0);

        int currentArticle = ascending.stream()
                .filter(article -> article+8 == 10)
                .findAny().orElse(0);

        System.out.println(ascending);
        System.out.println(ascending.remove(currentArticle));
        System.out.println(ascending);
        System.out.println(ascending.remove(currentArticle));
        System.out.println(ascending);
    }
}
