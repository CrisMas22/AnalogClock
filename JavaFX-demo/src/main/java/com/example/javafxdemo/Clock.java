package com.example.javafxdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Calendar;

public class Clock extends Parent {
    StackPane stackPane = new StackPane(); //container of elements
    Group group = new Group();
    //We create clock hands
    Dial secondHand = new Dial(200,50,5,Color.CRIMSON);
    Dial minuteHand = new Dial(150,40,10, Color.BLACK);
    Dial hourHand = new Dial(100,30,15, Color.BLACK);

    Calendar calendar;      //created directly after the method

    public Clock(){
        layoult();
    }
    // design clock (circles)
    private void layoult(){

        Circle outline = new Circle(240, Color.BLACK); //first element = outline of the clock
        outline.setEffect(new Lighting());

        Circle moon = new Circle(220, Color.WHITESMOKE);
        moon.setEffect(new InnerShadow());

        //Creation of the text
        Text text_12 = new Text("12");                  // To write a 12 on the clock
        Text text_1 = new Text("1");
        Text text_2 = new Text("2");
        Text text_3 = new Text("3");
        Text text_4 = new Text("4");
        Text text_5 = new Text("5");
        Text text_6 = new Text("6");
        Text text_7 = new Text("7");
        Text text_8 = new Text("8");
        Text text_9 = new Text("9");
        Text text_10 = new Text("10");
        Text text_11 = new Text("11");


        //position of the text respect to the circle
        text_12.setTranslateY(-150);                       // on the Y axe we will write 12 on the top (minus go up, plus go down)
        text_1.setTranslateY(-150);
        text_2.setTranslateY(-150);
        text_3.setTranslateX(150);                         // 150 is the radio of the circle where is located the number
        text_4.setTranslateY(-150);
        text_5.setTranslateY(-150);
        text_6.setTranslateY(150);
        text_7.setTranslateY(-150);
        text_8.setTranslateY(-150);
        text_9.setTranslateX(-150);
        text_10.setTranslateY(-150);
        text_11.setTranslateY(-150);


        //adding the circle and the text
        stackPane.getChildren().addAll(outline, moon);   // we add the outline through the method getChildren and add
        stackPane.getChildren().addAll(text_12,text_3,text_6,text_9);

        //to create the lines which show the hours
        Rectangle[]tick_hour = new Rectangle[12];
        for (int i = 0; i < 12; i++) {
            tick_hour[i] = new Rectangle(20,5, Color.BLUE);
            tick_hour[i].setTranslateX(190* Math.cos(-(Math.PI/6)*i));  //the lines stay all in a line
            tick_hour[i].setTranslateY(190* Math.sin(-(Math.PI/6)*i));  // also with this one, all the line find their place around the circle
            tick_hour[i].setRotate(-(180/6)*i);                         // now the lines have a perfect rotation
        }
        stackPane.getChildren().addAll(tick_hour);

        //to create the lines which show the minutes
        Rectangle[]tick_minutes = new Rectangle[60];
        for (int i = 0; i < 60; i++) {
            if(i % 5 == 0) {
                continue;
            }
            tick_minutes[i] = new Rectangle(10,2, Color.BLUE);
            tick_minutes[i].setTranslateX(190* Math.cos(-(Math.PI/30)*i));  //the lines stay all in a line
            tick_minutes[i].setTranslateY(190* Math.sin(-(Math.PI/30)*i));  // also with this one, all the line find their place around the circle
            tick_minutes[i].setRotate(-(180/30)*i);                         // now the lines have a perfect rotation
            stackPane.getChildren().add(tick_minutes[i]);
        }
        group.getChildren().add(stackPane);
        group.getChildren().addAll(hourHand,minuteHand,secondHand);

        //little circles in the middle, we can delete it
        group.getChildren().add(new Circle(240,240,10,Color.BLUE));
        group.getChildren().add(new Circle(240,240,5,Color.BLACK));



        this.getChildren().add(group);               // then addAll + outline and moon (all the circles)
                                                        // I didn't understand get.children().add(stackPane)
        updateClock();                                 // without it, doesn't work
        executeClock();                                // without it, the clock appear, but doesn't move
    }
    //Method to make work clock hands
    private void updateClock(){
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        double angle_sec = 360/60*sec;
        double angle_min = 360/60*min + (360.0/60)/60*sec;  //+ (360.0/60)/60*sec means = every sec, it will move a little bit
        double angle_hour = 360/12*hour + (360.0/12)/60*min + (360.0/12)/3600 * sec; //+ (360.0/12)/60*min means = every min, it will move a little bit

        secondHand.updateDial(angle_sec);
        minuteHand.updateDial(angle_min);
        hourHand.updateDial(angle_hour);

    }//Control the movement of the secondHand's clock
    public void executeClock(){
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
