package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDataFrame {

    @Nested
    @DisplayName("Tests that should fail")
    private class DataFrameFail {

        @Test
        @DisplayName("instance creation should fail if type is null")
        void testNullType() {

            assertThrows(NullPointerException.class, () -> {
                DataFrame dataFrame = new DataFrame(null, DataFrame.Span.Week);
            });
        }

        @Test
        @DisplayName("instance creation should fail if span is null")
        void testNullSpan() {
            assertThrows(NullPointerException.class, () -> {
                DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, null);
            });
        }

        @Test
        @DisplayName("instance creation should fail if all params null")
        void testNullBoth() {
            assertThrows(NullPointerException.class, () -> {
                DataFrame dataFrame = new DataFrame(null, null);
            });
        }

        @Test
        @DisplayName("set data should fail if passed null")
        void testNullSetData() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
            assertThrows(NullPointerException.class, () -> {
                dataFrame.setData(null);
            });
        }
    }


    @Nested
    @DisplayName("Edge cases should pass")
    class DataFrameEdge {
        private ArrayList<Double> data;

        @BeforeEach
        void setup() {
            data = new ArrayList<Double>();
        }

        @Test
        @DisplayName("no items in list")
        void testPredicEmptyList() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);

            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.Week, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(0.0, dataFrame.getTrendPointA());
            assertEquals(0.0, dataFrame.getTrendPointB());
            assertEquals(0.0, dataFrame.getAverage());
            assertEquals(0.0, dataFrame.getMaxVal());
            assertEquals(0.0, dataFrame.getProgress());
        }

        @Test
        @DisplayName("one item in list")
        void testPredicSingleItemList() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
            data.add(new Double(1.0));
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.Week, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(0.0, dataFrame.getTrendPointA());
            assertEquals(0.0, dataFrame.getTrendPointB());
            assertEquals(0.0, dataFrame.getAverage());
            assertEquals(0.0, dataFrame.getMaxVal());
            assertEquals(0.0, dataFrame.getProgress());
        }
    }


    @Nested
    @DisplayName("Simple Tests should pass")
    class DataFrameSimple {
        private ArrayList<Double> data;

        @BeforeEach
        void setup() {
            data = new ArrayList<Double>();
            data.add(new Double(1.0));
            data.add(new Double(2.0));
            data.add(new Double(3.0));
            data.add(new Double(4.0));
            data.add(new Double(5.0));
            data.add(new Double(6.0));
        }

        @Test
        @DisplayName("typical use case predict week trend")
        void setDataWeek() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Week);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.Week, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(0.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }

        @Test
        @DisplayName("predict month trend")
        void setDataMonth() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Month);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.Month, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(-21.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }

        @Test
        @DisplayName("predict 3month trend")
        void setDataThreeMonth() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.ThreeMonth);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.ThreeMonth, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(-77.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }

        @Test
        @DisplayName("predict 6month trend")
        void setDataSixMonth() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.SixMonth);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.SixMonth, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(-161.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }

        @Test
        @DisplayName("predict one year trend")
        void setDataYear() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.Year);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.Year, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(-329.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }

        @Test
        @DisplayName("predict all trend")
        void setDataAll() {
            DataFrame dataFrame = new DataFrame(DataFrame.DataType.Weight, DataFrame.Span.All);
            dataFrame.setData(data);
            assertEquals(DataFrame.DataType.Weight, dataFrame.getDataType());
            assertEquals(DataFrame.Span.All, dataFrame.getSpan());
            assertEquals(data, dataFrame.getData());
            assertEquals(-665.0, dataFrame.getTrendPointA());
            assertEquals(7.0, dataFrame.getTrendPointB());
            assertEquals(3.5, dataFrame.getAverage());
            assertEquals(6.0, dataFrame.getMaxVal());
            assertEquals(58, (int) (dataFrame.getProgress() * 100));
        }
    }
}