package guru.qa.homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.homework.Constants.ISSUE_NAME;
import static guru.qa.homework.Constants.REPO;

@Feature("Issue")
@Owner("seminka")
public class StepsTest {
    @Test
    @DisplayName("Тест с использованием step")
    void stepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps step = new Steps();
        step.openMainPage();
        step.searchForRepository(REPO);
        step.clickOnRepositoryLink(REPO);
        step.openIssueTab();
        step.shouldSeeIssueWithName(ISSUE_NAME);
    }
}
