package com.niantic.part_1_sakila_dtos;

public class Actor
{
    private int actorID;
    private String firstName;
    private String lastName;

    public Actor() {}

    public Actor(int id, String firstName, String lastName)
    {
        this.actorID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId()
    {
        return this.actorID;
    }

    public void setActorId(int id)
    {
        this.actorID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }
}
