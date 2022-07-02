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
		 assertEquals(idExpect,food.getDbkey());// not get id fucntion
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

	 void testSetName(Food food, String newName){//test correct
		 try{
			 food.setName(newName);
			 assertEquals(newName,food.getName());
		 }
		 catch (IOException e){
			 fail("Can not set Name " + newName + "\n");
		 }
	 }

	 void testSetDescription(Food food, String newDescription){//test correct
		 try{
			 food.setDescription(newDescription);
			 assertEquals(newDescription,food.getDesciprtion());
		 }
		 catch (IOException e){
			 fail("Can not set Description " + newDescription + "\n");
		 }
	 }
	 // test all int variable
	 void  testIntWithInput(Food food, int expect){//test set calories,  protein,  carbs,  fat
		 try{
			 food.initNutrition(expect, expect, expect, expect);
		 }
		 catch (Exception e)
		 {
			 System.out.println(e.getMessage());
			 fail("Should not catch any exception, input is number is invalid");
		 }
		 testNutrition(food,expect,expect,expect,expect);
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

	 @Nested
 	@DisplayName("Empty tests")
 	class Test_Empty {
		 private Food testFood;

		 @BeforeEach
 		void setUp() {
 			testFood = new Food();
 		}

		 @Test
		 void testSetName()
		 {
			 try{
				 testFood.initDetails(1, null, "lala", 5, Unit.cups);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid name",e.getMessage());
			 }

			 try{
				 testFood.initDetails(1, "", "lala", 5, Unit.cups);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid name",e.getMessage());
			 }

		 }
		 @Test
		 void testSetDescription()
		 {
			 try{
				 testFood.initDetails(1, "food", null, 5, Unit.cups);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid description",e.getMessage());
			 }

		 }

		 @Test
		 void testSetUnit()
		 {
			 try{
				 testFood.initDetails(1, "food", "lala", 5, null);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid unit",e.getMessage());
			 }
		 }

		 @Test
		 void testSetphoto()
		 {
			 byte[] testPic =null;
			 try{
				 testFood.initDetails(1, "food", "lala", 5, Unit.cups);
				 testFood.initMetadata(true, testPic, FragmentType.diaryAdd);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid photo",e.getMessage());
			 }

			 testPic = new byte[0];
			 try{
				 testFood.initDetails(1, "food", "lala", 5, Unit.cups);
				 testFood.initMetadata(true, testPic, FragmentType.diaryAdd);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid photo",e.getMessage());
			 }
		 }

		 @Test
		 void testSetFragmentType()
		 {
			 byte[] testPic = {(byte) 12, (byte) 59, (byte) 96};
			 try{
				 testFood.initDetails(1, "food", "lala", 5, Unit.cups);
				 testFood.initMetadata(true, testPic, null);
				 fail("Should throw IO exception");
			 }
			 catch (Exception e){
				 assertTrue(e instanceof IOException);
				 assertEquals("Invalid fragment type",e.getMessage());
			 }
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
		 void testLeftEdge(){ // input is equal to minimum
		testIntWithInput(testFood,Constant.ENTRY_MIN_VALUE);
		 }


		 @Test
		 void testRightEdge(){ // input is equal to MAX
			 testIntWithInput(testFood,Constant.ENTRY_MAX_VALUE);
		 }

		 @Test
		 void testDBKey(){
			 try {
				 testFood.initDetails(0, "food", "lala", 5, Unit.cups);
				 assertEquals(0,testFood.getDbkey());
			 }
			 catch (Exception e)
			 {
				 fail("Should not throw execption, invalid DBkey");
			 }

			 try {
				 testFood.initDetails(Integer.MAX_VALUE, "food", "lala", 5, Unit.cups);
				 assertEquals(Integer.MAX_VALUE,testFood.getDbkey());
			 }
			 catch (Exception e)
			 {
				 fail("Should not throw execption, invalid DBkey");
			 }
		 }

		 @Test
		 void testQuality(){
			 try {
				 testFood.initDetails(5, "food", "lala", 1, Unit.cups);
				 assertEquals(1,testFood.getQuantity());
			 }
			 catch (Exception e)
			 {
				 fail("Should not throw execption, invalid Quality");
			 }

			 try {
				 testFood.initDetails(5, "food", "lala", 9999, Unit.cups);
				 assertEquals(9999, testFood.getQuantity());
			 }
			 catch (Exception e)
			 {
				 fail("Should not throw execption, invalid Quality");
			 }
		 }
	 }

 }

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