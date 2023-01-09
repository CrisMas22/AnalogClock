package com.example.javafxdemo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        Clock clock = new Clock();

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction((EventHandler) t -> clock.moon.setFill(colorPicker.getValue()));

        Group root = new Group();
        root.getChildren().add(clock);
        root.getChildren().add(colorPicker);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm());
        scene.setFill(Color.DARKSLATEBLUE);
        stage.setResizable(false);
        stage.setTitle("Clock");
        stage.getIcons().add(new Image("C:/users/mjaki/IdeaProjects/AnalogueClock/programming.png"));
        stage.initStyle(StageStyle.DECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}




