package simulation;

import at.tugraz.ist.compiler.interpreter.BestPlanFinder;
import at.tugraz.ist.compiler.interpreter.NoPlanFoundException;
import rule.Executor;

public class Simulation {

    public static int objectDetected = 0;

    public static void main(String[] args) {
        //System.out.println("\u001B[32mStarting Simulation...\u001B[0m");
        Executor executor = null;
        try {
            executor = new Executor(new BestPlanFinder());
            executor.executesNTimes(Environment.getInstance().GetWeatherConditionsLeft());
        } catch (ClassNotFoundException e) {
            assert false;
        } catch (NoPlanFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Successes %: " + objectDetected/4000.0);
    }
}