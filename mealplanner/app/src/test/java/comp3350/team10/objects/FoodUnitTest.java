package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FoodUnitTest {

	@Nested
	@DisplayName("Simple tests")
	class Test_Simple {
		private Food testFood;
		
		@BeforeEach
		void setUp() {
			testFood = new Food();
		}

		@Test
		void testDiffFrags() {
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryModify, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.recipe, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.cardSelection, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.noType, Edible.Unit.cups, 5, 1));
		}

		@Test
		void testDiffUnits() {
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryModify, Edible.Unit.oz, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryAdd, Edible.Unit.g, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.recipe, Edible.Unit.serving, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.cardSelection, Edible.Unit.tbsp, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.noType, Edible.Unit.tsp, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.cardSelection, Edible.Unit.ml, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.noType, Edible.Unit.liter, 5, 1));
		}

		@Test
		void testDefaultVals() {
			assertTrue(testFood.getName() == null);
			assertTrue(testFood.getIconPath() == -1);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
			assertTrue(testFood.getCalories() == 0);
		}
			
		@Test
		void testSetCaloies() {
			assertTrue(testFood.setCalories(100));
			assertTrue(testFood.getCalories() == 100);
		}

		@Test
		void testModCalories() {
			assertTrue(testFood.modifyCalories(100));
			assertTrue(testFood.getCalories() == 100);
			assertTrue(testFood.modifyCalories(-5));
			assertTrue(testFood.getCalories() == 95);
		}

		@Test
		void testSetUnit() {
			assertTrue(testFood.setBaseUnit(Edible.Unit.cups));
			assertTrue(testFood.setBaseUnit(Edible.Unit.oz));
			assertTrue(testFood.setBaseUnit(Edible.Unit.g));
			assertTrue(testFood.setBaseUnit(Edible.Unit.serving));
			assertTrue(testFood.setBaseUnit(Edible.Unit.tbsp));
			assertTrue(testFood.setBaseUnit(Edible.Unit.tsp));
			assertTrue(testFood.setBaseUnit(Edible.Unit.ml));
			assertTrue(testFood.setBaseUnit(Unit.liter));
		}
			
		@Test
		void testSetDBKey() {
			assertTrue(testFood.setDbkey(1));
			assertTrue(testFood.setDbkey(10));
		}

		@Test
		void testSetFragType() {
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.diaryModify));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.diaryAdd));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.recipe));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.cardSelection));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.noType));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.cardSelection));
			assertTrue(testFood.setFragmentType(ListItem.FragmentType.noType));
		}

		@Test
		void testSetIconPath() {
			assertTrue(testFood.setIconPath(5));
			assertTrue(testFood.setIconPath(15));
		}	

		@Test
		void testSetName() {
			assertTrue(testFood.setName("name"));
			assertTrue(testFood.setName("diffName"));
		}

		@Test
		void testSetQuantity() {	
			assertTrue(testFood.setQuantity(100));
			assertTrue(testFood.getQuantity() == 100);
			assertTrue(testFood.setQuantity(300));
			assertTrue(testFood.getQuantity() == 300);
		}
	}

	@Nested
	@DisplayName("Complex tests")
	class Test_Complex {
		private Food testFood;
		
		@BeforeEach
		void setUp() {
			testFood = new Food();
		}

		@Test
		void testComplexMealCreations() {
			assertTrue(testFood.init("very long food name", 10, 400, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 504, 700));
			assertTrue(testFood.init("even longer longggg\ng \nfood name", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 500, 100));
			assertTrue(testFood.init("12345555", 10, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 580, 900));
			assertTrue(testFood.init("#$%!!!+=[][][][[", 10, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 50, 4));
			assertTrue(testFood.init(" ", 10, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 150, 14));
			assertTrue(testFood.init("  $ ", 10, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 590, 666));
		}

		@Test
		void testModCalories() {
			assertTrue(testFood.modifyCalories(600));
			assertTrue(testFood.getCalories() == 600);
			assertTrue(testFood.modifyCalories(-500));
			assertTrue(testFood.getCalories() == 100);
		}

		@Test
		void testSetCalories() {
			assertTrue(testFood.setCalories(500));
			assertTrue(testFood.getCalories() == 500);
			assertTrue(testFood.setCalories(999));
			assertTrue(testFood.getCalories() == 999);
		}

		@Test
		void testModCaloriesAfterSettingCalories() {
			assertTrue(testFood.setCalories(500));
			assertTrue(testFood.modifyCalories(600));
			assertTrue(testFood.getCalories() == 1100);
			assertTrue(testFood.modifyCalories(-600));
			assertTrue(testFood.getCalories() == 500);
		}

		@Test
		void testSetIconPath() {
			assertTrue(testFood.setIconPath(500));
		}	

		@Test
		void testSetName() {
			assertTrue(testFood.setName("even longerrrrrrrrrrrrrrrrrrrrrr\n\nrrrrrrr food name"));
			assertTrue(testFood.setName("12345555"));
			assertTrue(testFood.setName("#$%!!!+=[][][][["));
		}

		@Test
		void testSetQuantity() {
			assertTrue(testFood.setQuantity(600));
		}
	}

	@Nested
	@DisplayName("Empty tests")
	class Test_Empty {
		private Food testFood;
		
		@BeforeEach
		void setUp() {
			testFood = new Food();
		}

		@Test
		void testSingleEmptysInCreation() {
			assertFalse(testFood.init(null, 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 0, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 0, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init("food", 1, 1, null, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, null, 5, 1));
			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 0, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 0));
		}

		@Test
		void testMultiEmptyInCreation() { 
			assertFalse(testFood.init(null, 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init(null, 0, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init(null, 0, 0, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init(null, 0, 0, null, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init(null, 0, 0, null, null, 5, 1));
			assertFalse(testFood.init(null, 0, 0, null, null, 0, 1));
			assertFalse(testFood.init(null, 0, 0, null, null, 0, 0));
		}

		@Test
		void testEmptyValues() {
			assertFalse(testFood.init(null, 0, 0, null, null, 0, 0));
			assertTrue(testFood.getName() == null);
			assertTrue(testFood.getIconPath() == -1);
			assertTrue(testFood.getCalories() == 0);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test 
		void testModCalories() {
			assertTrue(testFood.modifyCalories(0));
		}

		@Test 
		void testSetBaseUnit() {
			assertFalse(testFood.setBaseUnit(null));
		}

		@Test
		void testSetCaloies() {
			assertTrue(testFood.setCalories(0));
		}

		@Test
		void testSetFragType() {
			assertFalse(testFood.setFragmentType(null));
		}

		@Test
		void testSetIconPath() {
			assertTrue(testFood.setIconPath(0));
		}

		@Test
		void testSetName() {
			assertFalse(testFood.setName(null));
		}

		@Test
		void testSetQuantity() {
			assertFalse(testFood.setQuantity(0));
		}
	}

	@Nested
	@DisplayName("Edge case tests")
	class Test_EdgeCases {
		private Food testFood;
		
		@BeforeEach
		void setUp() {
			testFood = new Food();
		}
		
		@Test
		void testEdgeCasesInCreation() {
			assertTrue(testFood.init("\n", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertFalse(testFood.init("", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init(" ", 1, 1,  ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", Constant.ENTRY_MAX_VALUE, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, Constant.ENTRY_MAX_VALUE, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, 1));
			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 1, Constant.ENTRY_MAX_VALUE));
			assertTrue(testFood.init("food", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE));
		}

		@Test
		void testSetCalories() {
			assertTrue(testFood.setCalories(Constant.ENTRY_MAX_VALUE));
		}

		@Test
		void testModCalories() {
			assertTrue(testFood.modifyCalories(Constant.ENTRY_MAX_VALUE));
			assertTrue(testFood.getCalories() == Constant.ENTRY_MAX_VALUE);
			assertTrue(testFood.modifyCalories(-Constant.ENTRY_MAX_VALUE));
			assertTrue(testFood.getCalories() == 0);
		}

		@Test
		void testSetIconPath() {
			assertTrue(testFood.setIconPath(Constant.ENTRY_MAX_VALUE));
		}

		@Test
		void testSetName() {
			assertFalse(testFood.setName(""));
		}

		@Test
		void testSetQuantity() {
			assertTrue(testFood.setQuantity(Constant.ENTRY_MAX_VALUE));
			assertTrue(testFood.setQuantity(1));
		}
	}

	@Nested
	@DisplayName("Invalid tests")
	class Test_Invalid {
		private Food testFood;
		
		@BeforeEach
		void setUp() {
			testFood = new Food();
		}	

		@Test
		void testInvalidNameCreation() {
			assertFalse(testFood.init("", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.getName() == null);
			assertTrue(testFood.getIconPath() == -1);
			assertTrue(testFood.getCalories() == 0);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidIconPathCreation() {
			assertFalse(testFood.init("food", -1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == -1);
			assertTrue(testFood.getCalories() == 0);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidCaloriesCreation() {
			assertFalse(testFood.init("food", 1, -1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 0);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);

			assertFalse(testFood.init("food", 1, Constant.ENTRY_MAX_VALUE + 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.getName() == "food");
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 0);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidFragmentCreation() {
			assertFalse(testFood.init("food", 1, 1, null, Edible.Unit.cups, 5, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 1);
			assertTrue(testFood.getFragmentType() == null);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidUnitCreation() {
			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, null, 5, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 1);
			assertTrue(testFood.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testFood.getBaseUnit() == null);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidQuantityCreation() {
			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, -1, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 1);
			assertTrue(testFood.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testFood.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);

			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE + 1, 1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 1);
			assertTrue(testFood.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testFood.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testFood.getQuantity() == -1);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testInvalidDBKeyCreation() {
			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, -1));
			assertTrue(testFood.getName().equals("food"));
			assertTrue(testFood.getIconPath() == 1);
			assertTrue(testFood.getCalories() == 1);
			assertTrue(testFood.getFragmentType() == ListItem.FragmentType.diaryEntry);
			assertTrue(testFood.getBaseUnit() == Edible.Unit.cups);
			assertTrue(testFood.getQuantity() == 5);
			assertTrue(testFood.getDbkey() == -1);
		}

		@Test
		void testModCalories() {
			assertFalse(testFood.modifyCalories(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testFood.modifyCalories(-1));
		}

		@Test
		void testSetCaloies() {
			assertFalse(testFood.setCalories(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testFood.setCalories(-1));
		}

		@Test
		void testSetIconPath() {
			assertFalse(testFood.setIconPath(-1));
		}

		@Test
		void testSetQuantity() {	
			assertFalse(testFood.setQuantity(Constant.ENTRY_MAX_VALUE + 1));
			assertFalse(testFood.setQuantity(-1));
		}
	}
}