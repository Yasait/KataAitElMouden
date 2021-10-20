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
        //Arrays.asList(d1, d2, d3, d4, d5).stream();
        Map<Integer, Long> counts  = Stream.of(d1, d2, d3, d4, d5)
            .collect(groupingBy(d -> d, Collectors.counting()));
        return counts.entrySet().stream()
            .filter(entry -> entry.getValue() == 5 )
            .findFirst()
            .map(e -> 50)
            .orElse(0);
    }

    public static int number(int d1, int d2, int d3, int d4, int d5, int number){
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
    public Yatzy(int d1, int d2, int d3, int d4, int _5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
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

    public static List<Integer> findPairs(List<Integer> dice2) {
        return counts(dice2).entrySet().stream()
            .filter(entry -> entry.getValue() >= 2)
            .map(Map.Entry::getKey)
            .sorted(Comparator.reverseOrder())
            .collect(toList());
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        List<Integer> pairs = findPairs(dice);
        if (pairs.isEmpty()) {
            return 0;
        }  else {
            return pairs.get(0) * 2;
        }
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dice = asList(d1, d2, d3, d4, d5);
        List<Integer> pairs = findPairs(dice);
        if (pairs.size() >= 2) {
            return pairs.stream()
                .mapToInt(pair -> pair * 2)
                .sum();
        }
        return 0;
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5) {
        List<Integer> dice = asList(_1, _2, d3, d4, d5);
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

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



