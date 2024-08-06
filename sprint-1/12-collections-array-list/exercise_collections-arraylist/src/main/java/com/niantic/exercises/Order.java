package com.niantic.exercises;

import com.niantic.models.OrderLineItem;

import java.util.ArrayList;


/*
The exercises in this class are optional - this is the challenge exercise
 */
public class Order
{
    // this ArrayList is the container (shopping cart) for all items that are being ordered
    private ArrayList<OrderLineItem> shoppingCart = new ArrayList<>();

    public ArrayList<OrderLineItem> getShoppingCart()
    {
        return shoppingCart;
    }

    /*
    1. Add logic to allow a user to add an Item to a shopping cart
        - if the item already exists in the cart, update the quantity
        - search for a line item by name, and add the new quantity to the original quantity
     */
    public void addItem(OrderLineItem item)
    {
        // get the shopping cart to check items
        var cart = getShoppingCart();

        // check if cart has item, if no add to cart
        if (!cart.contains(item))
        {
            cart.add(item);
        }

        // otherwise, update the quantity
        else
        {
            int i = cart.indexOf(item);
            var product = cart.get(i);
            product.setQuantity(product.getQuantity() + item.getQuantity());
        }

        /* THIS WAS MY ORIGINAL FUNCTION UNTIL I DID REMOVE_ITEM AND REALIZED THERE WAS ALSO PROBABLY A FUNCTION TO EASILY FIND AN ITEM IN THE CART LIKE REMOVE()
        AND REMEMBERED LECTURE TALKING ABOUT HOW ITS EASY TO FIND STUFF IN ARRAYLISTS COMPARED TO LINKED LISTS. Keeping it for the memories.

        // variable to check if the item is in the cart or not
        boolean inCart = false;

        for (var product : cart)
        {
            // if the product in cart is the same as the new item, update the product's quantity and set inCart to be true
            if (product.getId() == item.getId())
            {
                product.setQuantity(product.getQuantity() + item.getQuantity());
                inCart = true;
            }
        }

        // if the new item is not in the cart, add it
        if (!inCart)
        {
            cart.add(item);
        } */

    }

    /*
    2. Add logic to allow a user to add an Item to a shopping cart
        - search for a line item by name, and remove it from the list
     */
    public void removeItem(OrderLineItem item)
    {
        var cart = getShoppingCart();

        cart.remove(item);
    }

    /*
    3. Search for the highest priced item in the shopping cart and return the
        line item that contains that item.

        If the shopping cart is empty return null
     */
    public OrderLineItem findHighestPriceProduct()
    {
        var cart = getShoppingCart();

        // Tracker Variables
        double maxPrice = 0.0;
        OrderLineItem mostExpensivePrice = null;

        // null if empty
        if (cart.size() == 0)
        {
            return mostExpensivePrice;
        }

        // find the product with the highest price (NOT LINE TOTAL) and set it to tracker variables
        else {
            for (var product : cart) {
                if (product.getPrice() > maxPrice) {
                    maxPrice = product.getPrice();
                    mostExpensivePrice = product;
                }
            }

            return mostExpensivePrice;
        }
    }

    /*
    4. Search for the most expensive line item in the shopping cart
        and return it

        If the shopping cart is empty return null
     */
    public OrderLineItem findMostExpensiveLineItem()
    {
        var cart = getShoppingCart();

        // tracker variables
        double maxLine = 0.0;
        OrderLineItem mostExpensiveLine = null;

        // null if empty
        if (cart.size() == 0)
        {
            return mostExpensiveLine;
        }

        // find the item with the highest LineTotal and set it to tracker variables
        else
        {
            for (var product : cart)
            {
                if (product.getLineTotal() > maxLine)
                {
                    maxLine = product.getLineTotal();
                    mostExpensiveLine = product;
                }
            }
            return mostExpensiveLine;
        }
    }

    /*
    5. Calculate and return the order total
     */
    public double getOrderTotal()
    {
        var cart = getShoppingCart();
        
        double orderTotal = 0.0;
        
        for (var product : cart)
        {
            orderTotal += product.getLineTotal();
        }
        
        return orderTotal;
    }

    /*
    6. Return the total number of items in the cart
     */
    public int getTotalItemCount()
    {
        var cart = getShoppingCart();

        int numOfItems = 0;

        // Find how many items in the cart (Not find how many types of products = cart.size())
        for (var product : cart)
        {
            numOfItems += product.getQuantity();;
        }

        return numOfItems;
    }

    /*
    7. Calculate the average price for all items in the shopping cart
     */
    public double getAveragePricePerItem()
    {
        var cart = getShoppingCart();
        int numOfItems = getTotalItemCount();

        double sumAvgPrice = 0.0;

        for (var product : cart)
        {
            sumAvgPrice += product.getLineTotal();
        }

        return sumAvgPrice / numOfItems;
    }
}
