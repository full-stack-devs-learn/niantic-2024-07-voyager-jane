package com.niantic;

public class Wizard extends Character
{
    private int mana;

    public Wizard(String name, int health, int level, int experience, int mana)
    {
        super(name, health, level, experience);

        this.mana = mana;
    }

    public int getMana()
    {
        return mana;
    }

    public void castSpell(Character target)
    {
        if (!isDefeated() && mana >= 10) {
            mana -= 10;
            target.takeDamage(attackDamage * 2);
            System.out.println(this.name + " attacks " + target.getName());
        }

        else
        {
            System.out.println(this.name + " has been defeated and cannot attack another player.");
        }
    }


    public void regenerateMana(int amount)
    {
        if (isDefeated())
        {
            System.out.println(this.name + " has been defeated and cannot regenerate mana.");
            return;
        }

        mana += amount;

        System.out.println(this.name + " regenerated " + amount +  " mana and now has " + mana + " mana.");
    }

    @Override
    public void levelUp()
    {
        super.levelUp();

        if (!isDefeated()) regenerateMana(10);
    }

    @Override
    public String specialAbility()
    {
        return "Cast Spells";
    }
}
