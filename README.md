# COVID-19-Screening-Assesment-with-MATLAB-and-Arduino
_________________________________________________________________________________________________________________________________________________________________________
**Introduction:** 
This major project involves a COVID-19 self-assessment programmed in MATLAB and the Arduino Uno with its sensors being actuated. The purpose of this program is to check whether or not the user taking the self-assessment, is positive for COVID-19. Moreover, this self-assessment features a temperature sensor to check if the user has a fever. Finally, a self-assessment report (coded and opened from MATLAB) is given at the end if the user has no signs of COVID-19. The user is free to show their report at their workplace or institution, as proof that they are healthy.
_________________________________________________________________________________________________________________________________________________________________________
**Context:**
This self-assessment consists of a built-in temperature sensor, followed by a series of mostly “Yes or No” questions. Afterwards, a text document (.txt) is saved on the user’s computer, that confirms the user is not infected of COVID-19, including their temperature reading. Compared to COVID-19 screening assessments on the internet, this one includes a temperature sensor, which gives a better outlook of the user’s likelihood of being tested positive/negative for COVID-19. This self-assessment also requires no internet to run, in case if there is any outage. In addition, this is a screening test which eliminates any concerns regarding privacy. Any website or mobile app may have the ability to steal your data and personal information, even if it’s just for a screening test. At last, the report is automatically written in a text document, which takes up less computer memory and space. PDFs and Word Documents (.docx files) tend to take longer to open and require internet (or some subscription) to download and open these files.
_________________________________________________________________________________________________________________________________________________________________________
**Technical Requerments/Specifications:**

The system should…

1.	Measure their temperature, to see if they are diagnosed with a fever. If their temperature is greater or equal to 37.5 degrees Celsius, the screening ends and the buzzer sound goes off from the board, beeping 3 times. It lets the user they may be infected with COVID-19.
2.	Ask a couple of self-assessment questions (mostly “Yes or No” questions). Depending on what the user chooses, the screening test ends, and the buzzer goes off 1-3 times (which again, indicates that they are infected) or proceeds into the next phase of the test. If the buzzer beeps just once, it tells the user they are suspected, to have COVID-19.
3.	The user will be asked to prompt their name on the command window, for their screening report.
4.	A text file is automatically created in their desktop, confirming that they there are no signs for COVID-19. This text file includes their name, date of the report generated, and their recorded temperature.

_______________________________________________________________________________________________________________________________________________________________________
**Components List**

1. A Functional PC or Laptop
2. 1x Arduino Grove Uno Board
3. 1x Micro USB Cable
4. 1x Four-Wired Grove Cable
5. 1x Grove Temperature Sensor 
_______________________________________________________________________________________________________________________________________________________________________
**Procedure:**
1.	Get or buy any necessary parts (from components list) for the project.
2.	Connect the Arduino Uno board to a working computer, using a Micro USB cable.
3.	Connect the Grove Temperature Sensor into one side of a Grove Cable.
4.	Connect the other side of the cable to the “A0” pin on the board.
5.	Once the user starts the test, let them know through a pop-up window that their temperature will be measured, with instructions. The measuring will take 30 seconds, because the temperature sensor takes time to adjust from the room temperature to the user’s forehead.
6.	Make a for loop, making 100 measurements (iterations) for about 15-20 seconds.
7.	The D6 LED is also blinking to each iteration to let the user know, that the temperature is being measured.
8.	The sum volts from the loop are averaged.
9.	That mean volts is converted to the analog to the resistance and to the average temperature, with a constant with b = 3975. 
10.	Whatever their temperature is, make sure the user sees it in the command window.
11.	If their temperature is greater than 37.5 degrees Celsius”, the D5 buzzer on the board goes off 3 times and the LED light blinks 3 times. Then, a message pops up a window indicating they may be positive for COVID-19 with a fever, with next steps to take (ex. Call 911). Otherwise, they can proceed with the test.
12.	Ask the user screening questions. If any of the answers are “yes”, the same output will be in step 11.  If they are suspected to have COVID-19, the buzzer beeps once and the LED light flashes. This is followed by a message displaying they are suspected to be infected, and further instructions. If the user answers “no”, they continue the test. 
13.	If they answer “no” to all the questions, ask them to prompt their name in the command window, then press “enter” on the keyboard. 
14.	If the user doesn’t prompt anything, and presses enter, their name will be replaced with “the individual”. 
15.	Create a message in a string, indicating their name, their temperature, the date, time and confirmation that they are negative for COVID-19.
16.	That message will be written in a file called “screening_results.txt”, 
17.	The file will be created or overwritten in the user’s desktop, where it’s easily accessible. Note that the directory of where the file is saved may be different on another computer (instead of “C:\Users\yazan\OneDrive\Desktop\screening_results.txt”)
18.	Make the file have the ability to be overwritten with new info. If the user does a new test. 
19.	Write “fclose(“all”)” at the end to close all files that takes up any unnecessary memory in the computer. It also allows the user to delete their report, if they simply don’t need it anymore. Otherwise, there will be an error saying something like: “the action cannot be completed because file is still open…”. 
20.	Measure and record the temperature using both the Grove Temperature Sensor and a basic home thermometer, both on the forehead. Repeat 20 times.
21.	Take the mean of both readings.
22.	Calculate the difference between the mean of both readings.
23.	Measure and record the temperature using both the Grove Temperature Sensor on the forehead and a basic home thermometer orally (below the tongue). Repeat 10 times.
24.	Take the mean of both readings.
25.	Calculate the difference between the mean of both readings.
26.	The difference (to the nearest tenths) is added to the code, to match the accuracy of the oral test to the screening test in MATLAB.
27.	Add a secondary temperature test (based on the results), since the sensor takes a while to adapt to the changes in the temperature. The actual temperature will be recorded in the screening report.
_______________________________________________________________________________________________________________________________________________________________________
**Refrences:**
1. Volts to Temperature Code: “Temperature Reading on Matlab and Plotting.” YouTube, Magesh Jayakumar, 28 Nov. 2015, https://youtu.be/gjDzFyzH_ck (Accessed 5 Dec. 2022.)

*Code:*
```
analog = voltMean/0.0048875;
b = 3975;
resistance = (1023 - analog) * 10000/analog;
tempMean = (1/(log(resistance/10000)/b + 1/298.15) - 273.15);
```

2. COVID-19 Screening Algorithm: Covid-19 Ed/UC Algorithm - Shared Health. https://sharedhealthmb.ca/files/COVID-19-update.pdf. 
_______________________________________________________________________________________________________________________________________________________________________
