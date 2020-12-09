package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Day09
{

    public static void main( String[] args )
    {
        final Integer PREAMBLE_LENGTH=25;
        InputStream inputStream = Day09.class.getClassLoader().getResourceAsStream("aoc-input-09.txt");
        Scanner inputScanner = new Scanner(inputStream);

        LinkedList<Integer> preamble = new LinkedList<>();

        // fill preamble
        for (int i=0; i<PREAMBLE_LENGTH; i++) {
            preamble.offer(inputScanner.nextInt());
        }


        while (inputScanner.hasNext()) {
            Integer check = inputScanner.nextInt();
            Boolean valid = false;
            for (int i=0; i<PREAMBLE_LENGTH; i++) {
                Integer inverse = check-preamble.get(i);
                if (preamble.contains(inverse) && preamble.indexOf(inverse) != i) {
                    valid = true;
                }
            }
            if (valid) {
                preamble.removeFirst();
                preamble.addLast(check);
            } else {
                System.out.println(check);
                break;
            }

        }
        inputScanner.close();



    }

}
