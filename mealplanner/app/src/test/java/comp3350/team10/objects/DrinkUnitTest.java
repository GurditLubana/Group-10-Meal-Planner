// package comp3350.team10.objects;

// import comp3350.team10.objects.*;
// import comp3350.team10.objects.ListItem.FragmentType;
// import comp3350.team10.objects.Edible.Unit;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;


// import static org.junit.Assert.*;
// import org.junit.Test;

// public class DrinkUnitTest {
// 	private Drink testDrink;

// 	@BeforeEach
// 	void setUp(){
// 		testDrink = new Drink();
// 	}

// 	@Test
// 	private void Test_Simple() {
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryModify, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.recipe, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.cardSelection, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.noType, Edible.Unit.cups, 5, 1));

// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryModify, Edible.Unit.oz, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryAdd, Edible.Unit.g, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.recipe, Edible.Unit.serving, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.cardSelection, Edible.Unit.tbsp, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.noType, Edible.Unit.tsp, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.cardSelection, Edible.Unit.ml, 5, 1));
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.noType, Edible.Unit.liter, 5, 1));
		
// 		assertTrue(testDrink.getName() == "drink");
// 		assertTrue(testDrink.getIconPath() == 1);
// 		assertTrue(testDrink.getFragmentType() == ListItem.FragmentType.noType);
// 		assertTrue(testDrink.getBaseUnit() == Edible.Unit.liter);
// 		assertTrue(testDrink.getQuantity() == 5);
// 		assertTrue(testDrink.getDbkey() == 1);
		
// 		assertTrue(testDrink.changeInstructions(new String[] {"you"}));
// 		assertTrue(testDrink.changeInstructions(new String[] {"you", "should"}));

// 		assertTrue(testDrink.modifyCalories(100));
// 		assertTrue(testDrink.getCalories() == 100);
// 		assertTrue(testDrink.modifyCalories(-5));
// 		assertTrue(testDrink.getCalories() == 95);

// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.cups));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.oz));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.g));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.serving));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.tbsp));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.tsp));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.ml));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.l));
		
// 		assertTrue(testDrink.setCalories(100));
// 		assertTrue(testDrink.getCalories() == 100);
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.cups));

// 		assertTrue(testDrink.setDbkey(1));
// 		assertTrue(testDrink.setDbkey(10));
		
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.diaryModify));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.diaryAdd));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.recipe));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.cardSelection));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.noType));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.cardSelection));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.noType));

// 		assertTrue(testDrink.setIconPath(5));
// 		assertTrue(testDrink.setIconPath(15));

// 		assertTrue(testDrink.setIngredients(new String[] {"you"}));
// 		assertTrue(testDrink.setIngredients(new String[] {"you", "should"}));

// 		assertTrue(testDrink.setName("name"));
// 		assertTrue(testDrink.setQuantity(100));
// 		assertTrue(testDrink.getQuantity() == 100);
// 	}

// 	@Test
// 	private void Test_Complex() {
// 		assertTrue(testDrink.init("drink", 1, ListItem.FragmentType.diaryEntry, Edible.Unit.cups, 5, 1));
		
// 		assertTrue(testDrink.getName() == "drink");
// 		assertTrue(testDrink.getIconPath() == 1);
// 		assertTrue(testDrink.getFragmentType() == ListItem.FragmentType.noType);
// 		assertTrue(testDrink.getBaseUnit() == Edible.Unit.liter);
// 		assertTrue(testDrink.getQuantity() == 5);
// 		assertTrue(testDrink.getDbkey() == 1);

// 		assertTrue(testDrink.changeInstructions(new String[] {"you"}));

// 		assertTrue(testDrink.modifyCalories(100));
// 		assertTrue(testDrink.setBaseUnit(Edible.Unit.l));
		
// 		assertTrue(testDrink.setCalories(100));
// 		assertTrue(testDrink.setFragmentType(ListItem.FragmentType.noType));

// 		assertTrue(testDrink.setIconPath(5));
// 		assertTrue(testDrink.setIconPath(15));
// 		assertTrue(testDrink.setName("name"));
// 		assertTrue(testDrink.setQuantity(100));

// 		assertTrue(testDrink.setIngredients(new String[] {"you"}));



// 		assertTrue(testDrink.modifyCalories(-94));
// 		assertTrue(testDrink.getCalories() == 1);
// 	}

// 	@Test
// 	private void Test_Empty() {
	
// 	}
	
// 	@Test
// 	private void Test_EdgeCases() {
	
// 	}
	
// 	@Test
// 	private void Test_Invalid() {
		
// 	}
// 	public void test1() {
// 		String[] testInstruct = {"1","2","3"};
// 		String[] testIngredients = {"banana","apple","peach"};
		
// 		DrinkIngredient testDrinkIngredient0 = new DrinkIngredient("juice", 12.25, "3", true, false);
// 		DrinkIngredient testDrinkIngredient1 = new DrinkIngredient("apple", 12.5, "2", false, false);
// 		DrinkIngredient testDrinkIngredient2 = new DrinkIngredient("cake", 5.43, "1", true, true);
		
// 		DrinkIngredient testDrinkIngredient4 = new DrinkIngredient("apple", 12.5, "2", false, false);
// 		DrinkIngredient testDrinkIngredient5 = new DrinkIngredient("cake", 5.43, "1", true, true);
		
// 		DrinkIngredient[] testDrinkIngredientList = new DrinkIngredient[3];
// 		testDrinkIngredientList[0] = testDrinkIngredient0;
// 		testDrinkIngredientList[1] = testDrinkIngredient1;
// 		testDrinkIngredientList[2] = testDrinkIngredient2;
		
// 		DrinkIngredient[] testDrinkIngredientList1 = new DrinkIngredient[2];
// 		testDrinkIngredientList1[0] = testDrinkIngredient4;
// 		testDrinkIngredientList1[1] = testDrinkIngredient5;
		
		
// 		Drink testDrink0 = new Drink("water", 1, testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
		
		
// 		assertEquals("water", testDrink0.getName());
// 		assertEquals(1, testDrink0.getIconPath());
// 		assertEquals(0, testDrink0.getCalories());
		
// 		DrinkIngredient[] getDrinkIngredientList = testDrink0.getIngredients();
		
// 		for (int i = 0; i < getDrinkIngredientList.length; i++) {
// 			assertEquals(testDrinkIngredientList[i].getName(), getDrinkIngredientList[i].getName());
// 			assertEquals(testDrinkIngredientList[i].isAlcoholic(), getDrinkIngredientList[i].isAlcoholic());
// 		}
		
// 		testDrink0.changeIngredients(testDrinkIngredientList1);
		
// 		getDrinkIngredientList = testDrink0.getIngredients();
		
// 		for (int i = 0; i < getDrinkIngredientList.length; i++) {
// 			assertEquals(testDrinkIngredientList1[i].getName(), getDrinkIngredientList[i].getName());
// 			assertEquals(testDrinkIngredientList1[i].isAlcoholic(), getDrinkIngredientList[i].isAlcoholic());
// 		}
		
		
// 		Drink testDrink1 = new Drink("water", 1, 564,testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
		
// 	}
// }

