package comp3350.team10.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    public enum FragmentType {
        noType, diaryAdd, diaryModify, recipeSelect
    }

    private ArrayList<Edible> localDataSet; // the list Recyclerview renders
    private int viewType;

    @Override
    public int getItemViewType(int pos) {
        String identifier = localDataSet.get(pos).getName();
        int result = -1;

        if (identifier.equals(FragmentType.diaryAdd.name())) {
            result = FragmentType.diaryAdd.ordinal();
        } else if (identifier.equals(FragmentType.diaryModify.name())) {
            result = FragmentType.diaryModify.ordinal();
        } else if (identifier.equals(FragmentType.recipeSelect.name())) {
            result = FragmentType.recipeSelect.ordinal();
        } else {
            result = FragmentType.noType.ordinal();
        }

        this.viewType = result;

        return result;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout fragmentView;

        public ViewHolder(View view) {
            super(view);

            fragmentView = (FrameLayout) view.findViewById(R.id.frame_container);
        }

        public FrameLayout getView() {
            return fragmentView;
        }
    }

    public RecyclerViewAdapter(ArrayList<Edible> dataSet) {
        this.localDataSet = dataSet;
    }

    public void changeData(ArrayList<Edible> newData) {
        this.localDataSet = newData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }

    public ArrayList<Edible> getDataSet() {
        return this.localDataSet;
    }

    public int getViewType() {
        return this.viewType;
    }

    public Bitmap getBitmapFromFile(Context context, String fileName) {
        InputStream istr = null;
        Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        try {
            istr = assetManager.open("images/" + fileName);
            bitmap = BitmapFactory.decodeStream(istr);
            istr.close();
        } catch (Exception e) {
            bitmap = null;
        }
        return bitmap;
    }
}
