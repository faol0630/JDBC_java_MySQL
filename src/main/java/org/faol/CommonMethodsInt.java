package org.faol;

import java.util.Scanner;

public interface CommonMethodsInt {
    default int insertId() {

        int id = 0;

        boolean flag = false;

        while (!flag) {

            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Student's id:  ");

                id = scanner.nextInt();

                flag = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid entry.Try again");

            }
        }
        return id;
    }


    default String insertName() {

        String name;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Student's name: ");

        name = scanner.nextLine();

        return name;

    }

}
