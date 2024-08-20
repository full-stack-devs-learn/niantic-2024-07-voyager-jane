package com.niantic.part_2_challenge;

public class Printer
{
    private static final int MAX_SHEET_CAPACITY = 500;
    private static final int MAX_TONER = 1000;

    private int sheets;
    private int toner;

    public int getSheets()
    {
        return sheets;
    }

    public int getToner()
    {
        return toner;
    }

    public Printer(int sheets, int toner)
    {
        // Check for Edge Cases of Over Max or Negative Values
        if (sheets > MAX_SHEET_CAPACITY) this.sheets = MAX_SHEET_CAPACITY;
        else if (sheets < 0) this.sheets = 0;
        else this.sheets = sheets;

        if (toner > MAX_TONER) this.toner = MAX_TONER;
        else if (toner < 0) this.toner = 0;
        else this.toner = toner;
    }

    public int print(int pages)
    {
        int maxCapacity = Math.min(sheets, toner);
        int pagesPrinted = Math.min(pages, maxCapacity);

        toner -= pagesPrinted;
        sheets -= pagesPrinted;

        return pagesPrinted;
    }

    public void addPaper(int paper)
    {
        if(paper > MAX_SHEET_CAPACITY)
        {
            sheets = MAX_SHEET_CAPACITY;
        }
        else
        {
            sheets += paper;
        }
    }

    public void replaceToner()
    {
        this.toner = MAX_TONER;
    }
}
