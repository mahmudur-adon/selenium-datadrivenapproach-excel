package sauce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

public class StartSauce {

    static void writefile() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        FileWriter file1 = new FileWriter("D:\\maven project\\testfile\\productlist04.txt");
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(5000);
        List<WebElement> product = driver.findElements(By.cssSelector("div.inventory_item_name"));
        //System.out.println(product.size());

//        for(int i=0; i< product.size();i++){
//            String path= "//*[@id=\"item_"+i+"_title_link\"]/div";
//            WebElement ele= driver.findElement(By.xpath(path));
//            System.out.println(ele.getText());
//        }

        for(WebElement e:product){


            file1.write(e.getText());
            file1.write("\n");
            System.out.println(e.getText());


        }
        file1.close();

    }
    static void readExcelfile() throws Exception, FileNotFoundException{
        File src = new File("D:\\maven project\\testfile\\cred.xlsx");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        FileInputStream fis = new FileInputStream(src);
        //For xlsx xssfworkbook
        XSSFWorkbook wb =new XSSFWorkbook(fis);
        //for xls use HSSFWorkbook
        //HSSFWorkbook wb =new HSSFWorkbook(fis);
        //getsheet at index 0 chooses the sheet 1 of xlsx file
        XSSFSheet sheet1= wb.getSheetAt(0);
        //int rowcount = sheet1.getLastRowNum();
        //System.out.println("Total row = "+rowcount);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys(sheet1.getRow(1).getCell(0).getStringCellValue());
        driver.findElement(By.id("password")).sendKeys(sheet1.getRow(1).getCell(1).getStringCellValue());
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(5000);
    }

    static void writeExcelfile() throws Exception, FileNotFoundException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        File src = new File("D:\\\\maven project\\\\testfile\\\\product.xlsx");
        FileInputStream fis = new FileInputStream(src);
        //For xlsx xssfworkbook
        XSSFWorkbook wb =new XSSFWorkbook(fis);
        //for xls use HSSFWorkbook
        //HSSFWorkbook wb =new HSSFWorkbook(fis);
        //getsheet at index 0 chooses the sheet 1 of xlsx file
        XSSFSheet sheet1= wb.getSheetAt(0);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(5000);
        List<WebElement> product = driver.findElements(By.cssSelector("div.inventory_item_name"));
        System.out.println(product.size());

        for(int i=0; i< product.size();i++){
            //String path= "//*[@id=\"item_"+i+"_title_link\"]/div";
            //WebElement ele= driver.findElement(By.xpath(path));
            String name= product.get(i).getText();
            sheet1.getRow(i).createCell(2).setCellValue(name);

            System.out.println(name);
        }
        FileOutputStream fout = new FileOutputStream(src);
        wb.write(fout);
        wb.close();


        //getrow 0 chooses the first row and get cell 0 chooses the first column
//        sheet1.getRow(0).createCell(2).setCellValue("pass");
//        sheet1.getRow(1).createCell(2).setCellValue("fail");
//        FileOutputStream fout = new FileOutputStream(src);
//        wb.write(fout);
//        wb.close();

    }



    public static void main (String args[]) throws Exception,FileNotFoundException {




        //readExcelfile();
        writeExcelfile();
        //writefile();


    }
}
