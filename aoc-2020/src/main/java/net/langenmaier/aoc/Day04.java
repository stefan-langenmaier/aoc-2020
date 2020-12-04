package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day04
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day04.class.getClassLoader().getResourceAsStream("aoc-input-04.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Integer cntPassports = 0;

        Pattern fieldPattern = Pattern.compile("[a-z]+:\\S+");
        Map<String, String> passport = new HashMap<String, String>();
        while (inputScanner.hasNextLine()) {
            String line = inputScanner.nextLine();
            if (line.isEmpty()) {
                if (isValid(passport)) {
                    cntPassports += 1;
                }
                passport = new HashMap<String, String>();
            } else {
                Scanner fieldScanner = new Scanner(line);
                while(fieldScanner.hasNext()){
                    String[] fields = fieldScanner.next(fieldPattern).split(":");
                    passport.put(fields[0], fields[1]);
                }
                fieldScanner.close();
            }
        }
        if (isValid(passport)) {
            cntPassports += 1;
            System.out.print("  ");
        } else {
            System.out.print("X ");
        }
        inputScanner.close();

        System.out.println(cntPassports);

    }

    private static boolean isValid(Map<String, String> passport) {
        String[] REQUIRED_FIELDS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        
        for (String field : REQUIRED_FIELDS) {
            if (!passport.containsKey(field)) return false;
        }

        return true;
    }

    private static void print(Map<String, String> passport) {
        String[] FIELDS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        
        for (String field : FIELDS) {
            if (passport.containsKey(field)) { 
                System.out.print(String.format("%s:%-9s ",  field, passport.get(field)));
            } else {
                System.out.print(String.format("%s:%-9s ",  field, "XXX"));
            }
        }

        System.out.println();
    }
}
