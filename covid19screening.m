function covid19screening
clear all; close all
a = arduino('COM4', "Uno");
writeDigitalPin(a, "D4", 1)
uiwait(msgbox(['Please place the temperature sensor on your forehead. ' ...
    'This will take about 15-20 seconds and will be done twice. Click "OK" to continue.']))
writeDigitalPin(a, "D4", 1)
fprintf("\nYour temperature will be measured.\nNote: this is not your actual" + ...
    " temperature, this is for the sensor to adapt\n" + ...
    "to your body temperature. Please wait a few moments...")
% tic
for i = 1:100
    writeDigitalPin(a, "D4", 1); pause(0.01); writeDigitalPin(a, "D4", 0); pause(0.01)
 volt(i) = readVoltage(a, 'A0');
 i = i + 1;
end
% toc
voltMean = mean(volt);
analog = voltMean/0.0048875;
b = 3975;
resistance = (1023 - analog) * 10000/analog;
tempMean = (1/(log(resistance/10000)/b + 1/298.15) - 273.15);
tempEstimate1 = tempMean + 4.1;
comWndwTemp = '\n\nYour temperature is %.1f °C.\n';

if tempEstimate1 >= 37.5
    msgBoxFever = '\n\nYour temperature is %.1f °C, you have a fever, stay home.';
    msgbox(sprintf(msgBoxFever, tempEstimate))
    fprintf(sprintf(comWndwTemp, tempEstimate))
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return
    
else 
   fprintf(sprintf(comWndwTemp, tempEstimate1));
end 
writeDigitalPin(a, "D4", 1)
answer1 = questdlg(sprintf(['Q1: Have you tested positive for COVID-19 in the last 10 days, ' ...
    '(either by rapid test or laboratory-based test?)']), "Question 1", "Yes", ...
    "No", "No");
if answer1 == "Yes"
    msgbox("You may be positive for COVID-19, you must self-iscolate and/or " + ...
        "consult a physician. If you feel very unwell, Call 911 or go directly to " + ...
        "your nearest emergency department.", "NOTICE", "Error")
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return 
end 
writeDigitalPin(a, "D4", 1)
answer2 = questdlg(['Q2: Have you been exposed in the last 14 days to ' ...
    'someone that has tested positive for COVID-19 ' ...
    'or has COVID-19 symptoms?'], "Question 2", "Yes", ...
    "No", "No");
if answer2 == "Yes"
   msgbox("You may be positive for COVID-19, you must self-iscolate and/or " + ...
        "consult a physician. If you feel very unwell, Call 911 or go directly to " + ...
        "your nearest emergency department.", "NOTICE", "Error")
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return
end 
answer3 = questdlg(sprintf(['Q3: Do you have ANY of the following symptoms?' ...
    '\n- Chills or Shivers\n- Sore Throat or Hoarse Voice\n- Cough\n- ' ...
    'Difficulty of Breathing\n- Loss of Taste or Smell' ...
    '\n- Vomiting or Diarrhea for +24h']), 'Question 3', 'Yes', ...
    'No', 'No');
writeDigitalPin(a, "D4", 1)
if answer3 == "Yes"
      msgbox("You may be positive for COVID-19, you must self-iscolate and/or " + ...
        "consult a physician. If you feel very unwell, Call 911 or go directly to " + ...
        "your nearest emergency department.", "NOTICE", "Error")
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return
end 
writeDigitalPin(a, "D4", 1)
answer4 = questdlg(sprintf(['Q4: Do you have TWO OR MORE of the following ' ...
    'symptoms?\n- Runny Nose\n- Muscle Aches\n- Fatigue\n- Conjunctivitis' ...
    '\n- Headache\n- Skin Rash of Unknown Cause' ...
    '\n- Nausea or Loss of Appetite']), 'Question 4', 'Yes', ['Only ONE ' ...
    'of them'], 'No', 'No');
if answer4 == "Yes"
       msgbox("You may be positive for COVID-19, you must self-iscolate and/or " + ...
        "consult a physician. If you feel very unwell, Call 911 or go directly to " + ...
        "your nearest emergency department.", "NOTICE", "Error")
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return
    elseif answer4 == "Only ONE of them"
         msgbox("You are SUSPECTED to be positive for COVID-19. Self-isolate" + ...
             " and notify your doctor.", "NOTICE", "Warn")
         writeDigitalPin(a, "D4", 1); pause(0.2); writeDigitalPin(a, "D4", 0);
         writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
         return 
end 
    uiwait(msgbox(['Please place the temperature sensor on your forehead again.' ...
        ' Click "OK" to continue.']))
writeDigitalPin(a, "D4", 1)

fprintf("Your actual temperature will be measured. Please wait a few moments...")
% tic
for i = 1:100
    writeDigitalPin(a, "D4", 1); pause(0.01); writeDigitalPin(a, "D4", 0); pause(0.01)
 volt(i) = readVoltage(a, 'A0');
 i = i + 1;
end
% toc
voltMean = mean(volt);
analog = voltMean/0.0048875;
b = 3975;
resistance = (1023 - analog) * 10000/analog;
tempMean = (1/(log(resistance/10000)/b + 1/298.15) - 273.15);
tempEstimate2 = tempMean + 4.1;
comWndwTemp = '\n\nYour ACTUAL temperature is %.1f °C.\n';

if tempEstimate2 >= 37.5
    msgBoxFever = '\n\nYour temperature is %.1f °C, you have a fever, stay home.';
    msgbox(sprintf(msgBoxFever, tempEstimate2))
    fprintf(sprintf(comWndwTemp, tempEstimate2))
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0); 
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    writeDigitalPin(a, "D4", 0); pause(0.2); writeDigitalPin(a, "D4", 1); 
    writePWMDutyCycle(a,'D5',0.33); pause(0.5); writeDigitalPin(a,'D5',0);
    return
    
else 
   fprintf(sprintf(comWndwTemp, tempEstimate2));
end 
if answer1 == "No" && answer2 == "No" && answer3 == "No" && answer4 == "No"
    uiwait(msgbox(['No signs of COVID-19. Click "OK" to enter your name ' ...
        'in the command window.'], "NOTICE"));
end
prompt = "Please enter your name:\n";
datetimeString = string(datetime);
name = input(prompt, "s");
if isempty(name)
    name = 'the individual';
end 
writeDigitalPin(a, "D4", 1); pause(0.2); writeDigitalPin(a, "D4", 0); pause(0.5)
writeDigitalPin(a, "D4", 1); pause(0.2); writeDigitalPin(a, "D4", 0); pause(0.5)
writeDigitalPin(a, "D4", 1); pause(0.2); writeDigitalPin(a, "D4", 0); pause(0.5)
tempRecorded = "Recorded temperature is %.1f °C.";
tempRecordedText = sprintf(tempRecorded, tempEstimate2); 
fileText = "COVID-19 Screening Results:\n\nThis certifies that, " + name + ", " + ...
    "is tested negative for COVID-19, on " ...
    + datetimeString + ".\nNo self-isolation is required.\nPlease show this " + ...
    "to your supervisor at your workplace or institution.\n" + tempRecordedText ;
openResults = fopen("C:\Users\yazan\OneDrive\Desktop\screening_results.txt" ,"w"); 
% change the directory above corresponding to your PC 
% ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
% ||||||||||||||||||||||||||||||||||||||||||||||||||
fprintf(openResults, fileText);
open("screening_results.txt")
fclose("all");



