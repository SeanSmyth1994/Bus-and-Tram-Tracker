package com.example.bustracker.Logic;

import android.widget.TextView;

import com.example.bustracker.R;

public class timeLogic {

    static String message;
    static String message1;
    static String message2;
    static String message3;
    static String message4;



    public static void setupLocation(TextView txt,TextView txt1,TextView txt2,TextView txt3, TextView txt4,int mins, int mins1, int mins2, int mins3, int mins4, int walkingTime, int drawable) {
        if(walkingTime == mins){
            txt.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_warning_black_24dp, 0);
            message = "Run";
        }
        else if (walkingTime < mins) {
            txt.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_done_black_24dp, 0);
            message = "You will make it";
        } else if (walkingTime > mins) {
            txt.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_clear_black_24dp, 0);
            message = "You will not make it";
        }
        if (walkingTime < mins1) {
            txt1.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_done_black_24dp, 0);
            message1 = "You will make it";
        } else if (walkingTime > mins1) {
            txt1.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_clear_black_24dp, 0);
            message1 = "You will not make it";
        }
        if (walkingTime < mins2) {
            txt2.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_done_black_24dp, 0);
            message2 = "You will make it";
        } else if (walkingTime > mins2) {
            txt2.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_clear_black_24dp, 0);
            message2 = "You will not make it";
        }
        if (walkingTime < mins3) {
            txt3.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_done_black_24dp, 0);
            message3 = "You will make it";
        } else if (walkingTime > mins3) {
            txt3.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_clear_black_24dp, 0);
            message3 = "You will not make it";
        }
        if (walkingTime < mins4) {
            txt4.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_done_black_24dp, 0);
            message4 = "You will make it";
        } else if (walkingTime > mins4) {
            txt4.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, R.drawable.ic_clear_black_24dp, 0);
            message4 = "You will not make it";
        }

    }
    public static void updateUi(TextView txt,TextView txt1,TextView txt2,TextView txt3, TextView txt4,int mins, int mins1, int mins2, int mins3, int mins4, String routeName1, String routeName2, String routeName3, String routeName4, String routeName5, String directionName1,
                                String directionName2,String directionName3,String directionName4,String directionName5){
        if(timeLogic.getMessage() == null){
            message = "Loading...";
        }
        if(timeLogic.getMessage1() == null){
            message1 = "Loading...";
        }
        if(timeLogic.getMessage2() == null){
            message2 = "Loading...";
        }
        if(timeLogic.getMessage3() == null){
            message3 = "Loading...";
        }
        if(timeLogic.getMessage4() == null){
            message4 = "Loading...";
        }
        if (mins == 1) {
            txt.setText(routeName1 + " to: " + directionName1 + "\nArriving in " + mins + " minute" + "\n" + message);
        } else if (mins == 0) {
            txt.setText(routeName1 + " to: " + directionName1 +  "\nArriving Now" + "\n" + message);
        } else if (mins1 == 1) {
            txt1.setText(routeName2 + " to: " + directionName2 +  "\nArriving in " + mins + " minute" + "\n" + message1);
        } else if (mins1 == 0) {
            txt1.setText(routeName2 + " to: " + directionName2 +  "\nArriving Now" + "\n" + message1);
        } else if (mins2 == 1) {
            txt2.setText(routeName3 + " to: " + directionName3 +  "\nArriving in " + mins + " minute" + "\n" + message2);
        } else if (mins2 == 0) {
            txt2.setText(routeName3 + " to: " + directionName3 + "\nArriving Now" + "\n" + message2);
        } else if (mins3 == 1) {
            txt3.setText(routeName4 + " to: " + directionName4 + "\nArriving in " + mins + " minute" + "\n" + message3);
        } else if (mins3 == 0) {
            txt3.setText(routeName4 + " to: " + directionName4 + "\nArriving Now" + "\n" + message3);
        } else if (mins4 == 1) {
            txt4.setText(routeName5 + " to: " + directionName5 + "\nArriving in " + mins + " minute" + "\n" + message4);
        } else if (mins4 == 0) {
            txt4.setText(routeName5 + " to: " + directionName5 + "\nArriving Now" + "\n" + message4);

        } else {
            txt.setText(routeName1 + " to: " + directionName1 + "\nDeparture time: " + mins + " mins" + "\n" + message);
            txt1.setText(routeName2 + " to: " + directionName2 + "\nDeparture time: " + mins1 + " mins"+ "\n" + message1);
            txt2.setText(routeName3 + " to: " + directionName3 + "\nDeparture time: " + mins2 + " mins" + "\n" + message2);
            txt3.setText(routeName4 + " to: " + directionName4 + "\nDeparture time: " + mins3 + " mins"+ "\n" + message3);
            txt4.setText(routeName5 + " to: " + directionName5 + "\nDeparture time: " + mins4 + " mins"+ "\n" + message4);
        }
    }



    public static String getMessage() {
        return message;
    }

    public static String getMessage1() {
        return message1;
    }

    public static String getMessage2() {
        return message2;
    }

    public static String getMessage3() {
        return message3;
    }

    public static String getMessage4() {
        return message4;
    }
}
