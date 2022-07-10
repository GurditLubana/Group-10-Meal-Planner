package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import comp3350.team10.application.Main;


public class TestDrinkIngredients {

    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {

        private DrinkIngredient ingredient;
        private Edible edibleItem;

        @BeforeEach
        void setup() {

            Main.startUp();

            ingredient = new DrinkIngredient();

            edibleItem = new Edible();

            edibleItem.initDetails(2,"item1","hello\nWorld\n",4, Edible.Unit.g);

            edibleItem.initNutrition(340,40,20,200);

            edibleItem.initCategories(true,true,true,false,true);

            edibleItem.initMetadata(false,"ApplesPicture.jpg");

        }

        @Test
        @DisplayName("Testing constructor of the edible class")
        void testEdibleConstructor()
        {


            assertEquals("item1",edibleItem.getName());

            assertEquals("hello\nWorld\n",edibleItem.getDescription());

            assertEquals(4,edibleItem.getQuantity());

            assertEquals(Edible.Unit.g,edibleItem.getUnit());


            assertEquals(340,edibleItem.getCalories());

            assertEquals(40,edibleItem.getProtein());

            assertEquals(200, edibleItem.getFat());

            assertEquals(20, edibleItem.getCarbs());


            assertTrue(edibleItem.getIsAlcoholic());

            assertTrue(edibleItem.getIsSpicy());

            assertTrue(edibleItem.getIsVegan());

            assertFalse(edibleItem.getIsVegetarian());

            assertTrue(edibleItem.getIsGlutenFree());


            assertFalse(edibleItem.getIsCustom());

            assertEquals("ApplesPicture.jpg",edibleItem.getPhoto());



        }


        @Test
        @DisplayName("Tests setting a simple calorie count for an edible")
        void testSetCalories()  {

            edibleItem.setCalories(5);
            assertEquals(edibleItem.getCalories(), 5);

            edibleItem.setCalories(10);
            assertEquals(edibleItem.getCalories(), 10);

            edibleItem.initNutrition(5,1, 1, 1);
            assertEquals(edibleItem.getCalories(), 5);

            edibleItem.initNutrition(90,1, 1, 1);
            assertEquals(edibleItem.getCalories(), 90);
        }


        @Test
        @DisplayName("Tests setting a simple protein count for an edible")
        void testSetProtein() {
            edibleItem.setProtein(5);
            assertEquals(edibleItem.getProtein(), 5);

            edibleItem.setProtein(10);
            assertEquals(edibleItem.getProtein(), 10);

            edibleItem.initNutrition(1,5, 1, 1);
            assertEquals(edibleItem.getProtein(), 5);

            edibleItem.initNutrition(1,10, 1, 1);
            assertEquals(edibleItem.getProtein(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple carb count for an edible")
        void testSetCarbs() {
            edibleItem.setCarbs(5);
            assertEquals(edibleItem.getCarbs(), 5);

            edibleItem.setCarbs(10);
            assertEquals(edibleItem.getCarbs(), 10);

            edibleItem.initNutrition(1,1, 5, 1);
            assertEquals(edibleItem.getCarbs(), 5);

            edibleItem.initNutrition(1,1, 10, 1);
            assertEquals(edibleItem.getCarbs(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple fat count for an edible")
        void testSetFat() {
            edibleItem.setFat(5);
            assertEquals(edibleItem.getFat(), 5);
            edibleItem.setFat(10);
            assertEquals(edibleItem.getFat(), 10);

            edibleItem.initNutrition(1,1, 1, 5);
            assertEquals(edibleItem.getFat(), 5);

            edibleItem.initNutrition(1,1, 1, 10);
            assertEquals(edibleItem.getFat(), 10);
        }

        @Test
        @DisplayName("Tests setting an edibles unit")
        void testSetUnit() {

            edibleItem.setBaseUnit(Edible.Unit.cups);
            assertEquals(edibleItem.getUnit(), Edible.Unit.cups);

            edibleItem.setBaseUnit(Edible.Unit.g);
            assertEquals(edibleItem.getUnit(), Edible.Unit.g);

            edibleItem.setBaseUnit(Edible.Unit.ml);
            assertEquals(edibleItem.getUnit(), Edible.Unit.ml);

            edibleItem.setBaseUnit(Edible.Unit.oz);
            assertEquals(edibleItem.getUnit(), Edible.Unit.oz);

            edibleItem.setBaseUnit(Edible.Unit.liter);
            assertEquals(edibleItem.getUnit(), Edible.Unit.liter);

            edibleItem.setBaseUnit(Edible.Unit.serving);
            assertEquals(edibleItem.getUnit(), Edible.Unit.serving);

            edibleItem.setBaseUnit(Edible.Unit.tbsp);
            assertEquals(edibleItem.getUnit(), Edible.Unit.tbsp);

            edibleItem.setBaseUnit(Edible.Unit.tsp);
            assertEquals(edibleItem.getUnit(), Edible.Unit.tsp);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            assertEquals(edibleItem.getUnit(), Edible.Unit.cups);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.g);
            assertEquals(edibleItem.getUnit(), Edible.Unit.g);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.ml);
            assertEquals(edibleItem.getUnit(), Edible.Unit.ml);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.oz);
            assertEquals(edibleItem.getUnit(), Edible.Unit.oz);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.liter);
            assertEquals(edibleItem.getUnit(), Edible.Unit.liter);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.serving);
            assertEquals(edibleItem.getUnit(), Edible.Unit.serving);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.tbsp);
            assertEquals(edibleItem.getUnit(), Edible.Unit.tbsp);

            edibleItem.initDetails(5, "name", "description", 10, Edible.Unit.tsp);
            assertEquals(edibleItem.getUnit(), Edible.Unit.tsp);
        }


        @Test
        @DisplayName("Testing ingredient's details")
        void testIngredientsDetails() throws IOException {

            ingredient.setIngredient((new Edible()).initNutrition(340, 40, 20, 200)
                    .initDetails(2, "Fruit Beer", "Pour it and drink it", 4, Edible.Unit.g)
                    .initCategories(true, true, true, false, true));

            assertEquals(2,ingredient.getIngredient().getDbkey());
            assertEquals("Fruit Beer", ingredient.getIngredient().getName());
            assertEquals("Pour it and drink it", ingredient.getIngredient().getDescription());

            assertEquals(Edible.Unit.g, ingredient.getIngredient().getUnit());
            ingredient.setQuantityUnit(Edible.Unit.ml);
            assertEquals(Edible.Unit.ml, ingredient.getQuantityUnits());

            assertEquals(4, ingredient.getIngredient().getQuantity());
            ingredient.setQuantity(789);
            assertEquals(789, ingredient.getQuantity());

            assertFalse(ingredient.getIsSubstitute());
            ingredient.setSubstitute(true);
            assertTrue(ingredient.getIsSubstitute());

        }


        @Test
        @DisplayName("Testing ingredient's Nutrient ")
        void testIngredientsNutrients() throws IOException {

            ingredient.setIngredient((new Edible()).initNutrition(340, 40, 20, 200)
                    .initDetails(2, "Fruit Beer", "Pour it and drink it", 4, Edible.Unit.g)
                    .initCategories(true, true, true, false, true));


            assertEquals(340, ingredient.getIngredient().getCalories());
            ingredient.getIngredient().setCalories(450);
            assertEquals(450, ingredient.getIngredient().getCalories());

            assertEquals(40, ingredient.getIngredient().getProtein());
            ingredient.getIngredient().setProtein(50);
            assertEquals(50, ingredient.getIngredient().getProtein());

            assertEquals(200, ingredient.getIngredient().getFat());
            ingredient.getIngredient().setFat(400);
            assertEquals(400, ingredient.getIngredient().getFat());


            assertEquals(20, ingredient.getIngredient().getCarbs());
            ingredient.getIngredient().setCarbs(10);
            assertEquals(10, ingredient.getIngredient().getCarbs());

        }


            @Test
        @DisplayName("Tests setting different boolean values to the categories ")
        void testIngredientsCategories() throws IOException {

            ingredient.setIngredient((new Edible()).initNutrition(340,40,20,200)
                                                    .initDetails(2,"Fruit Beer","Pour it and drink it",4, Edible.Unit.g)
                                                    .initCategories(true,true,true,false,true));


            assertTrue(ingredient.getIngredient().getIsAlcoholic());
            ingredient.getIngredient().setAlcoholic(false);
            assertFalse(ingredient.getIngredient().getIsAlcoholic());

            assertTrue(ingredient.getIngredient().getIsSpicy());
            ingredient.getIngredient().setSpicy(false);
            assertFalse(ingredient.getIngredient().getIsSpicy());

            assertTrue(ingredient.getIngredient().getIsVegan());
            ingredient.getIngredient().setVegan(false);
            assertFalse(ingredient.getIngredient().getIsVegan());

            assertFalse(ingredient.getIngredient().getIsVegetarian());
            ingredient.getIngredient().setVegetarian(true);
            assertTrue(ingredient.getIngredient().getIsVegetarian());

            assertTrue(ingredient.getIngredient().getIsGlutenFree());
            ingredient.getIngredient().setGlutenFree(false);
            assertFalse(ingredient.getIngredient().getIsGlutenFree());

            ingredient.getIngredient().setCustom(true);
            assertTrue(ingredient.getIngredient().getIsCustom());

        }
    }





    @Nested
    @DisplayName("Tests cases those are valid but complex")
    class ComplexTests {

        private DrinkIngredient ingredient;
        private Edible edibleItem;

        @BeforeEach
        void setup() throws IOException {

            Main.startUp();

            ingredient = new DrinkIngredient();

            edibleItem = new Edible();

            ingredient.setIngredient(edibleItem);

        }

        @Test
        @DisplayName("Tests setting complex values for ingredient's quantity")
        void testSetQuantity() throws IOException {

            ingredient.setQuantity(10.99);
            assertEquals(10.99, ingredient.getQuantity());

            ingredient.setQuantity(9999.00000000);
            assertEquals(9999, ingredient.getQuantity());

            ingredient.setQuantity(0001.0);
            assertEquals(1, ingredient.getQuantity());

            ingredient.setQuantity(4000);
            assertEquals(4000, ingredient.getQuantity());

            ingredient.setQuantity(Math.sqrt(67));
            assertEquals(Math.sqrt(67), ingredient.getQuantity());

        }


        @Test
        @DisplayName("Tests setting complex values for edible's Name")
        void testSetName() {

            edibleItem.setName(" ");
            assertEquals(" ", ingredient.getIngredient().getName());

            edibleItem.setName("!!!!!#$%^&*()ghj");
            assertEquals("!!!!!#$%^&*()ghj", ingredient.getIngredient().getName());

            edibleItem.setName("                       ...!!");
            assertEquals("                       ...!!", ingredient.getIngredient().getName());

            edibleItem.setName("hello\n\n\nWorld\n");
            assertEquals("hello\n\n\nWorld\n", ingredient.getIngredient().getName());

            edibleItem.setName("\t\t\t\t\tr");
            assertEquals("\t\t\t\t\tr", ingredient.getIngredient().getName());

        }


        @Test
        @DisplayName("Tests setting complex values for edible's photo")
        void testSetPhoto() {

            edibleItem.setPhoto(" ");
            assertEquals(" ", ingredient.getIngredient().getPhoto());

            edibleItem.setPhoto("!!!!!#$%^&*()ghj");
            assertEquals("!!!!!#$%^&*()ghj", ingredient.getIngredient().getPhoto());

            edibleItem.setPhoto("                       ...!!");
            assertEquals("                       ...!!", ingredient.getIngredient().getPhoto());

            edibleItem.setPhoto("hello\n\n\nWorld\n");
            assertEquals("hello\n\n\nWorld\n", ingredient.getIngredient().getPhoto());

            edibleItem.setPhoto("\t\t\t\t\tr");
            assertEquals("\t\t\t\t\tr", ingredient.getIngredient().getPhoto());

        }


        @Test
        @DisplayName("Tests setting complex values for edible's Description")
        void testSetDescription() {

            edibleItem.setDescription(" ");
            assertEquals(" ", ingredient.getIngredient().getDescription());

            edibleItem.setDescription("!!!!!#$%^&*()ghj");
            assertEquals("!!!!!#$%^&*()ghj", ingredient.getIngredient().getDescription());

            edibleItem.setDescription("                       ...!!");
            assertEquals("                       ...!!", ingredient.getIngredient().getDescription());

            edibleItem.setDescription("hello\n\n\nWorld\n");
            assertEquals("hello\n\n\nWorld\n", ingredient.getIngredient().getDescription());

            edibleItem.setDescription("\t\t\t\t\tr");
            assertEquals("\t\t\t\t\tr", ingredient.getIngredient().getDescription());

        }
    }





    @Nested
    @DisplayName("Tests cases those should throw an exception")
    class shouldFailTests{

        private DrinkIngredient ingredient;
        private Edible edibleItem;

        @BeforeEach
        void setup() {

            Main.startUp();

            ingredient = new DrinkIngredient();

            edibleItem = new Edible();

        }





        @Test
        @DisplayName("Tests setting invalid values for edible's Name")
        void testSetName() {

            String longString = ".................................................................";

            assertThrows(IllegalArgumentException.class, () -> {


                edibleItem.setName("");
                assertEquals("", ingredient.getIngredient().getName());

                edibleItem.setName(null);
                assertNull( ingredient.getIngredient().getName());

                edibleItem.setName(longString);
                assertEquals(longString, ingredient.getIngredient().getName());


            });

        }


        @Test
        @DisplayName("Tests setting invalid values for edible's photo")
        void testSetPhoto() {

            String longString = ".................................................................";

            assertThrows(IllegalArgumentException.class, () -> {


                edibleItem.setPhoto("");
                assertEquals("", ingredient.getIngredient().getPhoto());

                edibleItem.setPhoto(null);
                assertNull( ingredient.getIngredient().getPhoto());

                edibleItem.setPhoto(longString);
                assertEquals(longString, ingredient.getIngredient().getPhoto());


            });

        }


        @Test
        @DisplayName("Tests setting invalid values for edible's Description")
        void testSetDescription() throws IOException {

            edibleItem.initDetails(2,"Edible Name","description",4000, Edible.Unit.tbsp);
            ingredient.setIngredient(edibleItem);

            String longString = ".................................................................";

            assertThrows(IllegalArgumentException.class, () -> {

                edibleItem.setDescription("");
                assertEquals("", ingredient.getIngredient().getDescription());

                edibleItem.setDescription(null);
                assertNull(ingredient.getIngredient().getDescription());

                edibleItem.setDescription(longString);
                assertEquals(longString, ingredient.getIngredient().getDescription());


            });
        }


        @Test
        @DisplayName("Tests setting invalid values for ingredient's quantity")
        void testSetQuantity() throws IOException {

            edibleItem.initDetails(1,"Edible Name","description",4, Edible.Unit.tbsp);
            ingredient.setIngredient(edibleItem);

            assertThrows(IllegalArgumentException.class, () -> {

                ingredient.getIngredient().setBaseQuantity(0);
                assertEquals(0, ingredient.getIngredient().getQuantity());

                ingredient.getIngredient().setBaseQuantity(-1);
                assertEquals(-1, ingredient.getQuantity());

                ingredient.getIngredient().setBaseQuantity(99999999);
                assertEquals(99999999, ingredient.getQuantity());

                ingredient.getIngredient().setBaseQuantity(0000000000000100000000);
                assertEquals(000000000000010000000000, ingredient.getQuantity());

            });

        }


        @Test
        @DisplayName("Tests setting invalid calorie count for an edible")
        void testSetCalories()  throws IOException {

            edibleItem.initNutrition(900,100,10,2);
            ingredient.setIngredient(edibleItem);

            ingredient.getIngredient().setCalories(5);
            assertEquals(ingredient.getIngredient().getCalories(), 5);

            ingredient.getIngredient().setCalories(10);
            assertEquals(ingredient.getIngredient().getCalories(), 10);

            ingredient.getIngredient().initNutrition(5,1, 1, 1);
            assertEquals(ingredient.getIngredient().getCalories(), 5);

            ingredient.getIngredient().initNutrition(90,1, 1, 1);
            assertEquals(ingredient.getIngredient().getCalories(), 90);
        }


        @Test
        @DisplayName("Tests setting an invalid proteins count for an edible")
        void testSetProtein() throws IOException {

            edibleItem.initNutrition(900,100,10,2);
            ingredient.setIngredient(edibleItem);

            assertThrows(IllegalArgumentException.class, () -> {

                ingredient.getIngredient().setProtein(-23);
                ingredient.getIngredient().setProtein(0);
                assertEquals(0, ingredient.getIngredient().getProtein());

                ingredient.getIngredient().setProtein(-1);
                assertEquals(-1, ingredient.getIngredient().getProtein());

                ingredient.getIngredient().setProtein(99999999);
                assertEquals(99999999, ingredient.getIngredient().getProtein());

                ingredient.getIngredient().setProtein(0000000000000100000000);
                assertEquals(000000000000010000000000, ingredient.getIngredient().getProtein());

            });
        }

        @Test
        @DisplayName("Tests setting an invalid carbs count for an edible")
        void testSetCarbs() throws IOException {

            edibleItem.initNutrition(900,100,10,2);
            ingredient.setIngredient(edibleItem);

            assertThrows(IllegalArgumentException.class, () -> {


                ingredient.getIngredient().setCarbs(0);
                assertEquals(0, ingredient.getIngredient().getCarbs());

                ingredient.getIngredient().setCarbs(-1);
                assertEquals(-1, ingredient.getIngredient().getCarbs());

                ingredient.getIngredient().setCarbs(99999999);
                assertEquals(99999999, ingredient.getIngredient().getCarbs());

                ingredient.getIngredient().setCarbs(0000000000000100000000);
                assertEquals(000000000000010000000000, ingredient.getIngredient().getCarbs());

            });
        }

        @Test
        @DisplayName("Tests setting an invalid fat count for an edible")
        void testSetFat() throws IOException {

            edibleItem.initNutrition(900,100,10,2);
            ingredient.setIngredient(edibleItem);
            assertThrows(IllegalArgumentException.class , () -> {


                ingredient.getIngredient().setFat(0);
                assertEquals(0, ingredient.getIngredient().getFat());

                ingredient.getIngredient().setFat(-1);
                assertEquals(-1, ingredient.getIngredient().getFat());

                ingredient.getIngredient().setFat(99999999);
                assertEquals(99999999, ingredient.getIngredient().getFat());

                ingredient.getIngredient().setFat(0000000000000100000000);
                assertEquals(000000000000010000000000, ingredient.getIngredient().getFat());

            });
        }

    }


}