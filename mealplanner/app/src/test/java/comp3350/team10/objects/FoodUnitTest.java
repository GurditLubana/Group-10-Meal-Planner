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
 			assertTrue(testFood.getCalories() >= Constant.ENTRY_MIN_VALUE);
 			assertTrue( testFood.getCalories() <= Constant.ENTRY_MAX_VALUE);
 		}

 		@Test
 		void testgetQuantity(){
 			assertTrue(testFood.getQuantity() >= Constant.ENTRY_MIN_VALUE);
 			assertTrue( testFood.getQuantity() <= Constant.ENTRY_MAX_VALUE);
 		}

 		@Test
 		void testIconPath(){
 			assertTrue(testFood.getIconPath() >= Constant.ENTRY_MIN_VALUE);
 			assertTrue( testFood.getIconPath() <= Constant.ENTRY_MAX_VALUE);
 		}

 		@Test
 		void testDbKey(){
 			assertTrue(testFood.getDbkey() >= Constant.ENTRY_MIN_VALUE);
 			assertTrue(testFood.getDbkey() <= Constant.ENTRY_MAX_VALUE);
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
			 int addCal = 350;
 			SetFood.modifyCalories(addCal);
 			assertTrue(SetFood.getCalories() != currCal);
 			assertTrue(SetFood.getCalories() == (currCal + addCal));
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
			 int setCal = 250;
 			SetFood.setCalories(setCal);
 			assertTrue(SetFood.getCalories() != curr);
 			assertTrue(SetFood.getCalories() == setCal);
 		}

 		@Test
 		void TestQuantitySetter(){
 			int currQuantity = SetFood.getQuantity();
			int setQty = 100;
 			SetFood.setQuantity(setQty);
 			assertTrue(SetFood.getQuantity() != currQuantity);
 			assertTrue(SetFood.getQuantity() == setQty);
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
			int dbkey = 1;
 			SetFood.setDbkey(dbkey);
 			assertTrue(SetFood.getDbkey() != currDb);
 			assertTrue(SetFood.getDbkey() == dbkey);
 		}

 		@Test
 		void TestSetName(){
 			String currName = SetFood.getName();
			String setName = "NewFood";
 			SetFood.setName(setName);
 			assertTrue(SetFood.getName() != currName);
 			assertTrue(SetFood.getName() == setName);
 		}

 		@Test
 		void testIconSetter(){
 			int currpath = SetFood.getIconPath();
			int setPath = 30;
 			SetFood.setIconPath(setPath);
 			assertTrue(SetFood.getIconPath() != currpath);
 			assertTrue(SetFood.getIconPath() == setPath);
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

