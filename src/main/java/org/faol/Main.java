package org.faol;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        String follow_exit = "";

        while (!follow_exit.equals("no")) {

            System.out.println("Press any key to choose operation.Type 'no' to exit");
            follow_exit = scanner.nextLine();

            if (!follow_exit.equals("no")) {
                main.crud();
            }else{
                System.out.println("App closing");
            }
        }

        scanner.close();

    }

    public void crud() {
        FindAll findAll = new FindAll();
        InsertRow insertRow = new InsertRow();
        DeleteRowByName deleteRow = new DeleteRowByName();
        DeleteRowById deleteRowById = new DeleteRowById();
        DeleteAllRows deleteAllRows = new DeleteAllRows();
        FindRowByName findRowByName = new FindRowByName();
        FindRowById findRowById = new FindRowById();
        UpdateRow updateRow = new UpdateRow();

        String selected;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the operation you want to perform \n" +
                "1 : Find all rows \n" +
                "2 : Find row by name \n" +
                "3 : Delete row by name \n" +
                "4 : Delete all rows \n" +
                "5 : Insert new row \n" +
                "6 : Update row \n" +
                "7 : Delete row by id \n" +
                "8 : Find row by id"
        );

        selected = scanner.nextLine();

        switch (selected) {

            case "1": {
                findAll.findAll();
                break;
            }
            case "2": {
                findRowByName.findRowByName();
                break;
            }
            case "3": {
                deleteRow.deleteRowByName();
                break;
            }
            case "4": {
                deleteAllRows.deleteAllRows();
                break;
            }
            case "5": {
                insertRow.insertRow();
                break;
            }
            case "6": {
                updateRow.updateRow();
                break;
            }
            case "7": {
                deleteRowById.deleteRowById();
                break;
            }
            case "8": {
                findRowById.findRowById();
                break;
            }
            default: {
                System.out.println("Operation not found");
                break;
            }
        }
    }
}
