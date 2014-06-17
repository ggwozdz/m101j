package code.lilasz.mongo.m101j.homework31;

import com.mongodb.BasicDBObject;
import org.junit.Test;

import java.util.Arrays;

import static code.lilasz.mongo.m101j.homework31.Homework31.getLowestHomeWorkScore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Homework31Test {

    public final BasicDBObject SMALLEST_SCORE = createScore("exam", 1.463179736705023);
    public final BasicDBObject SMALLEST_HW_SCORE = createScore("homework", 6.676176060654615);
    public final BasicDBObject LARGEST_HW_SCORE = createScore("homework", 35.8740349954354);
    public final BasicDBObject LARGEST_SCORE = createScore("quiz", 55.1623761273);

    @Test
    public void testGetLowestHomeWorkScore() {
        assertEquals(SMALLEST_HW_SCORE, getLowestHomeWorkScore(Arrays.asList(LARGEST_SCORE, SMALLEST_SCORE, SMALLEST_HW_SCORE)).get());
        assertEquals(SMALLEST_HW_SCORE, getLowestHomeWorkScore(Arrays.asList(LARGEST_SCORE, SMALLEST_HW_SCORE, SMALLEST_SCORE)).get());
        assertEquals(LARGEST_HW_SCORE, getLowestHomeWorkScore(Arrays.asList(LARGEST_SCORE, SMALLEST_SCORE, LARGEST_HW_SCORE)).get());
        assertFalse(getLowestHomeWorkScore(Arrays.asList(LARGEST_SCORE, SMALLEST_SCORE, SMALLEST_SCORE, LARGEST_SCORE)).isPresent());
    }

    private BasicDBObject createScore(String type, Double score) {
        return new BasicDBObject("type", type).append("score", score);
    }
}