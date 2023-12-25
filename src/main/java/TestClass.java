import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {
    WebDriver driver;
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void connect() {
        // Declare Webdriver
        WebDriverManager.chromedriver().setup();
        // Declare Chrome Webdriver
        chromeDriver = new ChromeDriver();
        // Connect Website
        chromeDriver.get("https://pltpro.net/");
        sleep(500);
        System.out.println("Connect Website Pass");
    }
    @Test
    public void order(){
        // Item Array
        String data [][] = new String [3][4];
        // Item 1
        data [0][0]= "//*[@id=\"content\"]/div[1]/div[2]/div/div[1]/a/img";
        data [0][1]= "//*[@id=\"quantity_1733\"]";
        data [0][2]= "4";
        data [0][3]= "//*[@id=\"content\"]/div/table/tbody/tr[1]/td[3]/div/span/button/i";
        // Item 2
        data [1][0]= "//*[@id=\"content\"]/div[1]/div[1]/div/div[1]/a/img";
        data [1][1]= "//*[@id=\"quantity_1734\"]";
        data [1][2]= "4";
        data [1][3]= "//*[@id=\"content\"]/div/table/tbody/tr[2]/td[3]/div/span/button";
        // Item 3
        data [2][0]= "//*[@id=\"content\"]/div[1]/div[3]/div/div[1]/a/img";
        data [2][1]= "//*[@id=\"quantity_1732\"]";
        data [2][2]= "4";
        data [2][3]= "//*[@id=\"content\"]/div/table/tbody/tr[3]/td[3]/div/span/button/i";
        // User Array For User Data
        String[] user = {"Truong Minh Khiet","0983114479","truongminhkhiett@gmail.com","Gigarion Nguyen Van Troi","Selenium la trum`"};

        for(int i = 0;i<data.length;i++)
        {
            buy(data[i]);
            if (i+1 == data.length) // If item is the last
            {
                // Input User Order Infor
                chromeDriver.findElement(By.xpath("//*[@id=\"collapse-shipping\"]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(user[0]);
                chromeDriver.findElement(By.xpath("//*[@id=\"collapse-shipping\"]/div/table/tbody/tr[2]/td[2]/input")).sendKeys(user[1]);
                chromeDriver.findElement(By.xpath("//*[@id=\"collapse-shipping\"]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(user[2]);
                chromeDriver.findElement(By.xpath("//*[@id=\"collapse-shipping\"]/div/table/tbody/tr[4]/td[2]/input")).sendKeys(user[3]);
                chromeDriver.findElement(By.xpath("//*[@id=\"collapse-shipping\"]/div/table/tbody/tr[5]/td[2]/textarea")).sendKeys(user[4]);
                sleep(4000);
                chromeDriver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/div[2]/button")).click();
                System.out.println("Order Successfully");
            }
            else
            {
                // Continue Buying
                chromeDriver.findElement(By.xpath("//*[@id=\"content\"]/form/div[2]/div[1]/a")).click();
            }

        }
        sleep(4000);
    }

    @AfterMethod
    public void cleanUp(){
        chromeDriver.quit();
    }

    // Wait Function
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    // Buy Function
    public void buy(String[] data){
        // Pick item
        chromeDriver.findElement(By.xpath(data[0])).click();
        // Test "+" function.
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[1]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[1]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[1]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[1]")).click();
        // Test "-" function.
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[2]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[2]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"product\"]/div/div/form/div/a[2]")).click();
        // Add to cart.
        chromeDriver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
        sleep(2000);
        // Edit amount of item in cart.z
        chromeDriver.findElement(By.xpath(data[1])).clear();
        // Input text
        chromeDriver.findElement(By.xpath(data[1])).sendKeys(data[2]);
        // Update amount of item in cart.
        chromeDriver.findElement(By.xpath(data[3])).click();
    }
}