package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;

public class RVARecipeBook extends RecyclerViewAdapter {
    private FragToRecipeBook sendToRecipeBook;          // interface to pass data to recipebook
    private Context context;

    public RVARecipeBook(ArrayList<Edible> dataSet) {
        super(dataSet);
    }

    
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolder viewHolder;
        View view;

        if (viewType == FragmentType.recipeSelect.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card_context, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recipe_book_card, viewGroup, false);
        }

        this.context = view.getContext();
        if (this.context instanceof FragToRecipeBook) {
            this.sendToRecipeBook = (FragToRecipeBook) this.context;
        }

        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        if (super.getViewType() == FragmentType.recipeSelect.ordinal()) {
            setCardSelectionListeners(viewHolder);
        } else {
            setRecipeData(viewHolder, position);
            setCardListeners(viewHolder, position);
        }
    }

    private void setCardListeners(ViewHolder viewHolder, int position) {
        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    showContextUI(position, getSelectedRecipeCard());
                }
            }
        });
    }

    private void setRecipeData(ViewHolder viewHolder, int position) {
        ImageView itemImage = viewHolder.getView().findViewById(R.id.mealImage);
        TextView textDesc = viewHolder.getView().findViewById(R.id.mealDesc);
        TextView mealCalories = viewHolder.getView().findViewById(R.id.mealCals);
        Edible currentFood = getDataSet().get(position);
        Bitmap image;

        textDesc.setText(currentFood.getName());
        mealCalories.setText(String.format("%3d", (int) currentFood.getCalories()));
        image = super.getBitmapFromFile(this.context, currentFood.getPhoto());
        if (image != null) {
            itemImage.setImageBitmap(image);
        } else {
            itemImage.setImageResource(R.drawable.ic_eggplant);
        }
    }

    private void setCardSelectionListeners(ViewHolder viewHolder) {
        Button backButton = viewHolder.getView().findViewById(R.id.btnBackRecipe);
        Button addButton = viewHolder.getView().findViewById(R.id.addToPlannerBtn2);
        String launcher = "";

        addButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View view) {
                if (sendToRecipeBook != null) {
                    sendToRecipeBook.addToMealDiary();
                }
            }
        });

        if (this.sendToRecipeBook != null) {
            launcher = this.sendToRecipeBook.getIntentExtra("Source");

            if (launcher != null && launcher.equals("NAV")) {
                viewHolder.getView().findViewById(R.id.addToPlannerBtn2).setVisibility(View.GONE);
                viewHolder.getView().findViewById(R.id.addIcon).setVisibility(View.GONE);
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToRecipeBook != null) {
                    showContextUI(position, getModEntryCard());
                }
            }
        });
    }
}