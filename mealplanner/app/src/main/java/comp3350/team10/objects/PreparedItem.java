package comp3350.team10.objects;

public abstract class PreparedItem extends Edible {
    private String[] instructions;
    
    public PreparedItem(String name, String path, int calories, String[] instructions) {
        super(name, path);

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
