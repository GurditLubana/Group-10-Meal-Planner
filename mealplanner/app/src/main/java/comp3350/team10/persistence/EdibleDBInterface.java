package comp3350.team10.persistence;

import comp3350.team10.objects.Edible;

public interface EdibleDBInterface { //updates cache
    public void addEdible(Edible newEdible);
    public void updateEdible(Edible modEdible);
}
