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
    class WhenTheSequenceIsOrdered{
        @Nested
        class AndThereIsOneItem{
            @Test
            void shouldReturnOneBox(){
                assertEquals("1", packagingBoxes.optimize("1"));
            }
        }

        @Nested
        class AndThereAreTwoItems{
            @Nested
            class WithATotalSizeBelow10Included{
                @Test
                void shouldReturnOneBox(){
                    assertEquals("54", packagingBoxes.optimize("54"));
                }
            }

            @Nested
            class WithASizeAbove10{
                @Test
                void shouldReturnTwoBoxes(){
                    assertEquals("5/6", packagingBoxes.optimize("56"));
                }
            }
        }

        @Nested
        class AndThereAreThreeItems{
            @Test
            void shouldReturnTwoBoxWith2itemsAnd1Item(){
                assertEquals("64/4", packagingBoxes.optimize("644"));
            }

            @Test
            void shouldReturnTwoBoxWith1ItemAnd2Items(){
                assertEquals("6/72", packagingBoxes.optimize("672"));
            }

            @Test
            void shouldReturnThreeBoxWith1ItemEach(){
                assertEquals("8/7/4", packagingBoxes.optimize("874"));
            }

        }

    }

}