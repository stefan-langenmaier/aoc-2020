package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.Scanner;

public class Day05
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day05.class.getClassLoader().getResourceAsStream("aoc-input-05.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Long highestSeatId = 0l;

        while (inputScanner.hasNext()) {
            String seat = inputScanner.next();

            Long seatId = seatId(seat);

            if(seatId > highestSeatId) {
                highestSeatId = seatId;
            }

        }

        System.out.println(highestSeatId);
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
