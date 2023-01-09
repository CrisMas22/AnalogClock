package com.example.javafxdemo;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {   //Interface

        Clock clock = new Clock();

        ColorPicker colorPicker = new ColorPicker();colorPicker.setOnAction((EventHandler) t -> clock.moon.setFill(colorPicker.getValue()));

        Group root = new Group();
        root.getChildren().add(clock);
        root.getChildren().add(colorPicker);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm()); //I'm crying!This one worked, but i don't understand it
        scene.setFill(Color.DARKSLATEBLUE);            //Color in background of the clock
        //https://stackoverflow.com/questions/40792476/intellij-idea-how-to-open-css-filelike-application-css
        stage.setResizable(false);                  //to avoid the change of the size
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



/** Note that this clock does not keep perfect time, but is close.
 Its main purpose is to demonstrate various features of JavaFX. */

