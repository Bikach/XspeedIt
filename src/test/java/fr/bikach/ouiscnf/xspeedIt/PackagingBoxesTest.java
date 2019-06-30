package fr.bikach.ouiscnf.xspeedIt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
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
                assertAll(
                        () -> assertEquals("1", packagingBoxes.optimize("1")),
                        () -> assertEquals("5", packagingBoxes.optimize("5")),
                        () -> assertEquals("9", packagingBoxes.optimize("9"))
                );
            }
        }

        @Nested
        class AndThereAreTwoItems{
            @Nested
            class WithATotalSizeBelow10Included{
                @Test
                void shouldReturnOneBox(){
                    assertAll(
                            () -> assertEquals("54", packagingBoxes.optimize("54")),
                            () -> assertEquals("27", packagingBoxes.optimize("27")),
                            () -> assertEquals("64", packagingBoxes.optimize("64"))
                    );

                }
            }

            @Nested
            class WithASizeAbove10{
                @Test
                void shouldReturnTwoBoxes(){
                    assertAll(
                            () -> assertEquals("5/6", packagingBoxes.optimize("56")),
                            () -> assertEquals("2/9", packagingBoxes.optimize("29")),
                            () -> assertEquals("4/8", packagingBoxes.optimize("48"))
                    );
                }
            }
        }

        @Nested
        class AndThereAreThreeItems{
            

        }

    }

}