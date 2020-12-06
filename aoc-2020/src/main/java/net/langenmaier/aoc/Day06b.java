package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day06b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day06b.class.getClassLoader().getResourceAsStream("aoc-input-06.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Integer cntCustomDeclarations = 0;

        Map<String, Integer> groupDeclaration = new HashMap<String, Integer>();
        Integer groupSize = 0;
        while (inputScanner.hasNextLine()) {
            String declaration = inputScanner.nextLine();
            if (declaration.isEmpty()) {
                cntCustomDeclarations += commonAnswers(groupDeclaration, groupSize);

                groupDeclaration = new HashMap<String, Integer>();
                groupSize = 0;
            } else {
                groupSize += 1;
                for (int pos=0; pos<declaration.length(); pos++) {
                    String option = declaration.substring(pos, pos+1);
                    Integer count = 1;
                    if (groupDeclaration.containsKey(option)) {
                        count = groupDeclaration.get(option) + 1;
                    }
                    groupDeclaration.put(option, count);
               }
            }
        }
        cntCustomDeclarations += commonAnswers(groupDeclaration, groupSize);

        inputScanner.close();

        System.out.println(cntCustomDeclarations);

    }

    public static Integer commonAnswers(Map<String, Integer> groupDeclaration, Integer groupSize) {
        Integer commonAnswers = 0;
        for (Integer value: groupDeclaration.values()) {
            if (value == groupSize) {
                commonAnswers += 1;
            }
        }
        return commonAnswers;
    }

}
