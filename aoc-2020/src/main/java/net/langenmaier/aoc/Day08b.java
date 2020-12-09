package net.langenmaier.aoc;

import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day08b
{
    public enum Op {
        NOP, ACC, JMP
    }
    public static void main( String[] args )
    {
        InputStream inputStream = Day08b.class.getClassLoader().getResourceAsStream("aoc-input-08.txt");
        Scanner inputScanner = new Scanner(inputStream);

        List<Map.Entry<Op, Integer>> code = new ArrayList<Map.Entry<Op, Integer>>();

        while (inputScanner.hasNext()) {
            Op op = Op.valueOf(inputScanner.next().toUpperCase());
            Integer value = inputScanner.nextInt();
            Map.Entry<Op, Integer> instruction = new AbstractMap.SimpleEntry<Op, Integer>(op, value);
            code.add(instruction);
        }
        inputScanner.close();

        for (int i=0; i<code.size(); i++) {
            List<Map.Entry<Op, Integer>> copy = new ArrayList<>();
            for (int j=0; j<code.size(); j++) {
                Map.Entry<Op, Integer> instruction = new AbstractMap.SimpleEntry<>(code.get(j).getKey(), code.get(j).getValue());
                if (i==j) {
                    // switch instructions
                    if (Op.JMP.equals(code.get(j).getKey())) {
                        instruction = new AbstractMap.SimpleEntry<>(Op.NOP, code.get(j).getValue());
                    } else if (Op.NOP.equals(code.get(j).getKey())) {
                        instruction = new AbstractMap.SimpleEntry<>(Op.JMP, code.get(j).getValue());
                    }
                }
                copy.add(instruction);
            }

            Set<Integer> usedInstructionPoint = new HashSet<>();
            Integer instructionPoint = 0;
            Integer accumulator = 0;
            Boolean terminated = false;
            while(!usedInstructionPoint.contains(instructionPoint)) {
                usedInstructionPoint.add(instructionPoint);
                Map.Entry<Op, Integer> instruction = copy.get(instructionPoint);
                if (Op.ACC.equals(instruction.getKey())) {
                    accumulator += instruction.getValue();
                    instructionPoint += 1;
                } else if (Op.JMP.equals(instruction.getKey())) {
                    instructionPoint += instruction.getValue();
                } else if (Op.NOP.equals(instruction.getKey())) {
                    instructionPoint += 1;
                }
                if (instructionPoint == code.size()) {
                    terminated = true;
                    break;
                }
            }
    
            if (terminated) {
                System.out.println(accumulator);
                break;
            }

        }



    }

}
