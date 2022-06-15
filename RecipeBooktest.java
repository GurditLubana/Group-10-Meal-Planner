import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class RecipeBooktest {
	@Test
	public void testInial() {// just after the initial the recipe book
		
		RecipeBookOps test = new RecipeBookOps();
		//test getFoodRecipes
		LinkedList food = null;
		food = test.getFoodRecipes();
		assertNotNull(food); // the link list not been null but size can be 0
		for (int i = 0; i < food.size(); i++) {
			Edible testEdible = food.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a food obj
		}
		//test getFoodRecipes
		
		//test getDrinkRecipes
		LinkedList drink = null;
		drink = test.getFoodRecipes();
		assertNotNull(drink); //  the link list not been null but size can be 0
		for (int i = 0; i < drink.size(); i++) {
			Edible testEdible = drink.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a drink obj
		}
		//test getDrinkRecipes
		
		//test getMealRecipes
		LinkedList meal = null;
		meal = test.getFoodRecipes();
		assertNotNull(meal); // the link list not been null but size can be 0
		for (int i = 0; i < meal.size(); i++) {
			Edible testEdible = meal.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a meal obj
		}
		//test getMealRecipes
		
		
	}
	
	
	// here we add 1 new recipe to RecipeBookOps
	public void testAddNewRecipeNormal() {
		
		RecipeBookOps test = new RecipeBookOps();
		
		
		// the things not in the recipe book
		test.addFood("testFood", 2, 5, Edible.Unit.g, 54);
		test.addDrink("testDrink", 1, 100, "lala", "happy", Edible.Unit.g, 10);
		test.addMeal("testMeal", 1, 100, "lala", "happy", Edible.Unit.g, 10);//why the String ingredients, String instructions in different order of add meal and add drink
		// the things not in the recipe book
	
		//test getFoodRecipes
		LinkedList food = null;
		food = test.getFoodRecipes();
		assertNotNull(food); // the link list not been null
		boolean findFood= false; // need find this food
		int findFoodNum = 0;
		for (int i = 0; i < food.size(); i++) {
			Edible testEdible = food.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a food obj
			if (testEdible.getName().equals("testFood")) {
				findFood =true;
				findFoodNum++;
			}
			
		}
		//should only find 1 in the list
		assertEquals(1, findFoodNum);
		assertTrue(findFood);
		
		//test getFoodRecipes
		
		//test getDrinkRecipes
		LinkedList drink = null;
		drink = test.getFoodRecipes();
		boolean findDrink= false; // need find this drink
		int findDrinkNum=0;
		assertNotNull(drink); // the link list not been null
		for (int i = 0; i < drink.size(); i++) {
			Edible testEdible = drink.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a drink obj
			if (testEdible.getName().equals("testDrink")) {
				findDrink =true;
				findDrinkNum++;
			}
		}
		//should only find 1 in the list
		assertEquals(1, findDrinkNum);
		assertTrue(findDrink);
		//test getDrinkRecipes
		
		//test getMealRecipes
		LinkedList meal = null;
		meal = test.getFoodRecipes();
		boolean findMeal=false;  // need find this meal
		int findMealNumber = 0;
		assertNotNull(meal); // the link list not been null
		for (int i = 0; i < meal.size(); i++) {
			Edible testEdible = meal.get(i);
			assertNotNull(testEdible); // cannot get a null node
			assertTrue(testEdible instanceof Food); // it should be a meal obj
			if (testEdible.getName().equals("testMeal")) {
				findMeal =true;
				findMealNumber++;
			}
		}
		//should only find 1 in the list
		assertEquals(1, findMealNumber);
		assertTrue(findMeal);
		//test getMealRecipes
	}
	
	
	// here we add 1 recipe with same name to RecipeBookOps
	//assume when meet the same name of recipe update the data
		public void testSameName() {
			
			RecipeBookOps test = new RecipeBookOps();
			
			
			// the things not in the recipe book
			test.addFood("testFood", 2, 5, Edible.Unit.serving, 54);
			test.addDrink("testDrink", 1, 100, "lala", "happy",Edible.Unit.serving, 10); //assume first String ingredients, second String instructions
			test.addMeal("testMeal", 1, 100, "lala", "happy", Edible.Unit.serving, 10);//why the String ingredients, String instructions in different order of add meal and add drink
			
			test.addFood("testFood", 2, 4, Edible.Unit.g, 880);
			test.addDrink("testDrink", 4, 10, "la", "hap", Edible.Unit.g, 450);
			test.addMeal("testMeal", 5, 10, "la", "hap", Edible.Unit.g, 150);//why the String ingredients, String instructions in different order of add meal and add drink
			
			// the things not in the recipe book
		
			
			//test getFoodRecipes
			LinkedList food = null;
			food = test.getFoodRecipes();
			assertNotNull(food); // the link list not been null
			boolean findFood= false; // need find this food
			int findFoodNum = 0;
			Edible testFood = null; //get the thing add before for the detail test
			for (int i = 0; i < food.size(); i++) {
				Edible testEdible = food.get(i);
				assertNotNull(testEdible); // cannot get a null node
				assertTrue(testEdible instanceof Food); // it should be a food obj
				if (testEdible.getName().equals("testFood")) {
					findFood =true;
					findFoodNum++;
					assertTrue(findFoodNum < 2); // not bigger that 2;
					testFood = testEdible;
				}
				
			}
			//should only find 1 in the list
			assertEquals(1, findFoodNum);
			assertTrue(findFood);
			//test the testFood should same a second time add
			assertNotNull(testFood);
			assertEquals(2, testFood.getIconPath());
			assertEquals(4, testFood.getCalories());
			assertEquals(Edible.Unit.g, testFood.getBaseUnit());
			assertEquals(880, testFood.getQuantity());
			//test getFoodRecipes
			
			
			
			//test getDrinkRecipes
			LinkedList drink = null;
			drink = test.getFoodRecipes();
			boolean findDrink= false; // need find this drink
			int findDrinkNum=0;
			assertNotNull(drink); // the link list not been null
			Edible testDrink = null;//get the thing add before for the detail test 
			for (int i = 0; i < drink.size(); i++) {
				Edible testEdible = drink.get(i);
				assertNotNull(testEdible); // cannot get a null node
				assertTrue(testEdible instanceof Food); // it should be a drink obj
				if (testEdible.getName().equals("testDrink")) {
					findDrink =true;
					findDrinkNum++;
					assertTrue(findDrinkNum <2);
					testDrink = testEdible;
				}
			}
			//should only find 1 in the list
			assertEquals(1, findDrinkNum);
			assertTrue(findDrink);
			
			//test the testDrink should same a second time add
			assertNotNull(testDrink);
			assertEquals(4, testDrink.getIconPath());
			assertEquals(10, testDrink.getCalories());
			assertEquals(Edible.Unit.g, testDrink.getBaseUnit());
			assertEquals(450, testDrink.getQuantity());
			assertEquals("la", ((Drink)testDrink).getIngredients());
			assertEquals("hap", ((Drink)testDrink).getInstructions());
			//test getDrinkRecipes
			
			//test getMealRecipes
			LinkedList meal = null;
			meal = test.getFoodRecipes();
			boolean findMeal=false;  // need find this meal
			int findMealNumber = 0;
			Edible testMeal = null;//get the thing add before for the detail test
			assertNotNull(meal); // the link list not been null
			for (int i = 0; i < meal.size(); i++) {
				Edible testEdible = meal.get(i);
				assertNotNull(testEdible); // cannot get a null node
				assertTrue(testEdible instanceof Food); // it should be a meal obj
				if (testEdible.getName().equals("testMeal")) {
					findMeal =true;
					findMealNumber++;
					assertTrue(findMealNumber <2);
					testMeal =testEdible;
				}
			}
			//should only find 1 in the list
			assertEquals(1, findMealNumber);
			assertTrue(findMeal);
			//test the testDrink should same a second time add
			assertNotNull(testMeal);
			assertEquals(5, testMeal.getIconPath());
			assertEquals(10, testMeal.getCalories());
			assertEquals(Edible.Unit.g, testMeal.getBaseUnit());
			assertEquals(150, testMeal.getQuantity());
			assertEquals("la", ((Meal)testMeal).getIngredients());
			assertEquals("hap", ((Meal)testMeal).getInstructions());
			
			//test getMealRecipes
			
			

}

