package simulation;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results {
    private static Results ourInstance;

    static {
        try {
            ourInstance = new Results();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File file;

    public static Results getInstance() {
        return ourInstance;
    }

    private Results() throws IOException {
        //file = new File("result.csv");
        //file.delete();
        //FileWriter fileWriter = new FileWriter(file, true);
        //fileWriter.append("Camera;LIDAR;RADAR;Success\n");
        //fileWriter.flush();
    }

    public void WriteRaw(String raw) throws IOException {
        //FileWriter fileWriter = new FileWriter(file, true);
        //fileWriter.append(raw);
        //fileWriter.flush();
    }

    public void WriteSensoreUsed(WeatherCondition.Sensor sensor, boolean success) throws IOException {
        //FileWriter fileWriter = new FileWriter(file, true);
//
        //String suc = success ? "1" : "0";
        //switch (sensor) {
        //    case Camera:
        //        fileWriter.append("1;0;0;" + suc + "\n");
        //        break;
        //    case LiDAR:
        //        fileWriter.append("0;1;0;" + suc + "\n");
        //        break;
        //    case RADAR:
        //        fileWriter.append("0;0;1;" + suc + "\n");
        //        break;
        //}
        //fileWriter.flush();
    }
}
