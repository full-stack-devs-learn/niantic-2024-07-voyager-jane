package com.nianatic;

public class Exercises
{
    /*
     * In this function you need to convert a word
     * either to all upper case letters or all
     * lower case.
     *
     * The user will provide you with the word, and
     * which case you should change it to.
     *
     * changeCase("Fancy", false) => fancy
     * changeCase("chocolate", true) => CHOCOLATE
     * changeCase("PARTY", false) => party
     * changeCase("PARTY", true) => PARTY
     *
     */
    public String changeCase(String word, boolean toUpperCase)
    {
        if (toUpperCase)
        {
            return word.toUpperCase();
        }

        else
        {
            return word.toLowerCase();
        }
    }

    /*
     * As a full stack developer you will often need to
     * dynamically create Html in code. In this function
     * you are required to take the input and convert it
     * to a valid Html String.
     *
     * Html is made up of elements (also known as tags)
     * such as:
     *
     * <p>your content here</p>
     *
     * The element is <p> for paragraph
     *
     * Most elements have both an opening <p>
     * and a closing </p> tag, and the content
     * goes inside.
     *
     * The user will provide you with the content, and
     * the name of Html element.
     *
     * You need to return the formatted Html
     *
     * createHtml("This is a paragraph", "p") => <p>This is a paragraph</p>
     * createHtml("Bold text", "strong") => <strong>Bold text</strong>
     *
     */
    public String createHtml(String content, String elementName)
    {
        // if there are multiple instances even with just one argument, need to put the argument position (#$) or else it errors out, %s for strings
        String tag = "<%1$s>" + content + "</%1$s>";
        String htmlFinal = String.format(tag, elementName);

        return htmlFinal;
    }

    /*
     * Sometimes Html elements do not have any content
     *
     * <p></p>
     *
     * Elements that have no content can be "self closing".
     *
     * <p />
     *
     * Modify the logic that you used for the CreateHtml
     * to return a self closing element when there is no
     * content provided.
     *
     * moreHtml("This is a paragraph", "p") => <p>This is a paragraph</p>
     * moreHtml("", "p") => <p />
     *
     */
    public String moreHtml(String content, String elementName)
    {
        // equals check for if the string has the same contents and is the better == for strings. == would check location and not content
        if (content.equals(""))
        {
            String selfClose = String.format("<%s />", elementName);

            return selfClose;
        }

        else
        {
            String hasContent = createHtml(content, elementName);

            return hasContent;
        }

    }

    /*
     * Xml is similar to Html - it uses element names
     * to describe the data that is being stored.
     *
     * Unlike html, however, there are no pre-defined
     * element names in xml. In other words, you can use
     * any word as a tag name.
     *
     * <customer><id>1</id><name>Belinda Carter</name></customer>
     *
     * The user has a list of customer id and names. They
     * will provide you each customer id and their name.
     * You are required to return a formatted xml String
     * like the pattern shown above.
     *
     * createXml(1, "Belinda Carter") => <customer><id>1</id><name>Belinda Carter</name></customer>
     *
     */
    public String createXml(int id, String name)
    {
        // %d for integers
        String customerID = "<customer><id>%d</id><name>%s</name></customer>";
        String formatID = String.format(customerID, id, name);

        return  formatID;
    }

    /*
     * The customer has noted that even though
     * the xml that is returned is correct, it is still
     * somewhat difficult to read because it is all on
     * a single line
     *
     * <customer><id>1</id><name>Belinda Carter</name></customer>
     *
     * Xml can be made easier to read by adding line breaks
     * and indentation like this:
     * (note: indentations are 2 spaces)
     *
     * <customer>
     *   <id>1</id>
     *   <name>Belinda Carter</name>
     * </customer>
     *
     * Modify your logic to return formatted xml.
     * each element must be on its own line.
     * Indent the nested elements with 2 spaces
     *
     * String format codes (see https://www.w3schools.com/java/java_Strings_specchars.asp):
     * \n = new line
     * \t = tab
     * \\ = \
     *
     * formattedXml(1, "Belinda Carter") => <customer>
     *                                        <id>1</id>
     *                                        <name>Belinda Carter</name>
     *                                      </customer>
     *
     */
    public String formattedXml(int id, String name)
    {
        // put the end quotes with the last line of content! if you put the end quotes on a separate line from the content, its like a /n where it creates a new line after string content
        String multiLine = """
                <customer>
                  <id>%d</id>
                  <name>%s</name>
                </customer>""";

        String formatID = String.format(multiLine, id, name);

        return formatID;
    }

    /*
     * In this function you will be given all the
     * individual parts of a full name. You need to
     * return the fully formatted name as a single String
     *
     * The first and last names will always have a value,
     * but a user is not required to have a middle name
     * or a suffix.
     *
     * If middle name or suffix is provided, you must
     * format the name accordingly.
     *
     * Examples:
     * formatFullName("Glen", "", "Williamson", "") => Glen Williamson
     * formatFullName("Glen", "Carter", "Williamson", "") => Glen Carter Williamson
     * formatFullName("Glen", "", "Williamson", "Jr") => Glen Williamson, Jr
     * formatFullName("Glen", "Carter", "Williamson", "Jr") => Glen Carter Williamson, Jr
     *
     */
    public String formatFullName(String firstName, String middleName, String lastName, String suffix)
    {
        // building string from scratch starting with first name
        String fullName = firstName;

        // if there is a middle name, add it to fullName
        if (!middleName.equals(""))
        {
            fullName = fullName + " " + middleName;
        }

        // add last name to string
        fullName = fullName + " " + lastName;

        // if theres a suffix, add it to the end
        if (!suffix.equals(""))
        {
            fullName = fullName + ", " + suffix;
        }

        return  fullName;
    }

    /*
     * You are writing a program for HR.
     * When they hire a new employee they must be
     * given an username to access the network.
     *
     * The username must always be lower case and
     * is formatted as the employees first name
     * and last name separated by a period.
     *
     * Glen Williamson => glen.willimason
     *
     * If the employee has a middle name, the
     * middle initial should also be added like
     * this
     *
     * Glen Carter Williamson => glen.c.williamson
     *
     * Your function accepts the full name of the employee
     * and you must return the new username.
     *
     * Examples:
     * createUserName("Glen Williamson") => glen.williamson
     * createUserName("Glen Carter Williamson") => glen.c.williamson
     * createUserName("Glen Williamson, III") => glen.williamson
     *
     */
    public String createUserName(String fullName)
    {
        String userName;
        String firstName;
        String lastName;

        // change to lowercase
        String lowerName = changeCase(fullName, false).strip();

        // first, split the string by any comma + suffix that may be present. then split the first item in the array as it will contain First, Middle, Last
        String[] noSuffix = lowerName.split(",");
        String[] splitName = noSuffix[0].split(" ");

        // Start the string with first name
        firstName = splitName[0];

        // if there are only 2 items in the array, there is no middle name
        if (splitName.length == 2)
        {
            lastName = splitName[1];
            userName = String.format("%1$s.%2$s", firstName, lastName);
        }

        // if there are more than 2 items, then there is a middle name
        else
        {
            lastName = splitName[2];
            char middleInitial = splitName[1].charAt(0);
            userName = String.format("%1$s.%2$s.%3$s", firstName, middleInitial, lastName);
        }

        return userName;
    }
}
