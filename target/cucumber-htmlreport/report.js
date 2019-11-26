$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/practical_test.feature");
formatter.feature({
  "name": "Practical Test",
  "description": "  In order to test the AUT\n  As a registered user\n  I want to run the following tests",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Compare absence data with local file",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@practical1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I have navigated to the AUT",
  "keyword": "Given "
});
formatter.match({
  "location": "MyStepdefs.iHaveNavigatedToTheAUT()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I log in with valid credentials",
  "rows": [
    {
      "cells": [
        "EMAIL",
        "PASSWORD"
      ]
    },
    {
      "cells": [
        "njF83mf@jN7p.z7B",
        "3K4Mq*S%1ejqV0iu^glcK\u0026o$m4q^D157"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iLogInWithValidCredentials(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I navigate to the Absence Management page",
  "keyword": "When "
});
formatter.match({
  "location": "MyStepdefs.iNavigateToTheAbsenceManagementPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I use \"Y\" as the search criteria",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iUseAsTheSearchCriteria(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform the search",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iPerformTheSearch()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I generate the CSV containing the search results",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iGenerateTheCSVContainingTheSearchResults()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I download the spreadsheet to a predefined directory on the local machine",
  "keyword": "When "
});
formatter.match({
  "location": "MyStepdefs.iDownloadTheSpreadsheetToAPredefinedDirectoryOnTheLocalMachine()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The data in the spreadsheet should be consistent with the data displayed on screen",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.theDataInTheSpreadsheetShouldBeConsistentWithTheDataDisplayedOnScreen()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});