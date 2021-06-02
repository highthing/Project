package tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

public class test1{

    WebDriver driver = null;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver,30);


        // Kullanıcı belirlenen URL'e gider.
        driver.get("https://www.gittigidiyor.com/");
        driver.manage().window().maximize();

        // Mouse giriş yap ögesinin üzerine getirilir.
        Actions move = new Actions(driver);
        move.moveToElement(By.xpath("//div[text()=\"Giriş Yap\"]"));

        //Giriş yap butonuna tıklanır.
        driver.findElement(By.cssSelector(".qjixn8-0.sc-1bydi5r-0.kNKwwK")).click();


        //Kullanıcı adı ve şifre girilir.
        WebElement user = driver.findElement(By.id("L-UserNameField"));
        user.click();
        user.sendKeys("ahmet.solmaz1@hotmail.com");

        WebElement password = driver.findElement(new By.ByCssSelector(".gg-d-24[type='password']"));
        password.click();
        password.sendKeys("Heyyoo321.");

        WebElement login = driver.findElement(new By.ByCssSelector("#gg-login-enter"));

        Actions action = new Actions(driver);

        action.doubleClick(login).perform();


        //Arama kutusuna istenen kelime girilir.
        WebElement Search_Box = driver.findElement(By.xpath("//input[@placeholder=\"Keşfetmeye Bak\"]"));
        Search_Box.sendKeys("Bilgisayar");

        driver.findElement(By.cssSelector(".qjixn8-0.sc-1bydi5r-0.hKfdXF")).click();

        //Ürün listesinden rastgele bir ögeye tıklanır.
        WebElement Urun = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"AMD A8-9600 3.1GHz, 8GB Ram,1TB HDD 21.5 75Hz Monitörlü Oyuncu Bilgisayarı\"]")));
        Urun.click();

        //Ürün sayfasındaki fiyat alınır.
        String Fiyat_1 = driver.findElement(By.xpath("//div[text()=\"3.599,04 TL\"]")).getAttribute("innerHTML");

        //Ürün sepete eklenir.
        driver.findElement(By.id("add-to-basket")).click();

        //Sepete gidilir.
        driver.findElement(By.xpath("//span[text()=\"Sepetim\"]")).click();

        //Sepetteki fiyat alınır.
        String Fiyat_2 = driver.findElement(By.xpath("//div[text()=\"3.749,00 TL\"]")).getAttribute("innerHTML");

        //İki fiyat karşılaştırma işlemi yapılır.
        Assertions.assertEquals(Fiyat_1,Fiyat_2);

        //Sepetteki ürün sayısı ikiye çıkarılır.
        driver.findElement(By.xpath("//*[@id=\"cart-item-478627361\"]/div[1]/div[4]/div/div[2]/select"));
        driver.findElement(By.xpath("//*[@id=\"cart-item-478627361\"]/div[1]/div[4]/div/div[2]/select")).sendKeys("2");
        driver.findElement(By.xpath("//*[@id=\"cart-item-478627361\"]/div[1]/div[4]/div/div[2]/select")).sendKeys(Keys.ENTER);

        //Sepetteki ürünler silinir.
       driver.findElement(By.xpath("//*[@id=\"cart-item-478627361\"]/div[1]/div[3]/div/div[2]/div/a[1]")).click();


       //Sepetin boş olduğunun kontrol edilmesi.
       String Sepet_Bos = driver.findElement(By.xpath("//p[text()=\"0,00 TL\"]")).getAttribute("innerHTML");

       //Sepetteki fiyat ile sıfır karşılaştırılması.
       Assertions.assertEquals(Sepet_Bos,0);






















    }

}