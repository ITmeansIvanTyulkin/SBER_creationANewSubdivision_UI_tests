package administrationpage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdministrationPage {

    private final WebDriver driver;

    public AdministrationPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
    Локаторы для элементов страницы "https://cpri-ci02887368.apps.ift-efsemp1-dm.delta.sbrf.ru/cpri/admin"
    Это - начальная страница, на которую попадает АДМИН сразу после заполнения полей в форме входа.
     */
    private final By subdivisionButton = By.xpath("//div//section/a[2][contains(text(),'Подразделения')]");

    // Методы.
    @Step("Нажатие на кнопку 'Подразделения' для перехода в раздел сайта 'Подразделения'.")
    public void subdivisionButtonClick() {
        driver.findElement(subdivisionButton).click();
    }
}