package com.niantic.part_2_objects;

import org.w3c.dom.css.Rect;

public class Rectangle
{
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getArea()
    {
        return this.width * this.height;
    }
}
