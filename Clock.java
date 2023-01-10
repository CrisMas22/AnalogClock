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
    StackPane stackPane = new StackPane();
    Group group = new Group();
    Circle moon = new Circle(220, Color.WHITESMOKE);
    Dial secondHand = new Dial(200, 50, 5, Color.CRIMSON);
    Dial minuteHand = new Dial(150, 40, 10, Color.BLACK);
    Dial hourHand = new Dial(100, 30, 15, Color.BLACK);

    int changeColorCounter = 1;

    Calendar calendar;

    public Clock() {
        layoult();
    }

    public void layoult() {
        Circle outline = new Circle(240, Color.BLACK);
        outline.setEffect(new Lighting());
        moon.setEffect(new InnerShadow());

        Text text_12 = new Text("12");
        Text text_3 = new Text("3");
        Text text_6 = new Text("6");
        Text text_9 = new Text("9");

        text_12.setTranslateY(-150);
        text_3.setTranslateX(150);
        text_6.setTranslateY(150);
        text_9.setTranslateX(-150);

        stackPane.getChildren().addAll(outline, moon);
        stackPane.getChildren().addAll(text_12, text_3, text_6, text_9);

        Rectangle[] tick_hour = new Rectangle[12];
        for (int i = 0; i < 12; i++) {
            tick_hour[i] = new Rectangle(20, 5, Color.BLUE);
            tick_hour[i].setTranslateX(190 * Math.cos(-(Math.PI / 6) * i));
            tick_hour[i].setTranslateY(190 * Math.sin(-(Math.PI / 6) * i));
            tick_hour[i].setRotate(-(180 / 6) * i);
        }
        stackPane.getChildren().addAll(tick_hour);

        Rectangle[] tick_minutes = new Rectangle[60];
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                continue;
            }
            tick_minutes[i] = new Rectangle(10, 2, Color.BLUE);
            tick_minutes[i].setTranslateX(190 * Math.cos(-(Math.PI / 30) * i));
            tick_minutes[i].setTranslateY(190 * Math.sin(-(Math.PI / 30) * i));
            tick_minutes[i].setRotate(-(180 / 30) * i);
            stackPane.getChildren().add(tick_minutes[i]);
        }
        group.getChildren().add(stackPane);
        group.getChildren().addAll(hourHand, minuteHand, secondHand);

        //little circles in the middle, we can delete it
        group.getChildren().add(new Circle(240, 240, 10, Color.BLUE));
        group.getChildren().add(new Circle(240, 240, 5, Color.BLACK));


        this.getChildren().add(group);

        updateClock();
        executeClock();
    }

    private void updateClock() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        double angle_sec = 360 / 60 * sec;
        double angle_min = 360 / 60 * min + (360.0 / 60) / 60 * sec;
        double angle_hour = 360 / 12 * hour + (360.0 / 12) / 60 * min + (360.0 / 12) / 3600 * sec;

        secondHand.updateDial(angle_sec);
        minuteHand.updateDial(angle_min);
        hourHand.updateDial(angle_hour);

    }

    public void executeClock() {
        Timeline primaryLine = new Timeline();
        Timeline secondLine = new Timeline();

        secondLine.setCycleCount(Timeline.INDEFINITE);

        KeyFrame primaryKey = new KeyFrame(
                new Duration(1000 - calendar.get(Calendar.MILLISECOND) % 1000),
                event -> {
                    updateClock();
                    secondLine.play();
                }
        );
        KeyFrame secondKey = new KeyFrame(
                Duration.seconds(1),
                event -> {
                    updateClock();
                }
        );
        primaryLine.getKeyFrames().add(primaryKey);
        secondLine.getKeyFrames().add(secondKey);
        primaryLine.play();
    }

}
