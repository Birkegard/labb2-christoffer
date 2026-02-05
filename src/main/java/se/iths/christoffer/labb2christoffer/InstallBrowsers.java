package se.iths.christoffer.labb2christoffer;

import com.microsoft.playwright.Playwright;

public class InstallBrowsers {
    public static void main(String[] args) {
        System.out.println("Installerar Playwright-webbläsare...");
        try (Playwright playwright = Playwright.create()) {
            System.out.println("Playwright installerat och redo!");
        }
    }
}