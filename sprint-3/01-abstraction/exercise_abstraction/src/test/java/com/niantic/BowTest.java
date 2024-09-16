package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowTest
{
    private Bow standardBow;
    private Bow poisonBow;
    private Bow explosiveBow;

    @BeforeEach
    public void setup()
    {
        standardBow = new Bow("Golden Harp", 20, "standard", 5);
        poisonBow = new Bow("Corrupt Harmony", 20, "poison", 5);
        explosiveBow = new Bow("Sonnet of Sparks", 20, "explosive", 5);
    }

    @Test
    public void bowRange_shouldBe_20()
    {
        int expected = 20;

        int actual = standardBow.getRange();

        assertEquals(expected, actual, "Bow range should be 20.");
    }
}
