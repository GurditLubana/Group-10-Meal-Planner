package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.team10.R;
import comp3350.team10.objects.*;


public class RVATrends extends RecyclerViewAdapter {
    ArrayList<DataFrame> dataSet = null;
    FragToTrends send;

    public RVATrends(ArrayList<DataFrame> dataSet) {
        super(null);
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewHolder viewHolder = null;
        Context context = null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_trend_chart, viewGroup, false);

        context = view.getContext();
        if (context instanceof FragToTrends) {
            this.send = (FragToTrends) context;
        }

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

    public void changeDataSet(ArrayList<DataFrame> newData) {
        this.dataSet = newData;
        this.notifyDataSetChanged();
    }

    private void setChartData(ViewHolder viewHolder, int position) {
        GraphView graph = (GraphView) viewHolder.getView().findViewById(R.id.graph);
        DataFrame dataFrame = this.dataSet.get(position);
        DataPoint[] dataPointArray = new DataPoint[dataFrame.size()];
        ArrayList<Double> dataArray = dataFrame.getData();
        for (int i = 0; i < dataArray.size(); i++) {
            dataPointArray[i] = new DataPoint(i-dataArray.size(), dataArray.get(i).doubleValue());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointArray);
        graph.removeAllSeries();
        graph.addSeries(series);
        graph.setTitle(dataFrame.getDataType().name());
        graph.setTitleTextSize(72);
        graph.setTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setGridColor(Color.GRAY);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(1);
        switch(dataFrame.getSpan().ordinal()){
            case 0:
                graph.getViewport().setMinX(-7);
                break;
            case 1:
                graph.getViewport().setMinX(-28);
                break;
            case 2:
                graph.getViewport().setMinX(-74);
                break;
            case 3:
                graph.getViewport().setMinX(-128);
                break;
            case 4:
                graph.getViewport().setMinX(-350);
                break;
            default:
                graph.getViewport().setMinX(-700);
                break;
        }
    }


}
