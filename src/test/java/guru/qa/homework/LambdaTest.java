package guru.qa.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.homework.Constants.ISSUE_NAME;
import static guru.qa.homework.Constants.REPO;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue")
@Owner("seminka")
public class LambdaTest {
    @Test
    @DisplayName("Тест с использованием lambda step")
    void lambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий через верхний поиск", () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPO).submit();
        });

        step("Кликаем по найденому репозиторию", () -> {
            $(linkText(REPO)).click();
        });

        step("Открываем таб issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие искомого issue", () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }
}
