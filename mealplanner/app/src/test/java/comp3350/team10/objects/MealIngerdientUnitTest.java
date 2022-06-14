import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

//unit test of Meal Ingredient and Ingredient class
//Meal Ingredient is child class of Ingredient

public class MealIngerdientUnitTest {
	@Test
	public void testGetter() {
		
		Food testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
		MealIngredient testIngredient = new MealIngredient(2.3, "testUnit", testFood);
	
		
		assertTrue(testIngredient.getFood() instanceof Food );
		assertEquals(2.3,testIngredient.getQty());
		assertEquals("testUnit",testIngredient.getUnits()); 
	}
	
    @Test
	public void testSetter() {
		
		Food testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
		MealIngredient testIngredient = new MealIngredient(2.3, "testUnit", testFood);
	
		testIngredient.changeQty(4.4);
		testIngredient.changeUnits("lala");
		
		assertEquals(4.4,testIngredient.getQty());
		assertEquals("lala",testIngredient.getUnits()); 
	}

}

