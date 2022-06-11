package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.ListItem.Unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class ExerciseUnitTest {
	@Test
	public void test1() {
		Exercise testExercise = new Exercise("walk","just walk",10,3,100);
		
		assertEquals("walk", testExercise.getName());
		assertEquals("just walk", testExercise.getDescription());
		assertEquals(10, testExercise.getReps());
		assertEquals(3, testExercise.getSets());
		assertEquals(100, testExercise.getCalories());
		
	}
	

}


