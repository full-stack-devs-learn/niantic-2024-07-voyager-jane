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
        int expectedSheets = 250;
        int expectedToner = 450;

        // act
        printer.print(50);

        int actualSheets = printer.getSheets();
        int actualToner = printer.getToner();

        // assert
        assertEquals(expectedSheets, actualSheets, "Printer has 300 pages - print(50) should result in 250 pages left.");
        assertEquals(expectedToner, actualToner, "Printer has 500 toner - print(50) should result in 450 toner left.");
    }

    @Test
    public void print_shouldNot_makeSheetsAndToner_fallBelowZero()
    {
        // arrange
        int expectedSheets1 = 0;
        int expectedToner1 = 200;
        int expectedSheets2 = 200;
        int expectedToner2 = 0;

        Printer lessToner = new Printer(500, 300);

        // act
        printer.print(550);
        lessToner.print(550);

        int actualSheets1 = printer.getSheets();
        int actualToner1 = printer.getToner();
        int actualSheets2 = lessToner.getSheets();
        int actualToner2 = lessToner.getToner();

        // assert
        assertEquals(expectedSheets1, actualSheets1, "Printer has 300 pages and 500 toner - print(550) should result in 0 pages left.");
        assertEquals(expectedToner1, actualToner1, "Printer has 300 pages and 500 toner - print(550) should result in 200 toner left.");
        assertEquals(expectedSheets2, actualSheets2, "Printer has 500 pages and 300 toner - print(550) should result in 200 pages left.");
        assertEquals(expectedToner2, actualToner2, "Printer has 500 pages and 300 toner - print(550) should result in 0 toner left.");
    }

    @Test
    public void print_should_return_CorrectNumberOfPagesPrinted()
    {
        // arrange
        int expectedReturn = 300;

        Printer lessToner = new Printer(500, 300);

        // act
        int actualReturn1 = printer.print(550);
        int actualReturn2 = lessToner.print(550);

        // assert
        assertEquals(expectedReturn, actualReturn1, "Printer has 300 pages and 500 toner - print(550) should print 300 pages");
        assertEquals(expectedReturn, actualReturn2, "Printer has 500 pages and 300 toner - print(550) should print 300 pages");
    }

    @Test
    public void print_shouldNot_printNegativePagesOrUpdateVariables()
    {
        // arrange
        int expectedReturn = 0;
        int expectedSheets = 300;
        int expectedToner = 500;

        // act
        int actualReturn = printer.print(-5);
        int actualSheets = printer.getSheets();
        int actualToner = printer.getToner();

        // assert
        assertEquals(expectedReturn, actualReturn, "Negative pages as input for print() should print 0 pages.");
        assertEquals(expectedSheets, actualSheets, "Negative pages as input for print() should not change sheets value.");
        assertEquals(expectedToner, actualToner, "Negative pages as input for print() should not change toner value.");
    }
}