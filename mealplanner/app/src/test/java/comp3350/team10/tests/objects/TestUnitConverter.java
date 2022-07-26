package comp3350.team10.tests.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.UnitConverter;

public class TestUnitConverter {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private UnitConverter converter;

        @BeforeEach
        void setup() {
            converter = new UnitConverter();
        }

        @Test
        @DisplayName("can convert every unit to g")
        void testToG() {
            try {
                assertEquals(10, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.g, 10));
                assertEquals(10, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.g, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to cups")
        void testToCups() {
            try {
                assertEquals(2240, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.cups, 10));
                assertEquals(2240, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.cups, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to oz")
        void testToOz() {
            try {
                assertEquals(280, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.oz, 10));
                assertEquals(280, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.oz, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to serving")
        void testToServing() {
            try {
                assertEquals(2500, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.serving, 10));
                assertEquals(2500, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.serving, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to tbsp")
        void testToTbsp() {
            try {
                assertEquals(140, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.tbsp, 10));
                assertEquals(140, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tbsp, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to tsp")
        void testToTsp() {
            try {
                assertEquals(50, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.tsp, 10));
                assertEquals(50, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tsp, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to ml")
        void testToMl() {
            try {
                assertEquals(10, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.ml, 10));
                assertEquals(10, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.ml, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to liter")
        void testToL() {
            try {
                assertEquals(10000, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.oz, 10, 280, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.serving, 10, 2500, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.tbsp, 10, 140, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.tsp, 10, 50, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.ml, 10, 10, Edible.Unit.liter, 10));
                assertEquals(10000, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.liter, 10));
            } catch (Exception e) {
                fail(e);
            }
        }
    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {
        private UnitConverter converter;
        private int results;

        @BeforeEach
        void setup() {
            converter = new UnitConverter();
        }

        @Test
        @DisplayName("can convert g to liters to cups to g")
        void testComplex1() {
            try {
                assertEquals(10000, converter.convert(Edible.Unit.g, 10, 10, Edible.Unit.liter, 10));
                assertEquals(2240, converter.convert(Edible.Unit.liter, 10, 10000, Edible.Unit.cups, 10));
                assertEquals(10, converter.convert(Edible.Unit.cups, 10, 2240, Edible.Unit.g, 10));
            } catch (Exception e) {
                fail(e);
            }
        }
    }

    @Nested
    @DisplayName("Empty tests zero calorie input")
    class Test_Empty {
        private UnitConverter converter;

        @BeforeEach
        void setup() {
            converter = new UnitConverter();
        }

        @Test
        @DisplayName("can convert every unit to g")
        void testToG() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.g, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.g, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to cups")
        void testToCups() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.cups, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.cups, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to oz")
        void testToOz() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.oz, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.oz, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to serving")
        void testToServing() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.serving, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.serving, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to tbsp")
        void testToTbsp() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.tbsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.tbsp, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to tsp")
        void testToTsp() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.tsp, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.tsp, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to ml")
        void testToMl() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.ml, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.ml, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("can convert every unit to liter")
        void testToL() {
            try {
                assertEquals(0, converter.convert(Edible.Unit.g, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.cups, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.oz, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.serving, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.tbsp, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.tsp, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.ml, 10, 0, Edible.Unit.liter, 10));
                assertEquals(0, converter.convert(Edible.Unit.liter, 1, 0, Edible.Unit.liter, 10));
            } catch (Exception e) {
                fail(e);
            }
        }

    }

    @Nested
    @DisplayName("Edge case tests")
    class Test_EdgeCase {

        @Nested
        @DisplayName("quantity of 1 in conversions")
        class Test_value1 {
            private UnitConverter converter;

            @BeforeEach
            void setup() {
                converter = new UnitConverter();
            }

            @Test
            @DisplayName("can convert every unit to g")
            void testToG() {
                try {
                    assertEquals(10, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.g, 1));
                    assertEquals(10, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.g, 1));
                    assertEquals(1, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.g, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to cups")
            void testToCups() {
                try {
                    assertEquals(2240, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.cups, 1));
                    assertEquals(2240, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.cups, 1));
                    assertEquals(224, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.cups, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to oz")
            void testToOz() {
                try {
                    assertEquals(280, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.oz, 1));
                    assertEquals(280, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.oz, 1));
                    assertEquals(28, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.oz, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to serving")
            void testToServing() {
                try {
                    assertEquals(2500, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.serving, 1));
                    assertEquals(2500, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.serving, 1));
                    assertEquals(250, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.serving, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to tbsp")
            void testToTbsp() {
                try {
                    assertEquals(140, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.tbsp, 1));
                    assertEquals(140, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.tbsp, 1));
                    assertEquals(14, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tbsp, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to tsp")
            void testToTsp() {
                try {
                    assertEquals(50, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.tsp, 1));
                    assertEquals(50, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.tsp, 1));
                    assertEquals(5, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tsp, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to ml")
            void testToMl() {
                try {
                    assertEquals(10, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.ml, 1));
                    assertEquals(10, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.ml, 1));
                    assertEquals(1, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.ml, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }

            @Test
            @DisplayName("can convert every unit to liter")
            void testToL() {
                try {
                    assertEquals(10000, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.liter, 1));
                    assertEquals(10000, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.liter, 1));
                    assertEquals(1000, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.liter, 1));
                } catch (Exception e) {
                    fail(e);
                }
            }
        }
    }

    @Nested
    @DisplayName("Invalid tests")
    class Test_Invalid {


        @Nested
        @DisplayName("base quantity of 0")
        class Test_value1 {
            private UnitConverter converter;

            @BeforeEach
            void setup() {
                converter = new UnitConverter();
            }

            @Test
            @DisplayName("can convert every unit to g")
            void testToG() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.g, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to cups")
            void testToCups() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(224, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.cups, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to oz")
            void testToOz() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(28, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.oz, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to serving")
            void testToServing() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(250, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.serving, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tbsp")
            void testToTbsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(14, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.tbsp, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tsp")
            void testToTsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(5, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.tsp, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to ml")
            void testToMl() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.ml, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to liter")
            void testToL() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.g, 0, 10, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.cups, 0, 2240, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.oz, 0, 280, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.serving, 0, 2500, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tbsp, 0, 140, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tsp, 0, 50, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.ml, 0, 10, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1000, converter.convert(Edible.Unit.liter, 0, 1000, Edible.Unit.liter, 1));
                });
            }
        }

        @Nested
        @DisplayName("base quantity of -1")
        class Test_value2 {
            private UnitConverter converter;

            @BeforeEach
            void setup() {
                converter = new UnitConverter();
            }

            @Test
            @DisplayName("can convert every unit to g")
            void testToG() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.g, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.g, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to cups")
            void testToCups() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.cups, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(224, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.cups, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to oz")
            void testToOz() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.oz, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(28, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.oz, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to serving")
            void testToServing() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.serving, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(250, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.serving, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tbsp")
            void testToTbsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.tbsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(14, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.tbsp, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tsp")
            void testToTsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.tsp, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(5, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.tsp, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to ml")
            void testToMl() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.ml, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.ml, 1));
                });
            }

            @Test
            @DisplayName("can convert every unit to liter")
            void testToL() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.g, -1, 10, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.cups, -1, 2240, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.oz, -1, 280, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.serving, -1, 2500, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tbsp, -1, 140, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tsp, -1, 50, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.ml, -1, 10, Edible.Unit.liter, 1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1000, converter.convert(Edible.Unit.liter, -1, 1000, Edible.Unit.liter, 1));
                });
            }
        }

        @Nested
        @DisplayName("new quantity of -1")
        class Test_value3 {
            private UnitConverter converter;

            @BeforeEach
            void setup() {
                converter = new UnitConverter();
            }

            @Test
            @DisplayName("can convert every unit to g")
            void testToG() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.g, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.g, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to cups")
            void testToCups() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2240, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.cups, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(224, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.cups, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to oz")
            void testToOz() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(280, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.oz, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(28, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.oz, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to serving")
            void testToServing() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(2500, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.serving, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(250, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.serving, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tbsp")
            void testToTbsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(140, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.tbsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(14, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tbsp, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to tsp")
            void testToTsp() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(50, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.tsp, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(5, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.tsp, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to ml")
            void testToMl() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.ml, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.ml, -1));
                });
            }

            @Test
            @DisplayName("can convert every unit to liter")
            void testToL() {
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.g, 1, 10, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.cups, 1, 2240, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.oz, 1, 280, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.serving, 1, 2500, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tbsp, 1, 140, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.tsp, 1, 50, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(10000, converter.convert(Edible.Unit.ml, 1, 10, Edible.Unit.liter, -1));
                });
                assertThrows(IllegalArgumentException.class, () -> {
                    assertEquals(1000, converter.convert(Edible.Unit.liter, 1, 1000, Edible.Unit.liter, -1));
                });
            }
        }
    }
}