/* 
Contributed by Ainin (102810)
Role: Member 4 - Assessment Lead

Description:
1. Abstract base class for learning modules, providing common properties and implementing the ModuleAction interface.
2. Contains properties for the module name and score, which can be used by subclasses to manage specific learning modules.
*/

// Abstract base class for learning modules, providing common properties and implementing the ModuleAction interface.
public abstract class ModuleBase implements ModuleAction {
    protected String name;
    protected int score;

    // Constructor to initialize the module name and score
    public ModuleBase(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
