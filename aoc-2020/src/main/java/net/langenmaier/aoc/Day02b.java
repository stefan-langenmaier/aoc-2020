package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.Scanner;

public class Day02b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day01.class.getClassLoader().getResourceAsStream("aoc-input-02.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Integer cntValid = 0;
        
        while (inputScanner.hasNextLine()) {
            Scanner problemScanner = new Scanner(inputScanner.nextLine());
            String[] range = problemScanner.next().split("-");
            Integer firstPostion = Integer.parseUnsignedInt(range[0])-1; // to make it zero indexed
            Integer secondPostion = Integer.parseUnsignedInt(range[1])-1; // to make it zero indexed
            Character c = problemScanner.next().charAt(0);
            String domain = problemScanner.next();

            if (domain.charAt(firstPostion) == c && domain.charAt(secondPostion) != c) {
                cntValid += 1;
            } else if (domain.charAt(firstPostion) != c && domain.charAt(secondPostion) == c) {
                cntValid += 1;
            }
        }
        inputScanner.close();

        System.out.println(cntValid);
    }
}
