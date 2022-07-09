// create by zhihao Zhou 7904125
// create at 2022/07/08

// the problem need to fix, problem about setInstructions()
// 1. cannot update the correct value to the drink
//      there have problem about the about a update the data form the array list
//      "my array of all input is nutrition is total in 100 but the update result is 40"
// 2.if the input ingredients arraylist the size is 0 should let all the Nutrition and categories to default
    // but some of Categories is not set to default

package comp3350.team10.objects;

 import comp3350.team10.objects.*;

 import comp3350.team10.objects.Edible.Unit;
 import comp3350.team10.objects.*;

 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;
 import org.junit.platform.suite.api.SelectClasses;

 import java.io.IOException;
 import java.security.spec.ECField;
 import java.util.ArrayList;


 public class TestDrinkUnit {

     void testIngredentsList(Drink drink, ArrayList<DrinkIngredient> expect){
         ArrayList<DrinkIngredient> getIngredients = drink.getIngredients();
         assertEquals(expect,getIngredients);
         assertEquals(expect.size(),getIngredients.size());
        for (int i = 0; i < expect.size();i++){
            assertEquals(expect.indexOf(i),getIngredients.indexOf(i));
        }

     }

//     void testNutrition(Edible food, int caloriesExpect, int proteinExpect, int carbsExpect, int fatExpect) {
//         assertEquals(caloriesExpect, food.getCalories());
//         assertEquals(proteinExpect, food.getProtein());
//         assertEquals(carbsExpect, food.getCarbs());
//         assertEquals(fatExpect, food.getFat());
//     }
//
//     void testCategories(Edible food, boolean alcoholicExpect, boolean spicyExpect, boolean veganExpect, boolean vegetarianExpect, boolean glutenFreeExpect) {
//         assertEquals(alcoholicExpect, food.getIsAlcoholic());
//         assertEquals(spicyExpect, food.getIsSpicy());
//         assertEquals(veganExpect, food.getIsVegan());
//         assertEquals(vegetarianExpect, food.getIsVegetarian());
//         assertEquals(glutenFreeExpect, food.getIsGlutenFree());
//     }
     @Nested
 	@DisplayName("Simple tests")
 	class Test_Simple {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Edible testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Edible();
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

//                 testNutrition(testDrink,15,35,45,10);
                 assertEquals(15, testDrink.getCalories());
                 assertEquals(35, testDrink.getProtein());
                 assertEquals(45, testDrink.getCarbs());
                 assertEquals(10, testDrink.getFat());

//                 testCategories(testDrink,true,false,false,false,false);
                 assertEquals(true, testDrink.getIsAlcoholic());
                 assertEquals(false, testDrink.getIsSpicy());
                 assertEquals(false, testDrink.getIsVegan());
                 assertEquals(false, testDrink.getIsVegetarian());
                 assertEquals(false, testDrink.getIsGlutenFree());

                 testFood.initNutrition(10,10,10,10);
                 testFood.initCategories(false,false,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testDrink.setIngredients(testIngredients);

                 testIngredentsList(testDrink,testIngredients);

             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }
//             testNutrition(testDrink,10,10,10,10);
             assertEquals(10, testDrink.getCalories());
             assertEquals(10, testDrink.getProtein());
             assertEquals(10, testDrink.getCarbs());
             assertEquals(10, testDrink.getFat());

//             testCategories(testDrink,false,false,false,false,true);
             assertEquals(false, testDrink.getIsAlcoholic());
             assertEquals(false, testDrink.getIsSpicy());
             assertEquals(false, testDrink.getIsVegan());
             assertEquals(false, testDrink.getIsVegetarian());
             assertEquals(true, testDrink.getIsGlutenFree());
         }


     }

     @Nested
     @DisplayName("Complex tests")
     class Test_Complex {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Edible testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Edible();
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
//                 testNutrition(testDrink,15,35,45,10);
                 assertEquals(15, testDrink.getCalories());
                 assertEquals(35, testDrink.getProtein());
                 assertEquals(45, testDrink.getCarbs());
                 assertEquals(10, testDrink.getFat());

//                 testCategories(testDrink,true,false,false,false,false);
                 assertEquals(true, testDrink.getIsAlcoholic());
                 assertEquals(false, testDrink.getIsSpicy());
                 assertEquals(false, testDrink.getIsVegan());
                 assertEquals(false, testDrink.getIsVegetarian());
                 assertEquals(false, testDrink.getIsGlutenFree());

                //set 4 ingredient
                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(10,10,10,10);
                 testFood.initCategories(false,false,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(20,20,20,20);
                 testFood.initCategories(false,false,false,true,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(30,30,30,30);
                 testFood.initCategories(false,false,true,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(40,40,40,40);
                 testFood.initCategories(false,true,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);
                 //set 4 ingredient

                 System.out.println("there are "+testIngredients.size() + " Drink obj in the list");

                 testDrink.setIngredients(testIngredients);
                 // problem mark 1
                 // there have problem about the about a update the data form the array list
                 fail( "my array of all input is nutrition is total in 100 but the update result is 40");


             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }
//             testNutrition(testDrink,100,100,100,100);
             assertEquals(100, testDrink.getCalories());
             assertEquals(100, testDrink.getProtein());
             assertEquals(100, testDrink.getCarbs());
             assertEquals(100, testDrink.getFat());

//             testCategories(testDrink,false,true,true,true,true);
             assertEquals(false, testDrink.getIsAlcoholic());
             assertEquals(true, testDrink.getIsSpicy());
             assertEquals(true, testDrink.getIsVegan());
             assertEquals(true, testDrink.getIsVegetarian());
             assertEquals(true, testDrink.getIsGlutenFree());

             testIngredentsList(testDrink,testIngredients);



         }


     }

     @Nested
     @DisplayName("Edge tests")
     class Test_Edge {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Edible testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Edible();
             testDrinkIngredient = new DrinkIngredient();
             testIngredients = new ArrayList<DrinkIngredient>();

         }


         @Test
         void test_setIngredient()
         {
             try {
                 testDrink.initNutrition(15,35,45,10);
                 testDrink.initCategories(true,false,false,false,false);
//                 testNutrition(testDrink,15,35,45,10);
                 assertEquals(15, testDrink.getCalories());
                 assertEquals(35, testDrink.getProtein());
                 assertEquals(45, testDrink.getCarbs());
                 assertEquals(10, testDrink.getFat());

//                 testCategories(testDrink,true,false,false,false,false);
                 assertEquals(true, testDrink.getIsAlcoholic());
                 assertEquals(false, testDrink.getIsSpicy());
                 assertEquals(false, testDrink.getIsVegan());
                 assertEquals(false, testDrink.getIsVegetarian());
                 assertEquals(false, testDrink.getIsGlutenFree());

                 //set 2 ingredient
                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(9999,9999,9999,9999);
                 testFood.initCategories(false,false,false,false,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);

                 testFood = new Edible();
                 testDrinkIngredient = new DrinkIngredient();
                 testFood.initNutrition(20,20,20,20);
                 testFood.initCategories(false,false,false,true,true);
                 testDrinkIngredient.setIngredient(testFood);
                 testIngredients.add(testDrinkIngredient);
                 //set 2 ingredient

                 testIngredients.add(testDrinkIngredient);

                 testDrink.setIngredients(testIngredients);

             }
             catch (Exception e)
             {
                 fail("Should not throw exception");
             }
             // problem mark 1
//             testNutrition(testDrink,9999,9999,9999,9999);
             assertEquals(9999, testDrink.getCalories());
             assertEquals(9999, testDrink.getProtein());
             assertEquals(9999, testDrink.getCarbs());
             assertEquals(9999, testDrink.getFat());

//             testCategories(testDrink,false,false,false,true,true);
             assertEquals(false, testDrink.getIsAlcoholic());
             assertEquals(false, testDrink.getIsSpicy());
             assertEquals(false, testDrink.getIsVegan());
             assertEquals(true, testDrink.getIsVegetarian());
             assertEquals(true, testDrink.getIsGlutenFree());
         }


     }

     @Nested
     @DisplayName("Empty case")
     class Test_Empty {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Edible testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Edible();
             testDrinkIngredient = new DrinkIngredient();
             testIngredients = new ArrayList<DrinkIngredient>();

         }

         @Test
         void test_setInstructions(){ // null string
             try {
                 testDrink.setInstructions(null);
                 fail("should not success set a null instruction string to the drink");
             }
             catch (Exception e){
                 assertTrue(e instanceof IOException);
                 assertEquals("Invalid instructions",e.getMessage());
             }

             try {
                 testDrink.setInstructions("");

             }
             catch (Exception e){
                 fail("should success set a empty instruction string to the drink");
             }
             assertEquals("",testDrink.getInstructions());

         }


         @Test
         void test_setIngredient()
         {
             try {
                 testDrink.setIngredients(null);
                 fail("should  not success set a null ingredients arraylist to drink");
             }
             catch ( Exception e){
                 assertTrue(e instanceof IOException);
                 assertEquals("Invalid drink ingredients",e.getMessage());
             }

             try {
                 testDrink.setIngredients(testIngredients);
             }
             catch ( Exception e){
                 fail("should success set a empty ingredients arraylist to drink");
             }
             // problem mark 2
             //if the input ingredients arraylist the size is 0 should let all the Nutrition and categories to default
             // but some of Categories is not set to default

//             testNutrition(testDrink,0,0,0,0);
             assertEquals(0, testDrink.getCalories());
             assertEquals(0, testDrink.getProtein());
             assertEquals(0, testDrink.getCarbs());
             assertEquals(0, testDrink.getFat());

//             testCategories(testDrink,false,false,false,false,false);
             assertEquals(false, testDrink.getIsAlcoholic());
             assertEquals(false, testDrink.getIsSpicy());
             assertEquals(false, testDrink.getIsVegan());
             assertEquals(false, testDrink.getIsVegetarian());
             assertEquals(false, testDrink.getIsGlutenFree());
         }


     }

     @Nested
     @DisplayName("Invalid case")
     class Test_Invalid {

         private Drink testDrink;
         // this is for Edible class for Ingredient class
         private Edible testFood;
         // this is for Edible class for Ingredient class
         private DrinkIngredient testDrinkIngredient;
         private ArrayList<DrinkIngredient> testIngredients;

         @BeforeEach
         void setup(){
             testDrink = new Drink();
             testFood = new Edible();
             testDrinkIngredient = new DrinkIngredient();
             testIngredients = new ArrayList<DrinkIngredient>();

         }



         @Test
         void test_setIngredient()
         {
             testIngredients.add(null);
             try{
                 testDrink.setIngredients(testIngredients);
                 fail("there can not have null obj in ingredients list");
             }
             catch ( Exception e){
                 assertTrue(e instanceof IOException);
                 assertEquals("Invalid drink ingredients",e.getMessage());
             }
         }


     }

 }



