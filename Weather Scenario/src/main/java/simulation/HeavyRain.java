package simulation;

public class HeavyRain extends WeatherCondition {
    public HeavyRain(Cycle cycle) {
        super(cycle);
    }

    @Override
    public int GetCameraSuccessPercentage() {
        return 45;
    }

    @Override
    public int GetLiDARSuccessPercentage() {
        return 60;
    }

    @Override
    public int GetRADARSuccessPercentage() {
        return 70;
    }
}
