package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day03b.class.getClassLoader().getResourceAsStream("aoc-input-03.txt");
        List<String> map = loadMap(inputStream);

        System.out.println(
            treesPerDescent(map, 1, 1) *
            treesPerDescent(map, 3, 1) *
            treesPerDescent(map, 5, 1) *
            treesPerDescent(map, 7, 1) *
            treesPerDescent(map, 1, 2));
    }

    public static Integer treesPerDescent(List<String> map, Integer slopeX, Integer slopeY) {
        Integer cntTrees = 0;
        Integer currentPosition = 0;

        Integer mapLimit = map.get(0).length();
        for (int i = 0; i<map.size(); i += slopeY) {
            String square = map.get(i).substring(currentPosition, currentPosition + 1);
            if (square.equals("#")) {
                cntTrees += 1;
            }
            currentPosition = (currentPosition + slopeX) % mapLimit;
        }

        return cntTrees;
    }

    public static List<String> loadMap(InputStream input) {
        Scanner inputScanner = new Scanner(input);
        List<String> map = new ArrayList<String>();

        while(inputScanner.hasNext()) {
            map.add(inputScanner.nextLine());
        }
        inputScanner.close();

        return map;
    }
}
