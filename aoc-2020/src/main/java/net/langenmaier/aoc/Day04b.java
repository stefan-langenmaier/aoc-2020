package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day04b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day04b.class.getClassLoader().getResourceAsStream("aoc-input-04.txt");
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
        } else {
        }
        inputScanner.close();

        System.out.println(cntPassports);

    }

    private static boolean isValid(Map<String, String> passport) {
        String[] REQUIRED_FIELDS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        Pattern heightPattern = Pattern.compile("\\d+[a-z]+");
        Pattern hairPattern = Pattern.compile("#[0-9a-f]{6}");
        Pattern pidPattern = Pattern.compile("[0-9]{9}");
        
        for (String field : REQUIRED_FIELDS) {
            if (!passport.containsKey(field)) return false;
            if (field.equals("byr")) {
                Integer value = Integer.valueOf(passport.get(field));
                if (value < 1920 || value > 2002) {
                    return false;
                }
            }
            if (field.equals("iyr")) {
                Integer value = Integer.valueOf(passport.get(field));
                if (value < 2010 || value > 2020) {
                    return false;
                }
            }
            if (field.equals("eyr")) {
                Integer value = Integer.valueOf(passport.get(field));
                if (value < 2020 || value > 2030) {
                    return false;
                }
            }
            if (field.equals("hgt")) {
                Scanner heightScanner = new Scanner(passport.get(field));
                if (heightScanner.hasNext(heightPattern)) {
                    Integer length = passport.get(field).length();
                    Integer value = Integer.valueOf(passport.get(field).substring(0, length-2));
                    String metric = passport.get(field).substring(length-2);
                    heightScanner.close();
                    if (metric.equals("in")) {
                        if (value < 59 || value > 76) {
                            return false;
                        }
                    } else if (metric.equals("cm")) {
                        if (value < 150 || value > 193) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    heightScanner.close();
                    return false;
                }
            }
            if (field.equals("hcl")) {
                Scanner hairScanner = new Scanner(passport.get(field));
                if (!hairScanner.hasNext(hairPattern)) {
                    hairScanner.close();
                    return false;
                }
            }
            if (field.equals("ecl")) {
                String color = passport.get(field);
                if (!("amb".equals(color) ||
                    "blu".equals(color) ||
                    "brn".equals(color) ||
                    "gry".equals(color) ||
                    "grn".equals(color) ||
                    "hzl".equals(color) ||
                    "oth".equals(color))) {
                        return false;
                    }
            }
            if (field.equals("pid")) {
                Scanner pidScanner = new Scanner(passport.get(field));
                if (!pidScanner.hasNext(pidPattern)) {
                    pidScanner.close();
                    return false;
                }
            }
        }

        return true;
    }
    
    @SuppressWarnings("unused")
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
