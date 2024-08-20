package com.niantic.part_2_challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTests
{
    private Printer printer;

    @BeforeEach
    public void setup() { printer = new Printer(300, 500); }

    @Test
    public void newPrinter_shouldNot_haveParametersWith_moreThanMaxSheetsAndToner()
    {
        // arrange
        int expectedToner = 1000;
        int expectedSheets = 500;

        Printer overMaxTonerPrinter = new Printer(100, 1500);
        Printer overMexSheetsPrinter = new Printer(600, 300);

        // act
        int actualToner = overMaxTonerPrinter.getToner();
        int actualSheets = overMexSheetsPrinter.getSheets();

        // assert
        assertEquals(expectedToner, actualToner, "New Printer should not hold toner value that is greater than the MAX_TONER.");
        assertEquals(expectedSheets, actualSheets, "New Printer should not hold sheets value that is greater than the MAX_SHEET_CAPACITY.");
    }

    @Test
    public void newPrinter_shouldNot_haveNegative_sheetsAndToners()
    {
        // arrange
        int expectedToner = 0;
        int expectedSheets = 0;

        Printer negativeTonerPrinter = new Printer(100, -10);
        Printer negativeSheetsPrinter = new Printer(-10, 300);

        // act
        int actualToner = negativeTonerPrinter.getToner();
        int actualSheets = negativeSheetsPrinter.getSheets();

        // assert
        assertEquals(expectedToner, actualToner, "New Printer with negative toner input should default to 0.");
        assertEquals(expectedSheets, actualSheets, "New Printer with negative toner input should default to 0.");
    }

    @Test
    public void test()
    {
        // arrange
        
        // act

        // assert

    }

    @Test
    public void newtest()
    {
        // arrange
        // act
        // assert
    }
}