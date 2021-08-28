package com.tb.flp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Triangle {

    int id;
    int a;
    int b;
    int c;
    double area;
    long time;

    public Triangle(int id, int a, int b, int c, double area, long time) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.area = area;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public int getC() {
        return this.c;
    }

    public double getArea() {
        return this.area;
    }

    public String getTime() {
        SimpleDateFormat dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date resultdate = new Date(this.time);
        return dateTime.format(resultdate);
    }
}
