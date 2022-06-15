package comp3350.team10.objects;

public abstract class PreparedItem extends Edible {
    private String instructions;  //The instructions required for the item to prepare
    

    public PreparedItem() {
        super();

        this.instructions = null;        
    }

    public boolean init(String name, int iconPath, int calories, String[] instructions, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        return results = super.init(name, iconPath, type, baseUnit, quantity, dbkey) && super.modifyCalories(calories) 
            && this.setInstructions(instructions);
    }


    public String getInstructions() {
        return instructions;
    }

    public void changeInstructions(String newInstructions) {
        this.instructions = newInstructions;

        return true;
    }
}