package com.niantic.part_2_challenge;

public class GameCharacter
{
    private final String name;
    private final int maxEnergyLevel;
    private int energyLevel;

    public String getName()
    {
        return name;
    }

    public int getEnergyLevel()
    {
        return energyLevel;
    }

    public int getMaxEnergyLevel()
    {
        return maxEnergyLevel;
    }

    public GameCharacter(int maxEnergyLevel, String name)
    {
        this.maxEnergyLevel = maxEnergyLevel;
        this.name = name;

        this.energyLevel = maxEnergyLevel;
    }

    public void takeHit(int damage)
    {
        if (energyLevel - damage < 0)
        {
            energyLevel = 0;
        }

        else
        {
            energyLevel -= damage;
        }

    }

    public void heal(int amount)
    {
        if (isKnockedOut())
        {
            // do nothing
        }

        else if (amount + energyLevel > maxEnergyLevel)
        {
            energyLevel = maxEnergyLevel;
        }

        else
        {
            energyLevel += amount;
        }
    }

    public boolean isKnockedOut()
    {
        return energyLevel == 0;
    }
}
