package comp3350.team10.objects;

public abstract class PreparedItem extends Edible {
    private String[] instructions;
    
    public PreparedItem(String name, int iconPath, int calories, String[] instructions, FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, type, baseUnit, quantity, dbkey);

        System.out.println("before modifying cals");
        super.modifyCalories(calories);
        this.instructions = instructions;
        System.out.println("after prepared constructor");
    }


    public String[] getInstructions() {
        return instructions;
    }

    public void changeInstructions(String[] newInstructions) {
        this.instructions = newInstructions;
    }
}
