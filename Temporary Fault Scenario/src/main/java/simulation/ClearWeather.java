package simulation;

public class ClearWeather extends WeatherCondition {

    public ClearWeather(Cycle cycle) {
        super(cycle);
    }

    @Override
    public int GetCameraSuccessPercentage() {
        return 100;
    }

    @Override
    public int GetLiDARSuccessPercentage() {
        return 100;
    }

    @Override
    public int GetRADARSuccessPercentage() {
        return 100;
    }
}
