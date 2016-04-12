int ledPin13=13;
int ledPin12=12;
int ledPin11=8 ;

int state13=0;
int state12=0;
int state11=0;

int data=0;

void setup()
{
  pinMode(ledPin13,OUTPUT);
  pinMode(ledPin12,OUTPUT);
  pinMode(ledPin11,OUTPUT);
  
  //digitalWrite(ledPin,state);
  Serial.begin(9600);
}

void loop()
{
  //Serial.println(data);
  
  if(Serial.available()>0)
  {
    
    data=Serial.read();
    Serial.println(data);
  
    switch(data)
    {
      case 65:
        state13=!state13;
        digitalWrite(ledPin13,state13);
        
      break;
      
      case 66:
        state12=!state12;
        digitalWrite(ledPin12,state12);
        
      break;
      
      case 99:
        state11=!state11;
        digitalWrite(ledPin11,state11);
        
      break;
    }
  }
}


