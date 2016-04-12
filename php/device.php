<?php
require_once ("php_serial.class.php"); // include the php serial class

$data= $_GET['name']; // get the message from the phone
$serial = new phpSerial();// create a serial object
 // specify where you want to send the data to (where the arduino is connected in the usb ports)
$serial->deviceSet("COM6");
$serial->confBaudRate(9600);// the transfer data rate of your arduino
$serial->deviceOpen();// start the communication
$serial->sendMessage($data); // send message to arduino
$serial->deviceClose();// close the serial connection

if($data == "A"){
	echo "Light";

}
else if($data == "B"){
	echo "Fan";

}
else{
	echo "Sound";

	
}

?>