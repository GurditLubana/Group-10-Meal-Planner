 package comp3350.team10.objects;

 import comp3350.team10.objects.*;
 import comp3350.team10.objects.ListItem.FragmentType;
 import comp3350.team10.objects.Edible.Unit;
 import comp3350.team10.persistence.SharedDB;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import java.util.Set;


 //import static org.junit.Assert.*;
 //import org.junit.Test;

 public class FoodUnitTest {
 	@Nested
 	@DisplayName("Initial Object State")
 	class Building {
 		Edible testFood;
 		@BeforeEach
 		void SetUp(){
			 testFood = new Food();
			 testFood.init("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
 		}

 		@Test
 		void testGetValues(){
 			assertEquals(450, testFood.getCalories());
 			assertTrue("pasta" == testFood.getName());
 			assertTrue(5 == testFood.getQuantity());
 			assertTrue(2 == testFood.getIconPath());
 			assertTrue(testFood.getFragmentType() == FragmentType.diaryEntry);
 		}

 		@Test
 		void nameNotNull(){
 			assertTrue(testFood.getName() != null);
 		}

 		@Test
 		void nameNotEmpty(){
 			assertFalse(testFood.getName() == "");
 		}
 		@Test
 		void testCalories(){
 			assertTrue(testFood.getCalories() >= 0);
 			assertTrue( testFood.getCalories() <= 9999);
 		}

 		@Test
 		void testgetQuantity(){
 			assertTrue(testFood.getQuantity() >= 0);
 			assertTrue( testFood.getQuantity() <= 9999);
 		}

 		@Test
 		void testIconPath(){
 			assertTrue(testFood.getIconPath() >= 0);
 			assertTrue( testFood.getIconPath() <= 9999);
 		}

 		@Test
 		void testDbKey(){
 			assertTrue(testFood.getDbkey() >= 0);
 			assertTrue(testFood.getDbkey() <= 9999);
 		}

 	}
 	@Nested
 	@DisplayName("Edible Tests")
 	class EdibleTests{

 		Food SetFood;
 		@BeforeEach
 		void SetUP(){
			 SetFood = new Food();
			 SetFood.init("Burger", 55, 300, FragmentType.diaryEntry, Unit.liter, 15, 3);
 		}
 		@Test
 		void TestsetCalories(){
 			int currCal = SetFood.getCalories();
 			SetFood.modifyCalories(350);
 			assertTrue(SetFood.getCalories() != currCal);
 			assertTrue(SetFood.getCalories() == (currCal + 350));
 		}
 		@Test
 		void TestFragmentSetter(){
 			ListItem.FragmentType curr = SetFood.getFragmentType();
 			SetFood.setFragmentType(FragmentType.cardSelection);
 			assertTrue(SetFood.getFragmentType() != curr);
 			assertTrue(SetFood.getFragmentType() == FragmentType.cardSelection);
 		}

 		@Test
 		void TestCalorieSetter(){
 			int curr = SetFood.getCalories();
 			SetFood.setCalories(250);
 			assertTrue(SetFood.getCalories() != curr);
 			assertTrue(SetFood.getCalories() == 250);
 		}

 		@Test
 		void TestQuantitySetter(){
 			int currQuantity = SetFood.getQuantity();
 			SetFood.setQuantity(100);
 			assertTrue(SetFood.getQuantity() != currQuantity);
 			assertTrue(SetFood.getQuantity() == 100);
 		}
 		@Test
 		void TestBaseSetter(){
 			Unit currBaseUnit = SetFood.getBaseUnit();
 			SetFood.setBaseUnit(Unit.serving);
 			assertTrue(SetFood.getBaseUnit() != currBaseUnit);
 			assertTrue(SetFood.getBaseUnit() == Unit.serving);
 		}

 		@Test
 		void TestSetDb(){
 			int currDb = SetFood.getDbkey();
 			SetFood.setDbkey(1);
 			assertTrue(SetFood.getDbkey() != currDb);
 			assertTrue(SetFood.getDbkey() == 1);
 		}

 		@Test
 		void TestSetName(){
 			String currName = SetFood.getName();
 			SetFood.setName("NewFood");
 			assertTrue(SetFood.getName() != currName);
 			assertTrue(SetFood.getName() == "NewFood");
 		}

 		@Test
 		void testIconSetter(){
 			int currpath = SetFood.getIconPath();
 			SetFood.setIconPath(30);
 			assertTrue(SetFood.getIconPath() != currpath);
 			assertTrue(SetFood.getIconPath() == 30);
 		}
 	}




 /*
 	public void test1() {
 		String[] testInstruct = {"1","2","3"};
 		String[] testIngredients = {"banana","apple","peach"};
		
 		Food testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
 		assertEquals(450, testFood.getCalories());
 		//test macro
 		Macros testMacros = testFood.getMacros();
 		assertTrue(testMacros.getCarbs() <= 15);
 		assertTrue(testMacros.getCarbs() >= 1);
		
 		assertTrue(testMacros.getFat() <= 15);
 		assertTrue(testMacros.getFat() >= 1);
		
 		assertTrue(testMacros.getProtein() <= 15);
 		assertTrue(testMacros.getProtein() >= 1);
 		//test macro
		
 		Food testFood1 = new Food("pasta", 2);
		
 		assertTrue(testFood1.getCalories() <= 300);
 		assertTrue(testFood1.getCalories() >= 25);
 	}

  */
	

 }

