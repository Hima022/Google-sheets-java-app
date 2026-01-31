package com.gsheets.app.service;

import java.util.List;
import java.util.Scanner;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.gsheets.app.config.GoogleSheetsConfig;
public class SheetServiceImpl implements SheetService {


	    @Override
	    public void createSheet(Scanner sc) {
	        try {
	            System.out.print("Enter new Sheet name: ");
	            String sheetName = sc.nextLine();

	            Sheets sheetsService = GoogleSheetsConfig.getSheetsService();

	            Spreadsheet spreadsheet = new Spreadsheet()
	                    .setProperties(new SpreadsheetProperties().setTitle(sheetName));

	            Spreadsheet createdSheet = sheetsService
	                    .spreadsheets()
	                    .create(spreadsheet)
	                    .execute();

	            System.out.println("✅ Sheet created successfully");
	            System.out.println("📄 Sheet ID: " + createdSheet.getSpreadsheetId());

	        } catch (Exception e) {
	            System.out.println("❌ Error while creating sheet");
	            e.printStackTrace();
	        }
	    }
	    

	    
	    
	    
	    @Override
	    public void readSheet(Scanner sc) {
	        try {
	            System.out.print("Enter Sheet ID: ");
	            String sheetId = sc.nextLine();

	            System.out.print("Enter range (example: Sheet1!A1:C5 or Sheet1!A:Z): ");
	            String range = sc.nextLine();

	            Sheets sheetsService = GoogleSheetsConfig.getSheetsService();

	            ValueRange response = sheetsService
	                    .spreadsheets()
	                    .values()
	                    .get(sheetId, range)
	                    .execute();

	            List<List<Object>> values = response.getValues();

	            if (values == null || values.isEmpty()) {
	                System.out.println("⚠ No data found.");
	                return;
	            }

	            System.out.println("📊 Sheet Data:");
	            for (List<Object> row : values) {
	                System.out.println(row);
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Error while reading sheet");
	            e.printStackTrace();
	        }
	    }
	    
	    


	    
	    
	    @Override
	    public void writeSheet(Scanner sc) {
	        try {
	            System.out.print("Enter Sheet ID: ");
	            String sheetId = sc.nextLine();

	            System.out.print("Enter cell (example: Sheet1!A1): ");
	            String cell = sc.nextLine();

	            System.out.print("Enter value to write: ");
	            String value = sc.nextLine();

	            Sheets sheetsService = GoogleSheetsConfig.getSheetsService();

	            ValueRange body = new ValueRange()
	                    .setValues(List.of(List.of(value)));

	            sheetsService
	                    .spreadsheets()
	                    .values()
	                    .update(sheetId, cell, body)
	                    .setValueInputOption("RAW")
	                    .execute();

	            System.out.println("✅ Data written successfully");

	        } catch (Exception e) {
	            System.out.println("❌ Error while writing to sheet");
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void copySheet(Scanner sc) {
	        try {
	            System.out.print("Enter SOURCE Sheet ID: ");
	            String sourceSheetId = sc.nextLine();

	            System.out.print("Enter TARGET Sheet ID: ");
	            String targetSheetId = sc.nextLine();

	            Sheets sheetsService = GoogleSheetsConfig.getSheetsService();

	            ValueRange sourceData = sheetsService
	                    .spreadsheets()
	                    .values()
	                    .get(sourceSheetId, "Sheet1!A:Z")
	                    .execute();

	            List<List<Object>> values = sourceData.getValues();

	            if (values == null || values.isEmpty()) {
	                System.out.println("⚠ Source sheet is empty.");
	                return;
	            }

	            ValueRange targetBody = new ValueRange().setValues(values);

	            sheetsService
	                    .spreadsheets()
	                    .values()
	                    .update(targetSheetId, "Sheet1!A1", targetBody)
	                    .setValueInputOption("RAW")
	                    .execute();

	            System.out.println("✅ Sheet data copied successfully");

	        } catch (Exception e) {
	            System.out.println("❌ Error while copying sheet");
	            e.printStackTrace();
	        }
	    }
	}
