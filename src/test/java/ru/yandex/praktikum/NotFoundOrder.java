package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;
import static org.junit.Assert.assertTrue;

public class NotFoundOrder {
    private WebDriver webDriver;

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser" , "firefox"));//для других браузеров ввести название браузера firefox / safari
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test //Проверка поиска по статусу заказа с несуществующим номером заказа
    public void OrderNotFound () {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("891111111111");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImgIsDisplayed());
    }
    @After
    public void tearDown(){
        webDriver.quit();
    }
}
