package com.niantic.exercises;

import com.niantic.models.LineItem;

import java.util.List;

public class Reducers
{

    /*
    1) using one or more Java Stream functions calculate the total sales amount
       for all line items in the given list.

       hint: use the getLineTotal() method to calculate the sales total

     */
    public double totalSales(List<LineItem> lineItems)
    {
        var sumSales = lineItems.stream()
                                .map(item -> item.getLineTotal())
                                .reduce(0.0, (temp, current) -> temp + current);
        return sumSales;
    }

    /*
    2) using one or more Java Stream functions calculate the average sales amount
       per line items in the given list.

     */
    public double averageSalesPerLineItem(List<LineItem> lineItems)
    {
        var sumSales = totalSales(lineItems);

        return sumSales / lineItems.size();
    }

    /*
    3) using one or more Java Stream functions calculate the average sales amount
       per items in the given list.

       hint: unlike problem number 2, we are not looking for the average of line totals
       we are looking for the average of each item (line items can have multiple quantities
       of a single item)

     */
    public double averageSalesPerItem(List<LineItem> lineItems)
    {
        var sumSaleItems = lineItems.stream()
                                    .map(item -> item.getUnitPrice() * item.getQuantity())
                                    .reduce(0.0, (temp, current) -> temp + current);

        var totalItems = totalItemCount(lineItems);

        return sumSaleItems / totalItems;
    }

    /*
    4) using one or more Java Stream functions calculate the total number
       of items that were purchased.

       hint: line items can have multiple quantities of an item

     */
    public int totalItemCount(List<LineItem> lineItems)
    {
        var totalItems = lineItems.stream()
                                    .map(item -> item.getQuantity())
                                    .reduce(0, (temp, current) -> temp + current);

        return totalItems;
    }

    /*
    5) using one or more Java Stream functions calculate the average number
       of items that were purchased per line item.

     */
    public double averageItemCount(List<LineItem> lineItems)
    {
        var totalItems = totalItemCount(lineItems);

        return (double) totalItems / lineItems.size();
    }

    /*
    6) using one or more Java Stream functions find the most expensive line item.

     */
    public double maxLineItem(List<LineItem> lineItems)
    {
        var allLines = lineItems.stream()
                                .map(item -> item.getLineTotal())
                                .toList();

        var maxLine = allLines.stream()
                                .reduce(allLines.get(0), (temp, current) -> temp > current ? temp : current);

        return maxLine;
    }

    /*
    7) using one or more Java Stream functions find the least expensive line item.

        hint: the least expensive line item is not $0.00

     */
    public double minLineItem(List<LineItem> lineItems)
    {
        var allLines = lineItems.stream()
                                .map(item -> item.getLineTotal())
                                .toList();

        var minLine = allLines.stream()
                                .reduce(allLines.get(0), (temp, current) -> temp < current ? temp : current);

        return minLine;
    }

}
