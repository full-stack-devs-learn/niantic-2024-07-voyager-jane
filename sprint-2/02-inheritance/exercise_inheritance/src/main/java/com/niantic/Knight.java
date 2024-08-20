package com.niantic;

public class Knight extends Character
{
    private int armor;

    public Knight(String name, int health, int level, int experience, int armor)
    {
        super(name, health, level, experience);

        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public void takeDamage(int damage)
    {
        if (isDefeated())
        {
            health = 0;
            System.out.println(this.name + " has been defeated!");
        }
        else if (getArmor() - damage < 0)
        {
            health += getArmor() - damage;
            System.out.println(this.name + "'s deflected most of the damage. " + this.name + " now has " + this.health + " health remaining.");
        }
        else System.out.println(this.name + "'s armor has deflected all damage from the attack! Health stays at " + getHealth());
    }

    @Override
    public void levelUp()
    {
        super.levelUp();

        if (!isDefeated())
        {
            armor += 5;
            System.out.println(this.name + "'s armor has increased by 5! Armor can now take " + getArmor() + " damage.");
        }
    }

    @Override
    public String specialAbility()
    {
        return "Armor Shield";
    }
}
