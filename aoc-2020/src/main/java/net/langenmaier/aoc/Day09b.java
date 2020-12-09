package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day09b
{

    public static void main( String[] args )
    {
        final Integer PREAMBLE_LENGTH=25;
        InputStream inputStream = Day09b.class.getClassLoader().getResourceAsStream("aoc-input-09.txt");
        Scanner inputScanner = new Scanner(inputStream);

        LinkedList<Long> preamble = new LinkedList<>();
        List<Long> list = new ArrayList<>();

        // fill preamble
        for (int i=0; i<PREAMBLE_LENGTH; i++) {
            Long nextLong = inputScanner.nextLong();
            preamble.offer(nextLong);
            list.add(nextLong);
        }

        Long failed = null;
        while (inputScanner.hasNext()) {
            Long check = inputScanner.nextLong();
            Boolean valid = false;
            for (int i=0; i<PREAMBLE_LENGTH; i++) {
                Long inverse = check-preamble.get(i);
                if (preamble.contains(inverse) && preamble.indexOf(inverse) != i) {
                    valid = true;
                }
            }
            if (valid) {
                preamble.removeFirst();
                preamble.addLast(check);
                list.add(check);
            } else {
                failed = check;
                break;
            }

        }

        inputScanner.close();
        System.out.println(failed);

        Long solution = 0l;
        for (int i=0; i<list.size(); i++) {
            Long sum = 0l;
            Long min = null;
            Long max = null;
            for (int j=i; j<list.size(); j++) {
                Long element = list.get(j);
                sum += element;
                if (min == null || element < min) {
                    min = element;
                }
                if (max == null || element > max) {
                    max = element;
                }
                if (sum >= failed) {
                    solution = min + max;
                    break;
                }
            }

            // == only works for small numbers, because of the constant cache pool
            if ((sum - failed)==0) {
                System.out.println(solution);
                break;
            }
        }
    }

}
