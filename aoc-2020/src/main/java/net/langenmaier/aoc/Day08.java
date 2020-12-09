package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day08
{
    public enum Op {
        NOP, ACC, JMP
    }
    public static void main( String[] args )
    {
        InputStream inputStream = Day08.class.getClassLoader().getResourceAsStream("aoc-input-08.txt");
        Scanner inputScanner = new Scanner(inputStream);

        List<Map.Entry<Op, Integer>> code = new ArrayList<Map.Entry<Op, Integer>>();

        while (inputScanner.hasNext()) {
            Op op = Op.valueOf(inputScanner.next().toUpperCase());
            Integer value = inputScanner.nextInt();
            Map.Entry<Op, Integer> instruction = new AbstractMap.SimpleEntry<Op, Integer>(op, value);
            code.add(instruction);
        }
        inputScanner.close();

        Set<Integer> usedInstructionPoint = new HashSet<>();
        Integer instructionPoint = 0;
        Integer accumulator = 0;
        while(!usedInstructionPoint.contains(instructionPoint)) {
            usedInstructionPoint.add(instructionPoint);
            Map.Entry<Op, Integer> instruction = code.get(instructionPoint);
            if (Op.ACC.equals(instruction.getKey())) {
                accumulator += instruction.getValue();
                instructionPoint += 1;
            } else if (Op.JMP.equals(instruction.getKey())) {
                instructionPoint += instruction.getValue();
            } else if (Op.NOP.equals(instruction.getKey())) {
                instructionPoint += 1;
            }
        }

        System.out.println(accumulator);

    }

}
