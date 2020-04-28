package simulation;

import java.util.ArrayDeque;
import java.util.Deque;

import static simulation.WeatherCondition.Cycle.Day;

public class Environment {
    private static Environment ourInstance = new Environment();
    private Deque<WeatherCondition> conditions;

    private Environment() {
        conditions = new ArrayDeque<>();
        addMultiple(new ClearWeather(Day), 1000);
        addMultiple(new LightRain(Day), 1000);
        addMultiple(new HeavyRain(Day), 1000);
        addMultiple(new ClearWeather(Day), 1000);
    }

    public int GetWeatherConditionsLeft()
    {
        return conditions.size();
    }

    public static Environment getInstance() {
        return ourInstance;
    }

    public WeatherCondition getNextWeatherCondition() {
        return conditions.poll();
    }

    private void addMultiple(WeatherCondition condition, int times) {
        for (int i = 0; i < times; i++) {
            conditions.offer(condition);
        }
    }
}
