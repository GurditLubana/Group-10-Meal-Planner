package comp3350.team10.objects;

import java.util.Arrays;

public abstract class PreparedItem extends Edible {
    private String[] instructions;  //The instructions required for the item to prepare
    

    public PreparedItem() {
        super();

        this.instructions = null;        
    }

    public boolean init(String name, int iconPath, int calories, String[] instructions, ListItem.FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        return super.init(name, iconPath, type, baseUnit, quantity, dbkey) && super.modifyCalories(calories) 
            && this.changeInstructions(instructions);
    }


    public String[] getInstructions() {
        return instructions;
    }

    public boolean changeInstructions(String[] newInstructions) {
        boolean results = false;

        if(newInstructions == null || (newInstructions.length > 0 && !Arrays.asList(newInstructions).contains(""))) {
            this.instructions = newInstructions;
            results = true;
        }

        return true;
    }
}