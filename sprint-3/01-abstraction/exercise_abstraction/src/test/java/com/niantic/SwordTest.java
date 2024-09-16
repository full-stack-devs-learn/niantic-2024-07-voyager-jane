package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordTest
{
    private Sword sword1;

    @BeforeEach
    public void setup()
    {
        sword1 = new Sword("Unbreakable Faith", 20);
    }

    @Test
    public void newSword_should_returnCorrectProperties()
    {
        // arrange
        String expectedName = "Unbreakable Faith";
        int expectedDamage = 20;
        int expectedPercentCharged = 0;

        // act
        String actualName = sword1.getName();
        int actualDamage = sword1.getDamage();
        int actualPercentCharged = sword1.getPercentCharged();

        // assert
        assertEquals(expectedName, actualName, "Name is correct");
        assertEquals(expectedDamage, actualDamage, "Damage is correct");
        assertEquals(expectedPercentCharged, actualPercentCharged, "Percent Charged is correct");
    }

    @Test
    public void swordAttack_should_returnDefaultDamage()
    {
        // arrange
        int expectedDamage = 20;

        // act
        int actualDamage = sword1.attack();

        // assert
        assertEquals(expectedDamage, actualDamage, "Attack should return 20 if sword's default damage is 20.");
    }

    @Test
    public void setPercentChanged_should_returnInRange()
    {
        // arrange
        int expectedPercent1 = 0;
        int expectedPercent2 = 36;
        int expectedPercent3 = 100;

        // act
        sword1.attack();
        sword1.setPercentCharged(0);
        int actualPercent1 = sword1.getPercentCharged();

        sword1.setPercentCharged(36);
        int actualPercent2 = sword1.getPercentCharged();

        sword1.setPercentCharged(100);
        int actualPercent3 = sword1.getPercentCharged();

        sword1.setPercentCharged(200);
        int actualPercent4 = sword1.getPercentCharged();

        sword1.setPercentCharged(-10);
        int actualPercent5 = sword1.getPercentCharged();

        // assert
        assertEquals(expectedPercent1, actualPercent1, "percentCharged should be 0 if setter input is <= 0.");
        assertEquals(expectedPercent1, actualPercent5, "percentCharged should be 0 if setter input is <= 0.");
        assertEquals(expectedPercent2, actualPercent2, "percentCharged should be 36 if setter input is 36.");
        assertEquals(expectedPercent3, actualPercent3, "percentCharged should be 100 if setter input >= 100.");
        assertEquals(expectedPercent3, actualPercent4, "percentCharged should be 100 if setter input >= 100.");
    }

    @Test
    public void swordAttack_should_chargePercentChargedCorrectly()
    {
        // arrange
        int expectedCharge = 10;
        int expectedNoCharge = 100;

        // act
        sword1.attack();
        int actualCharge = sword1.getPercentCharged();

        sword1.setPercentCharged(100);
        int actualNoCharge = sword1.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "After attack, percentCharged should charge if percentCharged < 100");
        assertEquals(expectedNoCharge, actualNoCharge, "After attack, percentCharged should not charge if percentCharged = 100");

    }

    @ParameterizedTest
    @CsvSource(value = {
            "20, 0",
            "20, 49",
            "40, 50",
            "40, 90",
            "80, 100"
    })
    public void swordPowerAttack_should_returnCorrectDamageBasedOnPercentCharged(int expected, int setCharge)
    {
        // arrange
        int expectedDmg = expected;
        sword1.setPercentCharged(setCharge);

        // act
        int actualDmg = sword1.powerAttack();

        // assert
        assertEquals(expectedDmg, actualDmg, "With percentCharged " + setCharge + " , powerAttack should return " + expected);
    }

    @Test
    public void swordPowerAttack_should_setPercentChargeCorrectly()
    {
        // arrange
        int expectedPC1 = 0;
        int expectedPC2 = 10;

        // act
        sword1.setPercentCharged(100);
        sword1.powerAttack();
        int actualPC1 = sword1.getPercentCharged();

        sword1.setPercentCharged(50);
        sword1.powerAttack();
        int actualPC2 = sword1.getPercentCharged();

        sword1.setPercentCharged(60);
        sword1.powerAttack();
        int actualPC3 = sword1.getPercentCharged();

        // assert
        assertEquals(expectedPC1, actualPC1, "At 100% charged, powerAttack should set percentCharged to 0.");
        assertEquals(expectedPC1, actualPC2, "At 50% charged, powerAttack should set percentCharged to 0.");
        assertEquals(expectedPC2, actualPC3, "At 60% charged, powerAttack should set percentCharged to 10.");
    }

    @Test
    public void swordRange_shouldBe_1()
    {
        // arrange
        int expectedRange = 1;

        // act
        int actualRange = sword1.getRange();

        // assert
        assertEquals(expectedRange, actualRange, "Sword range should be 1");
    }
}
