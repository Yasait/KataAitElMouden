import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YatzyTest {

    @Test
    public void test_chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = Yatzy.chance(new JeuDeDes(2, 3, 4, 5, 1));
        assertEquals(expected, actual);
        assertEquals(16, Yatzy.chance(new JeuDeDes(3, 3, 4, 5, 1)));
    }

    @Test
    public void test_yatzy_scores_50() {
        int expected = 50;
        int actual = Yatzy.yatzy(new JeuDeDes(4, 4, 4, 4, 4));
        assertEquals(expected, actual);
        assertEquals(50, Yatzy.yatzy(new JeuDeDes(6, 6, 6, 6, 6)));
        assertEquals(0, Yatzy.yatzy(new JeuDeDes(6, 6, 6, 6, 3)));
    }

    @Test
    public void test_ones() {
        assertTrue(Yatzy.ones(new JeuDeDes(1, 2, 3, 4, 5)) == 1);
        assertEquals(2, Yatzy.ones(new JeuDeDes(1, 2, 1, 4, 5)));
        assertEquals(0, Yatzy.ones(new JeuDeDes(6, 2, 2, 4, 5)));
        assertEquals(4, Yatzy.ones(new JeuDeDes(1, 2, 1, 1, 1)));
    }

    @Test
    public void test_twos() {
        assertEquals(4, Yatzy.twos(new JeuDeDes(1, 2, 3, 2, 6)));
        assertEquals(10, Yatzy.twos(new JeuDeDes(2, 2, 2, 2, 2)));
    }

    @Test
    public void test_threes() {
        assertEquals(6, Yatzy.threes(new JeuDeDes(1, 2, 3, 2, 3)));
        assertEquals(12, Yatzy.threes(new JeuDeDes(2, 3, 3, 3, 3)));
    }

    @Test
    public void test_fours() {
        assertEquals(12, new Yatzy().fours(new JeuDeDes(4, 4, 4, 5, 5)));
        assertEquals(8, new Yatzy().fours(new JeuDeDes(4, 4, 5, 5, 5)));
        assertEquals(4, new Yatzy().fours(new JeuDeDes(4, 5, 5, 5, 5)));
    }

    @Test
    public void test_fives() {
        assertEquals(10, new Yatzy().fives(new JeuDeDes(4, 4, 4, 5, 5)));
        assertEquals(15, new Yatzy().fives(new JeuDeDes(4, 4, 5, 5, 5)));
        assertEquals(20, new Yatzy().fives(new JeuDeDes(4, 5, 5, 5, 5)));
    }

    @Test
    public void test_sixes() {
        assertEquals(0, new Yatzy().sixes(new JeuDeDes(4, 4, 4, 5, 5)));
        assertEquals(6, new Yatzy().sixes(new JeuDeDes(4, 4, 6, 5, 5)));
        assertEquals(18, new Yatzy().sixes(new JeuDeDes(6, 5, 6, 6, 5)));
    }

    @Test
    public void test_one_pair() {
        assertEquals(6, Yatzy.pair(new JeuDeDes(3, 4, 3, 5, 6)));
        assertEquals(10, Yatzy.pair(new JeuDeDes(5, 3, 3, 3, 5)));
        assertEquals(12, Yatzy.pair(new JeuDeDes(5, 3, 6, 6, 5)));
    }

    @Test
    public void test_two_Pair() {
        assertEquals(16, Yatzy.twoPairs(new JeuDeDes(3, 3, 5, 4, 5)));
        assertEquals(16, Yatzy.twoPairs(new JeuDeDes(3, 3, 5, 5, 5)));
    }

    @Test
    public void test_three_of_a_kind() {
        assertEquals(9, Yatzy.threeOfAKind(new JeuDeDes(3, 3, 3, 4, 5)));
        assertEquals(15, Yatzy.threeOfAKind(new JeuDeDes(5, 3, 5, 4, 5)));
        assertEquals(9, Yatzy.threeOfAKind(new JeuDeDes(3, 3, 3, 3, 5)));
    }

    @Test
    public void test_four_of_a_knd() {
        assertEquals(12, Yatzy.fourOfAKind(new JeuDeDes(3, 3, 3, 3, 5)));
        assertEquals(20, Yatzy.fourOfAKind(new JeuDeDes(5, 5, 5, 4, 5)));
        assertEquals(8, Yatzy.fourOfAKind(new JeuDeDes(2, 2, 2, 2, 5)));
    }

    @Test
    public void test_smallStraight() {
        assertEquals(15, Yatzy.smallStraight(new JeuDeDes(1, 2, 3, 4, 5)));
        assertEquals(15, Yatzy.smallStraight(new JeuDeDes(2, 3, 4, 5, 1)));
        assertEquals(0, Yatzy.smallStraight(new JeuDeDes(1, 2, 2, 4, 5)));
    }

    @Test
    public void test_largeStraight() {
        assertEquals(20, Yatzy.largeStraight(new JeuDeDes(6, 2, 3, 4, 5)));
        assertEquals(20, Yatzy.largeStraight(new JeuDeDes(2, 3, 4, 5, 6)));
        assertEquals(0, Yatzy.largeStraight(new JeuDeDes(1, 2, 2, 4, 5)));
    }

    @Test
    public void test_fullHouse() {
        assertEquals(18, Yatzy.fullHouse(new JeuDeDes(6, 2, 2, 2, 6)));
        assertEquals(0, Yatzy.fullHouse(new JeuDeDes(2, 3, 4, 5, 6)));
    }
}
