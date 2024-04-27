package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;
import ru.yandex.praktikum.page.RentPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class OrderTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayTitle;
    private final String phone;

public OrderTest(String name, String surname, String address, String subwayTitle, String phone){
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.subwayTitle = subwayTitle;
    this.phone = phone;
}
@Parameterized.Parameters //Два набора тестовых данных
public static Object[][]data(){
    return new Object[][]{
            {"Дмитрий","Бардахчиев","Московская 33","Арбатская","89883382474"},
            {"Артем", "Какойто", "Пушкина 1", "Пушкинская", "89883379650"}
    };
}
private WebDriver webDriver;

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser" , "chrome"));//для других браузеров ввести название браузера firefox / safari
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

        @Test //Тест создания заказа через верхнюю кнопку
        public void CreateOrder () {

            MainPage mainPage = new MainPage(webDriver);
            mainPage.clickCreateOrderButton();

            OrderPage orderPage = new OrderPage(webDriver);
            orderPage.fillCustomerInfo(name, surname, address, subwayTitle, phone);
            orderPage.clickNextButton();

            RentPage rentPage = new RentPage(webDriver);
            rentPage.fillDeliveryDate("01.01.2025");
            rentPage.fillRentalPeriod();
            rentPage.clickOrderConfirmButton();
            rentPage.clickDialogueWindowYesButton();
            assertTrue(rentPage.orderCreatedModalIsDisplayed());

        } @Test // Создание заказа через нижнюю кнопку
        public void CreateOrderBottomButton () {

            MainPage mainPage = new MainPage(webDriver);
            mainPage.clickBottomCreateOrderButton();

            OrderPage orderPage = new OrderPage(webDriver);
            orderPage.fillCustomerInfo(name, surname, address, subwayTitle, phone);
            orderPage.clickNextButton();

            RentPage rentPage = new RentPage(webDriver);
            rentPage.fillDeliveryDate("01.01.2025");
            rentPage.fillRentalPeriod();
            rentPage.clickOrderConfirmButton();
            rentPage.clickDialogueWindowYesButton();
            assertTrue(rentPage.orderCreatedModalIsDisplayed());
        }

    @After
    public void tearDown(){
        webDriver.quit();
    }
    }
