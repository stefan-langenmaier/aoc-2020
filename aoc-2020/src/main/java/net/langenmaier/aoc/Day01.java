package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class Day01 
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

        for (Integer element : list) {
            Integer inverse = 2020 - element;
            if (list.contains(inverse)) {
                System.out.println(inverse * element);
                System.exit(0);
            }
        }
    }
}
