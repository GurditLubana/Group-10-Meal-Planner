package comp3350.team10.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;

public class RVAAddIngredient extends RecyclerViewAdapter {
    private FragToRecipeBook sendToRecipeBook;  //Interface to pass data to recipeBook
    private float addBtnScaleX = 0.5f;           //Shrinks the button so it properly fits in the RV
    private float addBtnScaleY = 0.4f;           //Shrinks the button so it properly fits in the RV
    private Context context;                    //The current app's context

    public RVAAddIngredient(ArrayList<Edible> dataSet) {
        super(dataSet);
    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolder viewHolder;
        View view;

        if (viewType == FragmentType.diaryModify.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card_context, viewGroup, false);
        } else if (viewType == FragmentType.diaryAdd.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_add_log, viewGroup, false);
            view.setScaleX(addBtnScaleX);
            view.setScaleY(addBtnScaleY);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card, viewGroup, false);
            view.findViewById(R.id.itemImage).setVisibility(view.GONE);
        }

        this.context = view.getContext();
        if (this.context instanceof FragToRecipeBook) {
            this.sendToRecipeBook = (FragToRecipeBook) this.context;
        }
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (super.getViewType() == FragmentType.diaryModify.ordinal()) {
            setDiaryContextListeners(viewHolder);
        } else if (super.getViewType() == FragmentType.diaryAdd.ordinal()) {
            setDiaryAddListeners(viewHolder);
        } else {
            setDiaryEntryData(viewHolder, position);
            setDiaryEntryListeners(viewHolder);
        }
    }

    private void setDiaryEntryData(ViewHolder viewHolder, final int position) {
        View view = viewHolder.getView();
        TextView itemName = view.findViewById(R.id.itemNameBox);
        TextView itemCals = view.findViewById(R.id.itemCalsBox);
        TextView itemQty = view.findViewById(R.id.itemQtyBox);
        Edible currentItem = super.getDataSet().get(position);

        itemName.setText(currentItem.getName());
        itemCals.setText(String.format("%3d", (int) currentItem.getCalories()));
        itemQty.setText(String.format("%3.2f", currentItem.getQuantity()));
    }

    private void setDiaryEntryListeners(ViewHolder viewHolder) {
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                showContextUI(position, getModEntryCard());
            }
        });
    }

    private void setDiaryContextListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnBackMealLog)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        showContextUI(position, getModEntryCard());
                    }
                });

        viewHolder.getView().findViewById(R.id.btnDeleteMeal)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.removeItem(position);
                        }
                    }
                });

        viewHolder.getView().findViewById(R.id.btnModifyMeal)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.loadEditorView();
                        }
                    }
                });
    }

    private void setDiaryAddListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnAddMeal)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.addEntry(position);
                        }
                    }
                });
    }
}
