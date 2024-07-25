package com.nianatic;

public class ExercisesChallenge
{

    /*
     * Given an array of numbers, add only
     * the first 2 numbers and return
     * the answer.
     *
     * The array may have fewer than 2 numbers,
     * so you will need to have logic to deal
     * with that.
     *
     * sumFirst2Numbers([1, 2, 3])       =>  3
     * sumFirst2Numbers([10, 8, 14, 32]) =>  18
     * sumFirst2Numbers([3])             =>  3
     * sumFirst2Numbers([])              =>  0
     */
    public int sumFirst2Numbers(int[] numbers)
    {

        int sum = 0;

        if (numbers.length == 0)
        {
            return sum;
        }

        else if (numbers.length < 2 && numbers.length > 0)
        {
            sum += numbers[0];
        }

        else
        {
            sum += numbers[0] + numbers[1];
        }

        return sum;

    }

    /*
     * Given an array of names, create a
     * new array of the same size. The new
     * array should have all of the names
     * in it but in reverse order.
     *
     * reverseNames(["Ron", "Lisa", "Kent", "Michelle"]) => ["Michelle", "Kent", "Lisa", "Ron"]
     */
    public String[] reverseNames(String[] names)
    {

        String[] reverseOrder = new String[names.length];
        int index = 0;

        // Reverse = index of reverseOrder; index = index of names;
        // Starting at the end of reverseOrder, add values of the names array in order. Increment index to continue going through the names array.
        for (int reverse = names.length - 1; reverse >= 0; reverse--)
        {
            reverseOrder[reverse] = names[index];
            index++;
        }

        return reverseOrder;

    }



    /*
     * Given a multidimensional array of numbers,
     * combine all numbers into a single 1 dimensional array
     *
     * The nested arrays will all have numbers
     * but the amount can vary
     *
     * combineArrays([
     *                [1,2,3],
     *                [4,5,6],
     *                [7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     *
     * combineArrays([
     *                [1,2],
     *                [3,4,5,6],
     *                [7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     *
     * combineArrays([
     *                [1,2,3,4],
     *                [5,6,7,8,9]
     *               ])         =>  [1,2,3,4,5,6,7,8,9]
     */
    public int[] combineArrays(int[][] numbers)
    {

        int[] comboArray;
        int arrayLength = 0;
        int index = 0;

        // To find how long comboArray needs to be, add al the lengths of the arrays in numbers.
        for (int[] array : numbers)
        {
            arrayLength += array.length;
        }

        comboArray = new int[arrayLength];

        // For every array in numbers, add all the values to comboArray.
        // At the end of every iteration in the second for loop, raise the index in order to continue down comboArray to add more values.
        for (int[] array : numbers)
        {
            for (int num : array)
            {
                comboArray[index] = num;
                index++;
            }
        }

        return comboArray;

    }
}
