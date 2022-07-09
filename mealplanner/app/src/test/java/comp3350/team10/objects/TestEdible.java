package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestEdible {

	void testSetName(Edible food, String newName){//test correct
		try{
			food.setName(newName);
			assertEquals(newName,food.getName());
		}
		catch (IllegalArgumentException e){
			fail("Can not set Name " + newName + "\n");
		}
	}

	void testSetDescription(Edible food, String newDescription){//test correct
		try{
			food.setDescription(newDescription);
			assertEquals(newDescription,food.getDescription());
		}
		catch (IllegalArgumentException e){
			fail("Can not set Description " + newDescription + "\n");
		}
	}
	// test all int variable
	void  testIntWithInput(Edible food, int expect){//test set calories,  protein,  carbs,  fat
		try{
			food.initNutrition(expect, expect, expect, expect);
		}
		catch (Exception e)
		{
			fail("Should not catch any exception, input is number is invalid");
		}

//		 testNutrition(food,expect,expect,expect,expect);
		assertEquals(expect, food.getCalories());
		assertEquals(expect, food.getProtein());
		assertEquals(expect, food.getCarbs());
		assertEquals(expect, food.getFat());
	}

	//some test function


	@Nested
	@DisplayName("Simple tests")
	class Test_Simple {
		private Edible testFood;
		private String photo = "simple";

		@BeforeEach
		void setUp() {

			testFood = new Edible();
			try {
				testFood.initDetails(1, "food", "lala", 5, Edible.Unit.cups);
				testFood.initNutrition(5, 5, 5, 5);
				testFood.initCategories(true, false, true, false, true);
				testFood.initMetadata(true, photo);
			} catch (Exception e) {
				fail("initial fail");
			}
		}

		@Test
		void testSimple() {
//			 testDetail(testFood, 1, "food", "lala", 5, Unit.cups);
			assertEquals(1,testFood.getDbkey());// not get id fucntion
			assertEquals("food", testFood.getName());
			assertEquals("lala", testFood.getDescription());
			assertEquals(5, testFood.getQuantity());
			assertEquals(Edible.Unit.cups, testFood.getUnit());

//			 testNutrition(testFood, 5, 5, 5, 5);
			assertEquals(5, testFood.getCalories());
			assertEquals(5, testFood.getProtein());
			assertEquals(5, testFood.getCarbs());
			assertEquals(5, testFood.getFat());

//			 testCategories(testFood, true, false, true, false, true);
			assertEquals(true, testFood.getIsAlcoholic());
			assertEquals(false, testFood.getIsSpicy());
			assertEquals(true, testFood.getIsVegan());
			assertEquals(false, testFood.getIsVegetarian());
			assertEquals(true, testFood.getIsGlutenFree());

//			 testMetadata(testFood, true, testPic);
			assertEquals(true, testFood.getIsCustom());
			//testPhotoBytes(testFood, testPic);
			assertEquals(photo,testFood.getPhoto());
		}
	}

	@Nested
	@DisplayName("Complex tests")
	class Test_Complex{
		private Edible testFood;
		private  String photo ="String test_instruction=\"very long instructions sdakjlfhadsljfkhldsakjhfiuweasdhyfuiklewahearewrw\" +\n" +
				"                        \"adsjfkghbewakjdshfljkaewhdflkaewj\\njewifhewl\\r isdfauhjljkewf\\n\\\\wieosuhjrfiol;ewk\" +\n" +
				"                        \"53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#\";";

		@BeforeEach
		void setUp() {

			testFood = new Edible();
			try {
				testFood.initDetails(1, "food", "lala", 5, Edible.Unit.cups);
				testFood.initNutrition(5, 5, 5, 5);
				testFood.initCategories(true, false, true, false, true);
				testFood.initMetadata(true, photo);
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

		@Test
		void testSetPhotoComplex(){
			assertEquals(photo,testFood.getPhoto());
		}
	}

	@Nested
	@DisplayName("Empty tests")
	class Test_Empty {
		private Edible testFood;

		@BeforeEach
		void setUp() {
			testFood = new Edible();
		}

		@Test
		void testSetName()
		{
			try{
				testFood.initDetails(1, null, "lala", 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch (Exception e){
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid name",e.getMessage());
			}

			try{
				testFood.initDetails(1, "", "lala", 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch (Exception e){
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid name",e.getMessage());
			}

		}
		@Test
		void testSetDescription()
		{
			try{
				testFood.initDetails(1, "food", null, 5, Edible.Unit.cups);
				fail("Should throw IO exception");
			}
			catch (Exception e){
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid description",e.getMessage());
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
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid unit",e.getMessage());
			}
		}

		@Test
		void testSetphoto()
		{
			byte[] testPic =null;
			//input a null photo should throw exception
			try{
				testFood.initDetails(1, "food", "lala", 5, Edible.Unit.cups);
				testFood.initMetadata(true, null);
				fail("Should throw IO exception");
			}
			catch (Exception e){
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid photo",e.getMessage());
			}
			//re design set photo (string) empty casenew code
			try{
				testFood.initDetails(1, "food", "lala", 5, Edible.Unit.cups);
				testFood.initMetadata(true, "");
				fail("Should throw IO exception");
			}
			catch (Exception e){
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid photo",e.getMessage());
			}
			//re design new code


//			testPic = new byte[0];
//			try{
//				testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//				testFood.initMetadata(true, testPic);
//				fail("Should throw IO exception");
//			}
//			catch (Exception e){
//				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid photo",e.getMessage());
//			}
//
//			byte[] testPic1 ={(byte) 65};
//			try{
//				testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//				testFood.initMetadata(true, testPic1);
//				fail("Should throw IO exception");
//			}
//			catch (Exception e){
//				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid photo",e.getMessage());
//			}
		}


	}


	@Nested
	@DisplayName("Edge case tests")
	class Test_EdgeCases {
		private Edible testFood;

		@BeforeEach
		void setUp() {
			testFood = new Edible();
		}
		@Test
		void testLeftEdge(){ // input is equal to minimum
			testIntWithInput(testFood,0);
		}


		@Test
		void testRightEdge(){ // input is equal to MAX
			testIntWithInput(testFood,9999);
		}

		@Test
		void testDBKey(){
			try {
				testFood.initDetails(0, "food", "lala", 5, Edible.Unit.cups);
				assertEquals(0,testFood.getDbkey());
			}
			catch (Exception e)
			{
				fail("Should not throw execption, invalid DBkey");
			}

			try {
				testFood.initDetails(Integer.MAX_VALUE, "food", "lala", 5, Edible.Unit.cups);
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
				testFood.initDetails(5, "food", "lala", 1, Edible.Unit.cups);
				assertEquals(1,testFood.getQuantity());
			}
			catch (Exception e)
			{
				fail("Should not throw execption, invalid Quality");
			}

			try {
				testFood.initDetails(5, "food", "lala", 9999, Edible.Unit.cups);
				assertEquals(9999, testFood.getQuantity());
			}
			catch (Exception e)
			{
				fail("Should not throw execption, invalid Quality");
			}
		}
	}


	@Nested
	@DisplayName("Invalid tests")
	class Test_Invalid {
		private Edible testFood;

		@BeforeEach
		void setUp() {
			testFood = new Edible();
		}

		@Test
		void testSetDbkey()
		{
			try {
				testFood.initDetails(-1, "food", "lala", 5, Edible.Unit.cups);
				fail("Should throw IO exception, id <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid DB key",e.getMessage());
			}
		}

		@Test
		void testSetProtein()
		{
			try {
				testFood.setProtein(-1);
				fail("Should throw IO exception, Protein <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid protein value",e.getMessage());
			}

			try {
				testFood.setProtein(10000);
				fail("Should throw IO exception, Protein >9999 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid protein value",e.getMessage());
			}
		}

		@Test
		void testSetFat()
		{
			try {
				testFood.setFat(-1);
				fail("Should throw IO exception, Fat <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid fat value",e.getMessage());
			}

			try {
				testFood.setFat(10000);
				fail("Should throw IO exception, Fat > 9999 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid fat value",e.getMessage());
			}
		}

		@Test
		void testSetCarbs()
		{
			try {
				testFood.setCarbs(-1);
				fail("Should throw IO exception, Carbs <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid carb value",e.getMessage());
			}

			try {
				testFood.setCarbs(10000);
				fail("Should throw IO exception, Carbs > 9999 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid carb value",e.getMessage());
			}
		}

		@Test
		void testSetCalories()
		{
			try {
				testFood.setCalories(-1);
				fail("Should throw IO exception, Calories <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid calorie value",e.getMessage());
			}

			try {
				testFood.setCalories(10000);
				fail("Should throw IO exception, Calories > 9999 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid calorie value",e.getMessage());
			}
		}

		@Test
		void testSetBaseQuantity()
		{
			try {
				testFood.setBaseQuantity(-1);
				fail("Should throw IO exception, BaseQuantity <0 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid quantity",e.getMessage());
			}

			try {
				testFood.setBaseQuantity(10000);
				fail("Should throw IO exception, BaseQuantity > 9999 throw IO exception");
			}
			catch (Exception e)
			{
				assertTrue(e instanceof IllegalArgumentException);
//				assertEquals("Invalid quantity",e.getMessage());
			}
		}


	}



}




