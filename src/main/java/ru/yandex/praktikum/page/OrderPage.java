package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver webDriver;
    //локатор поля имени на странице заказа
    private final By inputNameLocator = By.xpath("//input[@placeholder='* Имя']");
    //локатор поля фамилии на странице заказа
    private final  By inputSurnameLocator = By.xpath("//input[@placeholder='* Фамилия']");
    //локатор поля адреса на странице заказа
    private final  By inputAddressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля станции метро на странице заказа
    private final  By inputMetroStationLocator = By.xpath("//input[@placeholder='* Станция метро']");
    ////локатор поля номера телефона на странице заказа
    private final  By inputPhoneNumberLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор станции метро в выпадающем списке

    private final String metroDefinitionLocator = "//div[text()='%s']";
    private final By nextButtonLocator = (By.xpath("//button[text()='Далее']"));
    public OrderPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void fillCustomerInfo(String name, String surname, String address, String subwayTitle, String phone) {

        WebElement inputName = webDriver.findElement(inputNameLocator);
        inputName.sendKeys(name);


        WebElement inputSurname = webDriver.findElement(inputSurnameLocator);
        inputSurname.sendKeys(surname);


        WebElement inputAddress = webDriver.findElement(inputAddressLocator);
        inputAddress.sendKeys(address);


        WebElement inputMetroStation = webDriver.findElement(inputMetroStationLocator);
        inputMetroStation.click();

        WebElement metroDefinition = webDriver.findElement(By.xpath(String.format(metroDefinitionLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", metroDefinition);
        metroDefinition.click();


        WebElement inputPhoneNumber = webDriver.findElement(inputPhoneNumberLocator);
        inputPhoneNumber.sendKeys(phone);


    }

    public void clickNextButton() {
        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }
}
