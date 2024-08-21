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
    public void newPrinter_shouldNot_haveValues_moreThanMaxSheetsAndToner()
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
    public void replaceToner_should_resetToner_ToMaxCapacity()
    {
        // arrange
        int expected = 1000;

        // act
        printer.replaceToner();
        int actual = printer.getToner();

        // assert
        assertEquals(expected, actual, "replaceToner() should reset toner attribute to MAX_TONER.");
    }

    @Test
    public void addPaper_should_updateSheetsVariable()
    {
        // arrange
        int expected = 400;

        // act
        printer.addPaper(100);

        int actual = printer.getSheets();

        // assert
        assertEquals(expected, actual, "addPaper(100) to a Printer with 300 sheets should result in 400 sheets in the Printer.");
    }

    @Test
    public void addPaper_shouldNot_exceedMaxSheetsCapacity()
    {
        // arrange
        int expected = 500;

        // act
        printer.addPaper(400);

        int actual = printer.getSheets();

        // assert
        assertEquals(expected, actual, "Adding paper to a printer should not exceed printer's MAX_SHEET_CAPACITY.");
    }

    @Test
    public void addPaper_shouldNot_addNegativePaper()
    {
        // arrange
        int expected = 300;

        // act
        printer.addPaper(-10);

        int actual = printer.getSheets();

        // assert
        assertEquals(expected, actual, "addPaper() should default negative input to 0 and not subtract paper.");
    }

    @Test
    public void print_should_updateTonerAndSheets_afterPrintingPaper()
    {
        // arrange

        // act

        // assert

    }

    @Test
    public void print_shouldNot_makeSheetsAndToner_fallBelowZero()
    {
        // arrange
        // act
        // assert
    }

    @Test
    public void print_should_return_CorrectNumberOfPagesPrinted()
    {
        // arrange
        // act
        // assert
    }

    @Test
    public void print_shouldNot_printMoreThanSheetsAndTonerAvailable()
    {
        // arrange
        // act
        // assert
    }

    @Test
    public void print_shouldNot_printNegativePages()
    {
        // arrange
        // act
        // assert
    }
}