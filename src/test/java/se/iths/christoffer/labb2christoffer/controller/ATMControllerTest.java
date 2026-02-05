package se.iths.christoffer.labb2christoffer.controller;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ATMControllerTest {

    @LocalServerPort
    private int port;

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    public static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        context.close();
    }

    @Test
    public void shouldReturnHttpStatus200WhenNavigatingToBalancePage() {
        Response response = page.navigate("http://localhost:" + port + "/balance");
        assertEquals(200, response.status());
    }

    @Test
    public void shouldRenderBalancePageWithCorrectTitleAndHeading() {
        page.navigate("http://localhost:" + port + "/balance");

        assertEquals("Kontobalans", page.title());

        Locator heading = page.locator("h1").first();
        assertTrue(heading.isVisible());
        assertEquals("Balans på kontot", heading.textContent());
    }

    @Test
    public void shouldDisplayBalanceValueInTemplate() {
        page.navigate("http://localhost:" + port + "/balance");

        Locator balanceElement = page.locator("h1:has-text('Aktuellt saldo:')");
        assertTrue(balanceElement.isVisible());

        String balanceText = balanceElement.textContent();
        assertTrue(balanceText.contains("Aktuellt saldo:"));

        String numberPart = balanceText.replace("Aktuellt saldo:", "").trim();
        assertDoesNotThrow(() -> Integer.parseInt(numberPart));
    }
}
