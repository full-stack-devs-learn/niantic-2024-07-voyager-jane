package com.niantic.part_1_sakila_dtos;

public class Film
{
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;

    public Film() {}

    public Film(int filmId, String title, String description, int releaseYear, int languageId, int rentalDuration, double rentalRate, int length, double replacementCost, String rating, String specialFeatures)
    {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
    }

    public int getFilmId()
    {
        return this.filmId;
    }

    public void setFilmId(int filmId)
    {
        this.filmId = filmId;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getReleaseYear()
    {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId()
    {
        return this.languageId;
    }

    public void setLanguageId(int languageId)
    {
        this.languageId = languageId;
    }

    public int getRentalDuration()
    {
        return this.rentalDuration;
    }

    public void setRentalDuration(int rentalDuration)
    {
        this.rentalDuration = rentalDuration;
    }

    public double getRentalRate()
    {
        return this.rentalRate;
    }

    public void setRentalRate(double rentalRate)
    {
        this.rentalRate = rentalRate;
    }

    public int getLength()
    {
        return this.length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public double getReplacementCost()
    {
        return this.replacementCost;
    }

    public void setReplacementCost(double replacementCost)
    {
        this.replacementCost = replacementCost;
    }

    public String getRating()
    {
        return this.rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public String getSpecialFeatures()
    {
        return this.specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures)
    {
        this.specialFeatures = specialFeatures;
    }
}
