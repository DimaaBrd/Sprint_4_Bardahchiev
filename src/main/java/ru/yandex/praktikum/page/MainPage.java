package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;
    //Локатор для кнопки статус заказа
    private final By orderStatusLocator = By.xpath("//button[text()='Статус заказа']");
    //Локатор для кнопки принятия кук
    private final By cookiesButtonLocator = By.id("rcc-confirm-button");
    //Локатор для поля ввода номера заказа
    private final By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']");
//Локатор для кнопки GO
    private final By goButtonLocator = By.xpath("//button[text()='Go!']");
//Локатор для изображения NotFound
    private final By notFoundImgLocator = By.xpath("//img[@alt='Not found']");
//локатор для верхней кнопки заказать
    private final By createOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
//Локатор для нижней кнопки заказать
    private final By bottomCreateOrderButtonLocator = By.xpath("//div[contains(@class, 'Home')]/button[text()='Заказать']");
//Локатор для вопроса в аккордеоне
    private final String questionLocator = "accordion__heading-%s";
    //Локатор для ответа в аккордеоне
    private final String answerLocator = "//div[contains(@id ,'accordion__panel')][.='%s']";
    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public void clickOrderStatusButton() {
        WebElement orderStatusButton = webDriver.findElement(orderStatusLocator);
        orderStatusButton.click();
    }

    public void enterOrderNumber(String orderNumber ) {
        WebElement placeholderStatusSearch = webDriver.findElement(orderNumberInputLocator);
        placeholderStatusSearch.sendKeys( orderNumber);
    }

    public void clickGoButton() {
        WebElement goButton = webDriver.findElement(goButtonLocator);
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
    }

    public boolean notFoundImgIsDisplayed() {
        return webDriver.findElement(notFoundImgLocator).isDisplayed();

    }
    public void clickCreateOrderButton(){
        WebElement orderButton = webDriver.findElement(createOrderButtonLocator);
        orderButton.click();
    }
    public void closeCookiesWindow(){
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
       WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element );
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public boolean answerIsDisplayed(String expectedAnswer){
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
       return element.isDisplayed();
    }
   public void clickBottomCreateOrderButton(){
      WebElement element = webDriver.findElement(bottomCreateOrderButtonLocator);
       ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element );
       new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
       element.click();
  }



}
