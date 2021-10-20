import java.util.*;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Yatzy {

    public static final Collector<Integer, ?, Integer> countingInt = Collectors.reducing(0, e -> 1, Integer::sum);

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> counts  = Stream.of(d1, d2, d3, d4, d5)
            .collect(groupingBy(d -> d, Collectors.counting()));
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() == 5 )
            .findFirst()
            .map(e -> 50)
            .orElse(0);
    }

    public static int number(int d1, int d2, int d3, int d4, int d5, int number) {
        Map<Integer, Long> counts  = Stream.of(d1, d2, d3, d4, d5)
            .collect(groupingBy(d -> d, Collectors.counting()));
        return counts.getOrDefault(number,0L).intValue() * number;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return number(d1, d2, d3, d4, d5, 1);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return number(d1, d2, d3, d4, d5, 2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return number(d1, d2, d3, d4, d5, 3);
    }

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    public int fours() {
        return number(dice[0], dice[1], dice[2], dice[3], dice[4], 4);
    }

    public int fives() {
        return number(dice[0], dice[1], dice[2], dice[3], dice[4], 5);
    }

    public int sixes() {
        return number(dice[0], dice[1], dice[2], dice[3], dice[4], 6);
    }


    public static Map<Integer, Integer> counts(List<Integer> dice) {
        return dice.stream().collect(groupingBy(identity(), countingInt));
    }

    public static List<Integer> findPairs(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        return counts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .sorted(Comparator.reverseOrder())
            .collect(toList());
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> pairs = findPairs(d1, d2, d3, d4, d5);
        if (pairs.isEmpty()) {
            return 0;
        }  else {
            return pairs.get(0) * 2;
        }
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> pairs = findPairs(d1, d2, d3, d4, d5);
        if (pairs.size() >= 2) {
            return pairs.stream()
                .mapToInt(pair -> pair * 2)
                .sum();
        }
        return 0;
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        return counts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 4)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(0) * 4;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        return counts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 3)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(0) * 3;
    }

    public static int sum(List<Integer> dice) {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> sort(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        return dice.stream().sorted().collect(toList());
    }

    public static boolean isSmallStraight(int d1, int d2, int d3, int d4, int d5) {
        return sort(d1, d2, d3, d4, d5).equals(asList(1, 2, 3, 4, 5));
    }

    public static boolean isLargeStraight(int d1, int d2, int d3, int d4, int d5) {
        return sort(d1, d2, d3, d4, d5).equals(asList(2, 3, 4, 5, 6));
    }

    public static boolean isYatzy() {
        List<Integer> dice = new ArrayList<>();
        return counts(dice)
            .values()
            .stream()
            .anyMatch(count -> count == 5);
    }

    public static boolean isFullHouse(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        boolean hasThreeOfAKind = counts(dice).entrySet().stream()
            .filter(entry -> entry.getValue() >= 3)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(0) != 0;
        boolean hasPair = !findPairs(d1, d2, d3, d4, d5).isEmpty();
        boolean isYatzy = isYatzy();
        return hasThreeOfAKind && hasPair && !isYatzy;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        if (isSmallStraight(d1, d2, d3, d4, d5)) {
            return 15;
        }
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        if (isLargeStraight(d1, d2, d3, d4, d5)) {
            return 20;
        }
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        if (isFullHouse(d1, d2, d3, d4, d5)) {
            return sum(dice);
        }
        return 0;
    }
}



