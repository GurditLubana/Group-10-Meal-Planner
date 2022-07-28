package comp3350.team10.tests.integration;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import comp3350.team10.business.UserDataOps;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.DataAccess;
import comp3350.team10.persistence.LogDBInterface;
import comp3350.team10.persistence.RecipeDBInterface;
import comp3350.team10.persistence.UserDBInterface;
import comp3350.team10.tests.persistence.DataAccessStub;


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
				DBSelector.start(new DataAccessStub());

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
			LogDBInterface db = DBSelector.getLogDB();
			db.replaceLog(0, new DailyLog().init(currDate, new ArrayList<>(), 0, 0, 0));
			DBSelector.close();
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
			ops.addByKey(0, false);
			assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
			assertEquals("Apple",currLog.getEdibleList().get(0).getName());
			assertEquals("Pear", currLog.getEdibleList().get(1).getName());
			assertEquals("Cracker",currLog.getEdibleList().get(2).getName());
			assertEquals("Apple", currLog.getEdibleList().get(3).getName());
			assertEquals(1626, (int) currLog.getCalorieNet());
			assertEquals(374,(int) currLog.getEdibleCalories());
		}

		@Test
		@DisplayName("We should be able to add the first food item in the db to a DailyLog with an even number of entries")
		void addFoodToEvenNumberedLog() {

			ops = new MealDiaryOps();
			DailyLog currLog = ops.getCurrLog();

			int prevLogSize = currLog.getEdibleList().size();
			ops.addByKey(0, false);
			assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
			assertEquals("Apple", currLog.getEdibleList().get(0).getName());
			assertEquals("Pear",currLog.getEdibleList().get(1).getName());
			assertEquals("Cracker",currLog.getEdibleList().get(2).getName());
			assertEquals("Apple",currLog.getEdibleList().get(3).getName());
			assertEquals(1626, (int) currLog.getCalorieNet());
			assertEquals(374, (int) currLog.getEdibleCalories());

			ops.addByKey(0, false);
			assertEquals(prevLogSize + 2, currLog.getEdibleList().size());
			assertEquals("Apple", currLog.getEdibleList().get(0).getName());
			assertEquals("Pear",currLog.getEdibleList().get(1).getName());
			assertEquals("Cracker",currLog.getEdibleList().get(2).getName());
			assertEquals("Apple",currLog.getEdibleList().get(3).getName());
			assertEquals(1526, (int) currLog.getCalorieNet());
			assertEquals(474, (int) currLog.getEdibleCalories());
		}



		@Test
		@DisplayName("Integration test of MealDiaryOps to persistence those shouldn't pass.")
		public void testInvalidMealDiaryOps()
		{
			DBSelector.close();

			try {
				ops.addByKey(45,false);
				fail("Should throw an exception, cannot add new key when database is closed.");
			}
			catch (Exception e) {
				assertTrue(e instanceof NullPointerException);
			}

			DBSelector.start(new DataAccessStub());
		}
	}



	@Nested
	@DisplayName("Integration testing of Recipe Book Ops to persistence")
	class testRecipeBookOps {

		private RecipeBookOps ops;
		private RecipeDBInterface db;

		@BeforeEach
		void setup() {

			try
			{
				DBSelector.start(new DataAccessStub());
				this.ops = new RecipeBookOps();
				this.db = DBSelector.getRecipeDB();
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

		@AfterEach
		void shutdown() {

			DataAccess dataAccess = DBSelector.getSharedDB();
			dataAccess.removeTestData();
			DBSelector.close();
		}

		@Test
		@DisplayName("Testing getters of RecipeBook Ops")
		void testRecipeBookOpsGetters()
		{
			ArrayList<Edible> opsFoodRecipes = this.ops.getFoodRecipes();
			ArrayList<Edible> opsMealRecipes = this.ops.getMealRecipes();
			ArrayList<Edible> opsDrinkRecipes = this.ops.getDrinkRecipes();

			ArrayList<Edible> dbFoodRecipes = this.db.getFoodRecipes();
			ArrayList<Edible> dbMealRecipes = this.db.getMealRecipes();
			ArrayList<Edible> dbDrinkRecipes = this.db.getDrinkRecipes();

			assertEquals(dbFoodRecipes.size(), opsFoodRecipes.size());
			for(int i = 0; i < dbFoodRecipes.size(); i++){
				assertEquals(dbFoodRecipes.get(i).getDbkey(), opsFoodRecipes.get(i).getDbkey());
			}

			assertEquals(dbMealRecipes.size(), opsMealRecipes.size());
			for(int i = 0; i < dbMealRecipes.size(); i++){
				assertEquals(dbMealRecipes.get(i).getDbkey(), opsMealRecipes.get(i).getDbkey());
			}

			assertEquals(dbDrinkRecipes.size(), opsDrinkRecipes.size());
			for(int i = 0; i < dbDrinkRecipes.size(); i++){
				assertEquals(dbDrinkRecipes.get(i).getDbkey(), opsDrinkRecipes.get(i).getDbkey());
			}
		}

		@Test
		@DisplayName("Tests adding a food edible in meal diary ops")
		void testAddFood() {

			ArrayList<Edible> dbFoodRecipes;
			Edible testEdible;

			this.ops.addFood("Test Pan Cake", "Description", 2, Edible.Unit.g, 350, 400, 74, 89,
					false, true, false, true, false, "photo");

			dbFoodRecipes = this.db.getFoodRecipes();
			testEdible = dbFoodRecipes.get(dbFoodRecipes.size()-1);
			assertEquals("Test Pan Cake", testEdible.getName());
			assertEquals("Description", testEdible.getDescription());
			assertEquals(2, testEdible.getQuantity());
			assertEquals("g", testEdible.getUnit().name());
			assertEquals(350, testEdible.getCalories());
			assertEquals(400, testEdible.getProtein());
			assertEquals(74, testEdible.getCarbs());
			assertEquals(89, testEdible.getFat());
			assertFalse(testEdible.getIsAlcoholic());
			assertTrue(testEdible.getIsSpicy());
			assertFalse(testEdible.getIsVegan());
			assertTrue(testEdible.getIsVegetarian());
			assertFalse(testEdible.getIsGlutenFree());
			assertEquals("photo", testEdible.getPhoto());
		}

		@Test
		@DisplayName("Tests adding a meal recipe ops")
		void testAddMeals() {

			ArrayList<Edible> dbMealRecipes;
			Edible testEdible;

			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			Edible food = new Edible().initDetails(1, "Test name", "description", 1, Edible.Unit.g)
					.initNutrition(1, 1, 1, 1)
					.initCategories(true, false, false, true, false)
					.initMetadata(true, "photo");
			Edible secondFood = new Edible().initDetails(1, "Test name", "description", 1, Edible.Unit.g)
					.initNutrition(1, 1, 1, 1)
					.initCategories(false, false, true, true, true)
					.initMetadata(true, "photo");
			Edible smallDrink = new Drink().initDetails(1, "Test drink", "description", 5, Edible.Unit.g)
					.initNutrition(10, 10, 10, 10)
					.initCategories(false, true, false, true, false)
					.initMetadata(true, "photo");
			Ingredient currIngredient = new Ingredient();

			currIngredient.init(food, 1, Edible.Unit.cups);
			ingredients.add(currIngredient);

			currIngredient.init(secondFood, 1, Edible.Unit.ml);
			ingredients.add(currIngredient);

			currIngredient.init(smallDrink, 1, Edible.Unit.liter);
			ingredients.add(currIngredient);

			this.ops.addMeal("Test meal Pan Cake", "description", 2, Edible.Unit.g, "photo", "description", ingredients);

			dbMealRecipes = this.db.getMealRecipes();
			testEdible = dbMealRecipes.get(dbMealRecipes.size()-1);

			assertEquals("Test meal Pan Cake", testEdible.getName());
			assertEquals("description", testEdible.getDescription());
			assertEquals(2, testEdible.getQuantity());
			assertEquals("g", testEdible.getUnit().name());
			assertEquals(30, testEdible.getCalories());
			assertEquals(30, testEdible.getProtein());
			assertEquals(30, testEdible.getCarbs());
			assertEquals(30, testEdible.getFat());
			assertFalse(testEdible.getIsAlcoholic());
			assertTrue(testEdible.getIsSpicy());
			assertFalse(testEdible.getIsVegan());
			assertTrue(testEdible.getIsVegetarian());
			assertFalse(testEdible.getIsGlutenFree());
			assertTrue(testEdible.getIsCustom());
			assertEquals(testEdible.getPhoto(), "photo");
		}

		@Test
		@DisplayName("Tests adding a drink in recipe ops")
		void testAddPreparedDrinks() {

			ArrayList<Edible> dbDrinkRecipes;
			Edible testEdible;

			Edible currEdible = new Edible().initDetails(5, "Test name", "description", 5, Edible.Unit.g)
					.initNutrition(5, 5, 5, 5)
					.initCategories(true, true, true, true, true)
					.initMetadata(true, "photo");
			DrinkIngredient currIngredient = (DrinkIngredient) new DrinkIngredient().init(currEdible, 5, Edible.Unit.cups);
			ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
			ingredients.add(currIngredient);

			this.ops.addPreparedDrink("Test Banana Smoothie", "Description", 20, Edible.Unit.ml, "photo", "Description", ingredients);

			dbDrinkRecipes = this.db.getDrinkRecipes();
			testEdible = dbDrinkRecipes.get(dbDrinkRecipes.size()-1);

			assertEquals("Test Banana Smoothie", testEdible.getName());
			assertEquals("Description", testEdible.getDescription());
			assertEquals(20, testEdible.getQuantity());
			assertEquals("ml", testEdible.getUnit().name());
			assertEquals(5, testEdible.getCalories());
			assertEquals(5, testEdible.getProtein());
			assertEquals(5, testEdible.getCarbs());
			assertEquals(5, testEdible.getFat());
			assertTrue(testEdible.getIsAlcoholic());
			assertTrue(testEdible.getIsSpicy());
			assertTrue(testEdible.getIsVegan());
			assertTrue(testEdible.getIsVegetarian());
			assertTrue(testEdible.getIsGlutenFree());
			assertTrue(testEdible.getIsCustom());
			assertEquals(testEdible.getPhoto(), "photo");
		}

		@Test
		@DisplayName("Tests adding a simple drink (without ingredients) in recipe ops")
		void testAddSimpleDrinks() {

			ArrayList<Edible> dbDrinkRecipes;
			Edible testEdible;

			this.ops.addSimpleDrink("Test Pepsi", "Description", 250, Edible.Unit.ml, 350, 400, 74, 89,
					false, true, false, true, false, "photo");

			dbDrinkRecipes = this.db.getDrinkRecipes();
			testEdible = dbDrinkRecipes.get(dbDrinkRecipes.size()-1);

			assertEquals("Test Pepsi", testEdible.getName());
			assertEquals("Description", testEdible.getDescription());
			assertEquals(250, testEdible.getQuantity());
			assertEquals("ml", testEdible.getUnit().name());
			assertEquals(350, testEdible.getCalories());
			assertEquals(400, testEdible.getProtein());
			assertEquals(74, testEdible.getCarbs());
			assertEquals(89, testEdible.getFat());
			assertFalse(testEdible.getIsAlcoholic());
			assertTrue(testEdible.getIsSpicy());
			assertFalse(testEdible.getIsVegan());
			assertTrue(testEdible.getIsVegetarian());
			assertFalse(testEdible.getIsGlutenFree());
			assertTrue(testEdible.getIsCustom());
			assertEquals(testEdible.getPhoto(), "photo");
		}
	}




	@Nested
	@DisplayName("Integration testing of Trend Ops to persistence")
	class testTrendOps {

		private TrendsOps ops;
		private LogDBInterface db;


		@BeforeEach
		void setup() {

			try {
				DBSelector.start(new DataAccessStub());
				this.ops = new TrendsOps();
				this.db = DBSelector.getLogDB();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@AfterEach
		void shutdown() {
			DBSelector.close();
		}


		@Test
		@DisplayName("Trends ops data getters work as expected")
		void getDataFrame() {
			ArrayList<DataFrame> opsDataFrames = null;
			ArrayList<Double> dbDataFrame = null;
			ArrayList<Double> opsConsumedCalories = null;
			ArrayList<Double> opsNetCalories = null;
			ArrayList<Double> opsExerciseCalories = null;

			try {
				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.Week);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.Week.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.Week.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.Week.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.Month);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.Month.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.Month.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.Month.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.ThreeMonth);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.ThreeMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.ThreeMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.ThreeMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.SixMonth);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.SixMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.SixMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.SixMonth.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.Year);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.Year.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.Year.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.Year.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

				opsDataFrames = this.ops.getDataFrames(DataFrame.Span.All);
				opsConsumedCalories = opsDataFrames.get(0).getData();
				opsNetCalories = opsDataFrames.get(1).getData();
				opsExerciseCalories = opsDataFrames.get(2).getData();

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.numDays[DataFrame.Span.All.ordinal()]);
				assertEquals(dbDataFrame.size(), opsConsumedCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsConsumedCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.NetCalories, DataFrame.numDays[DataFrame.Span.All.ordinal()]);
				assertEquals(dbDataFrame.size(), opsNetCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsNetCalories.get(i).intValue());
				}

				dbDataFrame = this.db.getDataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.numDays[DataFrame.Span.All.ordinal()]);
				assertEquals(dbDataFrame.size(), opsExerciseCalories.size());

				for(int i = 0; i < dbDataFrame.size(); i++) {
					assertEquals(dbDataFrame.get(i).intValue(), opsExerciseCalories.get(i).intValue());
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@Test
		@DisplayName("instance creation should fail if db not started")
		void testNoDB() {

			DBSelector.close();
			try {
				ops = new TrendsOps();
				fail("Should throw an exception, no database has been started at this point.");
			} catch (Exception e) {
				assertTrue(e instanceof NullPointerException);
			}
		}
	}

	@Nested
	@DisplayName("Integration testing of User Ops to persistence")
	class testUserOps {

		private UserDataOps ops;
		private User dbUser;

		@BeforeEach
		void setup() {

			try {
				DBSelector.start(new DataAccessStub());
				UserDBInterface db = DBSelector.getUserDB();
				this.dbUser = db.getUser(1);
				this.ops = new UserDataOps();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@AfterEach
		void shutdown() {
			restoreDefault();
			DBSelector.close();
		}


		void restoreDefault() {
			UserDBInterface db = DBSelector.getUserDB();
			User dbUser = db.getUser(1);
			dbUser.setHeight(100);
			dbUser.setWeight(200);
			dbUser.setCalorieGoal(2000);
			dbUser.setExerciseGoal(600);
			db.updateUser(dbUser);
		}


		@Test
		@DisplayName("Testing getUser")
		public void testUserGetter() {
			User testUser = this.ops.getUser(1);

			assertEquals(this.dbUser.getUserID(), testUser.getUserID());
			assertEquals(this.dbUser.getCalorieGoal(), testUser.getCalorieGoal());
			assertEquals(this.dbUser.getExerciseGoal(), testUser.getExerciseGoal());
			assertEquals(this.dbUser.getHeight(), testUser.getHeight());
			assertEquals(this.dbUser.getName(), testUser.getName());
			assertEquals(this.dbUser.getWeight(), testUser.getWeight());
		}

		@Test
		@DisplayName("Testing setHeight")
		public void testHeightUpdater() {
			User testUser = this.ops.getUser(1);

			testUser.setHeight(321);
			this.ops.updateUser();

			assertEquals(this.dbUser.getUserID(), testUser.getUserID());
			assertEquals(this.dbUser.getCalorieGoal(), testUser.getCalorieGoal());
			assertEquals(this.dbUser.getExerciseGoal(), testUser.getExerciseGoal());
			assertEquals(321, this.dbUser.getHeight());
			assertEquals(this.dbUser.getName(), testUser.getName());
			assertEquals(this.dbUser.getWeight(), testUser.getWeight());
		}

		@Test
		@DisplayName("Testing setWeight")
		public void testWeightUpdater() {
			User testUser = this.ops.getUser(1);

			testUser.setWeight(155);
			this.ops.updateUser();

			assertEquals(this.dbUser.getUserID(), testUser.getUserID());
			assertEquals(this.dbUser.getCalorieGoal(), testUser.getCalorieGoal());
			assertEquals(this.dbUser.getExerciseGoal(), testUser.getExerciseGoal());
			assertEquals(this.dbUser.getHeight(), testUser.getHeight());
			assertEquals(this.dbUser.getName(), testUser.getName());
			assertEquals(155, this.dbUser.getWeight());
		}

		@Test
		@DisplayName("Testing calorieGoal")
		public void testCalorieGoalUpdater() {
			User testUser = this.ops.getUser(1);

			testUser.setCalorieGoal(1234);
			this.ops.updateUser();

			assertEquals(this.dbUser.getUserID(), testUser.getUserID());
			assertEquals(1234, this.dbUser.getCalorieGoal());
			assertEquals(this.dbUser.getExerciseGoal(), testUser.getExerciseGoal());
			assertEquals(this.dbUser.getHeight(), testUser.getHeight());
			assertEquals(this.dbUser.getName(), testUser.getName());
			assertEquals(this.dbUser.getWeight(), testUser.getWeight());
		}

		@Test
		@DisplayName("Testing exerciseGoal")
		public void testExerciseGoalUpdater() {
			User testUser = this.ops.getUser(1);

			testUser.setExerciseGoal(4321);
			this.ops.updateUser();

			assertEquals(this.dbUser.getUserID(), testUser.getUserID());
			assertEquals(this.dbUser.getCalorieGoal(), testUser.getCalorieGoal());
			assertEquals(4321, this.dbUser.getExerciseGoal());
			assertEquals(this.dbUser.getHeight(), testUser.getHeight());
			assertEquals(this.dbUser.getName(), testUser.getName());
			assertEquals(this.dbUser.getWeight(), testUser.getWeight());
		}


		@Test
		@DisplayName("instance creation should fail if db not started")
		void testNoDB() {

			DBSelector.close();

			try {
				ops = new UserDataOps();
				ops.getUser(1).getHeight();
				fail("Should throw an exception, no database has been started at this point.");
			} catch (Exception e) {
				assertTrue(e instanceof NullPointerException);
			}

			DBSelector.start(new DataAccessStub());
		}
	}
}