package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;
import comp3350.team10.persistence.DataAccessStub;
import comp3350.team10.persistence.SharedDB;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitConverterTests {

    @Nested
    @DisplayName("All conversions of per unit Table spoon")
    class tableSpoon {

        @Test
        void convertPerTbspToPerGram() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 1);

            Integer expected = 10;
           assertEquals(expected, result);


        }

        @Test
        void convertPerTbspToPerServing() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 1);
            UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

            Integer result2 = prevValue2.getCalories(Edible.Unit.serving, 1);


            Integer expected = 2500;
            assertEquals(expected,result2);


        }


        @Test
        void convertPerTbspToPerTsp() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 1);
            UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

            Integer result2 = prevValue2.getCalories(Edible.Unit.tsp, 1);


            Integer expected = 50;
            assertEquals(expected,result2);


        }

        @Test
        void convertPerTbspToPerLiter() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 1);
            UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

            Integer result2 = prevValue2.getCalories(Edible.Unit.liter, 1);


            Integer expected = 10;
            assertEquals(expected,result2);


        }

        @Test
        void convertPerTbspToPerOz() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 1);
            UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

            Integer result2 = prevValue2.getCalories(Edible.Unit.serving, 1);


            Integer expected = 2500;
            assertEquals(expected,result2);


        }

        @Test
        void convertPerTspToPerGram() {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp, 3, 144);
            Integer result = prevValue.getCalories(Edible.Unit.g, 40);

            Integer expected = 384;
            assertEquals(expected, result);

        }

        @Test
        void convertPerLiterToPerGram()
        {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.liter,2, 700);
            Integer result = prevValue.getCalories(Edible.Unit.g,400);

            Integer expected = 140;
            assertEquals(expected,result);

        }

        @Test
        void convertPerCupToPerGram()
        {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp,1, 774);
            Integer result = prevValue.getCalories(Edible.Unit.g,400);

            Integer expected = 61920;
            assertEquals(expected,result);
        }

        @Test
        void convertPerMLtoPerGram()
        {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.ml,100, 52);
            Integer result = prevValue.getCalories(Edible.Unit.g,400);

            System.out.println(result);

            Integer expected = 208;
            assertEquals(expected,result);
        }

        @Test
        void convertPerServingToPerGram()
        {
            UnitConverter prevValue = new UnitConverter(Edible.Unit.serving,3, 840);
            Integer result = prevValue.getCalories(Edible.Unit.g,400);

            System.out.println(result);

            Integer expected = 448;
            assertEquals(expected,result);
        }

    }
}