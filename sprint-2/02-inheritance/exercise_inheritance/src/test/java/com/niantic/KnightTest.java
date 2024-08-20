package com.niantic;

import org.junit.jupiter.api.BeforeEach;

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

    public void levelUp_shouldNot_increase_armorLevel_forDefeatedKnight()
    {

    }

    public void levelUp_should_increase_armorLevel_forKnight()
    {

    }



}
