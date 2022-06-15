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

	//simple test
	// just after the initial the recipe book see what will get the recipe it should be in food list are all food
	@Test
	public void testInial() {
		
		RecipeBookOps test = new RecipeBookOps();
		LinkedList food = null;
		food = test.getFoodRecipes();
		assertNotNull(food); 
		for (int i = 0; i < food.size(); i++) {
			Edible testEdible = food.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
		}
		
		
		LinkedList drink = null;
		drink = test.getFoodRecipes();
		assertNotNull(drink);
		for (int i = 0; i < drink.size(); i++) {
			Edible testEdible = drink.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
		}
		
	
		LinkedList meal = null;
		meal = test.getFoodRecipes();
		assertNotNull(meal); 
		for (int i = 0; i < meal.size(); i++) {
			Edible testEdible = meal.get(i);
			assertNotNull(testEdible);
			assertTrue(testEdible instanceof Food); 
		}
		
		
		
	}
	
	//complex test
	// here we add 3 new recipe to RecipeBookOps 1 recipe 1 kind, and it should find it success
	public void testAddNewRecipeNormal() {
		
		RecipeBookOps test = new RecipeBookOps();
		

		test.addFood("testFood", 2, 5, Edible.Unit.g, 54);
		test.addDrink("testDrink", 1, 100, "lala", "happy", Edible.Unit.g, 10);
		test.addMeal("testMeal", 1, 100, "lala", "happy", Edible.Unit.g, 10);//why the String ingredients, String instructions in different order of add meal and add drink
		
	
		
		LinkedList food = null;
		food = test.getFoodRecipes();
		assertNotNull(food); 
		boolean findFood= false; 
		int findFoodNum = 0;
		for (int i = 0; i < food.size(); i++) {
			Edible testEdible = food.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("testFood")) {
				findFood =true;
				findFoodNum++;
			}
			
		}
		assertEquals(1, findFoodNum);
		assertTrue(findFood);
		

		
		LinkedList drink = null;
		drink = test.getFoodRecipes();
		boolean findDrink= false; 
		int findDrinkNum=0;
		assertNotNull(drink); 
		for (int i = 0; i < drink.size(); i++) {
			Edible testEdible = drink.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("testDrink")) {
				findDrink =true;
				findDrinkNum++;
			}
		}
		assertEquals(1, findDrinkNum);
		assertTrue(findDrink);
		
		
		
		LinkedList meal = null;
		meal = test.getFoodRecipes();
		boolean findMeal=false;  
		int findMealNumber = 0;
		assertNotNull(meal); 
		for (int i = 0; i < meal.size(); i++) {
			Edible testEdible = meal.get(i);
			assertNotNull(testEdible);
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("testMeal")) {
				findMeal =true;
				findMealNumber++;
			}
		}
		assertEquals(1, findMealNumber);
		assertTrue(findMeal);
		
	}
	//empty test
	// here we add 3 empty recipe to RecipeBookOps to tree kind of recipe, and it should not find it
	public void testAddempty() {
		
		RecipeBookOps test = new RecipeBookOps();
		

		test.addFood("", 0, 0, null, 0);
		test.addDrink("", 0, 0, "", "", null, 0);
		test.addMeal("", 0, 0, "", "", null, 0);//why the String ingredients, String instructions in different order of add meal and add drink
		
	
		
		LinkedList food = null;
		food = test.getFoodRecipes();
		assertNotNull(food); 
		boolean findFood= false; 
		for (int i = 0; i < food.size(); i++) {
			Edible testEdible = food.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("")) {
				findFood =true;
			}
			
		}
		assertFalse(findFood);
		

		
		LinkedList drink = null;
		drink = test.getFoodRecipes();
		boolean findDrink= false; 
		
		assertNotNull(drink); 
		for (int i = 0; i < drink.size(); i++) {
			Edible testEdible = drink.get(i);
			assertNotNull(testEdible); 
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("")) {
				findDrink =true;
				
			}
		}
		assertFalse(findDrink);
		
		
		
		LinkedList meal = null;
		meal = test.getFoodRecipes();
		boolean findMeal=false;  
		
		assertNotNull(meal); 
		for (int i = 0; i < meal.size(); i++) {
			Edible testEdible = meal.get(i);
			assertNotNull(testEdible);
			assertTrue(testEdible instanceof Food); 
			if (testEdible.getName().equals("")) {
				findMeal =true;
				
			}
		}
		assertFalse(findMeal);
		
	}
	
	//complex test and error test
	// here we add 1 recipe with same name to RecipeBookOps
	//assume when meet the same name of recipe update the data
		public void testSameName() {
			
			RecipeBookOps test = new RecipeBookOps();
			
			
			
			test.addFood("testFood", 2, 5, Edible.Unit.serving, 54);
			test.addDrink("testDrink", 1, 100, "lala", "happy",Edible.Unit.serving, 10); //assume first String ingredients, second String instructions
			test.addMeal("testMeal", 1, 100, "lala", "happy", Edible.Unit.serving, 10);//why the String ingredients, String instructions in different order of add meal and add drink
			
			test.addFood("testFood", 2, 4, Edible.Unit.g, 880);
			test.addDrink("testDrink", 4, 10, "la", "hap", Edible.Unit.g, 450);
			test.addMeal("testMeal", 5, 10, "la", "hap", Edible.Unit.g, 150);
			
			
		
			
			
			LinkedList food = null;
			food = test.getFoodRecipes();
			assertNotNull(food); 
			boolean findFood= false; 
			int findFoodNum = 0;
			Edible testFood = null; 
			for (int i = 0; i < food.size(); i++) {
				Edible testEdible = food.get(i);
				assertNotNull(testEdible);
				assertTrue(testEdible instanceof Food);
				if (testEdible.getName().equals("testFood")) {
					findFood =true;
					findFoodNum++;
					assertTrue(findFoodNum < 2); 
					testFood = testEdible;
				}
				
			}
			
			assertEquals(1, findFoodNum);
			assertTrue(findFood);
			
			assertNotNull(testFood);
			assertEquals(2, testFood.getIconPath());
			assertEquals(4, testFood.getCalories());
			assertEquals(Edible.Unit.g, testFood.getBaseUnit());
			assertEquals(880, testFood.getQuantity());
		
			
			
			
			LinkedList drink = null;
			drink = test.getFoodRecipes();
			boolean findDrink= false;
			int findDrinkNum=0;
			assertNotNull(drink); 
			Edible testDrink = null;
			for (int i = 0; i < drink.size(); i++) {
				Edible testEdible = drink.get(i);
				assertNotNull(testEdible); 
				assertTrue(testEdible instanceof Food); /
				if (testEdible.getName().equals("testDrink")) {
					findDrink =true;
					findDrinkNum++;
					assertTrue(findDrinkNum <2);
					testDrink = testEdible;
				}
			}
			
			assertEquals(1, findDrinkNum);
			assertTrue(findDrink);
			
			
			assertNotNull(testDrink);
			assertEquals(4, testDrink.getIconPath());
			assertEquals(10, testDrink.getCalories());
			assertEquals(Edible.Unit.g, testDrink.getBaseUnit());
			assertEquals(450, testDrink.getQuantity());
			assertEquals("la", ((Drink)testDrink).getIngredients());
			assertEquals("hap", ((Drink)testDrink).getInstructions());
			
			
			
			LinkedList meal = null;
			meal = test.getFoodRecipes();
			boolean findMeal=false; 
			int findMealNumber = 0;
			Edible testMeal = null;
			assertNotNull(meal); 
			for (int i = 0; i < meal.size(); i++) {
				Edible testEdible = meal.get(i);
				assertNotNull(testEdible); 
				assertTrue(testEdible instanceof Food);
				if (testEdible.getName().equals("testMeal")) {
					findMeal =true;
					findMealNumber++;
					assertTrue(findMealNumber <2);
					testMeal =testEdible;
				}
			}
			
			assertEquals(1, findMealNumber);
			assertTrue(findMeal);
			
			assertNotNull(testMeal);
			assertEquals(5, testMeal.getIconPath());
			assertEquals(10, testMeal.getCalories());
			assertEquals(Edible.Unit.g, testMeal.getBaseUnit());
			assertEquals(150, testMeal.getQuantity());
			assertEquals("la", ((Meal)testMeal).getIngredients());
			assertEquals("hap", ((Meal)testMeal).getInstructions());
		}
			
			
			

}

