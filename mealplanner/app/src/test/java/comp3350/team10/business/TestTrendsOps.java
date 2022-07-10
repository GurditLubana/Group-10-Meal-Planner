package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.objects.DataFrame;
import comp3350.team10.persistence.SharedDB;

public class TestTrendsOps {

    @Nested
    @DisplayName("Simple Tests")
    class TrendsOpsSimple {
        private TrendsOps ops;

        @BeforeEach
        void setup() {
            try {
                SharedDB.start();
                ops = new TrendsOps();
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

        }

        @Test
        @DisplayName("get one week of data")
        void getWeek(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Week);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(7, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get one month of data")
        void getMonth(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Month);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(28, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 3 month of data")
        void getThreeMonth(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.ThreeMonth);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(84, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 6 month of data")
        void getSixMonth(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.SixMonth);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(168, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get one year of data")
        void getYear(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.Year);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(336, dataFrames.get(0).size());
        }

        @Test
        @DisplayName("get 2 year of data")
        void getAll(){
            ArrayList<DataFrame> dataFrames = null;
            try {
                dataFrames = ops.getDataFrames(DataFrame.Span.All);
            }
            catch(Exception e) {
                System.out.println(e);
                
            }

            assertEquals(DataFrame.DataType.values().length, dataFrames.size());
            assertEquals(672, dataFrames.get(0).size());
        }
    }

    @Nested
    @DisplayName("Tests that should fail")
    class TrendsOpsFail {
        private TrendsOps ops;

        @Test
        @DisplayName("instance creation should fail if db not started")
        void testNoDB(){

            assertThrows(NullPointerException.class, () -> {
                ops = new TrendsOps();
            });
        }

        @Test
        @DisplayName("requesting a null span should fail")
        void testNullRequest(){
            SharedDB.start();
            ops = new TrendsOps();
            assertThrows(NullPointerException.class, () -> {
                ops.getDataFrames(null);
            });
        }
    }
}