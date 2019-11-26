package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class stepDef {
    static WebDriver driver;
    WebDriverWait wait;
    int Streater = 0;
    int Acland = 0;
    int Yglesia = 0;
    int Graddon = 0;
    int Shoobridge = 0;
    String csvValue = "";
    ArrayList<ArrayList<String>> myList = new ArrayList<ArrayList<String>>();
    List<String> myList3 = new ArrayList<String>();
    List<String> myList4 = new ArrayList<String>();
@Before
public static void setUp(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
}
    @Given("I have navigated to the AUT")
    public void iHaveNavigatedToTheAUT() {
        driver.get("https://patientbooking.co.uk/brl/referral/");
    }

    @And("I log in with valid credentials")
    public void iLogInWithValidCredentials(DataTable datatable) {
        List<Map<String, String>> dt = datatable.asMaps(String.class, String.class);
        wait = new WebDriverWait(driver, 30);
        driver.findElement(By.id("inputEmail")).sendKeys(dt.get(0).get("EMAIL"));
        driver.findElement(By.id("inputPassword")).sendKeys(dt.get(0).get("PASSWORD"));
        driver.findElement(By.xpath("//div[contains(text(),'Sign in')]")).click();
        WebElement ref = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//h1"))));
        String refPortal = ref.getText();
        Assertions.assertEquals("Referral Portal", refPortal);
    }

    @When("I navigate to the Absence Overview page")
    public void iNavigateToTheAbsenceOverviewPage() {
        wait = new WebDriverWait(driver, 30);
        WebElement miReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'MI reports')]")));
        miReport.click();
        WebElement absenceOverview = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'Absence Overview')]")));
        absenceOverview.click();
    }

    @And("I set the time window")
    public void iSetTheTimeWindow(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> dt = dataTable.asMaps(String.class, String.class);
        wait = new WebDriverWait(driver, 30);
        WebElement startDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Start Date')]/following-sibling::input")));
        startDate.click();
        startDate.clear();
        Thread.sleep(3000);
        startDate.sendKeys(dt.get(0).get("START DATE"));
        WebElement endDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'End Date')]/following-sibling::input")));
        endDate.click();
        endDate.clear();
        Thread.sleep(3000);
        endDate.sendKeys(dt.get(0).get("END DATE"));
    }

    @And("I update the data")
    public void iUpdateTheData() {
        driver.findElement(By.xpath("//button[contains(text(),'Update data')]")).click();
    }

    @Then("The employee surnames on the first {int} pages should include {string}, {string} and {string}, but not {string} or {string}")
    public void theEmployeeSurnamesOnTheFirstPagesShouldIncludeAndButNotOr(int arg0, String arg1, String arg2, String arg3, String arg4, String arg5) throws InterruptedException {
        int num_of_pages = arg0;
        SoftAssert softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, 30);
        for (byte i = 0; i < num_of_pages; i++) {
            Thread.sleep(3000);
            List<WebElement> records_in_page = driver.findElements(By.xpath("//tbody/tr[@class='ng-scope']"));
            for (byte j = 1; j <= records_in_page.size(); j++) {
                WebElement sName = wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[" + j + "]/td[18]/div")));
                String surname = sName.getText();
                if (surname.equalsIgnoreCase(arg1)) {
                    Streater++;
                } else if (surname.equalsIgnoreCase(arg2)) {
                    Acland++;
                } else if (surname.equalsIgnoreCase(arg3)) {
                    Yglesia++;
                } else if (surname.equalsIgnoreCase(arg4)) {
                    Graddon++;
                    softAssert.assertTrue(Graddon == 0);
                } else if (surname.equalsIgnoreCase(arg5)) {
                    Shoobridge++;
                    softAssert.assertTrue(Shoobridge == 0);
                }

            }
            Boolean nextPage = driver.findElement(By.xpath("//span[contains(@aria-label,'Next')]")).isDisplayed();
            if (nextPage && i < (num_of_pages - 1)) {
                driver.findElement(By.xpath("//ul[@class='pagination']/li/span[contains(text()," + (i + 2) + ")]")).click();
            } else {
                break;
            }
        }
        softAssert.assertAll();
    }

    @And("Print the number of occurrences to the browser console")
    public void printTheNumberOfOccurrencesToTheBrowserConsole() {
        System.out.println("Occurance of Streater : " + Streater);
        System.out.println("Occurance of Acland : " + Acland);
        System.out.println("Occurance of Yglesia : " + Yglesia);
        System.out.println("Occurance of Graddon : " + Graddon);
        System.out.println("Occurance of Shoobridge : " + Shoobridge);
    }

    @When("I navigate to the Absence Management page")
    public void iNavigateToTheAbsenceManagementPage() {
        driver.findElement(By.xpath("//span[contains(text(),'Absence management')]")).click();
    }

    @And("I use {string} as the search criteria")
    public void iUseAsTheSearchCriteria(String arg0) {
        wait = new WebDriverWait(driver, 30);
        WebElement searchTxt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@title,'surname')]")));
        searchTxt.sendKeys(arg0);
    }

    @And("I perform the search")
    public void iPerformTheSearch() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@ng-modal='loader']")));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


    @And("I generate the CSV containing the search results")
    public void iGenerateTheCSVContainingTheSearchResults() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@ng-modal='loader']")));
        driver.findElement(By.xpath("//button[contains(text(),'Generate CSV')]")).click();
    }

    @When("I download the spreadsheet to a predefined directory on the local machine")
    public void iDownloadTheSpreadsheetToAPredefinedDirectoryOnTheLocalMachine() throws InterruptedException {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@ng-modal='loader']")));
        driver.findElement(By.xpath("//button[contains(text(),'Download CSV')]")).click();
        Thread.sleep(3000);
        int i = 1;
        int a = 0;
        Boolean flag = true;

        while (flag) {
            i++;
            List<WebElement> records_in_page = driver.findElements(By.xpath("//tbody/tr[contains(@class,'ng-scope')]"));
            for (byte j = 1; j <= records_in_page.size(); j++) {
                myList.add(new ArrayList<String>());
                for (byte k = 1; k <= 8; k++) {
                    String attribute = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[" + k + "]")).getAttribute("class");
                    String val = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[" + k + "]")).getText();
                    if (attribute.trim().length() == 0) {
                        val = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[" + k + "]/span")).getText();
                    }
                    if (val.trim().length() == 0) {
                        val = "";
                    }
                    myList.get(a).add(k - 1, val);
                }
                a++;
            }
            if (driver.findElements(By.xpath("//span[contains(@aria-label,'Next')]")).size() != 0) {
                driver.findElement(By.xpath("//ul[@class='pagination']/li/span[contains(text()," + i + ")]")).click();
                Thread.sleep(4000);
            } else {
                flag = false;
            }
        }

        String csvValue = "", csvVal;

        for (int m = 0; m < myList.size(); m++) {
            csvValue = "";
            for (int n = 0; n < myList.get(m).size(); n++) {
                csvVal = myList.get(m).get(n).trim();
                if (n == 7) {
                    csvVal = myList.get(m).get(n).replaceAll("[hd]", "");
                }
                csvValue = csvValue + csvVal.replaceAll("\"", "").replaceAll("-", " ").replaceAll(",", "").replaceAll(" ", "");
            }
            myList3.add(csvValue);
        }
    }

    @Then("The data in the spreadsheet should be consistent with the data displayed on screen")
    public void theDataInTheSpreadsheetShouldBeConsistentWithTheDataDisplayedOnScreen() throws
            InterruptedException, IOException {
        BufferedReader input = null;
        List<List<String>> downloadedData = new ArrayList<List<String>>();
        try {
            input = new BufferedReader(new FileReader("C:\\Users\\Ravi\\Downloads\\absences.csv"));
            String line = null;
            while ((line = input.readLine()) != null) {
                String[] data = line.split(",");
                downloadedData.add(Arrays.asList(data));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

        for (int m = 1; m < downloadedData.size(); m++) {
            csvValue = "";
            for (int n = 0; n < downloadedData.get(m).size(); n++) {
                if (n == 5) {
                } else {
                    csvValue = csvValue + downloadedData.get(m).get(n).trim().replaceAll("\"", "").replaceAll("-", " ").replaceAll(" ", "");
                }
            }
            myList4.add(csvValue);
        }
        Assert.assertTrue(myList3.equals(myList4));
    }

    @When("I navigate to the Logged-In-User's Profile page")
    public void iNavigateToTheLoggedInUserSProfilePage() {
        driver.findElement(By.xpath("//a[@data-original-title='My Account']")).click();
    }

    @And("I update the work address details")
    public void iUpdateTheWorkAddressDetails() {
        WebElement address1 = driver.findElement(By.xpath("//input[@ng-model='account.Address.Address1']"));
        address1.clear();
        address1.sendKeys("10");
        WebElement address2 = driver.findElement(By.xpath("//input[@ng-model='account.Address.Address2']"));
        address2.clear();
        address2.sendKeys("Downing Street");
    }

    @And("I update the contact options")
    public void iUpdateTheContactOptions() throws InterruptedException {
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, 30);
        WebElement contact = driver.findElement(By.xpath("//tr[@ng-repeat='option in account.ContactOptions'][1]/td[3]/input"));
        WebElement confirmation = driver.findElement(By.xpath("//tr[@ng-repeat='option in account.ContactOptions'][2]/td[3]/input"));
        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@ng-repeat='option in account.ContactOptions'][1]/td[3]/input"))).click();
        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@ng-repeat='option in account.ContactOptions'][2]/td[3]/input"))).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(contact).click().build().perform();
        actions.moveToElement(confirmation).click().build().perform();
    }

    @And("I confirm that an error occurs upon attempting to save without populating the required fields")
    public void iConfirmThatAnErrorOccursUponAttemptingToSaveWithoutPopulatingTheRequiredFields() {
        String err = "password isn't set.";
        wait = new WebDriverWait(driver, 30);
        driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]")).click();
        String actError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@ng-show='receivedError']"))).getText();
        Assert.assertEquals(actError, err);
    }

    @And("I save the changes")
    public void iSaveTheChanges() {
        WebElement password = driver.findElement(By.xpath("//input[@ng-model='account.Password']"));
        password.clear();
        password.sendKeys("3K4Mq*S%1ejqV0iu^glcK&o$m4q^D157");
        driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]")).click();
    }

    @Then("My changes should have successfully applied")
    public void myChangesShouldHaveSuccessfullyApplied() {
        String successMessage = "Account updated successfully";
        String actMessage = driver.findElement(By.xpath("//div[@ng-if='showAlert']")).getText();
        Assert.assertEquals(actMessage, successMessage);
    }
@After
    public static void tearDown(){
    driver.quit();
}
}
