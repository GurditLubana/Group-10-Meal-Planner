package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.Validator;

public class FragmentRecipeBookDialogs extends FragmentDialogCommon {
    public static String TAG = "AddRecipe";    //Tag name of this fragment (for reference)
    private static String defaultImage = "photo.jpeg";

    private TextView labelName;                //Label of name field
    private EditText inputName;                //Input field for item name
    private String name;                       //Value of name input
    private EditText inputCalories;            //Input field for item calories
    private int calories;                      //Value of calorie input
    private EditText inputInstructions;        //Input field for instructions
    private String instructions;               //Value of instructions input
    private EditText ingredientError;

    private CheckBox isAlcoholic;              //Check if Edible item contains alcohol.
    private CheckBox isSpicy;                  //Check if Edible item spicy.
    private CheckBox isVegetarian;             //Check if Edible item vegetarian.
    private CheckBox isVegan;                  //Check if Edible item vegan.
    private CheckBox isGlutenFree;             //Check if Edible item glutenFree.

    private int quantity;                      //Value of quantity input
    private String photo;                      //Value of ingredients input
    private Edible.Unit unit;                  //Value of units input

    private FragToRecipeBook send;             //Interface for communication with parent activity
    private String mode;                       //The type of dialog to show
    private ArrayList<Ingredient> ingredients;
    Edible addButton;

    private RecyclerView inputIngredients;     //Input field for item ingredients
    private ArrayList<Edible> ingredientEdibles;
    private RecyclerViewAdapter recyclerViewAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ingredients = new ArrayList<Ingredient>();
        this.ingredientEdibles = new ArrayList<Edible>();

        this.addButton = new Edible();
        this.addButton.setName(Constant.DIARY_ADD_CARD);
        this.ingredientEdibles.add(this.ingredientEdibles.size(), this.addButton);
    }


    private void initRecyclerView(View view) {
        View recycler = view.findViewById(R.id.dialogRecipeIngredientsInput);
        RecyclerView ingredientRecyclerView;

        if (recycler instanceof RecyclerView) {
            this.recyclerViewAdapter = new RVAAddIngredient(ingredientEdibles);
            ingredientRecyclerView = (RecyclerView) recycler;
            ingredientRecyclerView.setAdapter(this.recyclerViewAdapter);
            ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_recipe_book_dialogs, null);
        Context context = view.getContext();
        Bundle args = getArguments();
        Dialog dialog;

        this.mode = args.getString(Constant.DIALOG_TYPE);
        this.initRecyclerView(view);
        super.setTitle(view.findViewById(R.id.dialogRecipeTitle));
        super.setInputQuantity(view.findViewById(R.id.dialogRecipeQuantityInput));
        super.setUnitSpinner(view.findViewById(R.id.dialogRecipeSpinner));
        super.setBtnOk(view.findViewById(R.id.dialogRecipeBtnOk));
        super.setBtnCancel(view.findViewById(R.id.dialogRecipeBtnCancel));
        this.labelName = view.findViewById(R.id.dialogRecipeNameLabel);
        this.inputInstructions = view.findViewById(R.id.dialogRecipeInstructions);
        this.inputName = view.findViewById(R.id.dialogRecipeNameInput);
        this.inputCalories = view.findViewById(R.id.dialogRecipeCaloriesInput);
        this.inputIngredients = view.findViewById(R.id.dialogRecipeIngredientsInput);
        this.ingredientError = view.findViewById(R.id.ingredientError);
        this.isAlcoholic = view.findViewById(R.id.isAlcoholic);
        this.isSpicy = view.findViewById(R.id.isSpicy);
        this.isGlutenFree = view.findViewById(R.id.isGluteenFree);
        this.isVegetarian = view.findViewById(R.id.isVegetarian);
        this.isVegan = view.findViewById(R.id.isVegan);
        this.photo = defaultImage;

        if (context != null && context instanceof FragToRecipeBook) {
            setupAddRecipeDialog(context, FragToRecipeBook.EntryMode.valueOf(mode));
        }

        dialog = builder.setView(view).create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }

    private void setupAddRecipeDialog(Context context, FragToRecipeBook.EntryMode mode) {
        super.getInputQuantity().setHint("0 - 9999");
        this.inputCalories.setHint("0 - 9999");
        this.send = (FragToRecipeBook) context;

        switch (mode) {
            case ADD_FOOD:
                setFoodDialogFieldDefaults();
                break;
            case ADD_MEAL:
                setMealDialogFieldDefaults();
                break;
            case ADD_DRINK:
                setDrinkDialogFieldDefaults();
                break;
        }

        setSpinner();
        setOnClickListeners();
    }

    public void editEntry(Double amount, String unit, boolean isSubstitute) {
        Ingredient currItem = this.ingredients.get(this.recyclerViewAdapter.getSavedItemPosition());

        currItem.setQuantity(amount);
        currItem.setQuantityUnit(Edible.Unit.valueOf(unit));

        if (currItem instanceof DrinkIngredient) {
            ((DrinkIngredient) currItem).setSubstitute(isSubstitute);
        }

        this.recyclerViewAdapter.showContextUI(-1, null);
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    private void setFoodDialogFieldDefaults() {
        super.getTitle().setText("Add New Food");
        this.labelName.setText("Food Name");
        this.inputName.setHint("Food Name");
        this.inputInstructions.setVisibility(View.GONE);
        this.inputIngredients.setVisibility(View.GONE);
    }

    private void setMealDialogFieldDefaults() {
        super.getTitle().setText("Add New Meal");
        this.labelName.setText("Meal Name");
        this.inputName.setHint("Meal Name");
        this.inputInstructions.setHint("Cooking Instructions");
    }

    private void setDrinkDialogFieldDefaults() {
        super.getTitle().setText("Add New Drink");
        this.labelName.setText("Drink Name");
        this.inputName.setHint("Drink Name");
        this.inputInstructions.setHint("Mixing Instructions");
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter;

        super.initSpinner();
        if (super.getUnitSpinner().getAdapter() instanceof ArrayAdapter) {
            adapter = (ArrayAdapter) super.getUnitSpinner().getAdapter();
            super.getUnitSpinner().setSelection(adapter.getPosition(Edible.Unit.serving.name()));
        }
    }

    private void setOnClickListeners() {
        super.getBtnOk().setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                FragToRecipeBook.EntryMode currMode = FragToRecipeBook.EntryMode.valueOf(mode);

                if (validateData(currMode)) {
                    sendData(currMode);
                    restIngredients();
                    dismiss();
                }
            }
        });

        super.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                restIngredients();
                dismiss();
            }
        });
    }

    private void restIngredients() {
        this.ingredients.clear();
    }


    public void onResume() {
        super.onResume();

        this.ingredientEdibles = new ArrayList<Edible>();
        for (Ingredient currIngredient : this.ingredients) { //build a list based on edibles from ingredients
            ingredientEdibles.add(currIngredient.getIngredient());
        }

        if (!ingredientEdibles.contains(this.addButton)) {
            ingredientEdibles.add(this.addButton);
        }

        recyclerViewAdapter.changeData(ingredientEdibles);
    }

    private boolean validateData(FragToRecipeBook.EntryMode mode) {
        boolean validInput = true;
        EditText view = null;
        String dataValue;
        int intValue;

        try {
            view = this.inputName;
            dataValue = view.getText().toString().trim();
            Validator.validStringInputatLeastOne(dataValue, "");
            this.name = dataValue;

            if (mode != FragToRecipeBook.EntryMode.ADD_FOOD) {
                view = this.inputInstructions;
                dataValue = view.getText().toString().trim();
                Validator.validStringInputatLeastZero(dataValue, "");
                this.instructions = dataValue;
            }

            view = this.inputCalories;
            dataValue = view.getText().toString().trim();
            Validator.validStringInputatLeastOne(dataValue, "");
            intValue = Integer.parseInt(dataValue);
            Validator.atLeastZero(intValue, "");
            this.calories = intValue;

            view = this.getInputQuantity();
            dataValue = view.getText().toString().trim();
            Validator.validStringInputatLeastOne(dataValue, "");
            intValue = Integer.parseInt(dataValue);
            Validator.atLeastOne(intValue, "");
            this.quantity = intValue;

            if (mode == FragToRecipeBook.EntryMode.ADD_MEAL) {
                view = this.ingredientError;
                Validator.validArrayListAtLeastOne(this.ingredients, "");
            }

            this.unit = Edible.Unit.valueOf(super.getUnitSpinner().getSelectedItem().toString());
        } catch (NumberFormatException e) {
            view.setError("input must be inside the range of 1 - 9999");
        } catch (Exception e) {
            view.setError(e.toString().replaceAll("^.*(?=(input))", "").replace("string ", ""));
            validInput = false;
        }

        return validInput;
    }

    private void sendData(FragToRecipeBook.EntryMode mode) {
        ArrayList<DrinkIngredient> drinkIngredients;

        if (this.send != null && this.send instanceof FragToRecipeBook) {
            switch (mode) {
                case ADD_FOOD:
                    this.send.addFood(this.name, this.name, this.quantity, this.unit, this.calories, this.calories, this.calories, this.calories, this.isAlcoholic.isChecked(), this.isSpicy.isChecked(), this.isVegan.isChecked(), this.isVegetarian.isChecked(), this.isGlutenFree.isChecked(), this.photo);
                    break;
                case ADD_MEAL:
                    this.send.addMeal(this.name, this.name, this.quantity, this.unit, this.photo, this.instructions, this.ingredients);
                    break;
                case ADD_DRINK:
                    drinkIngredients = this.readDrinkIngredients();
                    this.send.addDrink(this.name, this.name, this.quantity, this.unit, this.calories, this.calories, this.calories, this.calories, this.isAlcoholic.isChecked(), this.isSpicy.isChecked(), this.isVegan.isChecked(), this.isVegetarian.isChecked(), this.isGlutenFree.isChecked(), this.photo, this.instructions, drinkIngredients);
                    break;
            }
        }
    }

    private ArrayList<DrinkIngredient> readDrinkIngredients() {
        ArrayList<DrinkIngredient> drinkIngredients = new ArrayList<DrinkIngredient>();

        for (Ingredient currIngredient : this.ingredients) {
            if (currIngredient instanceof DrinkIngredient) {
                drinkIngredients.add((DrinkIngredient) currIngredient);
            }
        }

        return drinkIngredients;
    }

    public void loadIngredients(Edible currEdible) {
        if (currEdible instanceof Meal) {
            for (Ingredient ingredient : ((Meal) currEdible).getIngredients()) {
                addIngredient(ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getQuantityUnits(), false);
            }
        } else if (currEdible instanceof Drink && ((Drink) currEdible).getIngredients().size() > 0) {
            for (DrinkIngredient ingredient : ((Drink) currEdible).getIngredients()) {
                addIngredient(ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getQuantityUnits(), ingredient.getIsSubstitute());
            }
        } else {
            addIngredient(currEdible, currEdible.getQuantity(), currEdible.getUnit(), false);
        }
    }

    private void addIngredient(Edible currEdible, double quantity, Edible.Unit unit, boolean isSubstitute) {
        DrinkIngredient newIngredient = new DrinkIngredient();

        if (isUniqueIngredient(currEdible)) {
            if (this.recyclerViewAdapter.getSavedItem() instanceof Drink) {
                newIngredient.init(currEdible, quantity, unit);
                newIngredient.setSubstitute(isSubstitute);
                ingredients.add(newIngredient);
            } else { //needs a regular ingredient
                ingredients.add(newIngredient.init(currEdible, quantity, unit)); //returns ingredient when using init
            }
        }
    }

    private boolean isUniqueIngredient(Edible newIngredient) {
        boolean alreadyAnIngredient = true;
        Edible currEdible;

        for (Ingredient currIngredient : this.ingredients) {
            currEdible = currIngredient.getIngredient();

            if (currEdible.getDbkey() == newIngredient.getDbkey() && currEdible.getIsCustom() == newIngredient.getIsCustom()) {
                alreadyAnIngredient = false;
                break;
            }
        }
        return alreadyAnIngredient;
    }

    public String getEntryQty() {
        return String.valueOf(this.ingredients.get(this.recyclerViewAdapter.getSavedItemPosition()).getQuantity());
    }

    public Edible.Unit getEntryUnit() {
        return this.ingredients.get(this.recyclerViewAdapter.getSavedItemPosition()).getQuantityUnits();
    }

    public boolean getIsChecked() {
        boolean isChecked = false;

        if (this.ingredients.get(this.recyclerViewAdapter.getSavedItemPosition()) instanceof DrinkIngredient) {
            isChecked = ((DrinkIngredient) this.ingredients.get(this.recyclerViewAdapter.getSavedItemPosition())).getIsSubstitute();
        }

        return isChecked;
    }

    public void removeItem(int position) {
        this.recyclerViewAdapter.removeItem(position);
        this.ingredients.remove(position);
    }
}