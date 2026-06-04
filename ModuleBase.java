//Contributed by Ainin (102810)

import javax.swing.*;

public abstract class ModuleBase extends JFrame implements ModuleAction {

    protected String name;
    protected int score;

    public ModuleBase(String title, String name, int score) {
        this.name = name;
        this.score = score;
    }
}
