package simulation;

import java.util.Random;

public abstract class WeatherCondition {

    private Cycle cycle;

    public WeatherCondition(Cycle cycle)
    {
        this.cycle = cycle;
    }

    abstract int GetCameraSuccessPercentage();

    abstract int GetLiDARSuccessPercentage();

    abstract int GetRADARSuccessPercentage();

    public boolean IsObjectDetected(Sensor sensor) {

        Random rnd = new Random();
        int number = rnd.nextInt(100) + 1;

        switch (sensor) {
            case Camera:
                if(cycle == Cycle.Night)
                    return false;
                return number <= GetCameraSuccessPercentage();
            case LiDAR:
                return number <= GetLiDARSuccessPercentage();
            case RADAR:
                return number <= GetRADARSuccessPercentage();
        }

        assert false; // Should never be reached
        return false;
    }

    public enum Sensor {Camera, LiDAR, RADAR}
    public enum Cycle {Day, Night}
}
