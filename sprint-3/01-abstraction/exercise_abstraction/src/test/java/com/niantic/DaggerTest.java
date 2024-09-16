package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaggerTest
{
    private Dagger dagger;

    @BeforeEach
    public void setup()
    {
        dagger = new Dagger("The Dagger", 20, 3);
    }

    @Test
    public void addDagger_should_increaseDaggerCount()
    {
        // arrange
        int expectedDaggerCount = 4;

        // act
        dagger.addDagger();
        int actualDaggerCount = dagger.getDaggerCount();

        // assert
        assertEquals(expectedDaggerCount, actualDaggerCount, "addDagger should increase daggerCount by 1.");
    }
}
