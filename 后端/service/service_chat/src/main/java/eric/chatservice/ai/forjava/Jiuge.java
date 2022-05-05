package eric.chatservice.ai.forjava;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Jiuge {

    public static void main(String[] args) throws InterruptedException {
        Jiuge jiuge = new Jiuge();
        jiuge.getPoem("秋日");
    }

    public String getPoem(String title){
        //System.setProperty("webdriver.chrome.driver", "/home/eric/server/google/chromedriver");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\java\\chatboard\\server\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //无浏览器模式
        options.addArguments("--no-sandbox");// 为了让root用户也能执行

        // 优化参数
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("blink-settings=imagesEnabled=false");
        options.addArguments("--disable-gpu");
        ChromeDriver driver = new ChromeDriver(options);//实例化

//        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://jiuge.thunlp.org/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement input = driver.findElementByXPath("//input[@id='encourage_words']");
        WebElement button = driver.findElementByXPath("//div[@class='generate']/a");
        input.sendKeys(title);
        button.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String text = driver.findElementByXPath("//div[@class='result']").getText();
        text = text.substring(4,29);
        System.out.println(text);
        driver.close();
        return text;
    }

}
