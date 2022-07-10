package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestEdible {

	@Nested
	@DisplayName("Simple tests")
	class Test_Simple {
		private Edible testEdible;
		private String testString;

		@BeforeEach
		void setUp() {
			testEdible = new Edible();
			testString = "testString";
		}

		@Test
		@DisplayName("Tests an Edible's contents upon new creation")
		void testInitialValues() {
			assertEquals(testEdible.getDbkey(), -1);
			assertNull(testEdible.getName());
			assertNull(testEdible.getDescription());
			assertEquals(testEdible.getQuantity(), -1);
			assertNull(testEdible.getUnit());

			assertEquals(testEdible.getCalories(), 0);
			assertEquals(testEdible.getProtein(), 0);
			assertEquals(testEdible.getCarbs(), 0);
			assertEquals(testEdible.getFat(), 0);

			assertFalse(testEdible.getIsAlcoholic());
			assertFalse(testEdible.getIsSpicy());
			assertFalse(testEdible.getIsVegan());
			assertFalse(testEdible.getIsVegetarian());
			assertFalse(testEdible.getIsGlutenFree());

			assertFalse(testEdible.getIsCustom());
			assertNull(testEdible.getPhoto());
		}

		@Test
		@DisplayName("Tests setting a simple DB key for an edible")
		void testSetDBKey() {
			testEdible.setDBKey(5);
			assertEquals(testEdible.getDbkey(), 5);

			testEdible.setDBKey(10);
			assertEquals(testEdible.getDbkey(), 10);

			testEdible.initDetails(5,"name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 5);

			testEdible.initDetails(10, "name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 10);
		}

		@Test
		@DisplayName("Tests setting a simple name for an edible")
			void testSetName() {
			testEdible.setName(testString);
			assertEquals(testEdible.getName(), testString);

			testEdible.setName("A different name");
			assertEquals(testEdible.getName(), "A different name");

			testEdible.initDetails(5,testString, "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), testString);

			testEdible.initDetails(5, "A different name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), "A different name");
		}

		@Test
		@DisplayName("Tests setting a simple description for an edible")
		void testSetDescription() {
			testEdible.setDescription(testString);
			assertEquals(testEdible.getDescription(), testString);

			testEdible.setDescription("A different description");
			assertEquals(testEdible.getDescription(), "A different description");

			testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), testString);

			testEdible.initDetails(5, "name", "A different description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), "A different description");
		}

		@Test
		@DisplayName("Tests setting a simple quantity for an edible")
		void testSetQuantity() {
			testEdible.setBaseQuantity(5);
			assertEquals(testEdible.getQuantity(), 5);

			testEdible.setBaseQuantity(10);
			assertEquals(testEdible.getQuantity(), 10);

			testEdible.initDetails(5,"name", "description", 5, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 5);

			testEdible.initDetails(10, "name", "description", 10, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 10);
		}

		@Test
		@DisplayName("Tests setting an edibles unit")
		void testSetUnit() {
			testEdible.setBaseUnit(Edible.Unit.cups);
			assertEquals(testEdible.getUnit(), Edible.Unit.cups);

			testEdible.setBaseUnit(Edible.Unit.g);
			assertEquals(testEdible.getUnit(), Edible.Unit.g);

			testEdible.setBaseUnit(Edible.Unit.ml);
			assertEquals(testEdible.getUnit(), Edible.Unit.ml);

			testEdible.setBaseUnit(Edible.Unit.oz);
			assertEquals(testEdible.getUnit(), Edible.Unit.oz);

			testEdible.setBaseUnit(Edible.Unit.liter);
			assertEquals(testEdible.getUnit(), Edible.Unit.liter);

			testEdible.setBaseUnit(Edible.Unit.serving);
			assertEquals(testEdible.getUnit(), Edible.Unit.serving);

			testEdible.setBaseUnit(Edible.Unit.tbsp);
			assertEquals(testEdible.getUnit(), Edible.Unit.tbsp);

			testEdible.setBaseUnit(Edible.Unit.tsp);
			assertEquals(testEdible.getUnit(), Edible.Unit.tsp);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
			assertEquals(testEdible.getUnit(), Edible.Unit.cups);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.g);
			assertEquals(testEdible.getUnit(), Edible.Unit.g);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.ml);
			assertEquals(testEdible.getUnit(), Edible.Unit.ml);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.oz);
			assertEquals(testEdible.getUnit(), Edible.Unit.oz);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.liter);
			assertEquals(testEdible.getUnit(), Edible.Unit.liter);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.serving);
			assertEquals(testEdible.getUnit(), Edible.Unit.serving);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.tbsp);
			assertEquals(testEdible.getUnit(), Edible.Unit.tbsp);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.tsp);
			assertEquals(testEdible.getUnit(), Edible.Unit.tsp);
		}

		@Test
		@DisplayName("Tests setting a simple calorie count for an edible")
		void testSetCalories() {
			testEdible.setCalories(5);
			assertEquals(testEdible.getCalories(), 5);

			testEdible.setCalories(10);
			assertEquals(testEdible.getCalories(), 10);

			testEdible.initNutrition(5,1, 1, 1);
			assertEquals(testEdible.getCalories(), 5);

			testEdible.initNutrition(10,1, 1, 1);
			assertEquals(testEdible.getCalories(), 10);
		}

		@Test
		@DisplayName("Tests setting a simple protein count for an edible")
		void testSetProtein() {
			testEdible.setProtein(5);
			assertEquals(testEdible.getProtein(), 5);

			testEdible.setProtein(10);
			assertEquals(testEdible.getProtein(), 10);

			testEdible.initNutrition(1,5, 1, 1);
			assertEquals(testEdible.getProtein(), 5);

			testEdible.initNutrition(1,10, 1, 1);
			assertEquals(testEdible.getProtein(), 10);
		}

		@Test
		@DisplayName("Tests setting a simple carb count for an edible")
		void testSetCarbs() {
			testEdible.setCarbs(5);
			assertEquals(testEdible.getCarbs(), 5);

			testEdible.setCarbs(10);
			assertEquals(testEdible.getCarbs(), 10);

			testEdible.initNutrition(1,1, 5, 1);
			assertEquals(testEdible.getCarbs(), 5);

			testEdible.initNutrition(1,1, 10, 1);
			assertEquals(testEdible.getCarbs(), 10);
		}

		@Test
		@DisplayName("Tests setting a simple fat count for an edible")
		void testSetFat() {
			testEdible.setFat(5);
			assertEquals(testEdible.getFat(), 5);

			testEdible.setFat(10);
			assertEquals(testEdible.getFat(), 10);

			testEdible.initNutrition(1,1, 1, 5);
			assertEquals(testEdible.getFat(), 5);

			testEdible.initNutrition(1,1, 1, 10);
			assertEquals(testEdible.getFat(), 10);
		}

		@Test
		@DisplayName("Tests setting an edibles alcoholic flag")
		void testSetIsAlcoholic() {
			testEdible.setAlcoholic(false);
			assertFalse(testEdible.getIsAlcoholic());

			testEdible.setAlcoholic(true);
			assertTrue(testEdible.getIsAlcoholic());

			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsAlcoholic());

			testEdible.initCategories(true, false, false, false, false);
			assertTrue(testEdible.getIsAlcoholic());
		}

		@Test
		@DisplayName("Tests setting an edibles spicy flag")
		void testSetIsSpicy() {
			testEdible.setSpicy(false);
			assertFalse(testEdible.getIsSpicy());

			testEdible.setSpicy(true);
			assertTrue(testEdible.getIsSpicy());

			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsSpicy());

			testEdible.initCategories(false, true, false, false, false);
			assertTrue(testEdible.getIsSpicy());
		}

		@Test
		@DisplayName("Tests setting an edibles vegan flag")
		void testSetIsVegan() {
			testEdible.setVegan(false);
			assertFalse(testEdible.getIsVegan());

			testEdible.setVegan(true);
			assertTrue(testEdible.getIsVegan());

			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsVegan());

			testEdible.initCategories(false, false, true, false, false);
			assertTrue(testEdible.getIsVegan());
		}

		@Test
		@DisplayName("Tests setting an edibles vegetarian flag")
		void testSetVegetarian() {
			testEdible.setVegetarian(false);
			assertFalse(testEdible.getIsVegetarian());

			testEdible.setVegetarian(true);
			assertTrue(testEdible.getIsVegetarian());

			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsVegetarian());

			testEdible.initCategories(false, false, false, true, false);
			assertTrue(testEdible.getIsVegetarian());
		}

		@Test
		@DisplayName("Tests setting an edibles gluten free flag")
		void testSetGlutenFree() {
			testEdible.setGlutenFree(false);
			assertFalse(testEdible.getIsGlutenFree());

			testEdible.setGlutenFree(true);
			assertTrue(testEdible.getIsGlutenFree());

			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsGlutenFree());

			testEdible.initCategories(false, false, false, false, true);
			assertTrue(testEdible.getIsGlutenFree());
		}

		@Test
		@DisplayName("Tests setting an edibles custom flag")
		void testSetIsCustom() {
			testEdible.setCustom(false);
			assertFalse(testEdible.getIsCustom());

			testEdible.setCustom(true);
			assertTrue(testEdible.getIsCustom());

			testEdible.initMetadata(false, "photo");
			assertFalse(testEdible.getIsCustom());

			testEdible.initMetadata(true, "photo");
			assertTrue(testEdible.getIsCustom());
		}

		@Test
		@DisplayName("Tests setting a simple photo for an edible")
		void testSetPhoto() {
			testEdible.setPhoto(testString);
			assertEquals(testEdible.getPhoto(), testString);

			testEdible.setPhoto("A different photo");
			assertEquals(testEdible.getPhoto(), "A different photo");

			testEdible.initMetadata(false, testString);
			assertEquals(testEdible.getPhoto(), testString);

			testEdible.initMetadata(false, "A different photo");
			assertEquals(testEdible.getPhoto(), "A different photo");
		}
	}

	@Nested
	@DisplayName("Complex tests")
	class Test_Complex {
		private Edible testEdible;
		private String testString;
		private String numberTestString;

		@BeforeEach
		void setUp() {
			testEdible = new Edible();
			numberTestString = "666";
			testString = "String test_instruction=\"very long instructions sdakjlfh&sl@$jfkhldsakjhfiuweasdhyfuiklewahearewrw\" +\n" +
					"                        \"adsjfkghbewakjdshfljkaewhdflkaewj\\njewifhewl\\r isdfauhjljkewf\\n\\\\wieosuhjrfiol;ewk\" +\n" +
					"                        \"53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#\";";
		}

		@Test
		@DisplayName("Tests setting a complex DB key for an edible")
		void testSetDBKey() {
			testEdible.setDBKey(500);
			assertEquals(testEdible.getDbkey(), 500);

			testEdible.setDBKey(1000);
			assertEquals(testEdible.getDbkey(), 1000);

			testEdible.initDetails(500,"name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 500);

			testEdible.initDetails(1000, "name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex name for an edible")
		void testSetName() {
			testEdible.setName(testString);
			assertEquals(testEdible.getName(), testString);

			testEdible.setName(numberTestString);
			assertEquals(testEdible.getName(), numberTestString);

			testEdible.initDetails(5,testString, "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), testString);

			testEdible.initDetails(5, numberTestString, "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), numberTestString);
		}

		@Test
		@DisplayName("Tests setting a complex description for an edible")
		void testSetDescription() {
			testEdible.setDescription(testString);
			assertEquals(testEdible.getDescription(), testString);

			testEdible.setDescription(numberTestString);
			assertEquals(testEdible.getDescription(), numberTestString);

			testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), testString);

			testEdible.initDetails(5, "name", numberTestString, 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), numberTestString);
		}

		@Test
		@DisplayName("Tests setting a complex quantity for an edible")
		void testSetQuantity() {
			testEdible.setBaseQuantity(500);
			assertEquals(testEdible.getQuantity(), 500);

			testEdible.setBaseQuantity(1000);
			assertEquals(testEdible.getQuantity(), 1000);

			testEdible.initDetails(5,"name", "description", 500, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 500);

			testEdible.initDetails(10, "name", "description", 1000, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex calorie count for an edible")
		void testSetCalories() {
			testEdible.setCalories(500);
			assertEquals(testEdible.getCalories(), 500);

			testEdible.setCalories(1000);
			assertEquals(testEdible.getCalories(), 1000);

			testEdible.initNutrition(500,1, 1, 1);
			assertEquals(testEdible.getCalories(), 500);

			testEdible.initNutrition(1000,1, 1, 1);
			assertEquals(testEdible.getCalories(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex protein count for an edible")
		void testSetProtein() {
			testEdible.setProtein(500);
			assertEquals(testEdible.getProtein(), 500);

			testEdible.setProtein(1000);
			assertEquals(testEdible.getProtein(), 1000);

			testEdible.initNutrition(1,500, 1, 1);
			assertEquals(testEdible.getProtein(), 500);

			testEdible.initNutrition(1,1000, 1, 1);
			assertEquals(testEdible.getProtein(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex carb count for an edible")
		void testSetCarbs() {
			testEdible.setCarbs(500);
			assertEquals(testEdible.getCarbs(), 500);

			testEdible.setCarbs(1000);
			assertEquals(testEdible.getCarbs(), 1000);

			testEdible.initNutrition(1,1, 500, 1);
			assertEquals(testEdible.getCarbs(), 500);

			testEdible.initNutrition(1,1, 1000, 1);
			assertEquals(testEdible.getCarbs(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex fat count for an edible")
		void testSetFat() {
			testEdible.setFat(500);
			assertEquals(testEdible.getFat(), 500);

			testEdible.setFat(1000);
			assertEquals(testEdible.getFat(), 1000);

			testEdible.initNutrition(1,1, 1, 500);
			assertEquals(testEdible.getFat(), 500);

			testEdible.initNutrition(1,1, 1, 1000);
			assertEquals(testEdible.getFat(), 1000);
		}

		@Test
		@DisplayName("Tests setting a complex photo for an edible")
		void testSetPhoto() {
			testEdible.setPhoto(testString);
			assertEquals(testEdible.getPhoto(), testString);

			testEdible.setPhoto(numberTestString);
			assertEquals(testEdible.getPhoto(), numberTestString);

			testEdible.initMetadata(false, testString);
			assertEquals(testEdible.getPhoto(), testString);

			testEdible.initMetadata(false, numberTestString);
			assertEquals(testEdible.getPhoto(), numberTestString);
		}
	}

	@Nested
	@DisplayName("Empty tests")
	class Test_Empty {
		private Edible testEdible;
		private String emptyTestString;

		@BeforeEach
		void setUp() {
			testEdible = new Edible();
			emptyTestString = "";
		}

		@Test
		@DisplayName("Tests setting a empty or null name for an edible")
		void testSetName() {
			try {
				testEdible.setName(null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.setName(emptyTestString);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, null, "description", 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try{
				testEdible.initDetails(1, emptyTestString, "description", 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		@DisplayName("Tests setting a empty or null description for an edible")
		void testSetDescription() {
			try {
				testEdible.setDescription(null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			testEdible.setDescription(emptyTestString);
			assertEquals(testEdible.getDescription(), emptyTestString);

			try {
				testEdible.initDetails(1, "name", null, 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			testEdible.initDetails(1, "name", emptyTestString, 5, Edible.Unit.cups);
			testEdible.setDescription(emptyTestString);
			assertEquals(testEdible.getDescription(), emptyTestString);
		}

		@Test
		@DisplayName("Tests setting a null unit for an edible")
		void testSetUnit() {
			try {
				testEdible.setBaseUnit(null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, "name", "description", 5, null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		@DisplayName("Tests setting a empty or null photo for an edible")
		void testSetPhoto() {
			try {
				testEdible.setPhoto(null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.setPhoto(emptyTestString);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initMetadata(false, null);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try{
				testEdible.initMetadata(false, emptyTestString);
				fail("Should throw IO exception");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}
	}

	@Nested
	@DisplayName("Edge case tests")
	class Test_EdgeCases {
		private Edible testEdible;
		private String testString;
		private String largeTestString;

		@BeforeEach
		void setUp() {
			testEdible = new Edible();
			testString = "testString";
			largeTestString= "";

			for(int i = 0; i < 9999; i++) {
				largeTestString = largeTestString + "a";
			}
		}

		@Test
		@DisplayName("Tests setting the smallest DB key for an edible")
		void testSetDBKey() {
			testEdible.setDBKey(0);
			assertEquals(testEdible.getDbkey(), 0);

			testEdible.initDetails(0,"name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 0);
		}

		@Test
		@DisplayName("Tests setting the same DB key that an edible already has")
		void testSetDuplicateDBKey() {
			testEdible.setDBKey(5);
			testEdible.setDBKey(5);
			assertEquals(testEdible.getDbkey(), 5);

			testEdible.initDetails(5,"name", "description", 1, Edible.Unit.g);
			testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getDbkey(), 5);
		}

		@Test
		@DisplayName("Tests setting the longest name for an edible")
		void testSetName() {
			testEdible.setName(largeTestString);
			assertEquals(testEdible.getName(), largeTestString);

			testEdible.initDetails(5, largeTestString, "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), largeTestString);
		}

		@Test
		@DisplayName("Tests setting the same name an edible already has")
		void testSetDuplicateName() {
			testEdible.setName(testString);
			testEdible.setName(testString);
			assertEquals(testEdible.getName(), testString);

			testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
			testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getName(), testString);
		}

		@Test
		@DisplayName("Tests setting the longest description for an edible")
		void testSetDescription() {
			testEdible.setDescription(largeTestString);
			assertEquals(testEdible.getDescription(), largeTestString);

			testEdible.initDetails(5, "name", largeTestString, 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), largeTestString);
		}

		@Test
		@DisplayName("Tests setting the same description an edible already has")
		void testSetDuplicateDescription() {
			testEdible.setDescription(testString);
			testEdible.setDescription(testString);
			assertEquals(testEdible.getDescription(), testString);

			testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
			testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
			assertEquals(testEdible.getDescription(), testString);
		}

		@Test
		@DisplayName("Tests setting the largest and smallest quantities for an edible")
		void testSetQuantity() {
			testEdible.setBaseQuantity(1);
			assertEquals(testEdible.getQuantity(), 1);

			testEdible.setBaseQuantity(9999);
			assertEquals(testEdible.getQuantity(), 9999);

			testEdible.initDetails(1,"name", "description", 1, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 1);

			testEdible.initDetails(1, "name", "description", 9999, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 9999);
		}

		@Test
		@DisplayName("Tests setting the same quantity an edible already has")
		void testSetDuplicateQuantity() {
			testEdible.setBaseQuantity(5);
			testEdible.setBaseQuantity(5);
			assertEquals(testEdible.getQuantity(), 5);

			testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
			testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
			assertEquals(testEdible.getQuantity(), 5);
		}

		@Test
		@DisplayName("Tests setting the same unit an edibles already has")
		void testSetUnit() {
			testEdible.setBaseUnit(Edible.Unit.cups);
			testEdible.setBaseUnit(Edible.Unit.cups);
			assertEquals(testEdible.getUnit(), Edible.Unit.cups);

			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
			testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
			assertEquals(testEdible.getUnit(), Edible.Unit.cups);
		}

		@Test
		@DisplayName("Tests setting the largest and smallest calorie counts for an edible")
		void testSetCalories() {
			testEdible.setCalories(0);
			assertEquals(testEdible.getCalories(), 0);

			testEdible.setCalories(9999);
			assertEquals(testEdible.getCalories(), 9999);

			testEdible.initNutrition(0,1, 1, 1);
			assertEquals(testEdible.getCalories(), 0);

			testEdible.initNutrition(9999,1, 1, 1);
			assertEquals(testEdible.getCalories(), 9999);
		}

		@Test
		@DisplayName("Tests setting the same calorie count an edible already has")
		void testSetDuplicateCalories() {
			testEdible.setCalories(5);
			testEdible.setCalories(5);
			assertEquals(testEdible.getCalories(), 5);

			testEdible.initNutrition(5,1, 1, 1);
			testEdible.initNutrition(5,1, 1, 1);
			assertEquals(testEdible.getCalories(), 5);
		}

		@Test
		@DisplayName("Tests setting the largest and smallest protein counts for an edible")
		void testSetProtein() {
			testEdible.setProtein(0);
			assertEquals(testEdible.getProtein(), 0);

			testEdible.setProtein(9999);
			assertEquals(testEdible.getProtein(), 9999);

			testEdible.initNutrition(1,0, 1, 1);
			assertEquals(testEdible.getProtein(), 0);

			testEdible.initNutrition(1,9999, 1, 1);
			assertEquals(testEdible.getProtein(), 9999);
		}

		@Test
		@DisplayName("Tests setting the same protein count an edible already has")
		void testSetDuplicateProtein() {
			testEdible.setProtein(5);
			testEdible.setProtein(5);
			assertEquals(testEdible.getProtein(), 5);

			testEdible.initNutrition(1,5, 1, 1);
			testEdible.initNutrition(1,5, 1, 1);
			assertEquals(testEdible.getProtein(), 5);
		}

		@Test
		@DisplayName("Tests setting the largest and smallest carb counts for an edible")
		void testSetCarbs() {
			testEdible.setCarbs(0);
			assertEquals(testEdible.getCarbs(), 0);

			testEdible.setCarbs(9999);
			assertEquals(testEdible.getCarbs(), 9999);

			testEdible.initNutrition(1,1, 0, 1);
			assertEquals(testEdible.getCarbs(), 0);

			testEdible.initNutrition(1,1, 9999, 1);
			assertEquals(testEdible.getCarbs(), 9999);
		}

		@Test
		@DisplayName("Tests setting the same carbs count an edible already has")
		void testSetDuplicateCarbs() {
			testEdible.setCarbs(5);
			testEdible.setCarbs(5);
			assertEquals(testEdible.getCarbs(), 5);

			testEdible.initNutrition(1,1, 5, 1);
			testEdible.initNutrition(1,1, 5, 1);
			assertEquals(testEdible.getCarbs(), 5);
		}

		@Test
		@DisplayName("Tests setting the largest and smallest fat count for an edible")
		void testSetFat() {
			testEdible.setFat(0);
			assertEquals(testEdible.getFat(), 0);

			testEdible.setFat(9999);
			assertEquals(testEdible.getFat(), 9999);

			testEdible.initNutrition(1,1, 1, 0);
			assertEquals(testEdible.getFat(), 0);

			testEdible.initNutrition(1,1, 1, 9999);
			assertEquals(testEdible.getFat(), 9999);
		}

		@Test
		@DisplayName("Tests setting the same fat count an edible already has")
		void testSetDuplicateFat() {
			testEdible.setFat(5);
			testEdible.setFat(5);
			assertEquals(testEdible.getFat(), 5);

			testEdible.initNutrition(1,1, 1, 5);
			testEdible.initNutrition(1,1, 1, 5);
			assertEquals(testEdible.getFat(), 5);
		}

		@Test
		@DisplayName("Tests setting the same alcoholic flag an edible already has")
		void testSetIsAlcoholic() {
			testEdible.setAlcoholic(false);
			testEdible.setAlcoholic(false);
			assertFalse(testEdible.getIsAlcoholic());

			testEdible.setAlcoholic(true);
			testEdible.setAlcoholic(true);
			assertTrue(testEdible.getIsAlcoholic());

			testEdible.initCategories(false, false, false, false, false);
			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsAlcoholic());

			testEdible.initCategories(true, false, false, false, false);
			testEdible.initCategories(true, false, false, false, false);
			assertTrue(testEdible.getIsAlcoholic());
		}

		@Test
		@DisplayName("Tests setting the same spicy flag an edible already has")
		void testSetIsSpicy() {
			testEdible.setSpicy(false);
			testEdible.setSpicy(false);
			assertFalse(testEdible.getIsSpicy());

			testEdible.setSpicy(true);
			testEdible.setSpicy(true);
			assertTrue(testEdible.getIsSpicy());

			testEdible.initCategories(false, false, false, false, false);
			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsSpicy());

			testEdible.initCategories(false, true, false, false, false);
			testEdible.initCategories(false, true, false, false, false);
			assertTrue(testEdible.getIsSpicy());
		}

		@Test
		@DisplayName("Tests setting the same vegan flag an edible already has")
		void testSetIsVegan() {
			testEdible.setVegan(false);
			testEdible.setVegan(false);
			assertFalse(testEdible.getIsVegan());

			testEdible.setVegan(true);
			testEdible.setVegan(true);
			assertTrue(testEdible.getIsVegan());

			testEdible.initCategories(false, false, false, false, false);
			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsVegan());

			testEdible.initCategories(false, false, true, false, false);
			testEdible.initCategories(false, false, true, false, false);
			assertTrue(testEdible.getIsVegan());
		}

		@Test
		@DisplayName("Tests setting the same vegetarian flag an edible already has")
		void testSetVegetarian() {
			testEdible.setVegetarian(false);
			testEdible.setVegetarian(false);
			assertFalse(testEdible.getIsVegetarian());

			testEdible.setVegetarian(true);
			testEdible.setVegetarian(true);
			assertTrue(testEdible.getIsVegetarian());

			testEdible.initCategories(false, false, false, false, false);
			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsVegetarian());

			testEdible.initCategories(false, false, false, true, false);
			testEdible.initCategories(false, false, false, true, false);
			assertTrue(testEdible.getIsVegetarian());
		}

		@Test
		@DisplayName("Tests setting the same gluten free flag an edible already has")
		void testSetGlutenFree() {
			testEdible.setGlutenFree(false);
			testEdible.setGlutenFree(false);
			assertFalse(testEdible.getIsGlutenFree());

			testEdible.setGlutenFree(true);
			testEdible.setGlutenFree(true);
			assertTrue(testEdible.getIsGlutenFree());

			testEdible.initCategories(false, false, false, false, false);
			testEdible.initCategories(false, false, false, false, false);
			assertFalse(testEdible.getIsGlutenFree());

			testEdible.initCategories(false, false, false, false, true);
			testEdible.initCategories(false, false, false, false, true);
			assertTrue(testEdible.getIsGlutenFree());
		}

		@Test
		@DisplayName("Tests setting the same is custom flag an edible already has")
		void testSetIsCustom() {
			testEdible.setCustom(false);
			testEdible.setCustom(false);
			assertFalse(testEdible.getIsCustom());

			testEdible.setCustom(true);
			testEdible.setCustom(true);
			assertTrue(testEdible.getIsCustom());

			testEdible.initMetadata(false, "photo");
			testEdible.initMetadata(false, "photo");
			assertFalse(testEdible.getIsCustom());

			testEdible.initMetadata(true, "photo");
			testEdible.initMetadata(true, "photo");
			assertTrue(testEdible.getIsCustom());
		}

		@Test
		@DisplayName("Tests setting the longest photo for an edible")
		void testSetPhoto() {
			testEdible.setPhoto(largeTestString);
			assertEquals(testEdible.getPhoto(), largeTestString);

			testEdible.initMetadata(false, largeTestString);
			assertEquals(testEdible.getPhoto(), largeTestString);
		}

		@Test
		@DisplayName("Tests setting the same photo an edible already has")
		void testSetDuplicatePhoto() {
			testEdible.setPhoto(testString);
			testEdible.setPhoto(testString);
			assertEquals(testEdible.getPhoto(), testString);

			testEdible.initMetadata(false, testString);
			testEdible.initMetadata(false, testString);
			assertEquals(testEdible.getPhoto(), testString);
		}
	}

	@Nested
	@DisplayName("Invalid tests")
	class Test_Invalid {
		private Edible testEdible;
		private String longTestString;

		@BeforeEach
		void setUp() {
			testEdible = new Edible();
			longTestString = "";

			for(int i = 0; i < 10000; i++) {
				longTestString = longTestString + "a";
			}
		}

		@Test
		void testSetDBkey() {
			try {
				testEdible.setDBKey(-1);
				fail("Should throw an exception, id is less than 0");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(-1, "name", "description", 5, Edible.Unit.cups);
				fail("Should throw an exception, id is less than 0");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		void testSetName() {
			try {
				testEdible.setName(longTestString);
				fail("Should throw an exception, this name is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, longTestString, "description", 5, Edible.Unit.cups);
				fail("Should throw an exception, this name is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		void testSetDescription() {
			try {
				testEdible.setDescription(longTestString);
				fail("Should throw an exception, this name is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, "name", longTestString, 5, Edible.Unit.cups);
				fail("Should throw an exception, this name is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		void testSetQuantity() {
			try {
				testEdible.setBaseQuantity(0);
				fail("Should throw an exception, this quantity is too low");
			} catch (Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.setBaseQuantity(10000);
				fail("Should throw an exception, this quantity is too big");
			} catch (Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, "name", "description", 0, Edible.Unit.cups);
				fail("Should throw an exception, this quantity is too low");
			} catch (Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initDetails(1, "name", "description", 10000, Edible.Unit.cups);
				fail("Should throw an exception, this quantity is too big");
			} catch (Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}

		@Test
		void testSetPhoto() {
			try {
				testEdible.setPhoto(longTestString);
				fail("Should throw an exception, this photo is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}

			try {
				testEdible.initMetadata(false, longTestString);
				fail("Should throw an exception, this photo is too long");
			}
			catch(Exception e) {
				assertTrue(e instanceof IllegalArgumentException);
			}
		}
	}
}