package net.langenmaier.aoc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Day05Test 
{
    @Test
    public void shouldAnswer()
    {
        assertEquals( 0l, (long)Day05.parse("F", "FB") );
        assertEquals( 0l, (long)Day05.parse("FFFF", "FB") );
        assertEquals( 1l, (long)Day05.parse("B", "FB") );
        assertEquals( 3l, (long)Day05.parse("BB", "FB") );
        assertEquals( 2l, (long)Day05.parse("BF", "FB") );
    }

    @Test
    public void shouldAnswerOfficial()
    {
        assertEquals( 44l, (long)Day05.parse("FBFBBFF", "FB") );
        assertEquals( 5l, (long)Day05.parse("RLR", "LR") );
        
    }

    @Test
    public void shouldAnswerSeatId()
    {
        assertEquals( 567l, (long)Day05.seatId("BFFFBBFRRR") );
        assertEquals( 119l, (long)Day05.seatId("FFFBBBFRRR") );
        assertEquals( 820l, (long)Day05.seatId("BBFFBBFRLL") );
    }

}
