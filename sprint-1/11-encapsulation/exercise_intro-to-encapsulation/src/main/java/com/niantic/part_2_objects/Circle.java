package com.niantic.part_2_objects;

public class Circle
{
    private int radius;

    public Circle() {}

    public Circle(int radius)
    {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public double getArea()
    {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public int getDiameter()
    {
        return 2 * this.radius;
    }

    public double getCircumference()
    {
        return Math.PI * getDiameter();
    }
}
