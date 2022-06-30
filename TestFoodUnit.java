//create by zhihao Zhou
// 2022/6/29

package comp3350.team10.objects;

 import comp3350.team10.objects.*;
 import comp3350.team10.objects.ListItem.FragmentType;
 import comp3350.team10.objects.Edible.Unit;

 import static org.junit.jupiter.api.Assertions.*;

 import android.app.LauncherActivity;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import java.io.IOException;

 public class TestFoodUnit {

	 //some test function

	 void testPhotoBytes(Food food, byte[] expect) {
		 assertEquals(expect.length, food.getPhotoBytes().length);
		 for (int i = 0; i < expect.length; i++) {
			 assertEquals(expect[i], (food.getPhotoBytes())[i]);
		 }

	 }

	 void testDetail(Food food, int idExpect, String nameExpect, String descriptionExpect, int quantityExpect, Unit unitExpect) {
//		 assertEquals(idExpect,food.getID());// not get id fucntion
		 assertEquals(nameExpect, food.getName());
		 assertEquals(descriptionExpect, food.getDesciprtion());
		 assertEquals(quantityExpect, food.getQuantity());
		 assertEquals(unitExpect, food.getUnit());
	 }

	 void testNutrition(Food food, int caloriesExpect, int proteinExpect, int carbsExpect, int fatExpect) {
		 assertEquals(caloriesExpect, food.getCalories());
		 assertEquals(proteinExpect, food.getProtein());
		 assertEquals(carbsExpect, food.getCarbs());
		 assertEquals(fatExpect, food.getFat());
	 }

	 void testCategories(Food food, boolean alcoholicExpect, boolean spicyExpect, boolean veganExpect, boolean vegetarianExpect, boolean glutenFreeExpect) {
		 assertEquals(alcoholicExpect, food.getIsAlcoholic());
		 assertEquals(spicyExpect, food.getIsSpicy());
		 assertEquals(veganExpect, food.getIsVegan());
		 assertEquals(vegetarianExpect, food.getIsVegetarian());
		 assertEquals(glutenFreeExpect, food.getIsGlutenFree());
	 }

	 void testMetadata(Food food, boolean customExpect, byte[] photoExpect, ListItem.FragmentType viewExpect) {
		 assertEquals(customExpect, food.getIsCustom());
		 testPhotoBytes(food, photoExpect);
		 assertEquals(viewExpect, food.getFragmentType());
	 }

	 void testSetName(Food food, String newName){
		 try{
			 food.setName(newName);
			 assertEquals(newName,food.getName());
		 }
		 catch (IOException e){
			 fail("Can not set Name " + newName + "\n");
		 }
	 }

	 void testSetDescription(Food food, String newDescription){
		 try{
			 food.setDescription(newDescription);
			 assertEquals(newDescription,food.getDesciprtion());
		 }
		 catch (IOException e){
			 fail("Can not set Description " + newDescription + "\n");
		 }
	 }

	 //some test function


	 @Nested
	 @DisplayName("Simple tests")
	 class Test_Simple {
		 private Food testFood;
		 private byte[] testPic = {(byte) 12, (byte) 59, (byte) 96};

		 @BeforeEach
		 void setUp() {

			 testFood = new Food();
			 try {
				 testFood.initDetails(1, "food", "lala", 5, Unit.cups);
				 testFood.initNutrition(5, 5, 5, 5);
				 testFood.initCategories(true, false, true, false, true);
				 testFood.initMetadata(true, testPic, FragmentType.diaryAdd);
			 } catch (Exception e) {
				 fail("initial fail");
			 }
		 }

		 @Test
		 void testSimple() {
			 testDetail(testFood, 1, "food", "lala", 5, Unit.cups);
			 testNutrition(testFood, 5, 5, 5, 5);
			 testCategories(testFood, true, false, true, false, true);
			 testMetadata(testFood, true, testPic, FragmentType.diaryAdd);
		 }
	 }

	 @Nested
 	 @DisplayName("Complex tests")
	 class Test_Complex{
		 private Food testFood;
		 private byte[] testPic = {(byte) 12, (byte) 59, (byte) 96};

		 @BeforeEach
		 void setUp() {

			 testFood = new Food();
			 try {
				 testFood.initDetails(1, "food", "lala", 5, Unit.cups);
				 testFood.initNutrition(5, 5, 5, 5);
				 testFood.initCategories(true, false, true, false, true);
				 testFood.initMetadata(true, testPic, FragmentType.diaryAdd);
			 } catch (Exception e) {
				 fail("initial fail");
			 }
		 }

		 @Test
		 void testSetNameComplex(){
			 testSetName(testFood,"very long food name");
			 testSetName(testFood,"even longer longggg\\ng \\nfood name");
			 testSetName(testFood,"12345555");
			 testSetName(testFood,"#$%!!!+=[][][][[");
			 testSetName(testFood," ");
			 testSetName(testFood," $");
		 }

		@Test
		 void testSetDescrptionComplex(){
			testSetDescription(testFood,"very long food Description");
			testSetDescription(testFood,"even longgfagewgawfd ggg\\ng sfeer longggg\\ng \\nfood Description");
			testSetDescription(testFood,"1234555545435214635695");
			testSetDescription(testFood,"#$%!!!+=[][][*/-*/-/-+*/][[");
			testSetDescription(testFood," ");
			testSetDescription(testFood," $");
		 }
	 }

 }

// 	@Nested
// 	@DisplayName("Empty tests")
// 	class Test_Empty {
// 		private Food testFood;
//
// 		@BeforeEach
// 		void setUp() {
// 			testFood = new Food();
// 		}
//
// 		@Test
// 		void testSingleEmptysInCreation() {
// 			assertFalse(testFood.init(null, 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", 0, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", 1, 0, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init("food", 1, 1, null, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, null, 5, 1));
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 0, 1));
// 			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 0));
// 		}
//
// 		@Test
// 		void testMultiEmptyInCreation() {
// 			assertFalse(testFood.init(null, 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init(null, 0, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init(null, 0, 0, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init(null, 0, 0, null, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init(null, 0, 0, null, null, 5, 1));
// 			assertFalse(testFood.init(null, 0, 0, null, null, 0, 1));
// 			assertFalse(testFood.init(null, 0, 0, null, null, 0, 0));
// 		}
//
// 		@Test
// 		void testEmptyValues() {
// 			assertFalse(testFood.init(null, 0, 0, null, null, 0, 0));
// 			assertNull(testFood.getName());
// 			assertEquals(-1, testFood.getIconPath());
// 			assertEquals(0, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testModCalories() {
// 			assertTrue(testFood.modifyCalories(0));
// 		}
//
// 		@Test
// 		void testSetBaseUnit() {
// 			assertFalse(testFood.setBaseUnit(null));
// 		}
//
// 		@Test
// 		void testSetCaloies() {
// 			assertTrue(testFood.setCalories(0));
// 		}
//
// 		@Test
// 		void testSetFragType() {
// 			assertFalse(testFood.setFragmentType(null));
// 		}
//
// 		@Test
// 		void testSetIconPath() {
// 			assertTrue(testFood.setIconPath(0));
// 		}
//
// 		@Test
// 		void testSetName() {
// 			assertFalse(testFood.setName(null));
// 		}
//
// 		@Test
// 		void testSetQuantity() {
// 			assertFalse(testFood.setQuantity(0));
// 		}
// 	}
//
// 	@Nested
// 	@DisplayName("Edge case tests")
// 	class Test_EdgeCases {
// 		private Food testFood;
//
// 		@BeforeEach
// 		void setUp() {
// 			testFood = new Food();
// 		}
//
// 		@Test
// 		void testEdgeCasesInCreation() {
// 			assertTrue(testFood.init("\n", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertFalse(testFood.init("", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init(" ", 1, 1,  ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", Constant.ENTRY_MAX_VALUE, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", 1, Constant.ENTRY_MAX_VALUE, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, 1));
// 			assertTrue(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 1, Constant.ENTRY_MAX_VALUE));
// 			assertTrue(testFood.init("food", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE));
// 		}
//
// 		@Test
// 		void testSetCalories() {
// 			assertTrue(testFood.setCalories(Constant.ENTRY_MAX_VALUE));
// 		}
//
// 		@Test
// 		void testModCalories() {
// 			assertTrue(testFood.modifyCalories(Constant.ENTRY_MAX_VALUE));
// 			assertEquals(Constant.ENTRY_MAX_VALUE, testFood.getCalories());
// 			assertTrue(testFood.modifyCalories(-Constant.ENTRY_MAX_VALUE));
// 			assertEquals(0, testFood.getCalories());
// 		}
//
// 		@Test
// 		void testSetIconPath() {
// 			assertTrue(testFood.setIconPath(Constant.ENTRY_MAX_VALUE));
// 		}
//
// 		@Test
// 		void testSetName() {
// 			assertFalse(testFood.setName(""));
// 		}
//
// 		@Test
// 		void testSetQuantity() {
// 			assertTrue(testFood.setQuantity(Constant.ENTRY_MAX_VALUE));
// 			assertTrue(testFood.setQuantity(1));
// 		}
// 	}
//
// 	@Nested
// 	@DisplayName("Invalid tests")
// 	class Test_Invalid {
// 		private Food testFood;
//
// 		@BeforeEach
// 		void setUp() {
// 			testFood = new Food();
// 		}
//
// 		@Test
// 		void testInvalidNameCreation() {
// 			assertFalse(testFood.init("", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertNull(testFood.getName());
// 			assertEquals(-1, testFood.getIconPath());
// 			assertEquals(0, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidIconPathCreation() {
// 			assertFalse(testFood.init("food", -1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(-1, testFood.getIconPath());
// 			assertEquals(0, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidCaloriesCreation() {
// 			assertFalse(testFood.init("food", 1, -1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(0, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
//
// 			assertFalse(testFood.init("food", 1, Constant.ENTRY_MAX_VALUE + 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 			assertSame("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(0, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidFragmentCreation() {
// 			assertFalse(testFood.init("food", 1, 1, null, Edible.Unit.cups, 5, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(1, testFood.getCalories());
// 			assertNull(testFood.getFragmentType());
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidUnitCreation() {
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, null, 5, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(1, testFood.getCalories());
// 			assertSame(testFood.getFragmentType(), FragmentType.diaryEntry);
// 			assertNull(testFood.getBaseUnit());
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidQuantityCreation() {
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, -1, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(1, testFood.getCalories());
// 			assertSame(testFood.getFragmentType(), FragmentType.diaryEntry);
// 			assertSame(testFood.getBaseUnit(), Unit.cups);
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
//
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE + 1, 1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(1, testFood.getCalories());
// 			assertSame(testFood.getFragmentType(), FragmentType.diaryEntry);
// 			assertSame(testFood.getBaseUnit(), Unit.cups);
// 			assertEquals(-1, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testInvalidDBKeyCreation() {
// 			assertFalse(testFood.init("food", 1, 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, -1));
// 			assertEquals("food", testFood.getName());
// 			assertEquals(1, testFood.getIconPath());
// 			assertEquals(1, testFood.getCalories());
// 			assertSame(testFood.getFragmentType(), FragmentType.diaryEntry);
// 			assertSame(testFood.getBaseUnit(), Unit.cups);
// 			assertEquals(5, testFood.getQuantity());
// 			assertEquals(-1, testFood.getDbkey());
// 		}
//
// 		@Test
// 		void testModCalories() {
// 			assertFalse(testFood.modifyCalories(Constant.ENTRY_MAX_VALUE + 1));
// 			assertFalse(testFood.modifyCalories(-1));
// 		}
//
// 		@Test
// 		void testSetCaloies() {
// 			assertFalse(testFood.setCalories(Constant.ENTRY_MAX_VALUE + 1));
// 			assertFalse(testFood.setCalories(-1));
// 		}
//
// 		@Test
// 		void testSetIconPath() {
// 			assertFalse(testFood.setIconPath(-1));
// 		}
//
// 		@Test
// 		void testSetQuantity() {
// 			assertFalse(testFood.setQuantity(Constant.ENTRY_MAX_VALUE + 1));
// 			assertFalse(testFood.setQuantity(-1));
// 		}
// 	}
// }