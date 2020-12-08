package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day07b
{
    public static void main( String[] args )
    {
        InputStream inputStream = Day07b.class.getClassLoader().getResourceAsStream("aoc-input-07.txt");
        Scanner inputScanner = new Scanner(inputStream);

        Map<String, Map<String, Integer>> allBags = new HashMap<String, Map<String, Integer>>();
        Map<String, Set<String>> invertedBags = new HashMap<String, Set<String>>();

        while (inputScanner.hasNextLine()) {
            String bag = inputScanner.nextLine();
            Scanner bagScanner = new Scanner(bag);
            String bagColor = bagScanner.next() + " " + bagScanner.next();

            // throw aways "bags contain"
            bagScanner.next(); bagScanner.next();

            Map<String, Integer> bagRules = new HashMap<String, Integer>();
            while(bagScanner.hasNextInt()) {
                Integer max = bagScanner.nextInt();
                String ruleColor = bagScanner.next() + " " + bagScanner.next();
                bagRules.put(ruleColor, max);
                if (invertedBags.containsKey(ruleColor)) {
                    Set<String> containers = invertedBags.get(ruleColor);
                    containers.add(bagColor);
                } else {
                    Set<String> containers = new HashSet<String>();
                    containers.add(bagColor);
                    invertedBags.put(ruleColor, containers);
                }


                // read the remaining dot or comma
                bagScanner.next();
            }

            allBags.put(bagColor, bagRules);
            bagScanner.close();

        }
        inputScanner.close();

        Integer containedBags = containedBags(allBags, "shiny gold");

        System.out.println(containedBags);

    }

    public static Integer containedBags(Map<String, Map<String, Integer>> allBags, String bag) {
        Integer noContainedBags = 0;
        Map<String, Integer> containedBags = allBags.get(bag);
        if (containedBags == null || containedBags.size() ==0) return 0;

        for (Map.Entry<String, Integer> innerBag : containedBags.entrySet()) {
            noContainedBags += innerBag.getValue()  + (innerBag.getValue()*containedBags(allBags, innerBag.getKey()));
        }

        return noContainedBags;
    }


}
