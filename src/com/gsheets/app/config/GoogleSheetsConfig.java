package com.gsheets.app.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;

public class GoogleSheetsConfig {

    private static Sheets sheetsService;

    public static Sheets getSheetsService() throws Exception {

        if (sheetsService == null) {

            var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            var jsonFactory = GsonFactory.getDefaultInstance();

            var in = new InputStreamReader(
                    new FileInputStream("credentials/oauth.json"));

            var clientSecrets = com.google.api.client.googleapis.auth.oauth2
                    .GoogleClientSecrets.load(jsonFactory, in);

            var flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport,
                    jsonFactory,
                    clientSecrets,
                    List.of(
                    	    "https://www.googleapis.com/auth/spreadsheets",
                    	    "https://www.googleapis.com/auth/drive.file",
                    	    "https://www.googleapis.com/auth/drive"
                    	))

                    .setAccessType("offline")
                    .build();

            Credential credential = new AuthorizationCodeInstalledApp(
                    flow,
                    new LocalServerReceiver())
                    .authorize("user");

            sheetsService = new Sheets.Builder(
                    httpTransport,
                    jsonFactory,
                    credential)
                    .setApplicationName("Google Sheets Java OAuth App")
                    .build();
        }

        return sheetsService;
    }
}
