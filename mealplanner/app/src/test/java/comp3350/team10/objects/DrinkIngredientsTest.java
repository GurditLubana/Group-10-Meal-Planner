package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;
import comp3350.team10.persistence.SharedDB;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DrinkIngredientsTest {

    DrinkIngredient testDrink;

    @Nested
    @DisplayName("Testing ingredient getters and setters")
    class Testgetter{

        @BeforeEach
        void SetIngredients(){
            testDrink = new DrinkIngredient("Mohito", 10, "ml", true,true);
        }

        @Test
        void testGetValues(){
            assertTrue(testDrink.getName() == "Mohito");
            assertTrue(testDrink.getQty() == 10);
            assertTrue(testDrink.isAlcoholic());
            assertTrue(testDrink.getUnits() == "ml");
            assertTrue(testDrink.isReplacement());
        }

        @Test
        void nameNotNull(){
            assertTrue(testDrink.getName() != null);
        }

        @Test
        void nameNotEmpty(){
            assertTrue(testDrink.getName() != "");
        }

        @Test
        void testQtyGetter(){
            assertTrue(testDrink.getQty() >=0);
            assertTrue(testDrink.getQty() <= 9999);
        }

        @Test
        void unitNotNull(){
            assertTrue(testDrink.getUnits() != null);
        }

        @Test
        void unitNotEmpty(){
            assertTrue(testDrink.getUnits() != "");
        }
    }

    @Nested
    @DisplayName("testing the change Quantity")
    class TestIngredientstuff{

        Ingredient currDrink;

        @BeforeEach
        void SetDrinks(){
            currDrink = new DrinkIngredient("Mohito", 10, "ml", true,true);
        }

        @Test
        void testQtySetter(){
            Double currQty = currDrink.getQty();
            Double setQty = 25.00;
            currDrink.changeQty(setQty);
            assertTrue(currDrink.getQty() >= 0);
            assertTrue(currDrink.getQty() <= 9999);
            assertTrue(currDrink.getQty() != currQty);
            assertTrue(currDrink.getQty() == setQty);
        }

        @Test
        void testUnitSetter(){
            String currUnit = currDrink.getUnits();
            currDrink.changeUnits("litre");
            assertTrue(currDrink.getUnits() != null);
            assertTrue(currDrink.getUnits() != "");
            assertTrue(currDrink.getUnits() != currUnit);
            assertTrue(currDrink.getUnits() == "litre");
        }

    }
}
