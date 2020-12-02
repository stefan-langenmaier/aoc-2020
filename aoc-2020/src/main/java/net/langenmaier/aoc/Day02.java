package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.Scanner;

public class Day02
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day01.class.getClassLoader().getResourceAsStream("aoc-input-02.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Integer cntValid = 0;
        
        while (inputScanner.hasNextLine()) {
            Scanner problemScanner = new Scanner(inputScanner.nextLine());
            String[] range = problemScanner.next().split("-");
            Integer min = Integer.parseUnsignedInt(range[0]);
            Integer max = Integer.parseUnsignedInt(range[1]);
            String c = problemScanner.next().substring(0, 1);
            String domain = problemScanner.next();

            Integer diff = domain.length() - domain.replace(c, "").length();

            if (min <= diff && diff <= max) {
                cntValid += 1;
            }
        }
        inputScanner.close();

        System.out.println(cntValid);
    }
}
