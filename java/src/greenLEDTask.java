import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

public class greenLEDTask {
    public greenLEDTask(IODevice arduino) throws IOException, InterruptedException {
        var greenLED = arduino.getPin(13); //pin 13 green led
        try {
            greenLED.setMode(Pin.Mode.OUTPUT);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        int i = 0;
       while (i < 150) {
         if (greenLED.getValue() == 0) {
              Thread.sleep(50);
              greenLED.setValue(1);
              i++;
         } else if (greenLED.getValue() == 1) {
              Thread.sleep(50);
              greenLED.setValue(0);
              i++;
           }
       }
    }
}
