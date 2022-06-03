package comp3350.team10.objects;

public interface ListItem {
    public enum FragmentType {diaryEntry, diaryModify, diaryAdd, recipe, cardSelection, noType};
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter};
    public FragmentType getFragmentType();
    public Unit getBaseUnit();
    public int getQuantity();
}
