package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;

public class RVARecipeBook extends RecyclerView.Adapter<RVARecipeBook.ViewHolder> {
    private LinkedList<Edible> localDataSet;          // the list Recyclerview renders
    private int selectedPos = RecyclerView.NO_POSITION; // tracks the last clicked item
    private FragToRecipeBook sendToRecipeBook;          // interface to pass data to recipebook
    private Edible saved;                             // var to save a meal entry when we show context UI

    @Override
    public int getItemViewType(int pos) {
        return localDataSet.get(pos).getFragmentType().ordinal();
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

    public RVARecipeBook(LinkedList<Edible> dataSet) {
        localDataSet = dataSet;
    }

    public void changeData(LinkedList<Edible> newData) {
        localDataSet = newData;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewHolder viewHolder = null;
        Context context = null;
        View view = null;

        switch (viewType) {
            case 4:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card_context, viewGroup, false);
                break;
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card, viewGroup, false);
                break;
        }

        context = view.getContext();
        if (context instanceof FragToRecipeBook) {
            this.sendToRecipeBook = (FragToRecipeBook) context;
        }

        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        switch (viewHolder.getItemViewType()) {
            case 4:
                setCardSelectionListeners(viewHolder, position);
                break;
            default:
                setRecipeData(viewHolder, position);
                setCardListeners(viewHolder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }


    private void setCardListeners(ViewHolder viewHolder, int position) {
        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    sendToRecipeBook.showContextUI(position);
                }
            }
        });
    }

    private void setRecipeData(ViewHolder viewHolder, int position) {
        ImageView itemImage = (ImageView) viewHolder.getView().findViewById(R.id.mealImage);
        TextView textDesc = (TextView) viewHolder.getView().findViewById(R.id.mealDesc);
        TextView mealCalories = (TextView) viewHolder.getView().findViewById(R.id.mealCals);

        Edible currentFood = (Edible) localDataSet.get(position);

        itemImage.setImageResource(currentFood.getIconPath());
        textDesc.setText(currentFood.getName());
        mealCalories.setText(Integer.toString(currentFood.getCalories()));
    }

    private void setCardSelectionListeners(ViewHolder viewHolder, int position) {
        Button viewButton = (Button) viewHolder.getView().findViewById(R.id.viewBtn2);
        Button backButton = (Button) viewHolder.getView().findViewById(R.id.btnBackRecipe);
        Button addButton = (Button) viewHolder.getView().findViewById(R.id.addToPlannerBtn2);
        ImageView addIcon = (ImageView) viewHolder.getView().findViewById(R.id.addIcon);
        String launcher = "";

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    sendToRecipeBook.addToMealDiary();
                }
            }
        });

        if (sendToRecipeBook != null) {

            launcher = sendToRecipeBook.getIntentExtra("Source");
            if (launcher != null && launcher.equals("NAV")) {
                viewHolder.getView().findViewById(R.id.addToPlannerBtn2).setVisibility(View.GONE);
                viewHolder.getView().findViewById(R.id.addIcon).setVisibility(View.GONE);
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();

                if (sendToRecipeBook != null) {
                    sendToRecipeBook.showContextUI(selectedPos);
                }
            }
        });

    }
}
