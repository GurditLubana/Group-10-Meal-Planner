package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class PreparedItem extends Edible {
    private ArrayList<String> instructions;     //The instructions required for the Edible to prepare

    public PreparedItem() {
        super();

        this.instructions = null;
    }


    public void init(int id, String name, String desc, int qty, Unit unit, ImageView photo, ListItem.FragmentType view,
                     ArrayList<String> instructions) throws IOException {
        super.init(id, name, desc, qty, unit, photo, view);
        this.setInstructions(instructions);
    }

    public void setInstructions(ArrayList<String> newInstructions) throws IOException {
        if (newInstructions != null && !newInstructions.contains(null)) {
            this.instructions = newInstructions;
        }
        else {
            throw new IOException("Invalid instructions");
        }
    }

    public ArrayList<String> getInstructions() {
        return this.instructions;
    }
}