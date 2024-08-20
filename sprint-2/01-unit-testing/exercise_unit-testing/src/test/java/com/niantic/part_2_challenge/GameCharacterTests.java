package com.niantic.part_2_challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTests
{
    private GameCharacter bobCharacter;
    private GameCharacter anneCharacter;

    @BeforeEach
    public void setup()
    {
        bobCharacter = new GameCharacter(10, "Bob");
        anneCharacter = new GameCharacter(10, "Anne");
    }

    @Test
    public void takeHit_shouldNotReduceBelowZeroEnergy_whenCalled()
    {
        // arrange
        int expectedEnergyLevel = 0;

        // act
        bobCharacter.takeHit(10);
        anneCharacter.takeHit(12);

        int actualEnergyLevel1 = bobCharacter.getEnergyLevel();
        int actualEnergyLevel2 = anneCharacter.getEnergyLevel();

        // assert
        assertEquals(expectedEnergyLevel, actualEnergyLevel1, "Max Energy Level is 10, so takeHit(10) should return 0.");
        assertEquals(expectedEnergyLevel, actualEnergyLevel2, "Max Energy Level is 10, so takeHit(12) should return 0.");
    }

    @Test
    public void takeHit_shouldReduceGivenEnergyLvl_whenCalled()
    {
        // arrange
        int expectedEnergyLevel1 = 5;
        int expectedEnergyLevel2 = 1;

        // act
        bobCharacter.takeHit(5);
        anneCharacter.takeHit(9);

        int actualExpectedEnergyLevel1 = bobCharacter.getEnergyLevel();
        int actualExpectedEnergyLevel2 = anneCharacter.getEnergyLevel();

        // assert
        assertEquals(expectedEnergyLevel1, actualExpectedEnergyLevel1, "10 energy level and takes 5 damage - should return 5 energy level.");
        assertEquals(expectedEnergyLevel2, actualExpectedEnergyLevel2, "10 energy level and takes 9 damage, should return 1 energy level.");
    }

    @Test
    public void isKnocked_shouldReturnTrueState_whenCalled()
    {
        // arrange

        // act
        bobCharacter.takeHit(10);

        // assert
        assertTrue(bobCharacter.isKnockedOut(), "When energyLevel is 0, isKnockedOut() should return True.");
        assertFalse(anneCharacter.isKnockedOut(), "When energyLevel is greater than 0, isKnockedOut() should return False.");
    }

    @Test
    public void heal_shouldNotIncreaseAboveMaxEnergy_whenCalled()
    {
        // arrange
        int expectedEnergyLevel = bobCharacter.getMaxEnergyLevel();

        // act
        bobCharacter.heal(5);

        int actualExpectedEnergyLevel = bobCharacter.getEnergyLevel();

        // assert
        assertEquals(expectedEnergyLevel, actualExpectedEnergyLevel, "Heal should not go over maxEnergyLevel");
    }

    @Test
    public void heal_shouldNotHealWhenKnockedOut_whenCalled()
    {
        // arrange
        int expectedEnergyLevel = 0;

        // act
        bobCharacter.takeHit(10);
        bobCharacter.heal(2);

        int actualEnergyLevel = bobCharacter.getEnergyLevel();

        // assert
        assertEquals(expectedEnergyLevel, actualEnergyLevel, "Heal should not add energy level when character is knocked out.");
    }

    @Test
    public void heal_shouldHealGivenEnergyLvl_whenCalled()
    {
        // arrange
        int expectedEnergyLevel1 = 4;
        int expectedEnergyLevel2 = 7;

        // act
        bobCharacter.takeHit(8);
        anneCharacter.takeHit(7);

        bobCharacter.heal(2);
        anneCharacter.heal(4);

        int actualEnergyLevel1 = bobCharacter.getEnergyLevel();
        int actualEnergyLevel2 = anneCharacter.getEnergyLevel();

        // assert
        assertEquals(expectedEnergyLevel1, actualEnergyLevel1, "With 2 energyLevel, heal(2) should result in energyLevel = 4.");
        assertEquals(expectedEnergyLevel2, actualEnergyLevel2, "With 3 energyLevel, heal(4) should result in energyLevel = 7.");
    }
}