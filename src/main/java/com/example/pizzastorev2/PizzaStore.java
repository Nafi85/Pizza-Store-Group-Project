/**
 CSC 210 0900
 GROUP PROJECT: Pizza Store
 Group Members: Nahidul Islam Nafi, Bilal ALi, Aaron Davis
 Due: 12/04/2024
 **/
package com.example.pizzastorev2;

public class PizzaStore
{
    // Fields
    private String[][] usernamePassword = {{"username", "password"}, {"Baskin", "100Chambers"}};
    private String[] itemNames = {"Pizza", "Garlic bread", "Regular Fries", "Loaded Fries"};
    private String[] itemImgUrls = {
            "pan-pizza.jpg",
            "garlic-bread.jpg",
            "reg-fries.jpg",
            "loaded-fries.jpg",
    };
    private double[] itemPrices = {10.0, 12.5, 15.0, 18.75};
    private final double taxRate = 0.08;

    // Get the username
    public String getUsername()
    {
        return usernamePassword[1][0];
    }

    // Get Password
    public String getPassword()
    {
        return usernamePassword[1][1];
    }

    // Get Price
    public double getPrice(String itemName)
    {
        for (int i = 0; i < itemNames.length; i++)
        {
            if (itemNames[i].equalsIgnoreCase(itemName))
            {
                return itemPrices[i];
            }
        }
        return -1;
    }

    // Get Image Url
    public String getImageUrl(String itemName)
    {
        for (int i = 0; i < itemNames.length; i++)
        {
            if (itemNames[i].equalsIgnoreCase(itemName))
            {
                return itemImgUrls[i];
            }
        }
        return itemImgUrls[0];
    }

    // Get index By the name
    public int getIndexByName(String itemName){
        for (int i = 0; i < itemNames.length; i++)
        {
            if (itemNames[i].equalsIgnoreCase(itemName))
            {
                return i;
            }
        }
        return 0;
    }

    // get names
    public String[] getItemNames()
    {
        return itemNames;
    }

    // calculate the cost
    public double calculateCost(String itemName, int quantity)
    {
        double price = getPrice(itemName);
        if (price != -1)
        {
            return price * quantity;
        }
        return 0;
    }

    // get the tax
    public double calculateTax(double cost)
    {
        return cost * taxRate;
    }

    // calculate the total cost
    public double calculateTotalCost(double cost, double tax)
    {
        return cost + tax;
    }

}
