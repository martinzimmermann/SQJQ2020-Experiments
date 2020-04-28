package simulation;

public class HeavyRain extends WeatherCondition {
    public HeavyRain(Cycle cycle) {
        super(cycle);
    }

    @Override
    public int GetCameraSuccessPercentage() {
        return 0;
    }

    @Override
    public int GetLiDARSuccessPercentage() {
        return 0;
    }

    @Override
    public int GetRADARSuccessPercentage() {
        return 100;
    }
}
