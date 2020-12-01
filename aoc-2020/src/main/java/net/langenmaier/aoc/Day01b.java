package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Day01b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day01.class.getClassLoader().getResourceAsStream("aoc-input-01.txt");
        Scanner inputScanner = new Scanner(inputStream);

        HashSet<Integer> list = new HashSet<Integer>();
        
        while (inputScanner.hasNext()) {
            list.add(inputScanner.nextInt());
        }
        inputScanner.close();

        for (Integer first : list) {
            Integer remainer = 2020 - first;
            for (Integer second : list) {
                Integer third = remainer - second;
                if (list.contains(third)) {
                    System.out.println(first * second * third);
                    System.exit(0);
                }
            }
        }
    }
}
