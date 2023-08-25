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
import static org.openqa.selenium.By.linkText;

@Feature("Issue")
@Owner("seminka")
public class ListenerTest {
    @Test
    @DisplayName("Тест с использованием Selenide+Listener")
    void listenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").setValue(REPO).submit();
        $(linkText(REPO)).click();

        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }
}
