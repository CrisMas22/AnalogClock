package com.example.javafxdemo;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Dial extends Rectangle {
    Rotate rotate = new Rotate(0,240,240);      //v1 and v2 = 240 is the center of the clock. We want the dial to move from a point that id the center of the clock

    public Dial(double body, double tail, double width,  Paint fill) {
        super(width, body, fill);           // body + tail
        this.setX(240-width/2);             //we use the radio of the circle(240) minus the width of the circle between 2 to have the dial on the middle
        this.setY(240-body);                //we use the radio of the circle minus the body to have the dial from the middle to the number, no longer
        this.setEffect(new DropShadow());   //shadow effect on the Dial
        this.setArcHeight(width);
        this.setArcWidth(width);
        this.getTransforms().add(rotate);
    }
    public void updateDial(double angle){   //it's public to be able to be called from other classes
        rotate.setAngle(angle);             //we use this method to update the angle of the rotation
    }
}
