import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;
import javax.swing.*;
import jm.JMC;
import jm.music.data.*;
import jm.util.Play;
public class alertTask {
    alertTask(Pin LED, IODevice BOARD) throws IOException {

        var led = BOARD.getPin(4);

        try {// get LED Light
            led.setMode(Pin.Mode.OUTPUT); // set it as an output
        }
        catch (IOException e){
            e.printStackTrace();
        }

//        var buzzer = BOARD.getPin(5);
//        buzzer.setMode(Pin.Mode.PWM);

        try {
            LED.setValue(1);
            Play.midi(new Note(JMC.f4, JMC.TN));
            Play.midi(new Note(JMC.f4, JMC.TN));
            Play.midi(new Note(JMC.f4, JMC.TN));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // BUZZER.setValue(1);

        JFrame frame = new JFrame("ALERT");
        JOptionPane.showMessageDialog(frame, """
                        You may be positive for COVID-19, you must self-isolate and/or
                        consult a physician. If you feel very unwell, Call 911 or go directly to\s
                        your nearest emergency department."""
                , "Notice", JOptionPane.ERROR_MESSAGE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // BUZZER.setValue(0);
        try{

            LED.setValue(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        BOARD.stop();
        System.exit(0);
    }
}