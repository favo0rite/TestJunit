package appline.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContributionsPage extends BasePage {
    @FindBy(xpath = "//span[@class='calculator__currency-field-btn']")
    List<WebElement> currencyButtons;

    @FindBy(xpath = "//label[text()='Сумма вклада']/parent::div/div/label/input")
    WebElement sumContributionInput;

    @FindBy(xpath = "//label[text()='Ежемесячное пополнение']/parent::div/div/label/input")
    WebElement monthlyPayment;

    @FindBy(xpath = "//select")
    WebElement timeOfDepositForm;


    @FindBy(xpath = "//div[@data-property='replenish']//following-sibling::div[@class='calculator__check-row']/div/label/span/div")
    WebElement checkbox;

    @FindBy(xpath = "//span[@class='js-calc-earned']")
    WebElement interestAccrued;

    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    WebElement replenishmentInFewMonths;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    WebElement removeInFewMonths;

    @Step("Выбираем валюту для вклада")
    public ContributionsPage clickCurrencyButton(String currencyName) {
        for (WebElement item : currencyButtons) {
            item = item.findElement(By.xpath(".//span[@class='calculator__currency-field-text']"));
            if (item.getText().contains(currencyName)) {
                elementToBeClickable(item).click();
                return this;
            }
        }
        fail("Валюта с наименованием = " + currencyName + "не найдена");
        return this;
    }


    @Step("Вводим сумму вклада")
    public ContributionsPage fillSumContributionInput(String value) {
        fillInputField(sumContributionInput, value);
        return this;
    }

    @Step("Вводим ежемесячное пополнение")
    public ContributionsPage fillMonthlyPaymentInput(String value) {
        fillInputField(monthlyPayment, value);
        return this;
    }


    @Step("Выбираем срок вложения")
    public ContributionsPage selectTimeOfDeposit(String timeOfDeposit) {
        Select select = new Select(timeOfDepositForm);
        select.selectByVisibleText(timeOfDeposit);
        return this;

    }

    @Step("Ставим галочку \"Ежемесячная капитализация\"")
    public ContributionsPage clickCheckBoxMonthlyCapitalization() {
        elementToBeClickable(checkbox);
        clickElementAction(checkbox);
        return this;
    }

    @Step("Проверяем значения полей")
    public ContributionsPage assertFields(String interestAccruedValue, String replenishmentInFewMonthsValue, String removeInFewMonthsValue) {
        elementTextToBePresent(interestAccrued, interestAccruedValue);
        elementTextToBePresent(replenishmentInFewMonths, replenishmentInFewMonthsValue);
        elementTextToBePresent(removeInFewMonths, removeInFewMonthsValue);

        assertEquals("Сумма начисленных процентов не совпала", interestAccrued.getText(),
                interestAccruedValue );
        assertEquals("Пополнение за определенное количество месяцев не совпало",
                replenishmentInFewMonths.getText(), replenishmentInFewMonthsValue);
        assertEquals("Снятие через определенное количество мясяцев не совпало",
                removeInFewMonths.getText(), removeInFewMonthsValue);
        return this;

    }
}
