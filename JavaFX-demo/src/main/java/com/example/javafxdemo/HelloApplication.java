package com.example.javafxdemo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        Clock clock = new Clock();

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction((EventHandler) t -> clock.moon.setFill(colorPicker.getValue()));

        Group root = new Group();
        root.getChildren().add(clock);
        root.getChildren().add(colorPicker);
        colorPicker.setLayoutX(345);

        Scene scene = new Scene(root);

        ColorPicker colorPickerBackground = new ColorPicker();
        colorPickerBackground.setOnAction((EventHandler) t -> scene.setFill(colorPickerBackground.getValue()));

        clock.hourHand.setStroke(Color.WHITESMOKE);
        clock.minuteHand.setStroke(Color.WHITESMOKE);

        root.getChildren().add(colorPickerBackground);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("StyleSheet.css")).toExternalForm());
        scene.setFill(Color.DARKSLATEBLUE);

        stage.setResizable(false);
        stage.setTitle("Clock");
        //stage.getIcons().add(new Image("C:/users/mjaki/IdeaProjects/AnalogueClock/programming.png"));
        stage.initStyle(StageStyle.DECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}




