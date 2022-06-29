package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.ListItem;

public class RVAMealDiary extends RecyclerViewAdapter {
    private FragToMealDiary sendToMealDiary;            // interface to pass data to mealdiary

    public RVAMealDiary(ArrayList<Edible> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        Context context = null;
        ViewHolder viewHolder = null;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card, viewGroup, false);
                break;
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_card_context, viewGroup, false);
                break;
            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_diary_add_log, viewGroup, false);
                break;
            default:
                view = null;
                break;
        }
        context = view.getContext();
        if (context instanceof FragToMealDiary) {
            this.sendToMealDiary = (FragToMealDiary) context;
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        switch (viewHolder.getItemViewType()) {
            case 1:
                setDiaryContextListeners(viewHolder);
                break;
            case 2:
                setDiaryAddListeners(viewHolder);
                break;
            default:
                setDiaryEntryData(viewHolder, position);
                setDiaryEntryListeners(viewHolder);
                break;
        }
    }

    private void setDiaryEntryData(ViewHolder viewHolder, final int position) {
        TextView itemName = viewHolder.getView().findViewById(R.id.itemNameBox);
        TextView itemQty = viewHolder.getView().findViewById(R.id.itemQtyBox);
        TextView itemUnit = viewHolder.getView().findViewById(R.id.itemUnitBox);
        TextView itemCals = viewHolder.getView().findViewById(R.id.itemCalsBox);
        ImageView itemImage = viewHolder.getView().findViewById(R.id.itemImage);
        Edible currentItem = super.getDataSet().get(position);

        itemName.setText(currentItem.getName());
        itemQty.setText(String.format("%3d", currentItem.getQuantity()));
        itemUnit.setText(currentItem.getUnit().toString());
        itemCals.setText(String.format("%3d", currentItem.getCalories()));

        //Bitmap bmp = BitmapFactory.decodeByteArray(currentItem.getPhotoBytes(), 0, currentItem.getPhotoBytes().length);
        //itemImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, itemImage.getWidth(), itemImage.getHeight(), false));

        //itemImage.setImageResource(currentLog.getEdibleEntry().getPhotoBytes());
    }


    private void setDiaryEntryListeners(ViewHolder viewHolder) {
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToMealDiary != null) {
                    sendToMealDiary.showContextUI(position);
                }
            }
        });
    }

    private void setDiaryContextListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnBackMealLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToMealDiary != null) {
                    sendToMealDiary.showContextUI(position);
                }
            }
        });

        viewHolder.getView().findViewById(R.id.btnDeleteMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToMealDiary != null) {
                    sendToMealDiary.removeItem(position);
                }
            }
        });

        viewHolder.getView().findViewById(R.id.btnModifyMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sendToMealDiary != null) {
                    sendToMealDiary.editItem();
                }
            }
        });
    }

    private void setDiaryAddListeners(ViewHolder viewHolder) {
        viewHolder.getView().findViewById(R.id.btnAddMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();

                if (sendToMealDiary != null) {
                    sendToMealDiary.addEntry(position);
                }
            }
        });
    }
}