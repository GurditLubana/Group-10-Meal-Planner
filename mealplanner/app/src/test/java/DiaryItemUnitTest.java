
import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.ListItem.Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Calendar;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class DiaryItemUnitTest {
	@Test
	public void test1() {
		
		Food testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
		
		DiaryItem test1 = new DiaryItem();
		assertEquals(FragmentType.diaryEntry, test1.getFragmentType());
		assertEquals(null, test1.getItem());
//		System.out.print(test1.getDate());
//		assertTrue(Calendar.getInstance().getTime().equals(test1.getDate()) ); // this is the current time include to the sec of a day not just the date,cannot test it
		//because the time will different when the after create the object
		
		//here is test the day month and year
		Date today = new Date();
		assertEquals(today.getDate(), test1.getDate().getDate());
		assertEquals(today.getMonth(), test1.getDate().getMonth());
		assertEquals(today.getYear(), test1.getDate().getYear());
		//here is test the day month and year
		assertEquals(0, test1.getKey());
		assertEquals(null, test1.getBaseUnit());
		assertEquals(0, test1.getQuantity());
		
		
		Date testDate = new Date();
		test1 = new DiaryItem(testFood,testDate,3);
		assertEquals(FragmentType.diaryEntry, test1.getFragmentType());
		assertNotNull(test1.getItem());
		assertTrue(test1.getItem() instanceof Food);
		assertTrue( test1.getDate().equals(testDate));
		assertEquals(3, test1.getKey());
		assertEquals(null, test1.getBaseUnit());
		assertEquals(0, test1.getQuantity());
		
		
		testDate = new Date();
		test1 = new DiaryItem(FragmentType.diaryAdd,testFood,testDate,4);
		assertEquals(FragmentType.diaryAdd, test1.getFragmentType());
		assertNotNull(test1.getItem());
		assertTrue(test1.getItem() instanceof Food);
		assertTrue( test1.getDate().equals(testDate));
		assertEquals(4, test1.getKey());
		assertEquals(null, test1.getBaseUnit());
		assertEquals(0, test1.getQuantity());
		

	}
	

}