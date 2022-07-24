package comp3350.team10.integration;

import static org.junit.Assert.assertNotEquals;
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
import comp3350.team10.business.UserDataOps;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.persistence.LogDBInterface;
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
			ops.addByKey(0, false);
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
			ops.addByKey(0, false);
			assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
			assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
			assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
			assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
			assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
			assertEquals((int) currLog.getCalorieNet(), 1626);
			assertEquals((int) currLog.getEdibleCalories(), 374);

			ops.addByKey(0, false);
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
		private LogDBInterface db;


		@BeforeEach
		void setup() {

			try {
				SharedDB.start();
				SharedDB.startStub();
				ops = new TrendsOps();
				db = SharedDB.getLogDB();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		@AfterEach
		void shutdown() {
			SharedDB.close();
		}


		@Test
		@DisplayName("get one week of data")
		void getWeek() {
			ArrayList<DataFrame> dataFrames = null;
			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.Week);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(7, dataFrames.get(0).size());
		}

		@Test
		@DisplayName("get one month of data")
		void getMonth() {
			ArrayList<DataFrame> dataFrames = null;

			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.Month);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(28, dataFrames.get(0).size());
		}

		@Test
		@DisplayName("get 3 month of data")
		void getThreeMonth() {
			ArrayList<DataFrame> dataFrames = null;
			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.ThreeMonth);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(84, dataFrames.get(0).size());
		}

		@Test
		@DisplayName("get 6 month of data")
		void getSixMonth() {
			ArrayList<DataFrame> dataFrames = null;
			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.SixMonth);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(168, dataFrames.get(0).size());
		}

		@Test
		@DisplayName("get one year of data")
		void getYear() {
			ArrayList<DataFrame> dataFrames = null;
			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.Year);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(336, dataFrames.get(0).size());
		}

		@Test
		@DisplayName("get 2 year of data")
		void getAll() {
			ArrayList<DataFrame> dataFrames = null;
			try {
				dataFrames = ops.getDataFrames(DataFrame.Span.All);
			} catch (Exception e) {
				System.out.println(e);

			}

			assertEquals(DataFrame.DataType.values().length, dataFrames.size());
			assertEquals(672, dataFrames.get(0).size());
		}


		@Test
		@DisplayName("Testing the return type of getDataFrames method")
		void testGetDataFrames() {

			assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(0) instanceof DataFrame);
			assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(1) instanceof DataFrame);
			assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(2) instanceof DataFrame);
			assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(3) instanceof DataFrame);

			assertEquals(ops.getDataFrames(DataFrame.Span.Week).size(), 4);
		}

		@Test
		@DisplayName("Testing getSpan method")
		void testGetSpan() {
			assertEquals(DataFrame.Span.Week, ops.getDataFrames(DataFrame.Span.Week).get(0).getSpan());
			assertEquals(DataFrame.Span.Month, ops.getDataFrames(DataFrame.Span.Month).get(1).getSpan());
			assertEquals(DataFrame.Span.SixMonth, ops.getDataFrames(DataFrame.Span.SixMonth).get(2).getSpan());
			assertEquals(DataFrame.Span.ThreeMonth, ops.getDataFrames(DataFrame.Span.ThreeMonth).get(3).getSpan());
			assertEquals(DataFrame.Span.Year, ops.getDataFrames(DataFrame.Span.Year).get(1).getSpan());
			assertEquals(DataFrame.Span.All, ops.getDataFrames(DataFrame.Span.All).get(0).getSpan());
		}

		@Test
		@DisplayName("Testing getDataType method")
		void testGetDataType() {

			assertEquals(DataFrame.DataType.ConsumedCalories, ops.getDataFrames(DataFrame.Span.Month).get(0).getDataType());
			assertEquals(DataFrame.DataType.NetCalories, ops.getDataFrames(DataFrame.Span.Week).get(1).getDataType());
			assertEquals(DataFrame.DataType.ExerciseCalories, ops.getDataFrames(DataFrame.Span.SixMonth).get(2).getDataType());
			assertEquals(DataFrame.DataType.Weight, ops.getDataFrames(DataFrame.Span.ThreeMonth).get(3).getDataType());

		}

		@Test
		@DisplayName("Testing the value of average for all datatype.")
		void testGetAverage() {
			DataFrame compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.Month);

			compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 28));
			assertEquals(ops.getDataFrames(DataFrame.Span.Month).get(2).getAverage(), compareWith.getAverage());


			compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
			assertEquals(ops.getDataFrames(DataFrame.Span.All).get(0).getAverage(), compareWith.getAverage());


			compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
			assertEquals((ops.getDataFrames(DataFrame.Span.Year)).get(1).getAverage(), compareWith.getAverage());

			compareWith = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.SixMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.Weight, 168));
			assertEquals((ops.getDataFrames(DataFrame.Span.SixMonth)).get(3).getAverage(), compareWith.getAverage());


		}

		@Test
		@DisplayName("Testing the Maximum value for all datatype.")
		void testGetMaxValue() {
			DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
			assertEquals(ops.getDataFrames(DataFrame.Span.All).get(0).getMaxVal(), compareWith.getMaxVal());

			compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
			assertEquals((ops.getDataFrames(DataFrame.Span.Year)).get(1).getMaxVal(), compareWith.getMaxVal());

			compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.Month);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 28));
			assertEquals(ops.getDataFrames(DataFrame.Span.Month).get(2).getMaxVal(), compareWith.getMaxVal());

			compareWith = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.SixMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.Weight, 168));
			assertEquals((ops.getDataFrames(DataFrame.Span.SixMonth)).get(3).getMaxVal(), compareWith.getMaxVal());
		}

		@Test
		@DisplayName("Testing the Progress value for all datatype")
		void testGetProgress() {
			DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
			assertEquals(ops.getDataFrames(DataFrame.Span.All).get(0).getProgress(), compareWith.getProgress());

			compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
			assertEquals((ops.getDataFrames(DataFrame.Span.Year)).get(1).getProgress(), compareWith.getProgress());

			compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
			assertEquals(ops.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getProgress(), compareWith.getProgress());

			compareWith = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.Weight, 7));
			assertEquals((ops.getDataFrames(DataFrame.Span.Week)).get(3).getProgress(), compareWith.getProgress());
		}

		@Test
		@DisplayName("Testing the TrendPoint-A value of all datatype in a given span.")
		void testGetTrendPointA() {
			DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
			assertEquals(ops.getDataFrames(DataFrame.Span.All).get(0).getTrendPointA(), compareWith.getTrendPointA());

			compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
			assertEquals((ops.getDataFrames(DataFrame.Span.Year)).get(1).getTrendPointA(), compareWith.getTrendPointA());

			compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
			assertEquals(ops.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getTrendPointA(), compareWith.getTrendPointA());

			compareWith = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.Weight, 7));
			assertEquals((ops.getDataFrames(DataFrame.Span.Week)).get(3).getTrendPointA(), compareWith.getTrendPointA());
		}

		@Test
		@DisplayName("Testing the TrendPoint-B value of all datatype in a given span.")
		void testGetTrendPointB() {
			DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.SixMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 168));
			assertEquals(ops.getDataFrames(DataFrame.Span.SixMonth).get(0).getTrendPointB(), compareWith.getTrendPointB());

			compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Month);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 28));
			assertEquals((ops.getDataFrames(DataFrame.Span.Month)).get(1).getTrendPointB(), compareWith.getTrendPointB());

			compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
			assertEquals(ops.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getTrendPointB(), compareWith.getTrendPointB());

			compareWith = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
			compareWith.setData(db.getDataFrame(DataFrame.DataType.Weight, 7));
			assertEquals((ops.getDataFrames(DataFrame.Span.Week)).get(3).getTrendPointB(), compareWith.getTrendPointB());
		}


		@Test
		@DisplayName("instance creation should fail if db not started")
		void testNoDB() {

			SharedDB.close();
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


		@BeforeEach
		void setup() {

			try {
				SharedDB.start();
				SharedDB.startStub();
				ops = new UserDataOps();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		@AfterEach
		void shutdown() {
			SharedDB.close();
		}


		@Test
		@DisplayName("Testing getUser")
		public void testUserGetter() {

			assertNotNull(ops.getUser());
			assertEquals(ops.getUser(), SharedDB.getUserDB().getUser());
		}

		@Test
		@DisplayName("Testing updateHeight")
		public void testHeightUpdater() {

			int previousHeight = ops.getUser().getHeight();
			ops.updateHeight(200);
			assertNotEquals(previousHeight, ops.getUser().getHeight());
			assertEquals(200, ops.getUser().getHeight());
		}

		@Test
		@DisplayName("Testing updateWeight")
		public void testWeightUpdater() {
			int previousWeight = ops.getUser().getWeight();
			ops.updateWeight(55);
			assertNotEquals(previousWeight, ops.getUser().getWeight());
			assertEquals(55, ops.getUser().getWeight());
		}

		@Test
		@DisplayName("Testing calorieGoal")
		public void testCalorieGoalUpdater() {

			double previousCalrorieGoal = ops.getUser().getCalorieGoal();
			ops.updateCalorieGoal(260.05);
			assertNotEquals(previousCalrorieGoal, ops.getUser().getCalorieGoal());
			assertEquals(260.05, ops.getUser().getCalorieGoal());
		}

		@Test
		@DisplayName("Testing exerciseGoal")
		public void testExerciseGoalUpdater() {

			double previousExerciseGoalUpdater = ops.getUser().getExerciseGoal();
			ops.updateExerciseGoal(500.99);
			assertNotEquals(previousExerciseGoalUpdater, ops.getUser().getExerciseGoal());
			assertEquals(500.99, ops.getUser().getExerciseGoal());
		}


		@Test
		@DisplayName("instance creation should fail if db not started")
		void testNoDB() {

			SharedDB.close();

			try {
				ops = new UserDataOps();
				ops.getUser().getHeight();
				fail("Should throw an exception, no database has been started at this point.");
			} catch (Exception e) {
				assertTrue(e instanceof NullPointerException);
			}
		}
//		@Test
//		@DisplayName("Testing userID  ")
//		void testUserId()
//		{
//
//		}

	}
}