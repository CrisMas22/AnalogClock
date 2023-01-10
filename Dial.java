package com.example.javafxdemo;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Dial extends Rectangle {
    Rotate rotate = new Rotate(0,240,240);

    public Dial(double body, double tail, double width,  Paint fill) {
        super(width, body, fill);
        this.setX(240-width/2);
        this.setY(240-body);
        this.setEffect(new DropShadow());
        this.setArcHeight(width);
        this.setArcWidth(width);
        this.getTransforms().add(rotate);
    }
    public void updateDial(double angle){
        rotate.setAngle(angle);
    }
}
