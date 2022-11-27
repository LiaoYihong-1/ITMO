package com.example.LAB2.Results;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Result implements Serializable {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private double r;
    @Getter
    @Setter
    private boolean inRange;
    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private double corX;
    @Getter
    @Setter
    private double corY;
    public Result(){
    };

    public Result(double x,double y, double r, boolean inRange,double corX,double corY){
        this.x = x;
        this.y = y;
        this.r = r;
        this.inRange = inRange;
        if(inRange){
            this.text = "in range";
        }else {
            this.text = "not in range";
        }
        this.corX = corX;
        this.corY = corY;
    }

}
