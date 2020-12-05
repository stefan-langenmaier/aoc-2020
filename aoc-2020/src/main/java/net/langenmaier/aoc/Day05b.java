package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day05b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day05b.class.getClassLoader().getResourceAsStream("aoc-input-05.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Long highestSeatId = null;
        Long lowestSeatId = null;
        Set<Long> seatIds = new HashSet<Long>();

        while (inputScanner.hasNext()) {
            String seat = inputScanner.next();

            Long seatId = seatId(seat);
            seatIds.add(seatId);

            if(highestSeatId == null || seatId > highestSeatId) {
                highestSeatId = seatId;
            }

            if(lowestSeatId == null || seatId < lowestSeatId) {
                lowestSeatId = seatId;
            }
        }

        for (long seatId=lowestSeatId; seatId<highestSeatId; seatId++) {
            if (!seatIds.contains(seatId)) {
                System.out.println(seatId);
            }
        }
        inputScanner.close();

    }

    public static Long seatId(String seat) {
        String column = seat.substring(0, 7);
        String row = seat.substring(7, seat.length());
        return parse(column, "FB") * 8 + parse(row, "LR");
    }

    public static Long parse(String input, String digits) {
        int length = input.length();
        int base = digits.length();
        Long sum = 0l;
        for (int pos=length; pos>0; pos--) {
            Integer digit = digits.indexOf(input.substring(pos-1, pos));
            Long pow = pow(base, length-pos);
            Long value = digit * pow;
            sum += value;
        }
        return sum;
    }

    public static Long pow(Integer base, Integer exp) {
        Long pow = 1l;
        for (int i=0; i<exp; i++) {
            pow *= base;
        }

        return pow;
    }


}
