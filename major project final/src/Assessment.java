import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.IODevice;
import org.firmata4j.ssd1306.SSD1306;
import org.firmata4j.I2CDevice;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.IOException;

// import JSSC library from Maven: io.github.java-native:jssc:2.9.4
// import firmata4j library from Maven: com.github.kurbatov:firmata4j:2.3.8
// if you are using Windows, please also import the SLF4J library into Maven: org.slf4j:slf4j-jcl:1.7.3
// import the Standard Library from Princeton via Maven for temperatureTask.
public class Assessment {
    public static void main(String[] args) throws IOException, InterruptedException {
        String port = "COM3";
        IODevice arduino = new FirmataDevice(port); // Board object, using the name of a port
        try {
            arduino.start(); // start comms with board;
            System.out.println("Assessment started.");
            arduino.ensureInitializationIsDone(); // make sure connection is good to board.
            I2CDevice i2cObject = arduino.getI2CDevice((byte) 0x3C); // Use 0x3C for the Grove OLED
            SSD1306 oled = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64); // 128x64 OLED SSD1515
            // Initialize the OLED (SSD1306) object
            oled.init();


            var led = arduino.getPin(4); // get LED Light
            led.setMode(Pin.Mode.OUTPUT); // set it as an output

            var tempPin = arduino.getPin(14);
            tempPin.setMode(Pin.Mode.ANALOG);

            var light = arduino.getPin(13);
            light.setMode(Pin.Mode.OUTPUT);

            JFrame frame = new JFrame("Notice");

            // Open up first dialog box, the calibration and a pop-up window with instructions will begin.

            light.setValue(1);
            JOptionPane.showMessageDialog(frame, """
                            The temperature sensor will be calibrated,\s
                            please place it on your forehead.\s
                            Click 'OK' to begin the calibration."""
                    , "Notice", JOptionPane.INFORMATION_MESSAGE);

            frame.setSize(500, 300);
            frame.add(new JLabel(new ImageIcon("C:/Users/yazan/OneDrive/Desktop/" +
                    "calibration.png")));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            light.setValue(0);

            Thread.sleep(10000);

            oled.getCanvas().write("""
                        The sensor will be
                        calibrated to
                        your forehead.
                                            
                        Please wait...""");
            oled.display();
            System.out.println("The sensor will be calibrated to your forehead.\nPlease wait...");
            new greenLEDTask(arduino);

            oled.clear();
            oled.getCanvas().setTextsize(1);
            oled.getCanvas().drawString(0, 3, """
                   Calibration
                   Finished!

                   You may remove the\s
                   sensor from your
                   forehead.""");
            oled.display();
            System.out.println("\nCalibration Finished!\nYou may remove the sensor from your forehead.");
            Object[] yes_no_options = {"Yes",
                    "No"};
            light.setValue(1);
            int answer1 = JOptionPane.showOptionDialog(frame,
                    "Q1: Have you tested positive for COVID-19 in the last 10 days,\n" +
                            "(either by rapid test or laboratory-based test?)",
                    "Question 1",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    yes_no_options,
                    yes_no_options[1]);
            if (answer1 == JOptionPane.YES_OPTION) {
                try {
                    new alertTask(led, arduino);
                    light.setValue(0);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                oled.clear();
                System.exit(0);
            }
            int answer2 = JOptionPane.showOptionDialog(frame,
                    "Q2: Have you been exposed in the last 14 days to someone\n " +
                            "that has tested positive for COVID-19 or has COVID-19 symptoms?",
                    "Question 2",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    yes_no_options,
                    yes_no_options[1]);
            if (answer2 == JOptionPane.YES_OPTION) {
                try {
                    new alertTask(led, arduino);
                    light.setValue(0);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                oled.clear();
                System.exit(0);
            }
            int answer3 = JOptionPane.showOptionDialog(frame,
                    """
                            Q3: Do you have ANY of the following symptoms...?
                                 - Chills or Shivers
                                 - Sore Throat
                                 - Hoarse Voice
                                 - Cough
                                 - Difficulty of Breathing
                                 - Loss of Taste or Smell
                                 - Vomiting or Diarrhea for +24h
                                 """,
                    "Question 3",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    yes_no_options,
                    yes_no_options[1]);
            if (answer3 == JOptionPane.YES_OPTION) {
                try {
                    new alertTask(led, arduino);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                oled.clear();
                System.exit(0);
            }
            int answer4 = JOptionPane.showOptionDialog(frame,
                    """
                            Q4: Do you have TWO OR MORE of the following symptoms?
                                 - Runny Nose
                                 - Muscle Aches
                                 - Fatigue
                                 - Conjunctivitis
                                 - Headache
                                 - Skin Rash of Unknown Cause
                                 - Nausea or Loss of Appetite
                            """,
                    "Question 4",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    yes_no_options,
                    yes_no_options[1]);
            if (answer4 == JOptionPane.YES_OPTION) {
                try {
                    new alertTask(led, arduino);
                    light.setValue(0);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                oled.clear();
                System.exit(0);
            }
            JFrame frame2 = new JFrame("Notice");

            // Open up first dialog box, the calibration and a pop-up window with instructions will begin.
            JOptionPane.showMessageDialog(frame2, """                          
                            Your temperature will now be measured,\s
                            please place it on your forehead.\s
                            Click 'OK' to begin the measurement.
                            This will take about 10 seconds."""
                    , "Notice", JOptionPane.INFORMATION_MESSAGE);
            frame2.setSize(400, 450);
            frame2.add(new JLabel(new ImageIcon("C:/Users/yazan/OneDrive/Desktop/" +
                    "temperature.png")));
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setVisible(true);

            light.setValue(0);

            oled.clear();
            Thread.sleep(10000);

            new temperatureTask(oled, arduino);


            if (answer1 == JOptionPane.NO_OPTION &
                    answer1 == JOptionPane.NO_OPTION & answer2 == JOptionPane.NO_OPTION &
                    answer3 == JOptionPane.NO_OPTION & answer4 == JOptionPane.NO_OPTION) {
                light.setValue(1);
            }

            light.setValue(0);
            oled.clear();
            arduino.stop(); // Finished with the board. Shut down the connection.
            System.exit(0);

        } catch (Exception ex) {
            System.out.println("Could not connect to board. Check your USB cable, port and your pin connections" +
                    " and try again.");
            System.exit(0);
        }
    }
}