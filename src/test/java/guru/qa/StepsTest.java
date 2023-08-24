package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();

        });

        step("Открываем там Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    void testAnnotatedStep() {
        WebSteps webSteps = new WebSteps();
        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.openIssueTab();
        webSteps.shouldSeeIssueWithNumber(ISSUE);
    }
}
