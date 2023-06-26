package subdivision;

import createfieldsgenerator.CreateFieldsGenerator;
import date.Date;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.*;

public class Subdivision {

    CreateFieldsGenerator createFieldsGenerator;
    // Генератор каждый раз создаёт новые данные, поэтому, чтобы ввести уже сгенерированные второй раз, делаю это через переменную.

    private final WebDriver driver;

    public Subdivision(WebDriver driver) {
        this.driver = driver;
    }

    /*
    Локаторы для элементов страницы https://cpri-ci02887368.apps.ift-efsemp1-dm.delta.sbrf.ru/cpri/admin/divisions
    После нажатия на кнопку 'Поздазделения' осуществляется переход на страницу подразделений, на которой есть
    кнопка 'Добавить подразделение' - её локатор ниже 'addANewSubdivisionButton'.
     */
    private final By addANewSubdivisionButton = By.xpath("//div//button[@title='Добавить подразделение']");

    // Локаторы для полей формы добавления нового подразделения
    private final By organizationField = By.xpath("//div//form//div/span[1]/input[@aria-owns='rc_select_0_list']");
    private final By organizationsList = By.xpath("//div[@class='ant-select-item-option-content'][contains(text(),'ПАО Сбербанк')]");
    private final By subdivisionField = By.xpath("//div//form//div/span[1]/input[@aria-owns='rc_select_1_list']");
    private final By subdivisionsList = By.xpath("//div[@class='ant-select-item-option-content'][contains(text(),'Байкальский банк')]");
    private final By numberOfGOSB = By.xpath("//div/form//div/input[@name='gosbNumber']");
    private final By fullName = By.xpath("//div/form//div/input[@name='fullName']");
    private final By abbreviatedName = By.xpath("//div/form//div/input[@name='shortName']");
    private final By periodFieldFrom = By.xpath("//div/form/div[6]/div[1]/div[2]/div/div/div/input");
    private final By periodFieldTo = By.xpath("//div/form/div[6]/div[2]/div[2]/div/div/div/input");
    private final By saveButton = By.xpath("//div/button[@class='Button_button__dxrcN'][contains(text(),'Сохранить')]");

    // Локатор заголовка всплывающей формы 'Новое подразделение'.
    public final By newSubdivisionTitle = By.xpath("//div//span/strong[contains(text(),'Новое подразделение')]");

    // Методы.
    @Step("Нажимаю кнопку 'Добавить подразделение'.")
    public void addANewSubdivisionPressButton() {
        driver.findElement(addANewSubdivisionButton).isEnabled();
        driver.findElement(addANewSubdivisionButton).click();
    }

    @Step("Выбираю организацию 'ПАО Сбербанк'.")
    public void chooseTheOrganization() {
        driver.findElement(organizationField).click();
        waitForTheSberbankItem();
        driver.findElement(organizationsList).click();
    }

    @Step("Выбираю подразделение.")
    public void chooseTheSubdivisionItem() {
        driver.findElement(subdivisionField).click();
        waitForTheSubdivisionItem();
        driver.findElement(subdivisionsList).click();
    }

    @Step("Генерирую номер 'ГОСБ' и вставляю результат в поле.")
    public void numberOfGosbGen() {
        driver.findElement(numberOfGOSB).isEnabled();
        driver.findElement(numberOfGOSB).clear();
        driver.findElement(numberOfGOSB).sendKeys(CreateFieldsGenerator.gosb());
    }

    @Step("Ввожу полное наименование организации в поле 'Полное наименование'.")
    public void fullNameOfOrganizationInput() {
        driver.findElement(fullName).isEnabled();
        driver.findElement(fullName).clear();
        driver.findElement(fullName).sendKeys(CreateFieldsGenerator.companies());
    }

    @Step("Ввожу сокращённое наименование организации в поле 'Сокращённо'.")
    public void abbreviatedNameOfOrganizationInput() {
        driver.findElement(abbreviatedName).isEnabled();
        driver.findElement(abbreviatedName).clear();
        driver.findElement(abbreviatedName).sendKeys(CreateFieldsGenerator.abbreviated());
    }

    @Step("Выбираю в календаре сегодняшнюю дату.")
    public void chooseTheDateFrom() {
        driver.findElement(periodFieldFrom).isEnabled();
        driver.findElement(periodFieldFrom).clear();
        driver.findElement(periodFieldFrom).sendKeys(Date.formatDateFrom());
    }

    @Step("Выбираю в календаре дату через - случайное количество месяцев методом amountOfMonths().")
    public void chooseTheDateTo() {
        driver.findElement(periodFieldTo).isEnabled();
        driver.findElement(periodFieldTo).clear();
        driver.findElement(periodFieldTo).sendKeys(Date.formatDateTo());
    }

    @Step("Нажимаю кнопку 'Сохранить'.")
    public void pressSaveButton() {
        driver.findElement(saveButton).isEnabled();
        driver.findElement(saveButton).click();
    }

    @Step("Единый шаг заполнения формы добавления подразделения БЕЗ нажатия кнопки 'Сохранить'.")
    public void fillTheFormOfAddingANewSubdivision() {
        // Выбираю организацию 'ПАО Сбербанк'.
        chooseTheOrganization();
        // Выбираю подразделение.
        chooseTheSubdivisionItem();
        // Генерирую номер 'ГОСБ' и вставляю результат в поле.
        numberOfGosbGen();
        // Ввожу полное наименование организации в поле 'Полное наименование'.
        fullNameOfOrganizationInput();
        // Ввожу сокращённое наименование организации в поле 'Сокращённо'.
        abbreviatedNameOfOrganizationInput();
        // Выбираю в календаре сегодняшнюю дату.
        chooseTheDateFrom();
        // Выбираю в календаре дату через - случайное количество месяцев методом amountOfMonths().
        chooseTheDateTo();
    }

    @Step("Единый шаг заполнения формы добавления нового подразделения с нажатием кнопки 'Сохранить'.")
    public void fillTheFormOfAddingANewSubdivisionAndSave() {
        // Выбираю организацию 'ПАО Сбербанк'.
        chooseTheOrganization();
        // Выбираю подразделение.
        chooseTheSubdivisionItem();
        // Генерирую номер 'ГОСБ' и вставляю результат в поле.
        numberOfGosbGen();
        // Ввожу полное наименование организации в поле 'Полное наименование'.
        fullNameOfOrganizationInput();
        // Ввожу сокращённое наименование организации в поле 'Сокращённо'.
        abbreviatedNameOfOrganizationInput();
        // Выбираю в календаре сегодняшнюю дату.
        chooseTheDateFrom();
        // Выбираю в календаре дату через - случайное количество месяцев методом amountOfMonths().
        chooseTheDateTo();
        // Нажимаю кнопку 'Сохранить'.
        pressSaveButton();
    }

    // Ожидания.
    @Step("Ожидание видимости на экране кнопки - 'Добавить подразделение'.")
    public void waitForTheAddANewSubdivisionButtonIsDisplayed() {
        new WebDriverWait(driver, TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(addANewSubdivisionButton));
    }

    @Step("Ожидание видимости на экране выпадающего списка организаций 'ПАО Сбербанк'.")
    public void waitForTheSberbankItem() {
        new WebDriverWait(driver, TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-select-item-option-content'][contains(text(),'ПАО Сбербанк')]")));
    }

    @Step("Ожидание видимости на экране выпадающего списка доступных для выбора подразделений.")
    public void waitForTheSubdivisionItem() {
        new WebDriverWait(driver, TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-select-item-option-content'][contains(text(),'Байкальский банк')]")));
    }

    @Step("Ожидание видимости на экране заголовка всплывающей формы 'Новое подразделение'.")
    public void waitForTheTitleOfTheForm() {
        new WebDriverWait(driver, TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(newSubdivisionTitle));
    }

    @Step("Ожидание видимости информационного сообщения 'Подразделение создано'.")
    public void waitForTheSubdivisionIsSuccessfullyCreatedMessage() {
        new WebDriverWait(driver, TIME_OUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/*[contains(text(), 'Подразделение создано')]")));
    }
}