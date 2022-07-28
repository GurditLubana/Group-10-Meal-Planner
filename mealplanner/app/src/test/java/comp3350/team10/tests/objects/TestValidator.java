package comp3350.team10.tests.objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.objects.Validator;

public class TestValidator {

    @Nested
    @DisplayName("Typical test cases that should pass")
    class Test_Simple {

        @Test
        @DisplayName("Typical midrange values should pass")
        public void testTypical() {
            try {
                ArrayList<Integer> testArray = new ArrayList<>();
                testArray.add(new Integer(6));
                testArray.add(new Integer(420));

                Validator.validArrayListNoNull(testArray, "");
                Validator.validArrayListAtLeastOne(testArray, "");
                Validator.atLeastZero(5, "");
                Validator.atLeastOne(5, "");
                Validator.atLeastZero(5.0, "");
                Validator.atLeastOne(5.0, "");
                Validator.zeroAtMinimum(5.0, "");
                Validator.validStringInputatLeastZero("ab", "");
                Validator.validStringInputatLeastOne("cd", "");
            } catch (Exception e) {
                fail(e);
            }
        }


    }

    @Nested
    @DisplayName("Edge case tests")
    class EdgeTestCases {

        @Test
        @DisplayName("minimum limit inclusive should pass")
        void testMinLimit() {

            try {
                Validator.atLeastZero(0, "");
                Validator.atLeastOne(1, "");
                Validator.atLeastZero(0.0, "");
                Validator.atLeastOne(1.0, "");
                Validator.zeroAtMinimum(0.0, "");
                Validator.validStringInputatLeastZero("", "");
                Validator.validStringInputatLeastOne("c", "");
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("maximum limit inclusive should pass")
        void testMaxLimit() {
            StringBuilder longTestString = new StringBuilder();
            longTestString.setLength(9999);
            longTestString.setCharAt(0, 'a');
            longTestString.setCharAt(9998, 'y');

            try {
                Validator.atLeastZero(9999, "");
                Validator.atLeastOne(9999, "");
                Validator.atLeastZero(9999.0, "");
                Validator.atLeastOne(9999.0, "");
                Validator.zeroAtMinimum(9999.0, "");
                Validator.validStringInputatLeastZero(longTestString.toString(), "");
                Validator.validStringInputatLeastOne(longTestString.toString(), "");
            } catch (Exception e) {
                fail(e);
            }
        }


    }

    @Nested
    @DisplayName("Invalid tests")
    class InvalidTestCases {

        @Test
        @DisplayName("Tests setting an empty or null name")
        void testEmpty() {

            ArrayList<Integer> testArray = new ArrayList<>();

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validArrayListNoNull(null, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validArrayListAtLeastOne(testArray, "");
            });

            testArray.add(null);
            testArray.add(new Integer(420));
            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validArrayListNoNull(testArray, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validStringInputatLeastZero(null, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validStringInputatLeastOne("", "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validStringInputatLeastOne(null, "");
            });
        }

        @Test
        @DisplayName("Less than minimum limit")
        void testLessThanMin() {


            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastZero(-1, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastOne(-1, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastZero(-1.0, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastOne(-1.0, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.zeroAtMinimum(-1.0, "");
            });
        }

        @Test
        @DisplayName("More than max")
        void testMoreThanMaxLimit() {
            StringBuilder longTestString = new StringBuilder();
            longTestString.setLength(10001);
            longTestString.setCharAt(0, 'a');
            longTestString.setCharAt(9999, 'y');
            longTestString.setCharAt(10000, 'z');

            ArrayList<Integer> testArray = new ArrayList<>();
            for (int i = 0; i < 10001; i++) {
                testArray.add(new Integer(1));
            }

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validArrayListAtLeastOne(testArray, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validStringInputatLeastZero(longTestString.toString(), "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.validStringInputatLeastOne(longTestString.toString(), "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastZero(99999, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastOne(99999, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastZero(99999.0, "");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                Validator.atLeastOne(99999.0, "");
            });
        }
    }


}
