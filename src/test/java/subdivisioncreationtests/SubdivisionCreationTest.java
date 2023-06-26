package subdivisioncreationtests;

import administrationpage.AdministrationPage;
import advance.AdvanceSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import subdivision.Subdivision;

import static chromedriverfactory.ChromeDriverFactory.setUpDriver;
import static constants.Constants.BASE_URL_ENTERING_FORM;
import static constants.Constants.HOME_PAGE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class SubdivisionCreationTest {

    private WebDriver driver;

    /**
     * @Author Ivan Tyulkin, QA;
     * @Date 19.06.2023;
     *
     * Сьют всего package. : Создание подразделения.
     * Предварительные действия до начала работы (описание методов произведено в package ru.sbrf.cpri.ru.sbrf.cpri.subdivision.subdivision.advance классе AdvanceSteps):
     * 1. Авторизация под администратором
     * 2. Зайти в раздел администрирование
     * 3. Зайти в раздел Подразделения
     */

    public SubdivisionCreationTest() {}

    @Before
    public void setUp() {
        setUpDriver();
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize(); // Запуск браузера в полном окне.
    }

    @Test
    @DisplayName("Тест на открытие URL страницы проекта.")
    @Description("Проверка, что происходит успешное открытие нужного URL страницы проекта.")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void homePageOpenedSuccessfulTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Открытие главной страницы проекта.
        advanceSteps.openHomePage();
        assertEquals(BASE_URL_ENTERING_FORM, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест на проверку отображения заголовка формы входа.")
    @Description("Проверка, что на экране виден заголовок формы входа 'ВХОД В СИСТЕМУ СТОРИ'.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void homePageEnteringFormIsDisplayedTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Открытие главной страницы проекта.
        advanceSteps.openHomePage();
        // Ожидание видимости на экране заголовка формы входа - ВХОД В СИСТЕМУ СТОРИ.
        advanceSteps.waitForTheEnteringForm();
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath("//div/h4[contains(text(),'Вход в систему СТОРИ')]")).isDisplayed()));
    }

    @Test
    @DisplayName("Тест на проверку успешного входа в систему.")
    @Description("Проверка, что осуществляется успешный вход в систему под АДМИНИСТРАТОРОМ.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldEnterTheSystemByAdminTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();
        assertEquals(HOME_PAGE_URL, driver.getCurrentUrl());                                            // Проверка соответствия URI ожидаемому.
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath("//div//section/a[1][contains(text(),'Учетные записи')]")).isDisplayed()));
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath("//div//section/a[3][contains(text(),'Сотрудники')]")).isDisplayed()));
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath("//div//section/a[2][contains(text(),'Подразделения')]")).isDisplayed()));
    }

    @Test
    @DisplayName("Тест на проверку успешного перехода в раздел 'Подразделения'.")
    @Description("Проверка, что осуществляется успешный переход в раздел 'Подразделения' для последующего создания нового подразделения.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldEnterTheSubdivisionListTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath(".//div//button[@title='Добавить подразделение']")).isDisplayed()));
    }

    @Test
    @DisplayName("Тест на проверку, что кнопка 'Добавить подразделение' активирована.")
    @Description("Проверка, что при загрузки страницы осуществляется успешная активация кнопки 'Добавить подразделение'.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldTheButtonOfAddASubdivisionIsActivatedTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();

        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемом URI.
                .xpath(".//div//button[@title='Добавить подразделение']")).isEnabled()));
    }

    @Test
    @DisplayName("Тест на проверку успешного открытия формы создания нового подразделения.")
    @Description("Проверка, что осуществляется успешное открытие формы содания нового подразделения и заголовок формы соотетствует ожидаемому.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldOpenANewSubdivisionAdditionFormTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();

        // Нажимаю кнопку 'Добавить подразделение.'
        subdivision.addANewSubdivisionPressButton();
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого элемента на ожидаемой форме.
                .xpath(".//div//span/strong[contains(text(),'Новое подразделение')]")).isEnabled()));
    }

    @Test
    @DisplayName("Тест на проверку, что кнопка 'Сохранить' не активна.")
    @Description("Проверка, что в пустой форме добавления нового подразделения, кнопка 'Сохранить' не активна.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldBeTheButtonNotActiveTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();

        // Нажимаю кнопку 'Добавить подразделение'.
        subdivision.addANewSubdivisionPressButton();
        assertThat(false, equalTo(driver.findElement(By                                            // Проверка НЕ отображения ожидаемого элемента.
                .xpath(".//div/button[@class='Button_button__dxrcN'][contains(text(),'Сохранить')]")).isEnabled()));
    }

    @Test
    @DisplayName("Тест на проверку, что после корректного заполнения формы кнопка 'Сохранить' активируется.")
    @Description("Проверка, что происходит активация кнопки 'Сохранить' после корректного заполнения всех полей формы добавления нового подразделения.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldTheButtonEnabledTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();

        // Нажимаю кнопку 'Добавить подразделение' и жду появление всплывающей формы.
        subdivision.addANewSubdivisionPressButton();
        subdivision.waitForTheTitleOfTheForm();

        // Заполняю форму создания нового подразделения единым шагом для всех полей формы.
        subdivision.fillTheFormOfAddingANewSubdivision();
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка отображения ожидаемого активного элемента.
                .xpath(".//div/button[@class='Button_button__dxrcN'][contains(text(),'Сохранить')]")).isEnabled()));
    }

    @Test
    @DisplayName("Тест на проверку, что можно добавить новое подразделение.")
    @Description("Проверка, что происходит добавление нового подразделения после корректного заполнения всех полей формы и нажатию кнопки 'Сохранить'.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://jira.sberbank.ru/secure/Tests.jspa#/testCase/CPRI-T474")
    public void shouldAddANewSubdivisionTest() {
        AdvanceSteps advanceSteps = new AdvanceSteps(driver);
        // Ввод данных пользователя типа АДМИНИСТРАТОР в поля формы и нажатие кнопки ВОЙТИ.
        advanceSteps.shouldEnterTheSystem();
        // Ожидание видимости на экране раздела УЧЁТНЫЕ ЗАПИСИ.
        advanceSteps.waitForTheContent();

        AdministrationPage administrationPage = new AdministrationPage(driver);
        // Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.
        administrationPage.subdivisionButtonClick();

        Subdivision subdivision = new Subdivision(driver);
        // Ожидание видимости на экране кнопки - 'Добавить подразделение'.
        subdivision.waitForTheAddANewSubdivisionButtonIsDisplayed();

        // Нажимаю кнопку 'Добавить подразделение' и жду появление всплывающей формы.
        subdivision.addANewSubdivisionPressButton();
        subdivision.waitForTheTitleOfTheForm();

        // Заполняю форму создания нового подразделения единым шагом для всех полей формы и нажимаю кнопку 'Сохранить'.
        subdivision.fillTheFormOfAddingANewSubdivisionAndSave();
        // Ожидаю всплывающее модульное окно с информационным сообщением.
        subdivision.waitForTheSubdivisionIsSuccessfullyCreatedMessage();
        assertThat(true, equalTo(driver.findElement(By                                            // Проверка информационного сообщения, что подразделение успешно создано.
                .xpath(".//div/*[contains(text(), 'Подразделение создано')]")).isEnabled()));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}