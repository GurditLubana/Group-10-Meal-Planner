package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;

public class RVADiaryCard extends RecyclerViewAdapter {
    ArrayList<DailyLog> dataSet = null;

    public RVADiaryCard(ArrayList<DailyLog> dataSet) {
        super(null);
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        ViewHolder viewHolder;
        Context context = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_diary_card, parent, false);


        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        setChartData(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    @Override
    public int getItemViewType(int pos) {
        return 0;
    }

    public void changeDataSet(ArrayList<DailyLog> newData) {
        this.dataSet = newData;
        this.notifyDataSetChanged();
    }
    private void setChartData(ViewHolder viewHolder, int position) {
        GraphView graph = (GraphView) viewHolder.getView().findViewById(R.id.graph2);
        DailyLog dailyLog1 = this.dataSet.get(position);
//        DataPoint[] dataPointArray = new DataPoint[dataFrame.size()];
//        ArrayList<Double> dataArray = dataFrame.getData();
        DataPoint[] dataPoints = new DataPoint[7];
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}
