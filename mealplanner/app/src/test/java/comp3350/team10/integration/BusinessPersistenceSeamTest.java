package comp3350.team10.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.persistence.SharedDB;


public class BusinessPersistenceSeamTest
{
	@Nested
	@DisplayName("Integration testing of Meal diary Ops to persistence")
	class testMealDiaryOps {

		private MealDiaryOps ops;
		private Calendar currDate;



		@BeforeEach
		void setup() {

			try {
				SharedDB.start();
				SharedDB.startStub();

				this.currDate = Calendar.getInstance();
				this.currDate.set(Calendar.MONTH, 9);
				this.currDate.set(Calendar.DAY_OF_MONTH, 21);
				this.currDate.set(Calendar.DAY_OF_MONTH, 10);
			}

			catch (Exception e)
			{
				System.out.println(e);
			}
		}

		@AfterEach
		void shutdown() {
			SharedDB.close();
		}


		@Test
		@DisplayName("Testing the declaration of Meal Diary Ops and dailyLog.")
		public void testNotNull()
		{

			assertNull(ops);
			ops = new MealDiaryOps();
			assertNotNull(ops);
			DailyLog currentLog = ops.getCurrLog();
			assertNotNull(currentLog);

		}


		@Test
		@DisplayName("Testing the Meal diary's log.")
		public void testCurrentMealDiaryLog()
		{

			ops = new MealDiaryOps();
			DailyLog currentLog = ops.getCurrLog();
			assertEquals(currDate.get(Calendar.YEAR), currentLog.getDate().get(Calendar.YEAR));
			assertEquals(currDate.get(Calendar.MONTH), currentLog.getDate().get(Calendar.MONTH));
			assertEquals(currDate.get(Calendar.DATE), currentLog.getDate().get(Calendar.DATE));

			int initialSize = currentLog.getEdibleList().size();
			ops.addByKey(4, true);
			ops.addByKey(6, false);
			assertEquals( initialSize + 2, ops.getCurrLog().getEdibleList().size());

		}

		@Test
		@DisplayName("We should be able to add the first food item in the db to a DailyLog with an odd number of entries")
		void addFoodToOddNumberedLog() {

			ops = new MealDiaryOps();
			DailyLog currLog = ops.getCurrLog();

			int prevLogSize = currLog.getEdibleList().size();
			ops.addByKey(1, false);
			assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
			assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
			assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
			assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
			assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
			assertEquals((int) currLog.getCalorieNet(), 1626);
			assertEquals((int) currLog.getEdibleCalories(), 374);
		}

		@Test
		@DisplayName("We should be able to add the first food item in the db to a DailyLog with an even number of entries")
		void addFoodToEvenNumberedLog() {

			ops = new MealDiaryOps();
			DailyLog currLog = ops.getCurrLog();

			int prevLogSize = currLog.getEdibleList().size();
			ops.addByKey(1, false);
			assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
			assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
			assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
			assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
			assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
			assertEquals((int) currLog.getCalorieNet(), 1626);
			assertEquals((int) currLog.getEdibleCalories(), 374);

			ops.addByKey(1, false);
			assertEquals(prevLogSize + 2, currLog.getEdibleList().size());
			assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
			assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
			assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
			assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
			assertEquals(currLog.getEdibleList().get(4).getName(), "Apple");
			assertEquals((int) currLog.getCalorieNet(), 1526);
			assertEquals((int) currLog.getEdibleCalories(), 474);
		}



		@Test
		@DisplayName("Integration test of MealDiaryOps to persistence those shouldn't pass.")
		public void testInvalidMealDiaryOps()
		{
			SharedDB.close();

			try {
				ops.addByKey(45,false);
				fail("Should throw an exception, cannot add new key when database is closed.");
			}
			catch (Exception e) {
				assertTrue(e instanceof NullPointerException);
			}

		}
	}



	@Nested
	@DisplayName("Integration testing of Recipe Book Ops to persistence")
	class testRecipeBookOps {

		private RecipeBookOps ops;
		private final String testString = "We'll use this string for description and instructions";

		@BeforeEach
		void setup() {

			try
			{
				SharedDB.start();
				SharedDB.startStub();
				ops = new RecipeBookOps();
			}

			catch (Exception e)
			{
				System.out.println(e);
			}

		}

		@AfterEach
		void shutdown() {
			SharedDB.close();
		}

		@Test
		@DisplayName("Tests adding a food edible in meal diary ops")
		void testAddFood() {

			int initialSize = ops.getFoodRecipes().size();

			ops.addFood("Pan Cake", testString, 2, Edible.Unit.g, 350, 400, 74, 89,
					false, true, false, true, false, "photo");

			assertEquals(initialSize + 1, ops.getFoodRecipes().size());

		}

		@Test
		@DisplayName("Tests adding a meal recipe ops")
		void testAddMeals() {
			int initialSize = ops.getMealRecipes().size();
			Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
					.initNutrition(5, 5, 5, 5)
					.initCategories(true, true, true, true, true)
					.initMetadata(true, "photo");
			Ingredient currIngredient = new Ingredient().init(currEdible, 5, Edible.Unit.cups);
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			ingredients.add(currIngredient);

			ops.addMeal("Pan Cake", testString, 2, Edible.Unit.g, "photo", testString, ingredients);

			assertEquals(initialSize + 1, ops.getMealRecipes().size());

		}

		@Test
		@DisplayName("Tests adding a drink in recipe ops")
		void testAddPreparedDrinks() {
			int initialSize = ops.getDrinkRecipes().size();
			Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
					.initNutrition(5, 5, 5, 5)
					.initCategories(true, true, true, true, true)
					.initMetadata(true, "photo");
			DrinkIngredient currIngredient = (DrinkIngredient) new DrinkIngredient().init(currEdible, 5, Edible.Unit.cups);
			ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
			ingredients.add(currIngredient);

			ops.addPreparedDrink("Banana Smoothie", testString, 2, Edible.Unit.ml, "photo", testString, ingredients);

			assertEquals(initialSize + 1, ops.getDrinkRecipes().size());
		}

		@Test
		@DisplayName("Tests adding a simple drink (without ingredients) in recipe ops")
		void testAddSimpleDrinks() {
			int initialSize = ops.getDrinkRecipes().size();

			ops.addSimpleDrink("Pepsi", testString, 2, Edible.Unit.ml, 350, 400, 74, 89,
					false, true, false, true, false, "photo");
			assertEquals(initialSize + 1, ops.getDrinkRecipes().size());
		}

		@Test
		@DisplayName("Testing getters of RecipeBook Ops")
		void testRecipeBookOpsGetters()
		{
			assertNotNull(ops);
			assertNotNull(ops.getFoodRecipes());
			assertNotNull(ops.getDrinkRecipes());
			assertNotNull(ops.getMealRecipes());

		}

	}


	@Nested
	@DisplayName("Integration testing of Trend Ops to persistence")
	class testTrendOps {

		private TrendsOps ops;
		private final String testString = "We'll use this string for description and instructions";

		@BeforeEach
		void setup() {

			try {
				SharedDB.start();
				SharedDB.startStub();
				ops = new TrendsOps();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		@AfterEach
		void shutdown() {
			SharedDB.close();
		}
	}

	}