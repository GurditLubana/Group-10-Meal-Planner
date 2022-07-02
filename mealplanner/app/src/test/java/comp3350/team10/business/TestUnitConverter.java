// package comp3350.team10.business;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;

// import java.time.temporal.ChronoUnit;
// import java.util.Calendar;
// import java.util.ArrayList;

// import comp3350.team10.objects.DailyLog;
// import comp3350.team10.objects.Edible;
// 
// import comp3350.team10.persistence.DataAccessStub;
// import comp3350.team10.persistence.SharedDB;

// public class TestUnitConverter {

//     @Nested
//     @DisplayName("Simple tests")
//     class Test_Simple {
//         private UnitConverter converter;
//         private int results;

//         @BeforeEach
//         void setup(){
//             converter = null;
//             results = -1;
//         }

//         @Test
//         void testGToG() {
//             converter = new UnitConverter(Edible.Unit.g, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(5 * 224.0 * (10/10)));

//             converter = new UnitConverter(Edible.Unit.g, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(15 * 224.0 * (10/10)));

//             converter = new UnitConverter(Edible.Unit.g, 10, 10);
//             results = converter.getCalories(Edible.Unit.g, 5);
//             assertEquals(results, Math.round(5 * (10/10)));

//             converter = new UnitConverter(Edible.Unit.g, 10, 10);
//             results = converter.getCalories(Edible.Unit.g, 15);
//             assertEquals(results, Math.round(15 * (10/10)));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.g, 5);
//             assertEquals(results, Math.round(1/224.0 * (10/10) * 5 * (10/10)));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.g, 15);
//             assertEquals(results, Math.round(1/224.0 * (10/10) * 15 * (10/10)));
//         }

//         @Test
//         void testGToOz() {
//             converter = new UnitConverter(Edible.Unit.oz, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/28.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.oz, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/28.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.oz, 10, 10);
//             results = converter.getCalories(Edible.Unit.oz, 5);
//             assertEquals(results, Math.round(1/28.0 * (10/10) * 5 * (10/10) * 28.0));

//             converter = new UnitConverter(Edible.Unit.oz, 10, 10);
//             results = converter.getCalories(Edible.Unit.oz, 15);
//             assertEquals(results, Math.round(1/28.0 * (10/10) * 15 * (10/10) * 28.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.oz, 5);
//             assertEquals(results, Math.round(1/224.0 * (10/10) * 5 * (10/10) * 28.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.oz, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 28.0));
//         }

//         @Test
//         void testGToServing() {
//             converter = new UnitConverter(Edible.Unit.serving, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/250.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.serving, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/250.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.serving, 10, 10);
//             results = converter.getCalories(Edible.Unit.serving, 5);
//             assertEquals(results, Math.round(1/250.0 * (10/10) * 5 * (10/10) * 250.0));

//             converter = new UnitConverter(Edible.Unit.serving, 10, 10);
//             results = converter.getCalories(Edible.Unit.serving, 15);
//             assertEquals(results, Math.round(1/250.0 * (10/10) * 15 * (10/10) * 250.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.serving, 5);
//             assertEquals(results, Math.round(1/224.0 * 5 * 250.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.serving, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 250.0));
//         }

//         @Test
//         void testGToTbsp() {
//             converter = new UnitConverter(Edible.Unit.tbsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/14.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.tbsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/14.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.tbsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.tbsp, 5);
//             assertEquals(results, Math.round(1/14.0 * (10/10) * 5 * (10/10) * 14.0));

//             converter = new UnitConverter(Edible.Unit.tbsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.tbsp, 15);
//             assertEquals(results, Math.round(1/14.0 * (10/10) * 15 * (10/10) * 14.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.tbsp, 5);
//             assertEquals(results, Math.round(1/224.0 * 5 * 14.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.tbsp, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 14.0));
//         }

//         @Test
//         void testGToTsp() {
//             converter = new UnitConverter(Edible.Unit.tsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/5.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.tsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/5.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.tsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.tsp, 5);
//             assertEquals(results, Math.round(1/5.0 * (10/10) * 5 * (10/10) * 5.0));

//             converter = new UnitConverter(Edible.Unit.tsp, 10, 10);
//             results = converter.getCalories(Edible.Unit.tsp, 15);
//             assertEquals(results, Math.round(1/5.0 * (10/10) * 15 * (10/10) * 5.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.tsp, 5);
//             assertEquals(results, Math.round(1/224.0 * 15 * 5.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.tsp, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 5.0));
//         }

//         @Test
//         void testGToMl() {
//             converter = new UnitConverter(Edible.Unit.ml, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/1.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.ml, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/1.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.ml, 10, 10);
//             results = converter.getCalories(Edible.Unit.ml, 5);
//             assertEquals(results, Math.round(1/1.0 * (10/10) * 5 * (10/10) * 1.0));

//             converter = new UnitConverter(Edible.Unit.ml, 10, 10);
//             results = converter.getCalories(Edible.Unit.ml, 15);
//             assertEquals(results, Math.round(1/1.0 * (10/10) * 15 * (10/10) * 1.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.ml, 5);
//             assertEquals(results, Math.round(1/224.0 * 5 * 1.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.ml, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 1.0));
//         }

//         @Test
//         void testGToL() {
//             converter = new UnitConverter(Edible.Unit.liter, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 5);
//             assertEquals(results, Math.round(1/1000.0 * (10/10) * 5 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.liter, 10, 10);
//             results = converter.getCalories(Edible.Unit.cups, 15);
//             assertEquals(results, Math.round(1/1000.0 * (10/10) * 15 * (10/10) * 224.0));

//             converter = new UnitConverter(Edible.Unit.liter, 10, 10);
//             results = converter.getCalories(Edible.Unit.liter, 5);
//             assertEquals(results, Math.round(1/1000.0 * (10/10) * 5 * (10/10) * 1000.0));

//             converter = new UnitConverter(Edible.Unit.liter, 10, 10);
//             results = converter.getCalories(Edible.Unit.liter, 15);
//             assertEquals(results, Math.round(1/1000.0 * (10/10) * 15 * (10/10) * 1000.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.liter, 5);
//             assertEquals(results, Math.round(1/224.0 * 5 * 1000.0));

//             converter = new UnitConverter(Edible.Unit.cups, 10, 10);
//             results = converter.getCalories(Edible.Unit.liter, 15);
//             assertEquals(results, Math.round(1/224.0 * 15 * 1000.0));
//         }
//     }

//     @Nested
//     @DisplayName("Complex tests")
//     class Test_Complex {
//         private UnitConverter converter;
//         private int results;

//         @BeforeEach
//         void setup(){
//             converter = null;
//             results = -1;
//         }
//     }

//     @Nested
//     @DisplayName("Empty tests")
//     class Test_Empty {
//         private UnitConverter converter;
//         private int results;

//         @BeforeEach
//         void setup(){
//             converter = null;
//             results = -1;
//         }
//     }

//     @Nested
//     @DisplayName("Edge case tests")
//     class Test_EdgeCase {
//         private UnitConverter converter;
//         private int results;

//         @BeforeEach
//         void setup(){
//             converter = null;
//             results = -1;
//         }
//     }

//     @Nested
//     @DisplayName("Invalid tests")
//     class Test_Invalid {
//         private UnitConverter converter;
//         private int results;

//         @BeforeEach
//         void setup(){
//             converter = null;
//             results = -1;
//         }
//     }
// }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// //     @Nested
// //     @DisplayName("All these conversions tests should pass ")
// //     class shouldPassTests {

// //         @Test
// //         void convertPerTbspToPerGram() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);

// //             Integer expected = 10;
// //            assertEquals(expected, result);


// //         }

// //         @Test
// //         void convertPerTbspToPerServing() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.serving, 1);


// //             Integer expected = 2500;
// //             assertEquals(expected,result2);


// //         }


// //         @Test
// //         void convertPerTbspToPerTsp() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.tsp, 1);


// //             Integer expected = 50;
// //             assertEquals(expected,result2);


// //         }

// //         @Test
// //         void convertPerTbspToPerLiter() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.liter, 1);


// //             Integer expected = 10;
// //             assertEquals(expected,result2);


// //         }

// //         @Test
// //         void convertPerTbspToPerOz() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tbsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.oz, 1);
// //             Integer expected = 280;
// //             assertEquals(expected,result2);


// //         }



// //         @Test
// //         void convertPerTspToPerServing() {

// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.serving, 1);


// //             Integer expected = 7000;
// //             assertEquals(expected,result2);

// //         }

// //         @Test
// //         void convertPerTspToPerMl() {

// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.ml, 1);


// //             Integer expected = 28;
// //             assertEquals(expected,result2);
// //         }


// //         @Test
// //         void convertPerTspToPerCups() {

// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.cups, 1);


// //             Integer expected = 6272;
// //             assertEquals(expected,result2);


// //         }

// //         @Test
// //         void convertPerTspToPerOz() {

// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.oz, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.oz, 1);


// //             Integer expected = 140;
// //             assertEquals(expected,result2);

// //         }

// //         @Test
// //         void convertPerTspToPerLiter() {

// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.oz, 1, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 1);
// //             UnitConverter prevValue2 = new UnitConverter(Edible.Unit.g, 1, result);

// //             Integer result2 = prevValue2.getCalories(Edible.Unit.liter, 1);


// //             Integer expected = 5;
// //             assertEquals(expected,result2);

// //         }



// //         @Test
// //         void convertPerTspToPerGram() {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp, 3, 144);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 40);

// //             Integer expected = 384;
// //             assertEquals(expected, result);

// //         }


// //         @Test
// //         void convertPerLiterToPerGram()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.liter,2, 700);
// //             Integer result = prevValue.getCalories(Edible.Unit.g,400);

// //             Integer expected = 140;
// //             assertEquals(expected,result);

// //         }

// //         @Test
// //         void convertPerCupToPerGram()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.tsp,1, 774);
// //             Integer result = prevValue.getCalories(Edible.Unit.g,400);

// //             Integer expected = 61920;
// //             assertEquals(expected,result);
// //         }

// //         @Test
// //         void convertPerMLtoPerGram()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.ml,100, 52);
// //             Integer result = prevValue.getCalories(Edible.Unit.g,400);

// //             Integer expected = 208;
// //             assertEquals(expected,result);
// //         }

// //         @Test
// //         void convertPerServingToPerGram()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.serving, 3, 840);
// //             Integer result = prevValue.getCalories(Edible.Unit.g, 400);


// //             Integer expected = 448;
// //             assertEquals(expected,result);
// //         }

// //     }

// //     @Nested
// //     @DisplayName("These are the edge cases. Some or all of them should fail the tests")
// //     class edgeCases{

// //         @Test
// //         void convertingToSameUnit()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.serving,3, 840);
// //             Integer result = prevValue.getCalories(Edible.Unit.serving,3);

// //             Integer expected = 840;
// //             assertEquals(expected,result);




// //         }

// //         @Test
// //         void testingIfQntyZero()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.serving,0, 840);
// //             Integer result = prevValue.getCalories(Edible.Unit.g,3);

// //             Integer expected = 0;
// //             assertEquals(expected,result);
// //          }


// //         @Test
// //         void testingIfCalorieZero()
// //         {
// //             UnitConverter prevValue = new UnitConverter(Edible.Unit.serving,3, 0);
// //             Integer result = prevValue.getCalories(Edible.Unit.serving,1);

// //             Integer expected = 0;
// //             assertEquals(expected,result);
// //         }
// //     }
// // }