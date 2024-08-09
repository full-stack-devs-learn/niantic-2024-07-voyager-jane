package com.niantic.application;

import com.niantic.models.Category;
import com.niantic.models.SubCategory;
import com.niantic.models.User;
import com.niantic.services.CategoryDao;
import com.niantic.services.SubCategoryDao;
import com.niantic.services.UserDao;

import java.util.ArrayList;
import java.util.Scanner;

public class BudgetTracker
{
    Scanner userInput = new Scanner(System.in);

    UserDao userDao = new UserDao();
    CategoryDao categoryDao = new CategoryDao();
    SubCategoryDao subCategoryDao = new SubCategoryDao();

    // <editor-fold desc="Home Page - Budget">
    public void run()
    {

        while(true)
        {
            int choice = homeScreenSelection();
            switch(choice)
            {
                case 1:
                    System.out.println("add transaction");
                    break;
                case 2:
                    System.out.println("reports");
                    break;
                case 3:
                    addNewUser();
                    break;
                case 4:
                    addNewCategory();
                    break;
                case 5:
                    addNewSubcategory();
                    break;
                case 6:
                    System.out.println("add vendor");
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Thank you for using Northwind!");
                    System.out.println("Goodbye");
                    System.out.println();
                    System.exit(0);
                default:
                    System.out.println("invalid selection");
                    break;
            }
        }

    }

    private int homeScreenSelection()
    {
        System.out.println();
        System.out.println("Budget Tracker");
        System.out.println("--------------------------------------");
        System.out.println("Select from the following options:");
        System.out.println();
        System.out.println("  1) Add Transaction");
        System.out.println("  2) Reports");
        System.out.println("  3) Add User");
        System.out.println("  4) Add Category");
        System.out.println("  5) Add Sub Category");
        System.out.println("  6) Add Vendor");
        System.out.println("  0) Quit");
        System.out.println();

        System.out.print("Enter an option: ");
        return Integer.parseInt(userInput.nextLine());
    }
    // </editor-fold>

    // <editor-fold desc="Add User">

    private void listAllUsers()
    {
        ArrayList<User> users = userDao.getAllUsers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("All Users");
        System.out.println("-".repeat(100));
        users.forEach(System.out::println);
        System.out.println();
    }

    private void addNewUser()
    {
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("Add User");
        System.out.println("-".repeat(100));
        System.out.println("Enter User Information");
        System.out.println();

        String userName = getInputString("User Name: ");
        String firstName = getInputString("First Name: ");
        String lastName = getInputString("Last Name: ");
        String phone = getInputString("Phone Number: ");
        String email = getInputString("Email: ");

        System.out.println();

        User user = new User(){{
            setUserName(userName);
            setFirstName(firstName);
            setLastName(lastName);
            setPhone(phone);
            setEmail(email);
        }};

        try {
            userDao.addUser(user);

            System.out.println("-".repeat(100));
            System.out.println();
            System.out.println(String.format("%s %s has been added as a User.", firstName.toUpperCase(), lastName.toUpperCase()));
            System.out.println();

            listAllUsers();

            waitTime();
        }

        catch (Exception e)
        {
            System.out.println(String.format("There was an error in adding %s.", firstName));

            waitTime();
        }

    }

    // </editor-fold>

    // <editor-fold desc="Add Category">

    private void listAllCategories()
    {
        ArrayList<Category> categories = categoryDao.getAllCategories();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("All Categories");
        System.out.println("-".repeat(100));

        categories.forEach(System.out::println);
        System.out.println();
    }

    private void addNewCategory()
    {
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("Add Category");
        System.out.println("-".repeat(100));
        System.out.println("Enter Category Information");
        System.out.println();

        String categoryName = getInputString("Category name: ");
        String description = getInputString("Description: ");

        System.out.println();

        Category category = new Category()
        {{
            setName(categoryName);
            setDescription(description);
        }};

        try
        {
            categoryDao.addCategory(category);

            System.out.println("-".repeat(100));
            System.out.println();
            System.out.println(String.format("%s has been added as a Category", categoryName.toUpperCase()));
            System.out.println();

            listAllCategories();

            waitTime();
        }

        catch (Exception e)
        {
            System.out.println(String.format("There was an error in adding %s.", categoryName));

            waitTime();
        }
    }

    // </editor-fold>

    // <editor-fold desc="Add Sub Category">

    private void listAllSubCategories()
    {
        ArrayList<SubCategory> subCategories = subCategoryDao.getAllSubCategory();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("All SubCategories");
        System.out.println("-".repeat(100));

        subCategories.forEach(System.out::println);
        System.out.println();
    }

    private void addNewSubcategory()
    {
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("Add Sub Category");
        System.out.println("-".repeat(100));
        System.out.println("Enter Sub Category Information");
        System.out.println();

        int categoryId = getInputInt("Category Id: ");
        String subCategoryName = getInputString("Sub Category Name: ");
        String description = getInputString("Description: ");

        System.out.println();

        SubCategory subCategory = new SubCategory()
        {{
            setCategoryId(categoryId);
            setName(subCategoryName);
            setDescription(description);
        }};

        try
        {
            subCategoryDao.addSubCategory(subCategory);

            System.out.println("-".repeat(100));
            System.out.println();
            System.out.println(String.format("%s has been added as a Sub Category", subCategoryName.toUpperCase()));
            System.out.println();

            listAllSubCategories();

            waitTime();
        }

        catch (Exception e)
        {
            System.out.println(String.format("There was an error in adding %s.", subCategoryName));

            waitTime();
        }

    }

    //</editor-fold>

    // <editor-fold desc="Other Functions">
    private void waitTime()
    {
        System.out.println();
        System.out.println("Done Reading? Press ENTER to continue.");
        userInput.nextLine();
        System.out.println();
    }

    private String getInputString(String line)
    {
        System.out.println(line);
        String input = userInput.nextLine();
        return input;
    }

    private int getInputInt(String line)
    {
        System.out.println(line);
        int input = userInput.nextInt();
        return input;
    }

    private double getInputDouble(String line)
    {
        System.out.println(line);
        double input = userInput.nextDouble();
        return input;
    }
    // </editor-fold
}
