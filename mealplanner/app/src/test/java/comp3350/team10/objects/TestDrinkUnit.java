 package comp3350.team10.objects;

 import comp3350.team10.objects.*;
 import comp3350.team10.objects.ListItem.FragmentType;
 import comp3350.team10.objects.Edible.Unit;
 import comp3350.team10.objects.*;

 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;
 import org.junit.platform.suite.api.SelectClasses;

 import java.util.ArrayList;


 public class TestDrinkUnit {

     void testNutrition(Edible food, int caloriesExpect, int proteinExpect, int carbsExpect, int fatExpect) {
         assertEquals(caloriesExpect, food.getCalories());
         assertEquals(proteinExpect, food.getProtein());
         assertEquals(carbsExpect, food.getCarbs());
         assertEquals(fatExpect, food.getFat());
     }

     void testCategories(Edible food, boolean alcoholicExpect, boolean spicyExpect, boolean veganExpect, boolean vegetarianExpect, boolean glutenFreeExpect) {
         assertEquals(alcoholicExpect, food.getIsAlcoholic());
         assertEquals(spicyExpect, food.getIsSpicy());
         assertEquals(veganExpect, food.getIsVegan());
         assertEquals(vegetarianExpect, food.getIsVegetarian());
         assertEquals(glutenFreeExpect, food.getIsGlutenFree());
     }
     @Nested
 	@DisplayName("Simple tests")
 	class Test_Simple {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Food testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Food();
             testDrinkIngredient = new DrinkIngredient();
             testIngredients = new ArrayList<DrinkIngredient>();

         }

         @Test
         void test_setInstructions(){
             try {
                 testDrink.setInstructions("simple instructions");
                 assertNotNull(testDrink.getInstructions());
                 assertEquals("simple instructions",testDrink.getInstructions());
             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }

         }

         @Test
         void test_setIngredient()
         {
             try {
                 testDrink.initNutrition(15,35,45,10);
                 testDrink.initCategories(true,false,false,false,false);
                 testNutrition(testDrink,15,35,45,10);
                 testCategories(testDrink,true,false,false,false,false);

                 testFood.initNutrition(10,10,10,10);
                 testFood.initCategories(false,false,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testDrink.setIngredients(testIngredients);

             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }
             testNutrition(testDrink,10,10,10,10);
             testCategories(testDrink,false,false,false,false,true);
         }


     }

     @Nested
     @DisplayName("Complex tests")
     class Test_Complex {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Food testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Food();
             testDrinkIngredient = new DrinkIngredient();
             testIngredients = new ArrayList<DrinkIngredient>();

         }

         @Test
         void test_setInstructions(){
             try {
                 String test_instruction="very long instructions sdakjlfhadsljfkhldsakjhfiuweasdhyfuiklewahearewrw" +
                         "adsjfkghbewakjdshfljkaewhdflkaewj\njewifhewl\r isdfauhjljkewf\n\\wieosuhjrfiol;ewk" +
                         "53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#";
                 testDrink.setInstructions(test_instruction);
                 assertNotNull(testDrink.getInstructions());
                 assertEquals(test_instruction,testDrink.getInstructions());
             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }

         }

         @Test
         void test_setIngredient()
         {
             try {
                 testDrink.initNutrition(15,35,45,10);
                 testDrink.initCategories(true,false,false,false,false);
                 testNutrition(testDrink,15,35,45,10);
                 testCategories(testDrink,true,false,false,false,false);
                //set 4 ingredient
                 testFood.initNutrition(10,10,10,10);
                 testFood.initCategories(false,false,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);

                 testFood.initNutrition(20,20,20,20);
                 testFood.initCategories(false,false,false,true,true);
                 testDrinkIngredient.setIngredient(testFood);

                 testFood.initNutrition(30,30,30,30);
                 testFood.initCategories(false,false,true,false,true);
                 testDrinkIngredient.setIngredient(testFood);

                 testFood.initNutrition(40,40,40,40);
                 testFood.initCategories(false,true,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 //set 4 ingredient

                 testIngredients.add(testDrinkIngredient);

                 testDrink.setIngredients(testIngredients);

             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }
             testNutrition(testDrink,100,100,100,100);
             testCategories(testDrink,false,true,true,true,true);
         }


     }

 }
//
// 	@Nested
// 	@DisplayName("Simple tests")
// 	class Test_Simple {
// 		private Drink testDrink;
//
// 		@BeforeEach
// 		void setUp() {
// 			testDrink = new Drink();
// 		}
//
// 		@Test
// 		void testDiffFrags() {
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients",ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryModify, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.recipe, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.initDetails("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.cups, 5, 1));
// 		}
//
// 		@Test
// 		void testDiffUnits() {
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryModify, Edible.Unit.oz, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryAdd, Edible.Unit.g, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.recipe, Edible.Unit.serving, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.tbsp, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.tsp, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.ml, 5, 1));
// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.liter, 5, 1));
// 		}
//
// 		@Test
// 		void testDefaultVals() {
// 			assertNull(testDrink.getName());
// 			assertEquals(-1, testDrink.getIconPath());
// 			assertNull(testDrink.getFragmentType());
// 			assertNull(testDrink.getBaseUnit());
// 			assertEquals(-1, testDrink.getQuantity());
// 			assertEquals(-1, testDrink.getDbkey());
// 			assertNull(testDrink.getInstructions());
// 			assertEquals(0, testDrink.getCalories());
// 			assertNull(testDrink.getIngredients());
// 		}
//
// 		@Test
// 		void testChangeInstructions() {
// 			assertTrue(testDrink.changeInstructions("you"));
// 			assertTrue(testDrink.changeInstructions("you should"));
// 		}
//
// 		@Test
// 		void testSetCaloies() {
// 			assertTrue(testDrink.setCalories(100));
// 			assertEquals(testDrink.getCalories(), 100);
// 		}
//
// 		@Test
// 		void testModCalories() {
// 			assertTrue(testDrink.modifyCalories(100));
// 			assertEquals(testDrink.getCalories(), 100);
// 			assertTrue(testDrink.modifyCalories(-5));
// 			assertEquals(testDrink.getCalories(), 95);
// 		}
//
// 		@Test
// 		void testSetUnit() {
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.cups));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.oz));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.g));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.serving));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.tbsp));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.tsp));
// 			assertTrue(testDrink.setBaseUnit(Edible.Unit.ml));
// 			assertTrue(testDrink.setBaseUnit(Unit.liter));
// 		}
//
// 		@Test
// 		void testSetDBKey() {
// 			assertTrue(testDrink.setDbkey(1));
// 			assertTrue(testDrink.setDbkey(10));
// 		}
//
// 		@Test
// 		void testSetFragType() {
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.diaryModify));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.diaryAdd));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.recipe));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.cardSelection));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.noType));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.cardSelection));
// 			assertTrue(testDrink.setFragmentType(ListItem.FragmentType.noType));
// 		}
//
// 		@Test
// 		void testSetIconPath() {
// 			assertTrue(testDrink.setIconPath(5));
// 			assertTrue(testDrink.setIconPath(15));
// 		}
//
// 		@Test
// 		void testSetIngredients() {
// 			assertTrue(testDrink.setIngredients("you"));
// 			assertTrue(testDrink.setIngredients("youshould"));
// 		}
//
// 		@Test
// 		void testSetName() {
// 			assertTrue(testDrink.setName("name"));
// 			assertTrue(testDrink.setName("diffName"));
// 		}
//
// 		@Test
// 		void testSetQuantity() {
// 			assertTrue(testDrink.setQuantity(100));
// 			assertEquals(testDrink.getQuantity(), 100);
// 			assertTrue(testDrink.setQuantity(300));
// 			assertEquals(testDrink.getQuantity(), 300);
// 		}
// 	}
//
//// 	@Nested
//// 	@DisplayName("Complex tests")
//// 	class Test_Complex {
//// 		private Drink testDrink;
////
//// 		@BeforeEach
//// 		void setUp() {
//// 			testDrink = new Drink();
//// 		}
////
//// 		@Test
//// 		void testComplexDrinkCreations() {
//// 			assertTrue(testDrink.init("very long drink name", 10, 400, "instructio\nns", "in\n\ngredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 504, 700));
//// 			assertTrue(testDrink.init("even longer longggg\ng \ndrink name", 1, 1, "iions", "idients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 500, 100));
//// 			assertTrue(testDrink.init("12345555", 10, 1, "12344444", "43222222", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 580, 900));
//// 			assertTrue(testDrink.init("#$%!!!+=[][][][[", 10, 1, "#$%!!!+=[][][][[", "\"\"|\"", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 50, 4));
//// 			assertTrue(testDrink.init(" ", 10, 1, " ", "  $ ", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 150, 14));
//// 			assertTrue(testDrink.init("  $ ", 10, 1, "  $ ", " ", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 590, 666));
//// 		}
////
//// 		@Test
//// 		void testSetIconPaths() {
//// 			assertTrue(testDrink.changeInstructions("I follow rivers  baby \ngood song"));
//// 			assertTrue(testDrink.changeInstructions("I follow \n\nrivers baby \n"));
//// 		}
////
//// 		@Test
//// 		void testModCalories() {
//// 			assertTrue(testDrink.modifyCalories(600));
//// 			assertEquals(testDrink.getCalories(), 600);
//// 			assertTrue(testDrink.modifyCalories(-500));
//// 			assertEquals(testDrink.getCalories(), 100);
//// 		}
////
//// 		@Test
//// 		void testSetCalories() {
//// 			assertTrue(testDrink.setCalories(500));
//// 			assertEquals(testDrink.getCalories(), 500);
//// 			assertTrue(testDrink.setCalories(999));
//// 			assertEquals(testDrink.getCalories(), 999);
//// 		}
////
//// 		@Test
//// 		void testModCaloriesAfterSettingCalories() {
//// 			assertTrue(testDrink.setCalories(500));
//// 			assertTrue(testDrink.modifyCalories(600));
//// 			assertEquals(testDrink.getCalories(), 1100);
//// 			assertTrue(testDrink.modifyCalories(-600));
//// 			assertEquals(testDrink.getCalories(), 500);
//// 		}
////
//// 		@Test
//// 		void testSetIconPath() {
//// 			assertTrue(testDrink.setIconPath(500));
//// 		}
////
//// 		@Test
//// 		void testSetName() {
//// 			assertTrue(testDrink.setName("even longerrrrrrrrrrrrrrrrrrrrrr\n\nrrrrrrr drink name"));
//// 			assertTrue(testDrink.setName("12345555"));
//// 			assertTrue(testDrink.setName("#$%!!!+=[][][][["));
//// 		}
////
//// 		@Test
//// 		void testSetQuantity() {
//// 			assertTrue(testDrink.setQuantity(600));
//// 		}
////
//// 		@Test
//// 		void testSetIngredients() {
//// 			assertTrue(testDrink.setIngredients("y\n\nou ingredient, ingrediant, ingreed\n\n"));
//// 		}
//// 	}
////
//// 	@Nested
//// 	@DisplayName("Empty tests")
//// 	class Test_Empty {
//// 		private Drink testDrink;
////
//// 		@BeforeEach
//// 		void setUp() {
//// 			testDrink = new Drink();
//// 		}
////
//// 		@Test
//// 		void testSingleEmptysInCreation() {
//// 			assertFalse(testDrink.init(null, 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 0, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 0, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init("drink", 1, 1, null, "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", null, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, null, 5, 1));
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 0, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 0));
//// 		}
////
//// 		@Test
//// 		void testMultiEmptyInCreation() {
//// 			assertFalse(testDrink.init(null, 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, null,  null, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, null,  null, null, 5, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, null,  null, null, 0, 1));
//// 			assertFalse(testDrink.init(null, 0, 0, null, null,  null, null, 0, 0));
//// 		}
////
//// 		@Test
//// 		void testEmptyValues() {
//// 			assertFalse(testDrink.init(null, 0, 0, null, null,  null, null, 0, 0));
//// 			assertNull(testDrink.getName());
//// 			assertEquals(-1, testDrink.getIconPath());
//// 			assertEquals(0, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testSetIngredients() {
//// 			assertFalse(testDrink.changeInstructions(null));
//// 		}
////
//// 		@Test
//// 		void testModCalories() {
//// 			assertTrue(testDrink.modifyCalories(0));
//// 		}
////
//// 		@Test
//// 		void testSetBaseUnit() {
//// 			assertFalse(testDrink.setBaseUnit(null));
//// 		}
////
//// 		@Test
//// 		void testSetCaloies() {
//// 			assertTrue(testDrink.setCalories(0));
//// 		}
////
//// 		@Test
//// 		void testSetFragType() {
//// 			assertFalse(testDrink.setFragmentType(null));
//// 		}
////
//// 		@Test
//// 		void testSetIconPath() {
//// 			assertTrue(testDrink.setIconPath(0));
//// 		}
////
//// 		@Test
//// 		void testSetName() {
//// 			assertFalse(testDrink.setName(null));
//// 		}
////
//// 		@Test
//// 		void testSetQuantity() {
//// 			assertFalse(testDrink.setQuantity(0));
//// 		}
//// 	}
////
//// 	@Nested
//// 	@DisplayName("Edge case tests")
//// 	class Test_EdgeCases {
//// 		private Drink testDrink;
////
//// 		@BeforeEach
//// 		void setUp() {
//// 			testDrink = new Drink();
//// 		}
////
//// 		@Test
//// 		void testEdgeCasesInCreation() {
//// 			assertTrue(testDrink.init("\n", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertFalse(testDrink.init("", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init(" ", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", Constant.ENTRY_MAX_VALUE, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "\n", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "\n", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "\n", "\n", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "\n", "", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "", "instructions", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "", "", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, 1));
//// 			assertTrue(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 1, Constant.ENTRY_MAX_VALUE));
//// 			assertTrue(testDrink.init("drink", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE));
//// 		}
////
//// 		@Test
//// 		void testSetCalories() {
//// 			assertTrue(testDrink.setCalories(Constant.ENTRY_MAX_VALUE));
//// 		}
////
//// 		@Test
//// 		void testChangeInstructions() {
//// 			assertTrue(testDrink.changeInstructions(""));
//// 			assertTrue(testDrink.changeInstructions("\n"));
//// 		}
////
//// 		@Test
//// 		void testModCalories() {
//// 			assertTrue(testDrink.modifyCalories(Constant.ENTRY_MAX_VALUE));
//// 			assertEquals(Constant.ENTRY_MAX_VALUE, testDrink.getCalories());
//// 			assertTrue(testDrink.modifyCalories(-Constant.ENTRY_MAX_VALUE));
//// 			assertEquals(0, testDrink.getCalories());
//// 		}
////
//// 		@Test
//// 		void testSetIconPath() {
//// 			assertTrue(testDrink.setIconPath(Constant.ENTRY_MAX_VALUE));
//// 		}
////
//// 		@Test
//// 		void testSetName() {
//// 			assertFalse(testDrink.setName(""));
//// 		}
////
//// 		@Test
//// 		void testSetQuantity() {
//// 			assertTrue(testDrink.setQuantity(Constant.ENTRY_MAX_VALUE));
//// 			assertTrue(testDrink.setQuantity(1));
//// 		}
////
//// 		@Test
//// 		void testSetIngredients() {
//// 			assertTrue(testDrink.setIngredients(""));
//// 		}
//// 	}
////
//// 	@Nested
//// 	@DisplayName("Invalid tests")
//// 	class Test_Invalid {
//// 		private Drink testDrink;
////
//// 		@BeforeEach
//// 		void setUp() {
//// 			testDrink = new Drink();
//// 		}
////
//// 		@Test
//// 		void testInvalidNameCreation() {
//// 			assertFalse(testDrink.init("", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertNull(testDrink.getName());
//// 			assertEquals(-1, testDrink.getIconPath());
//// 			assertEquals(0, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidIconPathCreation() {
//// 			assertFalse(testDrink.init("drink", -1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(-1, testDrink.getIconPath());
//// 			assertEquals(0, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidCaloriesCreation() {
//// 			assertFalse(testDrink.init("drink", 1, -1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(0, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
////
//// 			assertFalse(testDrink.init("drink", 1, Constant.ENTRY_MAX_VALUE + 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertSame("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(0, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidInstructionsCreation() {
//// 			assertFalse(testDrink.init("drink", 1, 1, null, "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertSame(testDrink.getBaseUnit(), Unit.cups);
//// 			assertEquals(5, testDrink.getQuantity());
//// 			assertEquals(1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidIngredientsCreation() {
//// 			assertFalse(testDrink.init("drink", 1,  1, "instructions", null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertEquals("instructions", testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertSame(testDrink.getBaseUnit(), Unit.cups);
//// 			assertEquals(5, testDrink.getQuantity());
//// 			assertEquals(1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidFragmentCreation() {
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", null, Edible.Unit.cups, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertNull(testDrink.getFragmentType());
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidUnitCreation() {
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, null, 5, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertNull(testDrink.getBaseUnit());
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidQuantityCreation() {
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, -1, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertSame(testDrink.getBaseUnit(), Unit.cups);
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
////
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE + 1, 1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertSame(testDrink.getBaseUnit(), Unit.cups);
//// 			assertEquals(-1, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void testInvalidDBKeyCreation() {
//// 			assertFalse(testDrink.init("drink", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, -1));
//// 			assertEquals("drink", testDrink.getName());
//// 			assertEquals(1, testDrink.getIconPath());
//// 			assertEquals(1, testDrink.getCalories());
//// 			assertNull(testDrink.getInstructions());
//// 			assertNull(testDrink.getIngredients());
//// 			assertSame(testDrink.getFragmentType(), FragmentType.diaryEntry);
//// 			assertSame(testDrink.getBaseUnit(), Unit.cups);
//// 			assertEquals(5, testDrink.getQuantity());
//// 			assertEquals(-1, testDrink.getDbkey());
//// 		}
////
//// 		@Test
//// 		void  testModCalories() {
//// 			assertFalse(testDrink.modifyCalories(Constant.ENTRY_MAX_VALUE + 1));
//// 			assertFalse(testDrink.modifyCalories(-1));
//// 		}
////
//// 		@Test
//// 		void testSetCaloies() {
//// 			assertFalse(testDrink.setCalories(Constant.ENTRY_MAX_VALUE + 1));
//// 			assertFalse(testDrink.setCalories(-1));
//// 		}
////
//// 		@Test
//// 		void testSetIconPath() {
//// 			assertFalse(testDrink.setIconPath(-1));
//// 		}
////
//// 		@Test
//// 		void testSetQuantity() {
//// 			assertFalse(testDrink.setQuantity(Constant.ENTRY_MAX_VALUE + 1));
//// 			assertFalse(testDrink.setQuantity(-1));
//// 		}
//// 	}
//// }