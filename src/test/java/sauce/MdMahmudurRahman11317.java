package sauce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.concurrent.TimeUnit;


public class MdMahmudurRahman11317 {
    WebDriver driver;

    static String readExcelfile() throws Exception, FileNotFoundException {
        File src = new File("D:\\practice-selenium\\src\\main\\resources\\sample_test_data.xlsx");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("start-maximized");

        WebDriver driver= new ChromeDriver(chromeOptions);

        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        driver.get("https://www.demoqa.com/automation-practice-form");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("input#firstName")).sendKeys(sheet1.getRow(1).getCell(0).getStringCellValue());
        driver.findElement(By.cssSelector("input#lastName")).sendKeys(sheet1.getRow(1).getCell(1).getStringCellValue());
        driver.findElement(By.cssSelector("input#userEmail")).sendKeys(sheet1.getRow(1).getCell(2).getStringCellValue());

        //radio--------------------------------------
        WebElement gender=driver.findElement(By.xpath("//label[contains(text(),'Female')]"));
                boolean selectState = gender.isSelected();

        //performing click operation only if element is not selected
                if (selectState == false) {
                    gender.click();
                }
        //driver.findElement(By.id("login-button")).click();

        driver.findElement(By.cssSelector("input#userNumber")).sendKeys(sheet1.getRow(1).getCell(4).getStringCellValue());
        driver.findElement(By.cssSelector("input#dateOfBirthInput")).click();

        //date--------------------------------------------------------------------
        WebElement year=driver.findElement(By.cssSelector("select.react-datepicker__year-select"));
        Select select1 =new Select(year);
        int yearValue = (int) sheet1.getRow(1).getCell(7).getNumericCellValue();
        //System.out.println(String.valueOf(yearValue));
        select1.selectByValue(String.valueOf(yearValue));


        WebElement month=driver.findElement(By.cssSelector("select.react-datepicker__month-select"));
        Select select=new Select(month);
        String monthValue =  sheet1.getRow(1).getCell(6).getStringCellValue();
        //System.out.println(monthValue);
        select.selectByVisibleText(monthValue);


        int date = (int) sheet1.getRow(1).getCell(5).getNumericCellValue();
        driver.findElement(By.cssSelector("div.react-datepicker__day.react-datepicker__day--0"+date)).click();

        //autocomplete-------------------------------------------------------------
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#subjectsInput")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element1);
        element1.click();
        element1.sendKeys(sheet1.getRow(1).getCell(8).getStringCellValue());
        element1.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        //checkbox----------------------------------------------------------
        WebElement hobby=driver.findElement(By.xpath("//label[contains(text(),'Reading')]"));
                boolean isSelected = hobby.isSelected();
                if (isSelected == false) {
                    hobby.click();
                }



        WebElement picture =driver.findElement(By.cssSelector("input#uploadPicture"));
        picture.sendKeys(sheet1.getRow(1).getCell(10).getStringCellValue());


        driver.findElement(By.cssSelector("#currentAddress")).sendKeys(sheet1.getRow(1).getCell(11).getStringCellValue());

        WebDriverWait wait1=new WebDriverWait(driver, 20);
        WebElement element2 = wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#react-select-3-input")));
        element2.sendKeys(sheet1.getRow(1).getCell(12).getStringCellValue());
        element2.sendKeys(Keys.ENTER);



        WebDriverWait wait2=new WebDriverWait(driver, 20);
        WebElement element3 = wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#react-select-4-input")));
        element3.sendKeys(sheet1.getRow(1).getCell(13).getStringCellValue());
        element3.sendKeys(Keys.ENTER);

        driver.findElement(By.id("submit")).click();

        WebElement out =driver.findElement(By.cssSelector("#example-modal-sizes-title-lg"));
        String output = out.getText();
        System.out.println(output);
        return output;

    }

    static void writeTextfile(String text) throws IOException {

        FileWriter file = new FileWriter("D:\\practice-selenium\\src\\main\\resources\\output.txt", true);
        file.write(text);
        file.close();
        System.out.println("file has been created" );


    }

    public static void main(String args[]) throws Exception, FileNotFoundException {


        String text = readExcelfile();
        writeTextfile(text);

    }
}
