package fr.bikach.ouiscnf.xspeedIt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxCounterTest {

    private BoxCounter boxCounter;

    @BeforeEach
    void setUp() {
        boxCounter = new BoxCounter();
    }


    @Nested
    class OnlyOneArticle {
        @Test
        void shouldReturnOneBoxWhenTheSizeArticleIsSmallerThanMediumSize(){
            assertEquals("1", boxCounter.optimize("1"));
        }

        @Test
        void shouldReturnOneBoxWhenTheSizeArticleIsGreaterThanMediumSize(){
            assertEquals("6", boxCounter.optimize("6"));
        }
    }

    @Nested
    class TwoArticles {
        @Test
        void shouldReturnOneBoxWhenTheTotalSizeArticlesIsSmallerThanMediumSize(){
            assertEquals("43", boxCounter.optimize("34"));
        }

        @Test
        void shouldReturnTwoBoxWhenTheTotalSizeArticlesIsGreaterThanMediumSize(){
            assertEquals("8/3", boxCounter.optimize("38"));
        }
    }

    @Nested
    class MoreThreeArticles{
        @Test
        void shouldReturnOneBoxWhenTheTotalSizeArticlesIsSmallerThanMediumSize() {
            assertEquals("41113", boxCounter.optimize("31114"));
        }

        @Test
        void shouldReturnTwoBoxesWhenTheTotalSizeOfALotOfArticlesIsGreaterThanMediumSize() {
            assertEquals("523/2", boxCounter.optimize("2253"));
        }

        @Test
        void shouldReturnThreeBoxesWhenTheTotalSizeOfTwoLotsOfArticlesIsGreaterThanMediumSize() {
            assertEquals("64/223/5", boxCounter.optimize("262543"));
        }

        @Test
        void shouldReturnFourBoxes() {
            assertEquals("82/73/64/235/334", boxCounter.optimize("262543473833"));
        }

        @Test
        void shouldReturnTheSameNumberOfBoxesAsTheExample() {
            assertEquals("91/82/81/73/73/64/6/55", boxCounter.optimize("163841689525773"));
        }

    }


}