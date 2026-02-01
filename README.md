# Google Sheets Java Console Application

This project is a Java-based console application that allows users to interact with Google Sheets using OAuth 2.0 authentication.

## Features

- Create new Google Sheets
- Read entire sheet or specific cells
- Write data to a cell
- Copy data between spreadsheets
- Menu-driven console UI
- OAuth 2.0 login flow

##  Tech Stack

- Java 11+
- Google Sheets API
- Google Drive API
- OAuth 2.0 Desktop Flow
- Eclipse IDE
- Git & GitHub

##  Setup Instructions

 OAuth credentials are NOT included in this repository.

Each user must create their own:

1. Create a Google Cloud Project
2. Enable:
   - Google Sheets API
   - Google Drive API
3. Create OAuth Client ID (Desktop App)
4. Download the JSON file
5. Rename to `oauth.json`
6. Place it inside:
credentials/oauth.json
7. Run the application and complete browser login.

   
## How to Run

- Import project into Eclipse
- Add required JARs to classpath
- Run `GoogleSheetsApplication.java`
- Follow menu prompts

##  Security Notes

Do NOT commit:

- `credentials/`
- `tokens/`

These are ignored via `.gitignore`.

##  Documentation

A Detailed Design Document (DDD) is available here:

(Google Docs link)

##  Author

Vadlamuru Dhana Sri Hima
