////create by Zhihao Zhou
//// 2022/7/2
//// have test simple,complex,empty,edge and invalid
////
//// !!!new problem found, when set null to for a photo it should throw exception but actually not
//// add new edge case to the photo
//
//package comp3350.team10.objects;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import comp3350.team10.objects.Edible.Unit;
//
//public class TestEdibleUnit {
//
//
//
//    //some test function
//
//    void testPhotoBytes(Edible food, byte[] expect) {
//        assertEquals(expect.length, food.getPhotoBytes().length);
//        for (int i = 0; i < expect.length; i++) {
//            assertEquals(expect[i], (food.getPhotoBytes())[i]);
//        }
//
//    }
//
//    void testDetail(Edible food, int idExpect, String nameExpect, String descriptionExpect, int quantityExpect, Unit unitExpect) {
//        assertEquals(idExpect,food.getDbkey());// not get id fucntion
//        assertEquals(nameExpect, food.getName());
//        assertEquals(descriptionExpect, food.getDescription());
//        assertEquals(quantityExpect, food.getQuantity());
//        assertEquals(unitExpect, food.getUnit());
//    }
//
//    void testNutrition(Edible food, int caloriesExpect, int proteinExpect, int carbsExpect, int fatExpect) {
//        assertEquals(caloriesExpect, food.getCalories());
//        assertEquals(proteinExpect, food.getProtein());
//        assertEquals(carbsExpect, food.getCarbs());
//        assertEquals(fatExpect, food.getFat());
//    }
//
//    void testCategories(Edible food, boolean alcoholicExpect, boolean spicyExpect, boolean veganExpect, boolean vegetarianExpect, boolean glutenFreeExpect) {
//        assertEquals(alcoholicExpect, food.getIsAlcoholic());
//        assertEquals(spicyExpect, food.getIsSpicy());
//        assertEquals(veganExpect, food.getIsVegan());
//        assertEquals(vegetarianExpect, food.getIsVegetarian());
//        assertEquals(glutenFreeExpect, food.getIsGlutenFree());
//    }
//
//    void testMetadata(Edible food, boolean customExpect, byte[] photoExpect) {
//        assertEquals(customExpect, food.getIsCustom());
//        testPhotoBytes(food, photoExpect);
//    }
//
//    void testSetName(Edible food, String newName){//test correct
//        try{
//            food.setName(newName);
//            assertEquals(newName,food.getName());
//        }
//        catch (IOException e){
//            fail("Can not set Name " + newName + "\n");
//        }
//    }
//
//    void testSetDescription(Edible food, String newDescription){//test correct
//        try{
//            food.setDescription(newDescription);
//            assertEquals(newDescription,food.getDescription());
//        }
//        catch (IOException e){
//            fail("Can not set Description " + newDescription + "\n");
//        }
//    }
//    // test all int variable
//    void  testIntWithInput(Edible food, int expect){//test set calories,  protein,  carbs,  fat
//        try{
//            food.initNutrition(expect, expect, expect, expect);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            fail("Should not catch any exception, input is number is invalid");
//        }
//        testNutrition(food,expect,expect,expect,expect);
//    }
//
//    //some test function
//
//
//    @Nested
//    @DisplayName("Simple tests")
//
//    class Test_Simple {
//        private Edible testFood;
//        private byte[] testPic = {(byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,};
//
//        @BeforeEach
//        void setUp() {
//
//            testFood = new Edible();
//            try {
//                testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//                testFood.initNutrition(5, 5, 5, 5);
//                testFood.initCategories(true, false, true, false, true);
//                testFood.initMetadata(true, testPic);
//            } catch (Exception e) {
//                fail("initial fail");
//            }
//        }
//
//        @Test
//        void testSimple() {
//            testDetail(testFood, 1, "food", "lala", 5, Unit.cups);
//            testNutrition(testFood, 5, 5, 5, 5);
//            testCategories(testFood, true, false, true, false, true);
//            testMetadata(testFood, true, testPic);
//        }
//    }
//
//    @Nested
//     @DisplayName("Complex tests")
//    class Test_Complex{
//        private Edible testFood;
//        private byte[] testPic = {(byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,
//                (byte) 12, (byte) 59, (byte) 96,(byte)54,(byte) 45,};
//
//        @BeforeEach
//        void setUp() {
//
//            testFood = new Edible();
//            try {
//                testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//                testFood.initNutrition(5, 5, 5, 5);
//                testFood.initCategories(true, false, true, false, true);
//                testFood.initMetadata(true, testPic);
//            } catch (Exception e) {
//                fail("initial fail");
//            }
//        }
//
//        @Test
//        void testSetNameComplex(){
//            testSetName(testFood,"very long food name");
//            testSetName(testFood,"even longer longggg\\ng \\nfood name");
//            testSetName(testFood,"12345555");
//            testSetName(testFood,"#$%!!!+=[][][][[");
//            testSetName(testFood," ");
//            testSetName(testFood," $");
//        }
//
//       @Test
//        void testSetDescrptionComplex(){
//           testSetDescription(testFood,"very long food Description");
//           testSetDescription(testFood,"even longgfagewgawfd ggg\\ng sfeer longggg\\ng \\nfood Description");
//           testSetDescription(testFood,"1234555545435214635695");
//           testSetDescription(testFood,"#$%!!!+=[][][*/-*/-/-+*/][[");
//           testSetDescription(testFood," ");
//           testSetDescription(testFood," $");
//        }
//    }
//
//    @Nested
//    @DisplayName("Empty tests")
//    class Test_Empty {
//        private Edible testFood;
//
//        @BeforeEach
//        void setUp() {
//            testFood = new Edible();
//        }
//
//        @Test
//        void testSetName()
//        {
//            try{
//                testFood.initDetails(1, null, "lala", 5, Unit.cups);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid name",e.getMessage());
//            }
//
//            try{
//                testFood.initDetails(1, "", "lala", 5, Unit.cups);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid name",e.getMessage());
//            }
//
//        }
//        @Test
//        void testSetDescription()
//        {
//            try{
//                testFood.initDetails(1, "food", null, 5, Unit.cups);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid description",e.getMessage());
//            }
//
//        }
//
//        @Test
//        void testSetUnit()
//        {
//            try{
//                testFood.initDetails(1, "food", "lala", 5, null);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid unit",e.getMessage());
//            }
//        }
//
//        @Test
//        void testSetphoto()
//        {
//
//            try{
//                testFood.initDetails(1, "food", "lala", 5, Unit.cups);
////                testFood.initMetadata(true, null);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid photo",e.getMessage());
//            }
//
//            byte[] testPic = new byte[0];
//            try{
//                testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//                testFood.initMetadata(true, testPic);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid photo",e.getMessage());
//            }
//
//            byte[] testPic1 ={(byte) 65};
//            try{
//                testFood.initDetails(1, "food", "lala", 5, Unit.cups);
//                testFood.initMetadata(true, testPic1);
//                fail("Should throw IO exception");
//            }
//            catch (Exception e){
//                assertTrue(e instanceof IOException);
//                assertEquals("Invalid photo",e.getMessage());
//            }
//        }
//
//
//    }
//
//
//    @Nested
//    @DisplayName("Edge case tests")
//    class Test_EdgeCases {
//        private Edible testFood;
//
//        @BeforeEach
//        void setUp() {
//            testFood = new Edible();
//        }
//        @Test
//        void testLeftEdge(){ // input is equal to minimum
//       testIntWithInput(testFood,0);
//        }
//
//
//        @Test
//        void testRightEdge(){ // input is equal to MAX
//            testIntWithInput(testFood,9999);
//        }
//
//        @Test
//        void testDBKey(){
//            try {
//                testFood.initDetails(0, "food", "lala", 5, Unit.cups);
//                assertEquals(0,testFood.getDbkey());
//            }
//            catch (Exception e)
//            {
//                fail("Should not throw execption, invalid DBkey");
//            }
//
//            try {
//                testFood.initDetails(Integer.MAX_VALUE, "food", "lala", 5, Unit.cups);
//                assertEquals(Integer.MAX_VALUE,testFood.getDbkey());
//            }
//            catch (Exception e)
//            {
//                fail("Should not throw execption, invalid DBkey");
//            }
//        }
//
//        @Test
//        void testQuality(){
//            try {
//                testFood.initDetails(5, "food", "lala", 1, Unit.cups);
//                assertEquals(1,testFood.getQuantity());
//            }
//            catch (Exception e)
//            {
//                fail("Should not throw execption, invalid Quality");
//            }
//
//            try {
//                testFood.initDetails(5, "food", "lala", 9999, Unit.cups);
//                assertEquals(9999, testFood.getQuantity());
//            }
//            catch (Exception e)
//            {
//                fail("Should not throw execption, invalid Quality");
//            }
//        }
//    }
//
//
//    @Nested
//    @DisplayName("Invalid tests")
//    class Test_Invalid {
//       private Edible testFood;
//
//       @BeforeEach
//       void setUp() {
//           testFood = new Edible();
//       }
//
//       @Test
//       void testSetDbkey()
//       {
//           try {
//               testFood.initDetails(-1, "food", "lala", 5, Unit.cups);
//               fail("Should throw IO exception, id <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid DB key",e.getMessage());
//           }
//       }
//
//       @Test
//       void testSetProtein()
//       {
//           try {
//               testFood.setProtein(-1);
//               fail("Should throw IO exception, Protein <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid protein value",e.getMessage());
//           }
//
//           try {
//               testFood.setProtein(10000);
//               fail("Should throw IO exception, Protein >9999 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid protein value",e.getMessage());
//           }
//       }
//
//       @Test
//       void testSetFat()
//       {
//           try {
//               testFood.setFat(-1);
//               fail("Should throw IO exception, Fat <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid fat value",e.getMessage());
//           }
//
//           try {
//               testFood.setFat(10000);
//               fail("Should throw IO exception, Fat > 9999 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid fat value",e.getMessage());
//           }
//       }
//
//       @Test
//       void testSetCarbs()
//       {
//           try {
//               testFood.setCarbs(-1);
//               fail("Should throw IO exception, Carbs <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid carb value",e.getMessage());
//           }
//
//           try {
//               testFood.setCarbs(10000);
//               fail("Should throw IO exception, Carbs > 9999 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid carb value",e.getMessage());
//           }
//       }
//
//       @Test
//       void testSetCalories()
//       {
//           try {
//               testFood.setCalories(-1);
//               fail("Should throw IO exception, Calories <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid calorie value",e.getMessage());
//           }
//
//           try {
//               testFood.setCalories(10000);
//               fail("Should throw IO exception, Calories > 9999 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid calorie value",e.getMessage());
//           }
//       }
//
//       @Test
//       void testSetBaseQuantity()
//       {
//           try {
//               testFood.setBaseQuantity(-1);
//               fail("Should throw IO exception, BaseQuantity <0 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid quantity",e.getMessage());
//           }
//
//           try {
//               testFood.setBaseQuantity(10000);
//               fail("Should throw IO exception, BaseQuantity > 9999 throw IO exception");
//           }
//           catch (Exception e)
//           {
//               assertTrue(e instanceof IOException);
//               assertEquals("Invalid quantity",e.getMessage());
//           }
//       }
//
//
//   }
//
//
//
//}
//
//
//
//
//
