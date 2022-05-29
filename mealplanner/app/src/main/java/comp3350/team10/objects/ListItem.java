package comp3350.team10.objects;

public interface ListItem {
    public enum FragmentType {diaryEntry, diaryModify, diaryAdd};
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter};
    public FragmentType getFragmentType();
}
