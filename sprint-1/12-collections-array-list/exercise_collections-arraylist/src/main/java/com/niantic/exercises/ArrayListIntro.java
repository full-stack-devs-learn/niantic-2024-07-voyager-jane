package com.niantic.exercises;

import java.util.ArrayList;

public class ArrayListIntro
{
    /*
    1. Create an ArrayList of Strings and add the names of your
       5 favorite heroes.

    Return the list.
     */
    public ArrayList<String> getHeroesList()
    {
        var heroes = new ArrayList<String>(5);

        heroes.add("Spider-Man");
        heroes.add("Thor");
        heroes.add("Captain America");
        heroes.add("Batman");
        heroes.add("Robin");

        return heroes;
    }

    /*
    2. Given a list of integers, create and return a new list of just the
       even numbers in the list

       findEvens( [1, 2, 3, 4] )            ->  [2, 4]
       findEvens( [21, 98, 78, 5, 6, 8] )   ->  [98, 78, 6, 8]
     */
    public ArrayList<Integer> findEvens(ArrayList<Integer> numbers)
    {
        var evens = new ArrayList<Integer>();

        for (int num : numbers)
        {
            if (num % 2 == 0)
            {
                evens.add(num);
            }
        }

        return evens;
    }

    /*
    3. Given a list of integers, return the sum of all numbers

       sum( [1, 2, 3, 4] )            ->  10
       sum( [21, 98, 78, 5, 6, 8] )   ->  216
     */
    public int sum(ArrayList<Integer> numbers)
    {
        int sumAll = 0;

        for (int num: numbers)
        {
            sumAll += num;
        }

        return sumAll;
    }

    /*
    4. Given a list of integers, return the highest number

       sum( [1, 2, 3, 4] )            ->  4
       sum( [21, 98, 78, 5, 6, 8] )   ->  98
     */
    public int max(ArrayList<Integer> numbers)
    {
        int maxNum = Integer.MIN_VALUE;

        for (var num : numbers)
        {
            if (num > maxNum)
            {
                maxNum = num;
            }
        }

        return maxNum;
    }

    /*
    5. Given a list of integers, return the lowest number

       sum( [1, 2, 3, 4] )            ->  1
       sum( [21, 98, -78, 5, 6, 8] )  ->  -78
     */
    public int min(ArrayList<Integer> numbers)
    {
        int minNum = Integer.MAX_VALUE;

        for (var num : numbers)
        {
            if (num < minNum)
            {
                minNum = num;
            }
        }

        return minNum;
    }

    /*
    6. Given a list of integers, return the average of all numbers
       This should return the average as an integer, not a floating point

       sum( [3, 1, 59, -4, 81, 23] )    ->  27
       sum( [21, 98, -78, 5, 6, 8] )    ->  53
     */
    public int average(ArrayList<Integer> numbers)
    {
        int sumAvg = sum(numbers);

        return (int) sumAvg / numbers.size();
    }

    /*
    7.  Build an arrayList that holds the fibonacci sequence

        The fibonacci sequence is a numeric pattern 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
        - each new number is added by calculating the sum of the previous 2 numbers
          0 + 1 = 1
          1 + 1 = 2
          1 + 2 = 3
          2 + 3 = 5
          etc.
        - the sequence must begin with 0, 1 so size will never be lower than 2

        Include as many numbers as is specified by the size input
     */
    public ArrayList<Integer> buildFibonacci(int size)
    {
        // Create new collection & tracker variables
        var fibSequence = new ArrayList<Integer>(size);

        int num1 = 0;
        int num2 = 1;
        int og1 = num1;

        // Add the starting sequence numbers
        fibSequence.add(num1);
        fibSequence.add(num2);

        // Run through loop if size is more than just the starting numbers
        if (size > 2) {
            for (int i = 1; i < size; i++) {

                // If the next iteration will stop the loop & the size is an odd number, break since an odd size won't include n2 as the last item
                if (i + 1 == size && size % 2 != 0) {
                    break;
                }

                // Otherwise add the next n2 to the sequence, don't need to add new n1 since n1 was added in the previous loop as the previous num2 / would have repeat items
                og1 = num1;
                num1 = num2;
                num2 += og1;
                fibSequence.add(num2);
            }
        }

        return fibSequence;
    }
}
