package com.example.javafxdemo;

public class AnalogClock {
    private int seconds;
    private int minutes;
    private int hours;

    public AnalogClock(final int hour, final int minute, final int second){
        this.hours = hour;
        this.minutes = minute;
        this.seconds = second;

        /*while(true){          The program proposed to create a methode (added next)
            this.addSecond();
        }*/

    }

    private void addSecond() {
        if(this.seconds < 60){
            this.seconds++;
        }
        else{
            this.seconds = 0;
        }
    }
    private void addMinute(){
        if(this.minutes < 60){
            this.minutes++;
        }
        else{
            this.minutes = 0;
        }
    }
    private void addHour(){
        if(this.hours < 12){
            this.hours++;
        }
        else{
            this.hours = 0;
        }
    }
}
