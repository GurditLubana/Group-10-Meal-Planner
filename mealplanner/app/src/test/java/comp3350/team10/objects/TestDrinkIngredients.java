package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
        void testSetters()
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

    }


}