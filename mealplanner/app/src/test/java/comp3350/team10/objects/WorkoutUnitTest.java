import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.ListItem.Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;


public class WorkoutUnitTest {
	@Test
	public void testGetter() {
		Exercise[] testListExercises = new Exercise[3];
		
		Exercise testExercise = new Exercise("walk","just walk",10,3,100);
		Exercise testExercise1 = new Exercise("run","just run",12,4,200);
		Exercise testExercise2 = new Exercise("fast walk","just fast walk",15,5,150);
		
		testListExercises[0] = testExercise;
		testListExercises[1] = testExercise1;
		testListExercises[2] = testExercise2;
		
		Workout testWorkout = new Workout(testListExercises);
		
		Exercise[] returtnExercises = testWorkout.getExercises();
		
		for (int i = 0; i < returtnExercises.length; i++) {
			assertEquals(testListExercises[i].getName(), returtnExercises[i].getName());
			assertEquals(testListExercises[i].getDescription(), returtnExercises[i].getDescription());
			assertEquals(testListExercises[i].getReps(), returtnExercises[i].getReps());
			assertEquals(testListExercises[i].getSets(), returtnExercises[i].getSets());
			assertEquals(testListExercises[i].getCalories(), returtnExercises[i].getCalories());

		}
		
		assertEquals(450,testWorkout.getCaloriesBurnt());
		
		// no getter for the complete
	}
	


	@Test
	public void testSetter() {
		
Exercise[] testListExercises = new Exercise[3];
		
		Exercise testExercise = new Exercise("walk","just walk",10,3,100);
		Exercise testExercise1 = new Exercise("run","just run",12,4,200);
		Exercise testExercise2 = new Exercise("fast walk","just fast walk",15,5,150);
		
		testListExercises[0] = testExercise;
		testListExercises[1] = testExercise1;
		testListExercises[2] = testExercise2;
		
		Workout testWorkout = new Workout(testListExercises);
		
		testWorkout.modifyComplete(true);
		
		// no getter for the complete 
		
	
	}

}

