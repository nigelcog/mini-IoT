# mini-IoT
This experiment showcases a miniature version of the Internet of Things. 

An Arduino UNO board and an Android device is used in this experiment. The android app communicates with the arduino wirelessly and turns on and off Light, Fan and Sound devices connected to the arduino. The Light,Fan and Sound devices are connected to the arduino via breadboard and wires respectively. The arduino receives power from the USB cable. PHP language is used to pass messages to the arduino using the serial com port of the PC via the USB cable.

The goal of this experiment is to demonstrate the flexibility and power of the android operating system when used together with the arduino UNO board. This experiment proves that anyone with basic programming skills can create something very useful.

<b>Instructions to make the project up and running:</b>

1. Open the Arduino code in the Arduino folder into your Arduino IDE and upload it to the Arduino UNO board.
2. In the Arduino IDE check the COM port of your Arduino and update it in "device.php" file in the php folder.
2. Upload the php files in the php folder to your XAMPP server in "htdocs/your_folder_name" and start the server(Apache and MySQL).
3. Download the Android project from the "android" folder and open it into Android Studio IDE.
4. In ActivityMain replace the IP Address "192.168.0.105" by your machines local IP address.
5. In ActivityMain replace the path "/arduino/test/device.php?name=" by your php file path inside htdocs. For example "/my_folder/device.php?name=".
6. Keep Arduino UNO board connected to the same PC with your XAMPP server running.
7. Run the android project in your android device(I haven't tested in the emulator but i think it should work fine. Use Android 4.1 and above).
8. Connect your Android device via WiFi in the same network as your Laptop/PC.
9. Run and test the app. When clicked on a particular card it should turn the devices on and off(Check Android monitor for Logcat message from the server and for any other error).

<b>Arduino Connection Instructions:</b>

1. You need to know arduino basics to assemble the circuit. Here Arduino UNO, a breadboard, Arduino specific USB cable and some wrires are used.
2. The circuit is very simple. You can create your own or use mine from this video: https://www.youtube.com/watch?v=8IcIdlPqmwo
3. Pins used are 8,12,13 and ground(the pin after 13).
4. Pin 8 is used for Light, pin 12 for Fan and pin 13 for sound. But you can use any pin for any purpose you choose.
5. Data A,B and c(small case) is being passed from the Android app to php to the arduino when you click any particular card.
6. Each data corresponds to a Pin and thus turns it on or off.

