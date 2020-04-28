package simulation;

public class LightRain extends WeatherCondition {

    public LightRain(Cycle cycle) {
        super(cycle);
    }

    @Override
    public int GetCameraSuccessPercentage() {
        return 65;
    }

    @Override
    public int GetLiDARSuccessPercentage() {
        return 70;
    }

    @Override
    public int GetRADARSuccessPercentage() {
        return 70;
    }
}
