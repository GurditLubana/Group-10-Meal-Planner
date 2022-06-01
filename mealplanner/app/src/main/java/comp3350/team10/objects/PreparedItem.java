public abstract class PreparedItem extends Edible {
    private String[] instructions;
    
    public PreparedItem(String name, String path, int calories, String[] instructions) {
        super(name, path);

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
