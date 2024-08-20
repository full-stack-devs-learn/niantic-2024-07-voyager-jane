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
    public void levelUp_shouldNot_increase_levelOrArmor_forDefeatedKnight()
    {
        // arrange
        int expectedArmor = 5;
        int expectedLevel = 1;

        knight.takeDamage(55);

        // act
        int actualArmor = knight.getArmor();
        int actualLevel = knight.getLevel();

        // assert
        assertEquals(expectedArmor, actualArmor, "levelUp() should not increase armor when knight is defeated.");
        assertEquals(expectedLevel, actualLevel, "levelUp() should not increase level when knight is defeated.");
    }

    @Test
    public void takeDamage_armor_should_deflectAllDamage_ifDamageIsEqualToOrLessThanArmor()
    {
        // arrange
        int expectedHealth = 50;

        // act
        knight.takeDamage(5);
        int actualHealth1 = knight.getHealth();

        knight.takeDamage(2);
        int actualHealth2 = knight.getHealth();

        // assert
        assertEquals(expectedHealth, actualHealth1, "Armor should deflect all damage equal to armor's value.");
        assertEquals(expectedHealth, actualHealth2, "Armor should deflect all damage less than armor's value");
    }

    @Test
    public void takeDamage_should_decreaseHealth_ifDamageIsGreaterThanArmor()
    {
        // arrange
        int expectedHealth = 45;

        // act
        knight.takeDamage(10);

        int actualHealth = knight.getHealth();

        // assert
        assertEquals(expectedHealth, actualHealth, "Knight's health should go down if damage is greater than armor's value.");
    }



}
