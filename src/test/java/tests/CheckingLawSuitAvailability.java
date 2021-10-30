package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CheckingLawSuitAvailability extends TestBase {
    @Test
    void successfulFillFormTest() {
        step("Открываем страницу с рассмотрением дел", () -> {
            open(hostname);
        });
        step("Вызываем первый выпадающий список с типами дел и выбираем гражданские дела", () -> {
            $(".fancy-select",0).click();
            $(by("data-raw-value", "civil")).click();
        });
        step("Клик на поле ФИО и заполняем фамилией "+ nameToSearch, () -> {
            $("div.full-name").click();
            $(":focus").val(nameToSearch).pressEnter();
        });
        step("Проверяем, что 'Судебные дела, удовлетворяющие запросу, не найдены'", () -> {
            $("div.hearings").shouldHave(text("Судебные дела, удовлетворяющие запросу, не найдены"));
        });

    }

}