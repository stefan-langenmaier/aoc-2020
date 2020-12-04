package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day03.class.getClassLoader().getResourceAsStream("aoc-input-03.txt");
        List<String> map = Day03.loadMap(inputStream);

        Integer cntTrees = 0;
        Integer slope = 3;
        Integer currentPosition = 0;

        Integer mapLimit = map.get(0).length();
        for (int i = 0; i<map.size(); i++) {
            String square = map.get(i).substring(currentPosition, currentPosition + 1);
            if (square.equals("#")) {
                cntTrees += 1;
            }
            currentPosition = (currentPosition + slope) % mapLimit;
        }
        

        System.out.println(cntTrees);
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
