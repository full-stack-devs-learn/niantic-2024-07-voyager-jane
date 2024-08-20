package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest
{
    private Knight knight;
    private Character villain;

    @BeforeEach
    public void setup()
    {
        knight = new Knight("Bob", 50, 1, 100, 5);
        villain = new Character("Archibald", 50, 1, 100);
    }

    @Test
    public void specialAbility_should_returnArmorShield()
    {
        // arrange
        String expected = "Armor Shield";

        // act
        String actual = knight.specialAbility();

        // assert
        assertEquals(expected, actual, "specialAbility() should return String: Armor Shield");
    }

    @Test
    public void isDefeated_should_returnActualState()
    {
        // arrange
        // act
        Knight secondKnight = new Knight("Mary", 50, 1, 100, 5);

        knight.takeDamage(55);
        secondKnight.takeDamage(10);

        // assert
        assertTrue(knight.isDefeated(), "When health is 0, knight is defeated.");
        assertFalse(secondKnight.isDefeated(), "When health is greater than 0, knight is not defeated.");
    }

    @Test
    public void levelUp_should_increase_armorLevel_forKnight()
    {
        // arrange
        int expected = 10;

        // act
        knight.levelUp();

        int actual = knight.getArmor();

        // assert
        assertEquals(expected, actual, "Armor should increase by 5 when Knight levelUp().");
    }

    @Test
    public void levelUp_shouldNot_increase_armorLevel_forDefeatedKnight()
    {
        // arrange
        int expected = 5;
        knight.takeDamage(55);

        // act


        // assert

    }

    @Test
    public void takeDamage_should_deflectDamage()
    {
        // arrange
        // act
        // assert
    }

    @Test
    public void takeDamage_should_decreaseHealth_ifDamageIsGreaterThanArmor()
    {
        // arrange
        // act
        // assert
    }



}
