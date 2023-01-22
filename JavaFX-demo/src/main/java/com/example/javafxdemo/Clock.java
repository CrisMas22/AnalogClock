package com.example.javafxdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Calendar;

public class Clock extends Parent {
    protected StackPane stackPane = new StackPane(); //container of elements
    protected Group group = new Group();
    protected Circle moon = new Circle(220, Color.WHITESMOKE);
    //We create clock hands
    protected Dial secondHand = new Dial(200, 50, 5, Color.CRIMSON);
    protected Dial minuteHand = new Dial(150, 40, 10, Color.BLACK);
    protected Dial hourHand = new Dial(100, 30, 15, Color.BLACK);

    protected Calendar calendar;      //created directly after the method

    public Clock() {
        layoult();
    }

    // design clock (circles)
    private void layoult() {
        Circle outline = new Circle(240, Color.BLACK); //first element = outline of the clock
        outline.setEffect(new Lighting());
        moon.setEffect(new InnerShadow());

        //Creation of the text
        Text text12 = new Text("12");                  // To write a 12 on the clock
        Text text3 = new Text("3");
        Text text6 = new Text("6");
        Text text9 = new Text("9");

        //position of the text respect to the circle
        text12.setTranslateY(-150);                       // on the Y axe we will write 12 on the top (minus go up, plus go down)
        text3.setTranslateX(150);                         // 150 is the radio of the circle where is located the number
        text6.setTranslateY(150);
        text9.setTranslateX(-150);

        //adding the circle and the text
        stackPane.getChildren().addAll(outline, moon);   // we add the outline through the method getChildren and add
        stackPane.getChildren().addAll(text12, text3, text6, text9);

        //to create the lines which show the hours
        Rectangle[] tickHour = new Rectangle[12];
        for (int i = 0; i < 12; i++) {
            tickHour[i] = new Rectangle(20, 5, Color.BLACK);
            tickHour[i].setTranslateX(190 * Math.cos(-(Math.PI / 6) * i));  //the lines stay all in a line
            tickHour[i].setTranslateY(190 * Math.sin(-(Math.PI / 6) * i));  // also with this one, all the line find their place around the circle
            tickHour[i].setRotate(-(180 / 6) * i);                         // now the lines have a perfect rotation
            tickHour[i].setStroke(Color.WHITESMOKE);
        }
        stackPane.getChildren().addAll(tickHour);

        //to create the lines which show the minutes
        Rectangle[] tickMinutes = new Rectangle[60];
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                continue;
            }
            tickMinutes[i] = new Rectangle(10, 2, Color.BLACK);
            tickMinutes[i].setTranslateX(190 * Math.cos(-(Math.PI / 30) * i));  //the lines stay all in a line
            tickMinutes[i].setTranslateY(190 * Math.sin(-(Math.PI / 30) * i));  // also with this one, all the line find their place around the circle
            tickMinutes[i].setRotate(-(180 / 30) * i);                         // now the lines have a perfect rotation
            stackPane.getChildren().add(tickMinutes[i]);
            tickMinutes[i].setStroke(Color.WHITESMOKE);
        }
        group.getChildren().add(stackPane);
        group.getChildren().addAll(hourHand, minuteHand, secondHand);

        //little circles in the middle, we can delete it
        group.getChildren().add(new Circle(240, 240, 10, Color.PURPLE));
        group.getChildren().add(new Circle(240, 240, 5, Color.BLACK));

        this.getChildren().add(group);               // then addAll + outline and moon (all the circles)
        // I didn't understand get.children().add(stackPane)
        updateClock();                                 // without it, doesn't work
        executeClock();                                // without it, the clock appear, but doesn't move
    }

    //Method to make work clock hands
    private void updateClock() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        double angleSec = 360 / 60 * sec;
        double angleMin = 360 / 60 * min + (360.0 / 60) / 60 * sec;  //+ (360.0/60)/60*sec means = every sec, it will move a little bit
        double angleHour = 360 / 12 * hour + (360.0 / 12) / 60 * min + (360.0 / 12) / 3600 * sec; //+ (360.0/12)/60*min means = every min, it will move a little bit

        secondHand.updateDial(angleSec);
        minuteHand.updateDial(angleMin);
        hourHand.updateDial(angleHour);

    }//Control the movement of the secondHand's clock

    private void executeClock() {
        Timeline primaryLine = new Timeline();
        Timeline secondLine = new Timeline();

        secondLine.setCycleCount(Timeline.INDEFINITE);     //once the second hand is moving, the others are moving automatically too. But the hour hand should move more little by little getting close to the next hour instead of a bif jump. We will about that adding an extra method

        KeyFrame primaryKey = new KeyFrame(
                new Duration(1000 - calendar.get(Calendar.MILLISECOND) % 1000),  //to synchronize the clock with the actual time. There are 1000 milisec in a sec. Of the 1000 milisec we will rest what it's already done. The module is to prevent the case where the module is 0.
                event -> {
                    updateClock();      //without it there isn't synchronization
                    secondLine.play();
                }
        );
        KeyFrame secondKey = new KeyFrame(
                Duration.seconds(1),
                event -> {
                    updateClock();      //without it, the second hand move just one time
                }
        );
        primaryLine.getKeyFrames().add(primaryKey);
        secondLine.getKeyFrames().add(secondKey);   //don't forget to call this method!!
        primaryLine.play();
    }

}
