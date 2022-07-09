// Test Ingredient Unit
// created by Zhihao Zhou, 7904125
// created at 2022/07/06

// something need to notice
//
// 1,some error message have spelling error, I just copy the message not change it
// 2,the Quantity type. I leave some comments and a fail() message about the quality type; after check delete the fail()
// 3,set an un-initial ingredient it can set, but should throw exception, it should fail set ingredient
package comp3350.team10.objects;

import comp3350.team10.objects.*;

import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


public class TestIngredientUnit {


    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        // this is for Edible class for Ingredient class
        private Edible testEdible;
        private Ingredient testIngredient;
        @BeforeEach
        void setup() {
        testEdible = new Edible();
        testIngredient = new Ingredient();
        }

        @Test
        void testSimple(){
            assertNull(testIngredient.getIngredient());
            assertEquals(-1, testIngredient.getQuantity());
            assertNull(testIngredient.getQuantityUnits());
            try{
                testEdible.initDetails(1,"lala","lala",1,Unit.cups);
                testEdible.initNutrition(5,5,5,5);
            }
            catch (IllegalArgumentException e){
                fail("testEdible initial fail");
            }

            try {
                // some problem here
//                fail("the Quality is int in edible class here is double, I think may need to change, I give 2 test for int and double, Int is comment. " +
//                        "If you gus change that comment the double one and uncomment");
                // for quality is double
                testIngredient.init(testEdible,25.9,Unit.g);
                // for int
                //testIngredient.init(testEdible,25,Unit.g);
            }
            catch (Exception e){
                fail("Should not throw exception");
            }
            assertNotNull(testIngredient.getIngredient());
            assertTrue(testIngredient.getIngredient() instanceof  Edible);
            assertEquals(testEdible, testIngredient.getIngredient());
            assertEquals(25.9,testIngredient.getQuantity());
            assertEquals(Unit.g,testIngredient.getQuantityUnits());
        }


    }

    @Nested
    @DisplayName("Simple Edge")
    class Test_Edge {
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testIngredient = new Ingredient();
        }

        @Test
        void test_edge_left_setQuality() {

            try {
                testIngredient.setQuantity(1);
            } catch (Exception e) {
                fail("Should not throw exception");
            }
            assertEquals(1 , testIngredient.getQuantity());

            // for test quantity is double (if change the Quantity to int, delete this section)
            try {
                testIngredient.setQuantity(0.00000000000000001);
            } catch (Exception e) {
                fail("Should not throw exception");
            }
            assertEquals(0.00000000000000001 , testIngredient.getQuantity());
            // for test quantity is double
        }

        @Test
        void test_edge_right_setQuality() {

            try {
                testIngredient.setQuantity(9999);
            } catch (Exception e) {
                fail("Should not throw exception");
            }
            assertEquals(9999 , testIngredient.getQuantity());

            // for test quantity is double (if change the Quantity to int, delete this section)
            try {
                testIngredient.setQuantity(9999.0);
            } catch (Exception e) {
                fail("Should not throw exception");
            }
            assertEquals(9999.0 , testIngredient.getQuantity());
            // for test quantity is double
        }
    }


    @Nested
    @DisplayName("test Empty")
    class Test_Empty {
        private Ingredient testIngredient;
        private Edible testEdible;

        @BeforeEach
        void setup() {
            testIngredient = new Ingredient();
            testEdible = new Edible();
        }

        @Test
        void test_empty_setIngredient() {
            // there have problem:
            // I write my view here:
            // in setIngredient not check Quantity, you check the Nutrition != -1
            // this is useless because the nutrition form edible cannot be -1, you edible class initial is equal to 0
            // this will let the un initial food obj success set

            // I write the test here with setIngredient to null and uninitial obj here

            try {
                testIngredient.setIngredient(null);
                fail("Cannot set null to ingredient");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invalid ingredient food",e.getMessage());
            }

            try {
                testIngredient.setIngredient(testEdible);
                fail("Cannot set un-initial food obj  to ingredient");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invalid ingredient food",e.getMessage());
            }
        }

        @Test
        void  test_empty_setQuantityUnit(){
            try {
                testIngredient.setQuantityUnit(null);
                fail("Cannot set null to QuantityUnit");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invald ingredient unit",e.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("test Invalid")
    class Test_Invalid {

        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testIngredient = new Ingredient();
        }

        @Test
        void test_invalid_left_setQuality() {

            try {
                testIngredient.setQuantity(0);
                fail("should not set quality to 0");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invald ingredient quantity",e.getMessage());
            }


            // for test quantity is double (if change the Quantity to int, delete this section)
            try {
                testIngredient.setQuantity(-0.00000000000000001);
                fail("should not set quality to -0.00000000000000001");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invald ingredient quantity",e.getMessage());
            }
            // for test quantity is double
        }

        @Test
        void test_invalid_right_setQuality() {

            try {
                testIngredient.setQuantity(10000);
                fail("should not set quality to 10000");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invald ingredient quantity",e.getMessage());
            }


            // for test quantity is double (if change the Quantity to int, delete this section)
            try {
                testIngredient.setQuantity(1000000.00546542);
                fail("should not set quality to 1000000.00546542");
            } catch (Exception e) {
                assertTrue(e instanceof IOException);
                assertEquals("Invald ingredient quantity",e.getMessage());
            }
            // for test quantity is double
        }

    }


}
