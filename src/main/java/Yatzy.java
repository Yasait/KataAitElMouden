import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Yatzy {

    public static final Collector<Integer, ?, Integer> countingInt = Collectors.reducing(0, e -> 1, Integer::sum);

    public static int chance(JeuDeDes jeu) {
        return jeu.sum();
    }

    public static int yatzy(JeuDeDes jeu) {
        if (jeu.isYatzy()) {
            return 50;
        }
        return 0;
    }

    public static int ones(JeuDeDes jeu) {
        return jeu.countDice(1);
    }

    public static int twos(JeuDeDes jeu) {
        return jeu.countDice(2) * 2;
    }

    public static int threes(JeuDeDes jeu) {
        return jeu.countDice(3) * 3;
    }

    public static int fours(JeuDeDes jeu) {
        return jeu.countDice(4) * 4;
    }

    public static int fives(JeuDeDes jeu) {
        return jeu.countDice(5) * 5;
    }

    public static int sixes(JeuDeDes jeu) {
        return jeu.countDice(6) * 6;
    }

    public static int pair(JeuDeDes roll) {
        List<Integer> pairs = roll.findPairs();
        if (pairs.isEmpty()) {
            return 0;
        } else {
            return pairs.get(0) * 2;
        }
    }

    public static int twoPairs(JeuDeDes roll) {
        List<Integer> pairs = roll.findPairs();
        if (pairs.size() >= 2) {
            return pairs.stream()
                .mapToInt(pair -> pair * 2)
                .sum();
        }
        return 0;
    }

    public static int threeOfAKind(JeuDeDes roll) {
        return roll.getDiceWithCountGreaterThan(3) * 3;
    }

    public static int fourOfAKind(JeuDeDes roll) {
        return roll.getDiceWithCountGreaterThan(4) * 4;
    }

    public static int smallStraight(JeuDeDes de) {
        if (de.isSmallStraight()) {
            return 15;
        }
        return 0;
    }

    public static int largeStraight(JeuDeDes de) {
        if (de.isLargeStraight()) {
            return 20;
        }
        return 0;
    }

    public static int fullHouse(JeuDeDes de) {
        if (de.isFullHouse()) {
            return de.sum();
        }
        return 0;
    }
}



