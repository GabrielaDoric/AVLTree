package LAB1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AVLMain {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("One file expected.");
        }

        List<Integer> values = new ArrayList<Integer>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            for (String line : lines) {
                List<String> numbers = Arrays.asList(line.trim().split("\\s+"));
                for (String number : numbers) {
                    values.add(Integer.parseInt(number));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        AVLTree tree = new AVLTree();
        for (Integer element : values) {
            tree.addElement(element);
        }

        System.out.print("Za dodavanje elementa upisi njegovu vrijednost u konzolu.\n");
        System.out.println("Elementi koje treba dodati:\t" + values);
        try (Scanner sc = new Scanner((System.in))) {
            while (true) {
                System.out.println();
                System.out.println(tree.printTree());
                String input = sc.nextLine();
                if (input.startsWith("exit")) {
                    break;
                } else {
                    try {
                        int element = Integer.parseInt(input.split("\\s")[0]);
                        tree.addElement(element);
                    } catch (Exception ignorable) {
                    }
                }
            }
        }
    }

}



