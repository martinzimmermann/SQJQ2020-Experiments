package actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;
import simulation.Simulation;

public class processObject implements RuleAction {
    @Override
    public boolean execute(Model model){
        Simulation.objectDetected++;
        //System.out.println("Object detected");
        return true;
    }

    @Override
    public void repair(Memory memory) {

    }
}
