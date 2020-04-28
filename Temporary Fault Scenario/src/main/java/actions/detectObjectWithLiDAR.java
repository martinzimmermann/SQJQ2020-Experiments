package actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;
import simulation.Environment;
import simulation.Results;
import simulation.WeatherCondition;

import java.io.IOException;

public class detectObjectWithLiDAR implements RuleAction {
    @Override
    public boolean execute(Model model){
        //System.out.println("Trying to detect Object with LiDAR");
        WeatherCondition.Sensor sensor = WeatherCondition.Sensor.LiDAR;
        boolean success = Environment.getInstance().getNextWeatherCondition().IsObjectDetected(sensor);
        try {
            Results.getInstance().WriteSensoreUsed(sensor, success);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void repair(Memory memory) {

    }
}
