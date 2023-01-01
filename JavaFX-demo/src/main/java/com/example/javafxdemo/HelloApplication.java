package com.example.javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {   //Interface
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        Clock clock = new Clock();
        Scene scene = new Scene(clock);
        scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm()); //I'm crying!This one worked, but i don't understand it
        //scene.setFill(Color.TOMATO);            //Color in background of the clock
        //https://stackoverflow.com/questions/40792476/intellij-idea-how-to-open-css-filelike-application-css
        stage.setResizable(false);                  //to avoid the change of the size
        stage.setTitle("Clock");
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

/*import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.*;
import javafx.util.Duration;

import java.util.*;

/** Note that this clock does not keep perfect time, but is close.
 It's main purpose is to demonstrate various features of JavaFX. */

