package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MealUnitTest {

	@Nested
	@DisplayName("Simple tests")
	class Test_Simple {
		private Meal testMeal;
		
		@BeforeEach
		void setUp() {
			testMeal = new Meal();
		}

		@Test
		void testDiffFrags() {
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients",ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryModify, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.recipe, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.cups, 5, 1));
		}

		@Test
		void testDiffUnits() {
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryModify, Edible.Unit.oz, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryAdd, Edible.Unit.g, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.recipe, Edible.Unit.serving, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.tbsp, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.tsp, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.cardSelection, Edible.Unit.ml, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.noType, Edible.Unit.liter, 5, 1));
		}

		@Test
		void testDefaultVals() {
			assertTrue(testMeal.getName() == null);
			assertTrue(testMeal.getIconPath() == -1);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getIngredients() == null);
		}

		@Test
		void testChangeInstructions() {
			assertTrue(testMeal.changeInstructions("you"));
			assertTrue(testMeal.changeInstructions("you should"));
		}
			
		@Test
		void testSetCaloies() {
			assertTrue(testMeal.setCalories(100));
			assertTrue(testMeal.getCalories() == 100);
		}

		@Test
		void testModCalories() {
			assertTrue(testMeal.modifyCalories(100));
			assertTrue(testMeal.getCalories() == 100);
			assertTrue(testMeal.modifyCalories(-5));
			assertTrue(testMeal.getCalories() == 95);
		}

		@Test
		void testSetUnit() {
			assertTrue(testMeal.setBaseUnit(Edible.Unit.cups));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.oz));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.g));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.serving));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.tbsp));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.tsp));
			assertTrue(testMeal.setBaseUnit(Edible.Unit.ml));
			assertTrue(testMeal.setBaseUnit(Unit.liter));
		}
			
		@Test
		void testSetDBKey() {
			assertTrue(testMeal.setDbkey(1));
			assertTrue(testMeal.setDbkey(10));
		}

		@Test
		void testSetFragType() {
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.diaryModify));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.diaryAdd));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.recipe));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.cardSelection));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.noType));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.cardSelection));
			assertTrue(testMeal.setFragmentType(ListItem.FragmentType.noType));
		}

		@Test
		void testSetIconPath() {
			assertTrue(testMeal.setIconPath(5));
			assertTrue(testMeal.setIconPath(15));
		}	

		@Test
		void testSetIngredients() {
			assertTrue(testMeal.setIngredients("you"));
			assertTrue(testMeal.setIngredients("youshould"));
		}	

		@Test
		void testSetName() {
			assertTrue(testMeal.setName("name"));
			assertTrue(testMeal.setName("diffName"));
		}

		@Test
		void testSetQuantity() {	
			assertTrue(testMeal.setQuantity(100));
			assertTrue(testMeal.getQuantity() == 100);
			assertTrue(testMeal.setQuantity(300));
			assertTrue(testMeal.getQuantity() == 300);
		}
	}

	@Nested
	@DisplayName("Complex tests")
	class Test_Complex {
		private Meal testMeal;
		
		@BeforeEach
		void setUp() {
			testMeal = new Meal();
		}

		@Test
		void testComplexMealCreations() {
			assertTrue(testMeal.init("very long meal name", 10, 400, "instructio\nns", "in\n\ngredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 504, 700));
			assertTrue(testMeal.init("even longer longggg\ng \nmeal name", 1, 1, "iions", "idients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 500, 100));
			assertTrue(testMeal.init("12345555", 10, 1, "12344444", "43222222", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 580, 900));
			assertTrue(testMeal.init("#$%!!!+=[][][][[", 10, 1, "#$%!!!+=[][][][[", "\"\"|\"", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 50, 4));
			assertTrue(testMeal.init(" ", 10, 1, " ", "  $ ", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 150, 14));
			assertTrue(testMeal.init("  $ ", 10, 1, "  $ ", " ", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 590, 666));
		}

		@Test
		void testSetIconPaths() {
			assertTrue(testMeal.changeInstructions("I follow rivers  baby \ngood song"));
			assertTrue(testMeal.changeInstructions("I follow \n\nrivers baby \n"));
		}

		@Test
		void testModCalories() {
			assertTrue(testMeal.modifyCalories(600));
			assertTrue(testMeal.getCalories() == 600);
			assertTrue(testMeal.modifyCalories(-500));
			assertTrue(testMeal.getCalories() == 100);
		}

		@Test
		void testSetCalories() {
			assertTrue(testMeal.setCalories(500));
			assertTrue(testMeal.getCalories() == 500);
			assertTrue(testMeal.setCalories(999));
			assertTrue(testMeal.getCalories() == 999);
		}

		@Test
		void testModCaloriesAfterSettingCalories() {
			assertTrue(testMeal.setCalories(500));
			assertTrue(testMeal.modifyCalories(600));
			assertTrue(testMeal.getCalories() == 1100);
			assertTrue(testMeal.modifyCalories(-600));
			assertTrue(testMeal.getCalories() == 500);
		}

		@Test
		void testSetIconPath() {
			assertTrue(testMeal.setIconPath(500));
		}	

		@Test
		void testSetName() {
			assertTrue(testMeal.setName("even longerrrrrrrrrrrrrrrrrrrrrr\n\nrrrrrrr meal name"));
			assertTrue(testMeal.setName("12345555"));
			assertTrue(testMeal.setName("#$%!!!+=[][][][["));
		}

		@Test
		void testSetQuantity() {
			assertTrue(testMeal.setQuantity(600));
		}
			
		@Test			
		void testSetIngredients() {
			assertTrue(testMeal.setIngredients("y\n\nou ingredient, ingrediant, ingreed\n\n"));
		}
	}

	@Nested
	@DisplayName("Empty tests")
	class Test_Empty {
		private Meal testMeal;
		
		@BeforeEach
		void setUp() {
			testMeal = new Meal();
		}

		@Test
		void testSingleEmptysInCreation() {
			assertFalse(testMeal.init(null, 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 0, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 0, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init("meal", 1, 1, null, "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init("meal", 1, 1, "instructions", null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", null, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, null, 5, 1));
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 0, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 0));
		}

		@Test
		void testMultiEmptyInCreation() { 
			assertFalse(testMeal.init(null, 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, null, "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, null, null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, null, null,  null, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, null, null,  null, null, 5, 1));
			assertFalse(testMeal.init(null, 0, 0, null, null,  null, null, 0, 1));
			assertFalse(testMeal.init(null, 0, 0, null, null,  null, null, 0, 0));
		}

		@Test
		void testEmptyValues() {
			assertFalse(testMeal.init(null, 0, 0, null, null,  null, null, 0, 0));
			assertTrue(testMeal.getName() == null);
			assertTrue(testMeal.getIconPath() == -1);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testSetIngredients() {
			assertFalse(testMeal.changeInstructions(null));
		}

		@Test 
		void testModCalories() {
			assertTrue(testMeal.modifyCalories(0));
		}

		@Test 
		void testSetBaseUnit() {
			assertFalse(testMeal.setBaseUnit(null));
		}

		@Test
		void testSetCaloies() {
			assertTrue(testMeal.setCalories(0));
		}

		@Test
		void testSetFragType() {
			assertFalse(testMeal.setFragmentType(null));
		}

		@Test
		void testSetIconPath() {
			assertTrue(testMeal.setIconPath(0));
		}

		@Test
		void testSetName() {
			assertFalse(testMeal.setName(null));
		}

		@Test
		void testSetQuantity() {
			assertFalse(testMeal.setQuantity(0));
		}
	}

	@Nested
	@DisplayName("Edge case tests")
	class Test_EdgeCases {
		private Meal testMeal;
		
		@BeforeEach
		void setUp() {
			testMeal = new Meal();
		}
		
		@Test
		void testEdgeCasesInCreation() {//1, "instructions", "ingredients"
			assertTrue(testMeal.init("\n", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testMeal.init("", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init(" ", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", Constant.ENTRY_MAX_VALUE, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, Constant.ENTRY_MAX_VALUE, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "\n", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "\n", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, 1));
			assertTrue(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 1, Constant.ENTRY_MAX_VALUE));
			assertTrue(testMeal.init("meal", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE));
		}

		@Test
		void testSetCalories() {
			assertTrue(testMeal.setCalories(Constant.ENTRY_MAX_VALUE));
		}
			
		@Test
		void testChangeInstructions() {	
			assertTrue(testMeal.changeInstructions(""));
			assertTrue(testMeal.changeInstructions("\n"));
		}

		@Test
		void testModCalories() {
			assertTrue(testMeal.modifyCalories(Constant.ENTRY_MAX_VALUE));
			assertTrue(testMeal.getCalories() == Constant.ENTRY_MAX_VALUE);
			assertTrue(testMeal.modifyCalories(-Constant.ENTRY_MAX_VALUE));
			assertTrue(testMeal.getCalories() == 0);
		}

		@Test
		void testSetIconPath() {
			assertTrue(testMeal.setIconPath(Constant.ENTRY_MAX_VALUE));
		}

		@Test
		void testSetName() {
			assertFalse(testMeal.setName(""));
		}

		@Test
		void testSetQuantity() {
			assertTrue(testMeal.setQuantity(Constant.ENTRY_MAX_VALUE));
			assertTrue(testMeal.setQuantity(1));
		}
		
		@Test
		void testSetIngredients() {
			assertTrue(testMeal.setIngredients(""));
		}
	}

	@Nested
	@DisplayName("Invalid tests")
	class Test_Invalid {
		private Meal testMeal;
		
		@BeforeEach
		void setUp() {
			testMeal = new Meal();
		}	

		@Test
		void testInvalidNameCreation() {
			assertFalse(testMeal.init("", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName() == null);
			assertTrue(testMeal.getIconPath() == -1);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidIconPathCreation() {
			assertFalse(testMeal.init("meal", -1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == -1);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidCaloriesCreation() {
			assertFalse(testMeal.init("meal", 1, -1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);

			assertFalse(testMeal.init("meal", 1, Constant.ENTRY_MAX_VALUE + 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName() == "meal");
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 0);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidInstructionsCreation() {
			assertFalse(testMeal.init("meal", 1, 1, "ingredients", null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testMeal.getQuantity() == 5);
			assertTrue(testMeal.getDbkey() == 1);
		}

		@Test
		void testInvalidIngredientsCreation() {
			assertFalse(testMeal.init("meal", 1,  1, "instructions", null, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testMeal.getQuantity() == 5);
			assertTrue(testMeal.getDbkey() == 1);
		}

		@Test
		void testInvalidFragmentCreation() {
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", null, Edible.Unit.cups, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == null);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidUnitCreation() {
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, null, 5, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == null);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidQuantityCreation() {
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, -1, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);

			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE + 1, 1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testMeal.getQuantity() == -1);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void testInvalidDBKeyCreation() {
			assertFalse(testMeal.init("meal", 1, 1, "instructions", "ingredients", ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, -1));
			assertTrue(testMeal.getName().equals("meal"));
			assertTrue(testMeal.getIconPath() == 1);
			assertTrue(testMeal.getCalories() == 1);
			assertTrue(testMeal.getInstructions() == null);
			assertTrue(testMeal.getIngredients() == null);
			assertTrue(testMeal.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testMeal.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testMeal.getQuantity() == 5);
			assertTrue(testMeal.getDbkey() == -1);
		}

		@Test
		void  testModCalories() {
			assertFalse(testMeal.modifyCalories(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testMeal.modifyCalories(-1));
		}

		@Test
		void testSetCaloies() {
			assertFalse(testMeal.setCalories(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testMeal.setCalories(-1));
		}

		@Test
		void testSetIconPath() {
			assertFalse(testMeal.setIconPath(-1));
		}

		@Test
		void testSetQuantity() {	
			assertFalse(testMeal.setQuantity(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testMeal.setQuantity(-1));
		}
	}
}