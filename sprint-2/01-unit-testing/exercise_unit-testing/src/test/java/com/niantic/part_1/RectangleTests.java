package com.niantic.part_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTests
{
    private Rectangle rectangle;

    @BeforeEach
    public void setup() {rectangle = new Rectangle();}

    @Test
    public void newDefaultRectangle_should_haveFiveWidthFiveHeight()
    {
        // arrange
        int expectedHeight = 5;
        int expectedWidth = 5;

        // act

        // assert
        int actualHeight = rectangle.getHeight();
        int actualWidth = rectangle.getWidth();

        assertEquals(expectedHeight, actualHeight, "New Rectangle() should have a height of 5.");
        assertEquals(expectedWidth, actualWidth, "New Rectangle() should have a width of 5.");
    }

    @Test
    public void newCustomRectangle_should_haveCorrectWidthHeight()
    {
        // arrange
        Rectangle rectangle1 = new Rectangle(2, 4);
        Rectangle rectange2 = new Rectangle(10, 8);
        Rectangle rectangle3 = new Rectangle(3, 3);

        int expectedWidth1 = 2;
        int expectedHeight1 = 4;
        int expectedWidth2 = 10;
        int expectedHeight2 = 8;
        int expectedWidth3 = 3;
        int expectedHeight3 = 3;

        // act
        int actualWidth1 = rectangle1.getWidth();
        int actualHeight1 = rectangle1.getHeight();
        int actualWidth2 = rectange2.getWidth();
        int actualHeight2 = rectange2.getHeight();
        int actualWidth3 = rectangle3.getWidth();
        int actualHeight3 = rectangle3.getHeight();

        // assert
        assertEquals(expectedWidth1, actualWidth1, "2 x 4 rectangle - Width should be 2.");
        assertEquals(expectedHeight1, actualHeight1, "2 x 4 rectangle - Height should be 4.");
        assertEquals(expectedWidth2, actualWidth2, "10 x 8 rectangle - Width should be 10.");
        assertEquals(expectedHeight2, actualHeight2, "10 x 8 rectangle - Height should be 8.");
        assertEquals(expectedWidth3, actualWidth3, "3 x 3 rectangle - Width should be 3.");
        assertEquals(expectedHeight3, actualHeight3, "3 x 3 rectangle - Height should be 3.");
    }

    @Test
    public void getArea_should_calculateAreaCorrectly()
    {
        // arrange
        Rectangle rectangle2 = new Rectangle(10, 2);
        Rectangle rectangle3 = new Rectangle(0, 4);
        Rectangle rectangle4 = new Rectangle(4, 0);
        Rectangle rectangle5 = new Rectangle(-3, 4);
        Rectangle rectangle6 = new Rectangle(8, -2);

        int expectedArea1 = 25;
        int expectedArea2 = 20;
        int expectedAreaZeroNegative = 0;

        // act
        int actualArea1 = rectangle.getArea();
        int actualArea2 = rectangle2.getArea();
        int actualArea3 = rectangle3.getArea();
        int actualArea4 = rectangle4.getArea();
        int actualArea5 = rectangle5.getArea();
        int actualArea6 = rectangle6.getArea();

        // assert
        assertEquals(expectedArea1, actualArea1, "Because width is 5 and height is 5 - Area should be 25.");
        assertEquals(expectedArea2, actualArea2, "Because width is 10 and height is 2 - Area should be 20.");
        assertEquals(expectedAreaZeroNegative, actualArea3, "Because width is 0 and height is 4 - Area should be 0.");
        assertEquals(expectedAreaZeroNegative, actualArea4, "Because width is 4 and height is 0 - Area should be 0.");
        assertEquals(expectedAreaZeroNegative, actualArea5, "Because width is -3 and height is 4 - Area should be 0.");
        assertEquals(expectedAreaZeroNegative, actualArea6, "Because width is 8 and height is -2 -Area should be 0.");
    }

    @Test
    public void getPerimeter_should_calculatePerimeterCorrectly()
    {
        // arrange
        Rectangle rectangle2 = new Rectangle(10, 2);
        Rectangle rectangle3 = new Rectangle(0, 4);
        Rectangle rectangle4 = new Rectangle(4, 0);
        Rectangle rectangle5 = new Rectangle(-3, 4);
        Rectangle rectangle6 = new Rectangle(8, -2);

        int expectedPerimeter1 = 20;
        int expectedPerimeter2 = 24;
        int expectedPerimeter3 = 0;
        int expectedPerimeter4 = 0;
        int expectedPerimeter5 = 0;
        int expectedPerimeter6 = 0;

        // act
        int actualPerimeter1 = rectangle.getPerimeter();
        int actualPerimeter2 = rectangle2.getPerimeter();
        int actualPerimeter3 = rectangle3.getPerimeter();
        int actualPerimeter4 = rectangle4.getPerimeter();
        int actualPerimeter5 = rectangle5.getPerimeter();
        int actualPerimeter6 = rectangle6.getPerimeter();

        // assert
        assertEquals(expectedPerimeter1, actualPerimeter1, "5 x 5 Rectangle - Perimeter should be 20.");
        assertEquals(expectedPerimeter2, actualPerimeter2, "10 x 2 Rectangle - Perimeter should be 24.");
        assertEquals(expectedPerimeter3, actualPerimeter3, "0 x 4 Rectangle - Perimeter should be 0.");
        assertEquals(expectedPerimeter4, actualPerimeter4, "4 x 0 Rectangle - Perimeter should be 0.");
        assertEquals(expectedPerimeter5, actualPerimeter5, "-3 x 4 Rectangle - Perimeter should be 0.");
        assertEquals(expectedPerimeter6, actualPerimeter6, "8 x -2 Rectangle - Perimeter should be 0.");
    }

}