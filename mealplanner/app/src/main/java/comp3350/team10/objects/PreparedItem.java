package comp3350.team10.objects;

public abstract class PreparedItem extends Edible {
    private String[] instructions;  //The instructions required for the item to prepare
    
    public PreparedItem(String name, int iconPath, int calories, String[] instructions, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, type, baseUnit, quantity, dbkey);

        super.modifyCalories(calories);
        this.instructions = instructions;
    }


    public String[] getInstructions() {
        return instructions;
    }

    public void changeInstructions(String[] newInstructions) {
        this.instructions = newInstructions;
    }
}