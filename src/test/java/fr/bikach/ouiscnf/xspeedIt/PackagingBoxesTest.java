package fr.bikach.ouiscnf.xspeedIt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackagingBoxesTest {

    private PackagingBoxes packagingBoxes;

    @BeforeEach
    void setUp() {
        packagingBoxes = new PackagingBoxes();
    }


    @Nested
    class OnlyOneArticle {
        @Test
        void shouldReturnOneBoxWhenTheSizeArticleIsSmallerThanMediumSize(){
            assertEquals("1", packagingBoxes.optimize("1"));
        }

        @Test
        void shouldReturnOneBoxWhenTheSizeArticleIsGreaterThanMediumSize(){
            assertEquals("6", packagingBoxes.optimize("6"));
        }
    }

    @Nested
    class TwoArticles {
        @Test
        void shouldReturnOneBoxWhenTheTotalSizeArticlesIsSmallerThanMediumSize(){
            assertEquals("43", packagingBoxes.optimize("34"));
        }

        @Test
        void shouldReturnTwoBoxWhenTheTotalSizeArticlesIsGreaterThanMediumSize(){
            assertEquals("8/3", packagingBoxes.optimize("38"));
        }
    }

    @Nested
    class MoreThreeArticles{
        @Test
        void shouldReturnOneBoxWhenTheTotalSizeArticlesIsSmallerThanMediumSize() {
            assertEquals("41113", packagingBoxes.optimize("31114"));
        }

        @Test
        void shouldReturnTwoBoxesWhenTheTotalSizeOfALotOfArticlesIsGreaterThanMediumSize() {
            assertEquals("523/2", packagingBoxes.optimize("2253"));
        }

        @Test
        void shouldReturnThreeBoxesWhenTheTotalSizeOfTwoLotsOfArticlesIsGreaterThanMediumSize() {
            assertEquals("64/223/5", packagingBoxes.optimize("262543"));
        }

        @Test
        void shouldReturnFourBoxes() {
            assertEquals("82/73/64/235/334", packagingBoxes.optimize("262543473833"));
        }

    }


    @Test
    void shouldReturnSameAnswerExample() {
        assertEquals("91/82/81/73/73/64/6/55", packagingBoxes.optimize("163841689525773"));
    }

}