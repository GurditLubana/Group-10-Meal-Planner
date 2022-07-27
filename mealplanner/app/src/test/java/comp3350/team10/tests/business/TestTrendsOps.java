package comp3350.team10.tests.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.LogDBInterface;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestTrendsOps {

    @Nested
    @DisplayName("Simple Tests")
    class TrendsOpsSimple {
        private TrendsOps ops;

        @BeforeEach
        void setup() {
            try {
                DBSelector.start(new DataAccessStub());
                ;
                ops = new TrendsOps();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }


        @Test
        @DisplayName("get one week of data")
        void getWeek() {
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Week);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(7, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get one month of data")
        void getMonth() {
            ArrayList<DataFrame> dataFrames = null;

            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Month);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(28, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 3 month of data")
        void getThreeMonth() {
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.ThreeMonth);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(84, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 6 month of data")
        void getSixMonth() {
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.SixMonth);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(168, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get one year of data")
        void getYear() {
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Year);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(336, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 2 year of data")
        void getAll() {
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.All);
            } catch (Exception e) {
                System.out.println(e);

            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(672, dataFrames.get(0).size());
        }


        @Test
        @DisplayName("Testing the return type of getDataFrames method")
        void testGetDataFrames() {

            assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(0) instanceof DataFrame);
            assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(1) instanceof DataFrame);
            assertTrue(ops.getDataFrames(DataFrame.Span.Month).get(2) instanceof DataFrame);

            assertEquals(ops.getDataFrames(DataFrame.Span.Week).size(), 3);
        }

        @Test
        @DisplayName("Testing getSpan method")
        void testGetSpan() {
            assertEquals(DataFrame.Span.Week, ops.getDataFrames(DataFrame.Span.Week).get(0).getSpan());
            assertEquals(DataFrame.Span.Month, ops.getDataFrames(DataFrame.Span.Month).get(1).getSpan());
            assertEquals(DataFrame.Span.SixMonth, ops.getDataFrames(DataFrame.Span.SixMonth).get(2).getSpan());
            assertEquals(DataFrame.Span.Year, ops.getDataFrames(DataFrame.Span.Year).get(1).getSpan());
            assertEquals(DataFrame.Span.All, ops.getDataFrames(DataFrame.Span.All).get(0).getSpan());
        }

        @Test
        @DisplayName("Testing getDataType method")
        void testGetDataType() {

            assertEquals(DataFrame.DataType.ConsumedCalories, ops.getDataFrames(DataFrame.Span.Month).get(0).getDataType());
            assertEquals(DataFrame.DataType.NetCalories, ops.getDataFrames(DataFrame.Span.Week).get(1).getDataType());
            assertEquals(DataFrame.DataType.ExerciseCalories, ops.getDataFrames(DataFrame.Span.SixMonth).get(2).getDataType());

        }
    }


    @Nested
    @DisplayName("Edge Case Tests")
    class TrendsOpsEdgeCases {
        private TrendsOps trendsOps;
        private LogDBInterface db;

        @BeforeEach
        void setup() {
            try {
                DBSelector.start(new DataAccessStub());
                ;
                db = DBSelector.getLogDB();
                trendsOps = new TrendsOps();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }

        @Test
        @DisplayName("Testing the value of average for all datatype.")
        void testGetAverage() {
            DataFrame compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.Month);

            compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 28));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.Month).get(2).getAverage(), compareWith.getAverage());


            compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.All).get(0).getAverage(), compareWith.getAverage());


            compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
            assertEquals((trendsOps.getDataFrames(DataFrame.Span.Year)).get(1).getAverage(), compareWith.getAverage());

        }

        @Test
        @DisplayName("Testing the Maximum value for all datatype.")
        void testGetMaxValue() {
            DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.All).get(0).getMaxVal(), compareWith.getMaxVal());

            compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
            assertEquals((trendsOps.getDataFrames(DataFrame.Span.Year)).get(1).getMaxVal(), compareWith.getMaxVal());

            compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.Month);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 28));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.Month).get(2).getMaxVal(), compareWith.getMaxVal());

        }

        @Test
        @DisplayName("Testing the Progress value for all datatype")
        void testGetProgress() {
            DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.All).get(0).getProgress(), compareWith.getProgress());

            compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
            assertEquals((trendsOps.getDataFrames(DataFrame.Span.Year)).get(1).getProgress(), compareWith.getProgress());

            compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getProgress(), compareWith.getProgress());

        }

        @Test
        @DisplayName("Testing the TrendPoint-A value of all datatype in a given span.")
        void testGetTrendPointA() {
            DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.All);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 672));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.All).get(0).getTrendPointA(), compareWith.getTrendPointA());

            compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Year);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 336));
            assertEquals((trendsOps.getDataFrames(DataFrame.Span.Year)).get(1).getTrendPointA(), compareWith.getTrendPointA());

            compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getTrendPointA(), compareWith.getTrendPointA());

        }

        @Test
        @DisplayName("Testing the TrendPoint-B value of all datatype in a given span.")
        void testGetTrendPointB() {
            DataFrame compareWith = new DataFrame(DataFrame.DataType.ConsumedCalories, DataFrame.Span.SixMonth);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ConsumedCalories, 168));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.SixMonth).get(0).getTrendPointB(), compareWith.getTrendPointB());

            compareWith = new DataFrame(DataFrame.DataType.NetCalories, DataFrame.Span.Month);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.NetCalories, 28));
            assertEquals((trendsOps.getDataFrames(DataFrame.Span.Month)).get(1).getTrendPointB(), compareWith.getTrendPointB());

            compareWith = new DataFrame(DataFrame.DataType.ExerciseCalories, DataFrame.Span.ThreeMonth);
            compareWith.setData(db.getDataFrame(DataFrame.DataType.ExerciseCalories, 84));
            assertEquals(trendsOps.getDataFrames(DataFrame.Span.ThreeMonth).get(2).getTrendPointB(), compareWith.getTrendPointB());

        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class TrendsOpsFail {
        private TrendsOps ops;

        @Test
        @DisplayName("instance creation should fail if db not started")
        void testNoDB() {

            try {
                ops = new TrendsOps();
                fail("Should throw an exception, no database has been started at this point.");
            } catch (Exception e) {
                assertTrue(e instanceof NullPointerException);
            }
        }

        @Test
        @DisplayName("Requesting a null span should fail")
        void testNullRequest() {
            DBSelector.start(new DataAccessStub());
            ;
            ops = new TrendsOps();

            try {
                ops.getDataFrames(null);
                fail("Should throw an exception, span can never be null.");
            } catch (Exception e) {
                assertTrue(e instanceof NullPointerException);
            }
        }

        @Test
        @DisplayName("Requesting an invalid index of DataFrame List.")
        void testOutOfBoundRequest() {
            DBSelector.start(new DataAccessStub());
            ;
            ops = new TrendsOps();

            try {
                ops.getDataFrames(DataFrame.Span.Month).get(4);
                ops.getDataFrames(DataFrame.Span.Month).get(-14);
                ops.getDataFrames(DataFrame.Span.Month).get(8 / 9);
                fail("Should throw an exception, all these bounds are invalid for a list of size 4.");
            } catch (Exception e) {
                assertTrue(e instanceof IndexOutOfBoundsException);
            }
        }
    }
}