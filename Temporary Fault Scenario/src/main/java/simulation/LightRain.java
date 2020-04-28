package simulation;

public class LightRain extends WeatherCondition {

    public LightRain(Cycle cycle) {
        super(cycle);
    }

    @Override
    public int GetCameraSuccessPercentage() {
        return 0;
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
