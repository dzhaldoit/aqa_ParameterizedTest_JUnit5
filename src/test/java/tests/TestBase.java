package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    @BeforeEach
    void setApp() {
        Configuration.baseUrl = "https://www.tinkoff.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @AfterEach
    void setDown() {
        Selenide.closeWebDriver();
    }
}