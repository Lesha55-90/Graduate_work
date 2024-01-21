package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.PaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditGateTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SqlHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void creditPositiveAllFieldValidApproved() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }
}