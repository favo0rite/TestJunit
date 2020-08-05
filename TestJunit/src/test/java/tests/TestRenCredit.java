package tests;

import basetestsclass.BaseTestClass;
import org.junit.Test;

public class TestRenCredit extends BaseTestClass {

    @Test
    public void testRublesCurrency() {
        app.getStartPage().gotToMenu("Вклады")
                .clickCurrencyButton("Рубли")
                .fillSumContributionInput("300000")
                .fillMonthlyPaymentInput("50000")
                .selectTimeOfDeposit("6 месяцев")
                .clickCheckBoxMonthlyCapitalization()
                .assertFields("7 422,56", "250 000",
                        "557 422,56");

    }

    @Test
    public void testDollarsCurrency() {
        app.getStartPage().gotToMenu("Вклады")
                .clickCurrencyButton("Доллары США")
                .fillSumContributionInput("500000")
                .fillMonthlyPaymentInput("30000")
                .selectTimeOfDeposit("9 месяцев")
                .clickCheckBoxMonthlyCapitalization()
                .assertFields("1 382,24", "240 000",
                        "741 382,24");
    }
}
