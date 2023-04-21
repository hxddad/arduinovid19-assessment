import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class reportWriter {
    reportWriter() {
        try {
            String name = (String) JOptionPane.showInputDialog(
                    null, """
                                No signs of COVID-19 detected.
                                Please enter your name for your report:
                                """,
                    "Notice",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);

            DateTimeFormatter date = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();

            var fileText1 = "COVID-19 Screening Results:\n\nThis certifies that, " + name + ", " +
                    "is tested negative for COVID-19, on "
                    + date.format(now) + " at " + time.format(now) + ".\nNo self-isolation is required.\nPlease show this " +
                    "to your supervisor at your workplace or institution.\n";

            var fileText2 = "COVID-19 Screening Results:\n\nThis certifies that, the user, " +
                    "is tested negative for COVID-19, on "
                    + date.format(now) + " at " + time.format(now) + ".\nNo self-isolation is required.\nPlease show this " +
                    "to your supervisor at your workplace or institution.\n";

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\yazan\\OneDrive\\Desktop\\" +
                    "screening_results.txt"));

            if ((name != null) && (name.length() != 0)) {
                writer.write(fileText1);
            } else {
                writer.write(fileText2);
            }
            System.out.println("File created. Successfully wrote to the file.");
            System.out.println("Please see a text file in your desktop.");
            writer.close();
        } catch (HeadlessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
