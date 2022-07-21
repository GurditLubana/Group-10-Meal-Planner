package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;

public class RVAAddIngredient extends RecyclerViewAdapter {
    private FragToRecipeBook sendToRecipeBook; // interface to pass data to recipeBook
    private float addBtnScale = 0.5f;
    Edible modEdible;
    private Context context;

    public RVAAddIngredient(ArrayList<Edible> dataSet) {
        super(dataSet);

        modEdible = new Edible();
        modEdible.setName(Constant.DIARY_SELECT_CARD);
    }


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolder viewHolder = null;
        View view = null;

        this.context = viewGroup.getContext();

        if (viewType == FragmentType.diaryModify.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card_context, viewGroup, false);
        } else if (viewType == FragmentType.diaryAdd.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_add_log, viewGroup, false);
            view.setScaleX(addBtnScale);
            view.setScaleY(addBtnScale);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card,
                    viewGroup, false);
            view.findViewById(R.id.itemCalsBox).setVisibility(view.GONE);
            view.findViewById(R.id.textView9).setVisibility(view.GONE);
            view.findViewById(R.id.itemQtyBox).setVisibility(view.GONE);
            view.findViewById(R.id.itemUnitBox).setVisibility(view.GONE);
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
        TextView itemName = viewHolder.getView().findViewById(R.id.itemNameBox);
        ImageView itemImage = viewHolder.getView().findViewById(R.id.itemImage);
        Edible currentItem = super.getDataSet().get(position);
        Bitmap image = null;

        itemName.setText(currentItem.getName());
        image = super.getBitmapFromFile(this.context, currentItem.getPhoto());

        if (image != null) {
            itemImage.setImageBitmap(image);
        } else {
            itemImage.setImageResource(R.drawable.ic_eggplant);
        }
    }

    private void setDiaryEntryListeners(ViewHolder viewHolder) {
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToRecipeBook != null) {
                    sendToRecipeBook.showIngredientContextUI(position, modEdible);
                }
            }
        });
    }

    private void setDiaryContextListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnBackMealLog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.showIngredientContextUI(position, modEdible);
                        }
                    }
                });

        viewHolder.getView().findViewById(R.id.btnDeleteMeal)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.removeItem(position);
                        }
                    }
                });

        viewHolder.getView().findViewById(R.id.btnModifyMeal)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
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
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAbsoluteAdapterPosition();

                        if (sendToRecipeBook != null) {
                            sendToRecipeBook.addEntry(position);
                        }
                    }
                });
    }
}
