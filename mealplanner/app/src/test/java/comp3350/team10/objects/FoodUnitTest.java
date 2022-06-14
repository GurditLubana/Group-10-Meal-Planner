package comp3350.team10.objects;
//macro is also test in this class
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
		Food testFood;
		Food SetFood;

		@BeforeEach
		void SetUp(){
			testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
			SetFood = new Food("pasta", 2);
		}

		@Test
		void testGetValues(){
			assertFalse(0 == testFood.getCalories());
			assertEquals(450, testFood.getCalories());
			assertTrue("pasta" == testFood.getName());
			assertTrue(5 == testFood.getQuantity());
			assertTrue(2 == testFood.getIconPath());
			assertTrue(testFood.getFragmentType() == FragmentType.diaryEntry);
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

