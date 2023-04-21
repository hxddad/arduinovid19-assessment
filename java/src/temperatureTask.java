import jm.JMC;
import jm.music.data.Note;
import jm.util.Play;
import org.firmata4j.I2CDevice;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;
// Delta Values to Temperature Conversion Code: https://wiki.seeedstudio.com/Grove-Temperature_Sensor_V1.2/
public class temperatureTask {
    static int A0 = 14;
    // static int A6 = 20;
    static final byte I2C0 = 0x3C; // oled screen

    temperatureTask(SSD1306 display, IODevice arduino) throws IOException, InterruptedException {
        I2CDevice i2cObject; // Use 0x3C for the Grove OLED
        try {
            i2cObject = arduino.getI2CDevice((I2C0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SSD1306 OLED = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64); // 128x64 OLED SSD1515
        OLED.init();

        var led = arduino.getPin(4);
        try {
            led.setMode(Pin.Mode.OUTPUT);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        var tempPin = arduino.getPin(14);
        try {
            tempPin.setMode(Pin.Mode.ANALOG);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        double deltaValue = tempPin.getValue();


        ArrayList<Double> deltaArray = new ArrayList<>();


        for (int index = 0; index < 100; index++) {
            deltaArray.add(deltaValue);
        }

        double temperature = 0;
        if (deltaArray.size() == 100) {
            int i = 0;
            double total = 0.0;
            while (i < 100) {
                double delta = deltaArray.get(i);
                total += delta;
                i++;
                if (i == 100) {
                    final int B = 4275;                        //Delta Values to Temperature Conversion Code:
                    final int R0 = 100000;                     //https://wiki.seeedstudio.com/Grove-Temperature_Sensor_V1.2/
                    var deltaAvg = total / deltaArray.size();
                    // System.out.println(deltaAvg);
                    double R = 1023.0 / deltaAvg - 1.0;
                    double newR = R0 * R;
                    temperature = (1.0 / (Math.log(newR / R0) / B + 1 / 298.15) - 273.15) + 3.1; // 3.1 bias value
                }
            }
        }

        StdDraw.setXscale(-3, 100);
        StdDraw.setYscale(-30, 100);

        // draw axis
        StdDraw.line(0, 0, 0, 80); // vertical axis
        StdDraw.line(0, 0, 100, 0); // horizontal axis

        //set pen radius and colour
        StdDraw.setPenRadius(0.005);

        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(50, 58.8, 50, 21.2);

        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledRectangle(50, 17.5, 50, 17.5);

        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledRectangle(50, 61, 50, 19);

        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(50, 37, 50, 2);


        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.text(0, 82, "°C");
        StdDraw.text(-3,0,"0");
        StdDraw.text(-3,10,"10");
        StdDraw.text(-3,20,"20");
        StdDraw.text(-3,30,"30");
        StdDraw.text(-4,37.5,"37.5");
        StdDraw.text(-3,45,"45");
        StdDraw.text(-3,50,"50");
        StdDraw.text(-3,60,"60");
        StdDraw.text(-3,70,"70");
        StdDraw.text(-3,80,"80");




        StdDraw.text(50, -15, "Iteration");
        StdDraw.text(0,-4,"0");
        StdDraw.text(25,-4,"25");
        StdDraw.text(50,-4,"50");
        StdDraw.text(75,-4,"75");
        StdDraw.text(100,-4,"100");
        StdDraw.text(50, 90, "Temperature Array");

        int i = 0;
        while (i < 100){
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.text(i, (deltaArray.get(i) * 0.1 - 27.7) + 3.1, "*");
            i++;
        }


        StdDraw.setPenColor(Color.GREEN);
        StdDraw.text(80,15, "No Fever");

        StdDraw.line(80, 20, 80,37);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(30,47, "Fever");

        StdDraw.line(30, 45, 30, 40);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(47,60, "Measurement Error");

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(47,20, "Measurement Error");


        var tempRound = String.format("%.1f", temperature);
        display.getCanvas().drawString(0, 0, "Your recorded\ntemperature is:\n" + tempRound + " degrees " +
                "celsius.\n\nYou may remove the\nsensor from your\nforehead.");
        display.display();
        System.out.println("Your recorded temperature is: " + tempRound + "°C");


        if (temperature >= 37.5 && temperature <= 42) {
            try {
                led.setValue(1);
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
            Play.midi(new Note(JMC.f4, JMC.TN));
            Play.midi(new Note(JMC.f4, JMC.TN));
            Play.midi(new Note(JMC.f4, JMC.TN));
            JFrame frame = new JFrame("ALERT");
            JOptionPane.showMessageDialog(frame, """
                                You have a fever. Please stay home and self-isolate."""
                    , "Notice", JOptionPane.ERROR_MESSAGE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            led.setValue(0);
            arduino.stop();
            System.exit(0);
        }

        else if (temperature <= 35 || temperature > 42) {
            System.out.println("""


                        Error:
                        Something went wrong with the temperature sensor.
                        Check your connection with the sensor and try again.
                        Otherwise, the sensor may be broken/damaged.
                        """);
            display.getCanvas().drawString(0,0,"""
                        Error:
                        Something went wrong with the temperature sensor.
                        Check your connection with the sensor and try again.
                        Otherwise, the sensor may be broken/damaged.
                        """);
            arduino.stop();
            Thread.sleep(1000);
            System.exit(0);
        }
    }
}
