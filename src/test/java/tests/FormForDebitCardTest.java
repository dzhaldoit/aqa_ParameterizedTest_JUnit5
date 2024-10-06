package tests;

import data.BirthdayArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormForDebitCardTest extends TestBase {
    @ArgumentsSource(BirthdayArgumentProvider.class)
    @ParameterizedTest(name = "Для даты {0} текст валидации долженбыть {1}")
    void successfulSendFormForDebitCard(String birthday, String ValidationText) {
        open("/cards/debit-cards/tinkoff-black/");
        $("#form").scrollIntoView(true);
        $("[data-qa-type='uikit/input.inputBox.inputContainer']").click();
        $("[name='birthdate']").setValue(birthday).pressEnter();
        $("#birthdate-error").shouldHave(exactText(ValidationText));
    }

    @CsvFileSource(resources = "/data/testData.csv")
    @ParameterizedTest(name = "Для url {0} текст заголовка должен быть {1}")
    void CheckLanguage (String UrlLocal, String ValidationText) {
        open(UrlLocal);
        $(".application").shouldHave(text(ValidationText));
    }


    @ParameterizedTest(name = "Если искать {0}, то первый элемент в поисковой выдаче будет {0}")
    @ValueSource(strings = {
            "Как скачать и войти в приложение Т‑Банка",
            "Как написать в чат",
            "Получить справку или выписку"
    })
    void checkOutPut(String textSearch){
        open("help/");
        $(".abxY7ZRH2").click();
        $("input.abECkQO50").setValue(textSearch).pressEnter();
        $("[data-dropdown-item-index='0']").shouldHave(text(textSearch));
    }
}