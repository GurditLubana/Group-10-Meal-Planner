package comp3350.team10.presentation;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import comp3350.team10.objects.Edible;

public class RVADiaryCard extends RecyclerViewAdapter {


    public RVADiaryCard(ArrayList<Edible> dataSet) {
        super(dataSet);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }
}
