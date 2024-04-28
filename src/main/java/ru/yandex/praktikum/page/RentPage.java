package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RentPage {
    private final WebDriver webDriver;
    //локатор поля адреса доставки самоката на странице Про Аренду
    private final By deliveryDateLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор поля срока аренды самоката на странице Про Аренду
    private final By rentalPeriodLocator = By.xpath("//div[text()='* Срок аренды']");
    //локатор элемента в дропдауне срока аренды  самоката на странице Про Аренду
    private final By rentalPeriodMenuItemLocator = By.xpath("//div[text()='трое суток']");
    //Локатор кнопки заказать на странице Про Аренду
    private final By orderConfirmButtonLocator = By.xpath("//div[contains(@class,'Order')]/button[text()='Заказать']");
//Локатор кнопки подтверждения заказа
    private final By dialogueWindowConfirmationLocator = By.xpath("//button[text()='Да']");
    //Локатор для ассерта что заказ оформлен
    private final By orderCreatedModalLocator = By.xpath("//div[text()='Заказ оформлен']");
    public RentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillDeliveryDate(String deliveryTime) {
        WebElement deliveryDate = webDriver.findElement(deliveryDateLocator);
        deliveryDate.sendKeys(deliveryTime, Keys.ENTER);
    }

    public void fillRentalPeriod() {
        WebElement rentalPeriod = webDriver.findElement(rentalPeriodLocator);
        rentalPeriod.click();
        WebElement rentalPeriodMenuItem = webDriver.findElement(rentalPeriodMenuItemLocator);
        rentalPeriodMenuItem.click();
    }

    public void clickOrderConfirmButton() {
        WebElement orderConfirmButton = webDriver.findElement(orderConfirmButtonLocator);
        orderConfirmButton.click();
    }
    public void clickDialogueWindowYesButton(){
        WebElement dialogueWindowYesButton = webDriver.findElement(dialogueWindowConfirmationLocator);
        dialogueWindowYesButton.click();
    }
    public boolean orderCreatedModalIsDisplayed(){
        return webDriver.findElement(orderCreatedModalLocator).isDisplayed();
    }

    }

