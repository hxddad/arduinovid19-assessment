# COVID-19-Screening-Assessment

Demo (skip to 3:34): https://youtu.be/qraSpWcP9CQ?si=UVgaaN6NWTvHakh6&t=214 
_______________________________________________________________________________________________________________________________________________________________________
**Introduction:** 


This project involves a COVID-19 self-assessment programmed in Java and the Arduino Uno with its sensors being actuated. This assessment also contains a temperature test, to determine if the user has a fever or not. If the user has no signs of COVID-19, a report will be printed on their desktop as a text file, confirming that they are negative for the virus. This report can be sent to the user’s institution or workplace to indicate as proof they are healthy.
_______________________________________________________________________________________________________________________________________________________________________
**Context:**


This self-assessment consists of a built-in temperature sensor, followed by a series of mostly “Yes or No” questions. Afterwards, a text document (.txt) is saved on the user’s computer, that confirms the user is not infected of COVID-19, including their temperature reading. Compared to COVID-19 screening assessments on the internet, this one includes a temperature sensor, which gives a better outlook of the user’s likelihood of being tested positive/negative for COVID-19. This self-assessment also requires no internet to run, in case if there is any outage. In addition, this is a screening test which eliminates any concerns regarding privacy. Any website or mobile app may have the ability to steal your data and personal information, even if it’s just for a screening test. At last, the report is automatically written in a text document, which takes up less computer memory and space. PDFs and Word Documents (.docx files) tend to take longer to open and require internet (or some subscription) to download and open these files.
_______________________________________________________________________________________________________________________________________________________________________
**Components List:**


1. A Functional PC or Laptop
2. 1x Arduino Grove Uno Board
3. 1x Micro USB Cable
4. 1x Four-Wired Grove Cable
5. 1x Grove Temperature Sensor 
_______________________________________________________________________________________________________________________________________________________________________
**Additional Material:**


Coronavirus has gone down significantly here in Ontario as of April 2023, because we were able to protect each other. COVID-19 is still around and can still affect anybody. This is why it is still our job to protect each other and take precautions to prevent another wave. Hur & Chang (2020) states that a self-report questionnaire can minimize the spread of COVID-19 and potentially helpful to overcome the pandemic. Moreover, contactless healthcare services have been proven to be very effective in the post-pandemic, due to the accelerating technological developments (Lee & Lee, 2021). Digitization is helping companies and institutions overcome obstacles relating to the spread of COVID-19 (Almeida et al., 2020). York University’s YU Screening tool is a good way of screening ourselves of whether to show up on campus or stay home. Whitelaw et al. (2020) states that digital technology has opened many ways to help facilitate and respond the pandemic to combat the more difficult issues. Whitelaw et al. also noted that countries like South Korea have integrated digital technology to combat these challenges, which could be a cause of their curve of infected cases to be flattened. This caused South Korea to have 0.5 COVID-19 deaths per 100,000 people (Whitelaw et al., 2020). 
_______________________________________________________________________________________________________________________________________________________________________
**Refrences:**


**1. Volts to Temperature Code:** “Temperature Reading on Matlab and Plotting.” YouTube, Magesh Jayakumar, 28 Nov. 2015, https://youtu.be/gjDzFyzH_ck (Accessed 5 Dec. 2022.)

Code from video:
```
analog = voltMean/0.0048875;
b = 3975;
resistance = (1023 - analog) * 10000/analog;
tempMean = (1/(log(resistance/10000)/b + 1/298.15) - 273.15);
```

**2. COVID-19 Screening Algorithm:** Covid-19 Ed/UC Algorithm - Shared Health. https://sharedhealthmb.ca/files/COVID-19-update.pdf. 

**3. Firmata4j Libraries for Arduino Uno:** Kurbatov, Oleg, and Dominik Stadler. “Firmata4j.” Firmata4j Source Codes, GitHub, 2014, github.com/kurbatov/firmata4j/tree/master/src/main/java/org/firmata4j. Accessed 2 Apr. 2023.

**4. Making Dialogue Boxes Using Swing & JOptionPane:** “How to Make Dialogs (the JavaTM Tutorials > Creating a GUI with JFC/Swing > Using Swing Components).” Docs.oracle.com, Oracle, docs.oracle.com/javase/tutorial/uiswing/components/dialog.html.

**5. Java.io.BufferedWriter Class Methods in Java:** Kumarar, Gulshan, et al. “Java.io.BufferedWriter Class Methods in Java.” GeeksforGeeks, 15 Aug. 2022, www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/.

**6. LocalDateTime & DateTimeFormater:** “Java LocalDateTime - Javatpoint.” www.javatpoint.com, www.javatpoint.com/java-localdatetime.

**7. Standard Library from Princeton:** “StdDraw.” Introcs.cs.princeton.edu, introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html.

**8. Temperature Sensor and Demo Code of Conversion from ADC Quanta Values to Temperature:** “Grove - Temperature Sensor V1.2 - Seeed Wiki.” Wiki.seeedstudio.com, wiki.seeedstudio.com/Grove-Temperature_Sensor_V1.2/.

**9. How Temperature has Little Signifigance for the Control of COVID-19:** Mitra, Biswadev, et al. "Temperature screening has negligible value for control of COVID‐19." Emergency Medicine Australasia 32.5 (2020): 867-869.

**10. The Useful Impacts of Online COVID-19 Screening Questionaires During the Pandemic:** Hur, Jian, and Min Cheol Chang. "Usefulness of an online preliminary questionnaire under the COVID-19 pandemic." Journal of Medical Systems 44.7 (2020): 1-2.

**11. Applications of Digital Technology in COVID-19 Pandemic Planning and Response:** Whitelaw, Sera, et al. “Applications of Digital Technology in COVID-19 Pandemic Planning and Response.” The Lancet Digital Health, vol. 2, no. 8, 29 June 2020, pp. 435–440, www.thelancet.com/journals/landig/article/PIIS2589-7500(20)30142-4/fulltext, https://doi.org/10.1016/S2589-7500(20)30142-4.

**12. Opportunities and challenges for contactless healthcare services in the post-COVID-19 Era:** Lee, Sang M., and DonHee Lee. “Opportunities and Challenges for Contactless Healthcare Services in the Post-COVID-19 Era.” Technological Forecasting and Social Change, vol. 167, June 2021, https://doi.org/10.1016/j.techfore.2021.120712. Accessed 10 Apr. 2023.

**13. JMusic:** Sorensen, Andrew, and Andrew Brown. “Music, Composition, and the Computer.” Explodingart.com, explodingart.com/jmusic/jmtutorial/x34.html#bing. Accessed 6 Apr. 2023.
_______________________________________________________________________________________________________________________________________________________________________
