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
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    public enum FragmentType {noType, diaryAdd, diaryModify, recipeSelect}

    private ArrayList<Edible> localDataSet; // the list Recyclerview renders
    private int savedItemPosition;                  //Saves the position of an item for temporary removal
    private Edible savedItem;                       //Saves the item for temporary removal
    private int viewType;

    private Edible modEntryCard;
    private Edible selectedRecipeCard;

    public RecyclerViewAdapter(ArrayList<Edible> dataSet) {
        this.localDataSet = dataSet;
        this.savedItemPosition = -1;
        this.savedItem = null;

        this.loadUICards();
    }

    private void loadUICards() {
        this.modEntryCard = new Edible();
        this.selectedRecipeCard = new Edible();

        this.modEntryCard.setName(Constant.DIARY_SELECT_CARD);
        this.selectedRecipeCard.setName(Constant.RECIPE_SELECT_CARD);
    }
    public Edible getModEntryCard() {
        return this.modEntryCard;
    }

    public Edible getSelectedRecipeCard() {
        return this.selectedRecipeCard;
    }

    public int getSavedItemPosition() {
        return this.savedItemPosition;
    }

    public Edible getSavedItem() {
        return this.savedItem;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < this.localDataSet.size()) {
            this.savedItem = null;
            this.savedItemPosition = -1;
            this.localDataSet.remove(position);
            this.notifyDataSetChanged();
        }
    }

    public void showContextUI(int position, Edible replacementUI) {
        int otherPosition = -1;

        if (position >= 0 && position != this.savedItemPosition) {
            if (this.savedItem == null) {
                saveItem(position);
            } else {
                otherPosition = this.savedItemPosition;
                swapSaved(position);
                this.notifyItemChanged(otherPosition);
            }
            this.localDataSet.remove(position);
            this.localDataSet.add(position, replacementUI); // this needs to be the other one
        } else {
            restoreSaved();
        }
        this.notifyItemChanged(position);
        this.notifyDataSetChanged();
    }

    private void saveItem(int position) {
        this.savedItemPosition = position;
        this.savedItem = this.localDataSet.get(position);
    }

    private void swapSaved(int position) {
        Edible temp = this.localDataSet.get(position);

        restoreSaved();
        this.savedItemPosition = position;
        this.savedItem = temp;
    }

    public void restoreSaved() {
        if (savedItem != null) {
            this.localDataSet.remove(this.savedItemPosition);
            this.localDataSet.add(this.savedItemPosition, this.savedItem);
            this.savedItemPosition = -1;
            this.savedItem = null;
        }
    }

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