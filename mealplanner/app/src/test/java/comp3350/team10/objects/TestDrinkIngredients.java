package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        @DisplayName("Testing details of the edible item")
        void testDetails()
        {


            assertEquals("item1",edibleItem.getName());

            assertEquals("hello\nWorld\n",edibleItem.getDescription());

            assertEquals(4,edibleItem.getQuantity());

            assertEquals(Edible.Unit.g,edibleItem.getUnit());


        }

        @Test
        @DisplayName("Testing details of the edible item")
        void testNutritions()
        {
            assertEquals(340,edibleItem.getCalories());

            assertEquals(40,edibleItem.getProtein());

            assertEquals(200, edibleItem.getFat());

            assertEquals(20, edibleItem.getCarbs());

        }

        @Test
        @DisplayName("Testing Categories of the edible item")
        void testCategories()
        {
            assertTrue(edibleItem.getIsAlcoholic());

            assertTrue(edibleItem.getIsSpicy());

            assertTrue(edibleItem.getIsVegan());

            assertFalse(edibleItem.getIsVegetarian());

            assertTrue(edibleItem.getIsGlutenFree());

        }

        @Test
        @DisplayName("Testing Metadata of the edible item")
        void testMetaData()
        {
            assertFalse(edibleItem.getIsCustom());

            assertEquals("ApplesPicture.jpg",edibleItem.getPhoto());
        }


        @Test
        @DisplayName("Testing setters")
        void testEdibleClassSetters()
        {
            edibleItem.setCustom(true);

            edibleItem.setAlcoholic(false);

            edibleItem.setGlutenFree(false);

            edibleItem.setVegan(false);

            edibleItem.setVegetarian(true);

            edibleItem.setSpicy(false);

            edibleItem.setCarbs(65);

            edibleItem.setBaseQuantity(34);

            edibleItem.setBaseUnit(Edible.Unit.cups);

            edibleItem.setCalories(90);

            edibleItem.setFat(78);

            edibleItem.setDescription("My world");

            edibleItem.setName("Banana");

            edibleItem.setPhoto("bananasPic.jpg");


            assertEquals("Banana",edibleItem.getName());

            assertEquals("My world",edibleItem.getDescription());

            assertEquals(Edible.Unit.cups,edibleItem.getUnit());

            assertFalse(edibleItem.getIsAlcoholic());

            assertFalse(edibleItem.getIsSpicy());

            assertFalse(edibleItem.getIsVegan());

            assertTrue(edibleItem.getIsVegetarian());

            assertFalse(edibleItem.getIsGlutenFree());

            assertTrue(edibleItem.getIsCustom());

            assertEquals("bananasPic.jpg",edibleItem.getPhoto());

            assertEquals(90,edibleItem.getCalories());

            assertEquals(40,edibleItem.getProtein());

            assertEquals(78, edibleItem.getFat());

            assertEquals(65, edibleItem.getCarbs());

            assertEquals(34,edibleItem.getQuantity());
        }


        @Test
        @DisplayName("Testing Ingredients ")
        void testIngredients() throws IOException {

            ingredient.setIngredient((new Edible()).initNutrition(340,40,20,200)
                                                    .initDetails(2,"Fruit Beer","Pour it and drink it",4, Edible.Unit.g)
                                                    .initCategories(true,true,true,false,true));


            assertEquals(2,ingredient.getIngredient().getDbkey());

            assertEquals("Fruit Beer", ingredient.getIngredient().getName());

            assertEquals("Pour it and drink it", ingredient.getIngredient().getDescription());

            assertEquals(Edible.Unit.g, ingredient.getIngredient().getUnit());

            assertEquals(340,ingredient.getIngredient().getCalories());

            assertEquals(40,ingredient.getIngredient().getProtein());

            assertEquals(200, ingredient.getIngredient().getFat());

            assertEquals(20, ingredient.getIngredient().getCarbs());

            assertEquals(4, ingredient.getIngredient().getQuantity());

            assertTrue(ingredient.getIngredient().getIsAlcoholic());

            assertTrue(ingredient.getIngredient().getIsSpicy());

            assertTrue(ingredient.getIngredient().getIsVegan());

            assertFalse(ingredient.getIngredient().getIsVegetarian());

            assertTrue(ingredient.getIngredient().getIsGlutenFree());

            ingredient.setQuantity(789);

            assertEquals(789, ingredient.getQuantity());

            ingredient.setQuantityUnit(Edible.Unit.ml);

            assertEquals(Edible.Unit.ml, ingredient.getQuantityUnits());

            assertFalse(ingredient.getIsSubstitute());

            ingredient.setSubstitute(true);

            assertTrue(ingredient.getIsSubstitute());

        }



    }
    @Nested
    @DisplayName("Edge Case tests")
    class ComplexTests {

        private DrinkIngredient ingredient;
        private Edible edibleItem;

        @BeforeEach
        void setup() {

            Main.startUp();

            ingredient = new DrinkIngredient();

            edibleItem = new Edible();



        }


        @Test
        @DisplayName("Testing details of the edible item")
        void testDetails() throws IOException {

            edibleItem.initDetails(2,"                       ...!!","hello\n\n\nWorld\n",4000, Edible.Unit.tbsp);

            edibleItem.initNutrition(9999,1,1,0);

            edibleItem.initCategories(true,true,true,false,true);

            edibleItem.initMetadata(false,"!!!!!#$%^&*()ghj");

            ingredient.setQuantity(70.99);

            assertEquals(70.99,ingredient.getQuantity());

            ingredient.setIngredient(edibleItem);

            ingredient.setQuantity(9999.00000000);

            assertEquals(9999,ingredient.getQuantity());

            ingredient.setQuantity(0001.0);

            assertEquals(1,ingredient.getQuantity());

            assertEquals("!!!!!#$%^&*()ghj",ingredient.getIngredient().getPhoto());

            assertEquals("                       ...!!",ingredient.getIngredient().getName());

            assertEquals("hello\n\n\nWorld\n",ingredient.getIngredient().getDescription());

            assertEquals(4000,ingredient.getIngredient().getQuantity());

            assertEquals(Edible.Unit.tbsp,ingredient.getIngredient().getUnit());

            assertEquals(9999,ingredient.getIngredient().getCalories());

            assertEquals("!!!!!#$%^&*()ghj", ingredient.getIngredient().getPhoto());

            assertEquals(0,ingredient.getIngredient().getFat());

            edibleItem.setDescription("\t\t\t\t\tr");

            assertEquals("\t\t\t\t\tr", ingredient.getIngredient().getDescription());
        }
    }

    @Nested
    @DisplayName("Tests those should fail")
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
        @DisplayName("these edible item tests should throw exception")
        void testDetails() throws IOException {

            edibleItem.initDetails(2,"                       ...!!","hello\n\n\nWorld\n",4000, Edible.Unit.tbsp);

            edibleItem.initNutrition(9999,1,1,0);

            edibleItem.initCategories(true,true,true,false,true);

            edibleItem.initMetadata(false,"!!!!!#$%^&*()ghj");

            ingredient.setQuantity(70.99);

            assertThrows(IllegalArgumentException.class, () -> {

                edibleItem.initDetails(2,"Edible ","hello\n\n\nWorld\n",4000, Edible.Unit.tbsp);

                edibleItem.initNutrition(9999999,1,1,0);

                ingredient.setIngredient(edibleItem);

                ingredient.getIngredient().setBaseQuantity(0);

                ingredient.getIngredient().setCalories(9999.01);

                ingredient.getIngredient().setCarbs(0000100001);

                assertFalse(ingredient.getIsSubstitute());

            });



        }


    }


}