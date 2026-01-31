package com.gsheets.app.access;
import java.util.Scanner;
import com.gsheets.app.service.*;
public class MenuAccess {



	    private final SheetService sheetService = new SheetServiceImpl();

	    public void start() {
	        Scanner sc = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n===== GOOGLE SHEETS MENU =====");
	            System.out.println("1. Create New Sheet");
	            System.out.println("2. Read Sheet");
	            System.out.println("3. Write to Sheet");
	            System.out.println("4. Copy Sheet Data");
	            System.out.println("5. Exit");
	            
	            System.out.print("Enter choice: ");

	            choice = sc.nextInt();
	            sc.nextLine(); // consume newline

	            switch (choice) {
	                case 1: sheetService.createSheet(sc);break;
	                case 2: sheetService.readSheet(sc);break;
	                case 3: sheetService.writeSheet(sc);break;
	                case 4: sheetService.copySheet(sc);break;
	                case 5: System.out.println("Exiting application...");break;
	                default: System.out.println("Invalid choice! Try again.");
	            }
	        } while (choice != 5);
	    }
	}

