public abstract class Edible {
    private int calories;
    private String name;
    private String path;

    public Edible(String name, String path) {
        this.calories = 0;
        this.name = name;
        this.path = path;  
    }


    public void modifyCalories(int amount) {
        this.calories += amount;
    }

    public int getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }
}
