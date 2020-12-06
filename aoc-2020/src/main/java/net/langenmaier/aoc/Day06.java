package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day06
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day06.class.getClassLoader().getResourceAsStream("aoc-input-06.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Integer cntCustomDeclarations = 0;

        Set<String> groupDeclaration = new HashSet<String>();
        while (inputScanner.hasNextLine()) {
            String declaration = inputScanner.nextLine();
            if (declaration.isEmpty()) {
                cntCustomDeclarations += groupDeclaration.size();
                groupDeclaration = new HashSet<String>();
            } else {
                for (int pos=0; pos<declaration.length(); pos++) {
                    String option = declaration.substring(pos, pos+1);
                    if (!groupDeclaration.contains(option)) {
                        groupDeclaration.add(option);
                    }
               }
            }
        }
        cntCustomDeclarations += groupDeclaration.size();

        inputScanner.close();

        System.out.println(cntCustomDeclarations);

    }

}
