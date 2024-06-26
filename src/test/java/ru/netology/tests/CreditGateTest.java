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
        assertEquals("APPROVED", "APPROVED");
    }

    @Test
    void creditPositiveAllFieldValidDeclined() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationApproved();
        assertEquals("DECLINED", "DECLINED");
    }

    @Test
    void creditNegativeAllFieldEmpty() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormat4Fields();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeNumberCard15Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getNumberCard15Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeCardNotInDatabase() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitNotificationFailure();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeMonth1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeMonthOver12() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeMonth00ThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth00ThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeMonth00OverThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth00OverThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeYear00() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.waitNotificationExpiredError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeYear1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeYearUnderThisYear() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearUnderThisYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeYearOverThisYearOn6() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearOverThisYearOn6());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeCvv1Symbol() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvv1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeCvv2Symbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvv2Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeOwner1Word() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolder1Word());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeOwnerCirillic() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeOwnerNumeric() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolderNumeric());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void creditNegativeOwnerSpecialSymbols() {
        val startPage = new PaymentMethod();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardSpecialSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", "0");
    }
}
