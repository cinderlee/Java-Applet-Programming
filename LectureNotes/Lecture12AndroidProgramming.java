// don't need the physical device necessarily to run the code

/*
Android Studio 
where you write lower level applications 

Start a new project - asked what type of project 
what is your goal for your project? (they're trying to simplify what you might do)
there are google maps, navigation, basic activity etc
will load components that are standard for your project type

Start off with empty activity
Next step: Fill in the details for the project
Name
Package Name: (if you have domain name, it will be written in reversed order) com.myCompany.spring2020android OR edu.nyu.tandon.cs.cs3913spring2020android
Save Location
Language: Java (language isn't really Java though)
    Android uses a Java derivative language called Dolvic
    Difference btwn Java and Dolvic - stuff from Java is not all in Dolvic
    don't have swing libraries, jbuttons, jframes
    can have sockets but diff structure, not the same
Minimum API Level: API16: Android 4.1(Jelly Bean)
    Android uses interesting names for operating systems
    Gingerbread, honeycomb, jelly bean, donut, eclair, etc. (all desserts)
    API versions that come out are very much similar to which Java uses JDK and JRE
        JDK had to be a lower level than the JRE that will run on it
        JRE can only run applications that are written for a JRE or lower
        Choose the lowest version that has all the features that we want
    If need API v23, then choose only API v23 and not v24 and higher
        If choose higher, reducing number of devices that can possible run your code
        Same like JRE, choose latest JDK - only newest JRE can support those Java programs
    (Android studio states % of devices your project can run on)

Nothing about Android Programming is fast
NOTHING!!!!!!


*/

// there's a activity_main.xml but not copying that 

// code in MainActivity.java
package edu.nyu.tandon.cs.cs3913spring2020android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{
    @Override
    protect void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

// when you click play 
/*
pop up appears to Select Deployment Target

click on create new virtual device
"what type of device would you like"

create an android virtual device: select pixel 3
Next step: Need to select a system image (may need to download)
    System image: Pie, Q, Oreo, Nougat, Marshmallow, Lollipop

Download Pie (VERY LARGE EMULATOR THAT WILL MAKE YOUR PHONE WORK ON MACHINE)
Virtual machine running inside that will emulate what Android is running
Android Studio Package will tie into that simulated phone so that it can send the code and you'll see application loaded
Don't need a separate device/hack phone to get it to work :D 

After it's done downloadign: Verify Configuration
Afterwards - Virtual Device is available to use -> Android Studio will build the application and start an Android virtual OS
First time you boot the phone - it takes a while
After first boot, leave it active so you don't have to repeatedly reboot (and for an eternity)

took 3 minutes to build 
boot phone takes about 10 min and then just leave it up in the background
any time we play/activate/push code again, it will push the new code and communicate with phone up and running
DO NOT CLOSE THE PHONE
LEAVE IT RUNNING IN THE BACKGROUND

Can use camera to take a screenshot 
can interface it and rotate like a phone
it is a phone, running exact same OS that runs on physical device

Can pull up application that are the same applications in the phone 
*/

// Similar to how we use JFrame to load a frame on the screen
// Use same utilities to load page on screen

// Any change in design changes the xml
// need to be in design mode to change panel 
// Click on Palette Buttons and drag Button into panel
// it modifies the xml, adding the Button Components
// Text Palette: password(numeric), text, etc
// The password numeric version will be using the number keypad on the phone!

// There are other component that are widgets! 
// can create a lot more components with them
// mapview - adds a map to your application 
// adview - add advertisements by adding that one component and google will take care of the financial stuff
// don't need to worry about how to create a map, ads, etc. 

// everything looks pretty but nothing works when you click on the buttons etcs
// main activity class is main!
// when we call main, instead of calling main, we instead call is onCreate, which will call its parent's onCreate

// activity_main is actually the xml that has the layout info
// equivalent to creating a jframe and setting it visible
// from android, you will provide xml of what layout looks like (won't be giving jpanel, frame etc)
// xml has the info for the generation of text input, button
// you don't have any capability to interact with that component though

// to tie the components of the xml with the mainactivity.java file
// each component needs an ID field
package edu.nyu.tandon.cs.cs3913spring2020android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    int i = 0;
    TextView iVal; // not tied to the xml file id for textview
    // need to find the object 
    Switch s;

    @Override
    protect void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // looking for R.id.iVal 
        // look for component in xml that has id iVal
        iVal = findViewById(R.id.iVal);
        // do stuff with the object
        // R is a class with statics that represents the current system view (frame)
        // iVal.setText("Main is RUNNING!!!");
        // will update the text saying Main is RUNNING

        // can access the components of the buttons and set listeners for buttons
        Button upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(new UpListener());
        // Button downButton = findViewById(R.id.downButton);
        s = findViewById(R.id.select);

        // select, ival, upbutton are integers for the class created for the activity
        // don't know what they are but they are statically defined
        // need to set constraints to set location of the component!
        // because we dont know the dimensions of the device we are on
    }

    public class UpListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(s.isCheck()){
                i++;
            }
            else{
                i--;
            }
            // i++;
            iVal.setText("I=" + i);
        }
    }
}
