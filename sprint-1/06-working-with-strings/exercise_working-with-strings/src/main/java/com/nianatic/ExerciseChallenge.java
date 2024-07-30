package com.nianatic;

public class ExerciseChallenge
{
    /*
     * Your companies website displays the name
     * of each of your employees. But the name
     * needs to be formatted with last name first.
     *
     * Examples:
     * Samuel Black             => Black, Samuel
     * Lori Bell, PhD           => Bell, Lori, PhD
     * Marcus Neuer III         => Neuer, Marcus, III
     * Miguel Bennett Jr., MBA  => Bennett, Miguel, Jr., MBA
     *
     * Your function will accept the full name of
     * an employee, and you must return the
     * appropriately formatted name.
     *
     * Input:
     * reformatName("Samuel Black")             => Black, Samuel
     * reformatName("Lori Bell, PhD")           => Bell, Lori, PhD
     * reformatName("Marcus Neuer III")         => Neuer, Marcus, III
     * reformatName("Miguel Bennett Jr., MBA")  => Bennett, Miguel, Jr., MBA
     *
     */
    public String reformatName(String fullName)
    {
        String finalFormat;
        String firstName;
        String lastName;

        // split off titles from First, Last and Suffix
        String[] commaSplit = fullName.split(", ");
        String[] nameSplit = commaSplit[0].split(" ");

        // Piece together base string - "Last, First"
        firstName = nameSplit[0];
        lastName = nameSplit[1];

        finalFormat = lastName + ", " + firstName;

        //  check if theres a suffix (3 items means suffix present in second split with space), and then add it if there is one
        if (nameSplit.length > 2)
        {
            String suffix = nameSplit[2];
            finalFormat = finalFormat + ", " + suffix;
        }

        // check if theres a title at the end (2 items in first split with comma), and add if there is one
        if (commaSplit.length == 2)
        {
            String title = commaSplit[1];
            finalFormat = finalFormat + ", " + title;
        }

        return finalFormat;
    }

    /*
     * JSON is yet another format that is used to
     * describe and transfer data. JSON stands for
     * JavaScript Object Notation
     *
     * There are no XML tags, but it is still self describing.
     *
     * It is more light-weight than XMl, and has become the
     * standard for transferring data over the web.
     *
     * { "id": 1, "name": "Belinda Carter"}
     *
     * The user will provide you a customer id and their name.
     * You are required to return a formatted JSON String
     * like the pattern shown above.
     *
     * createJSON(1, "Belinda Carter") => { "id": 1, "name": "Belinda Carter"}
     *
     */
    public String createJSON(int id, String name)
    {
        // escape characters
        String formatJSON = "{ \"id\": %d, \"name\": \"%s\" }";
        String finalJSON = String.format(formatJSON, id, name);

        return finalJSON;
    }

}
