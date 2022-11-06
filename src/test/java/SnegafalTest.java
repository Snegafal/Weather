import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class SnegafalTest {
    @Test
    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    @Test
    public void testTitlePage_WhenClickingGuideMenu () throws InterruptedException {

//            TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой
// https://openweathermap.org/guide и что title этой страницы
// OpenWeatherMap API guide - OpenWeatherMap

        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultLink = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(5000);
        WebElement guideInMenu = driver.findElement(
                By.xpath("//ul[@id='first-level-nav']//a[@href='/guide']"));
        guideInMenu.click();
        Thread.sleep(3000);
        String guidePageTitle = driver.getTitle();
        String guidePageLink = driver.getCurrentUrl();

        Assert.assertEquals(expectedResultTitle, guidePageTitle);
        Assert.assertEquals(expectedResultLink, guidePageLink);

        driver.quit();
    }

    @Test
//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах

    public void testTemperatureInCityInFarenheit () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        driver.get(url);
        Thread.sleep(5000);
        WebElement farenheitMeasure = driver.findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']"));
        farenheitMeasure.click();

        WebElement farenheitTemperatureTextInCity = driver.findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']"));
        String[] farenheitText = farenheitTemperatureTextInCity.getText().split("");

        Thread.sleep(1000);
        Assert.assertEquals(farenheitText[farenheitText.length - 1], expectedResult);

        driver.quit();
    }

    @Test
//    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом
// “We use cookies which are essential for the site to work.
// We also use non-essential cookies to help us improve our services.
// Any data collected is anonymised. You can allow all cookies or manage them individually.”
// 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    public void testCookiePanelWithTwoButtonsInFooter () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultCookieText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data " +
                "collected is anonymised. You can allow all cookies or manage them individually.";

        driver.get(url);
        Thread.sleep(5000);

        WebElement cookieTextPanel = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//p"));
        String actualResultCookiePanelText = cookieTextPanel.getText();

        WebElement allowAllButton = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//button[text()='Allow all']"));

        WebElement manageCookiesButton = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[@href='/cookies-settings']"));

        Assert.assertEquals(expectedResultCookieText, actualResultCookiePanelText);
        Assert.assertTrue(allowAllButton.isDisplayed());
        Assert.assertTrue(manageCookiesButton.isDisplayed());

        driver.quit();
    }

//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”,
// “How to start” и “Ask a question”

    @Test
    public void testIsFaqHowToStartAskAQuestionInSupport () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);
        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();

////        WebElement supportDropdownMenu = driver.findElement(
//                By.xpath("//ul[@id='support-dropdown-menu']"));
        WebElement faqSubmenu = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));
        WebElement howToStartSubmenu = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']"));
        WebElement askAQuestionSubmenu = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));

//        Assert.assertTrue(supportDropdownMenu.isDisplayed());
        Assert.assertTrue(faqSubmenu.isDisplayed());
        Assert.assertTrue(howToStartSubmenu.isDisplayed());
        Assert.assertTrue(askAQuestionSubmenu.isDisplayed());

        driver.quit();
    }

//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification
// failed, please try again.”

    @Test
    public void testErrorWhenMissingCaptcha () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String emailFieldText = "aaaa@yandex.ru";
        String messageTextareaText = "Hello";
        String expectedResultErrorMessage = "reCAPTCHA verification failed, please try again.";

        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();
        WebElement askAQuestionSubmenu = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        askAQuestionSubmenu.click();

        String mainWindow = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(mainWindow)) {
                driver.switchTo().window(winHandle);
            }
        }

        Thread.sleep(1000);
        WebElement emailField = driver.findElement(
                By.xpath("//input[@id='question_form_email']"));
        emailField.sendKeys(emailFieldText);
        Thread.sleep(2000);

        WebElement subjectSelectField = driver.findElement(
                By.xpath("//form[@id='new_question_form']//select[@id='question_form_subject']"));
        subjectSelectField.click();
        WebElement subjectSelectOption = driver.findElement(
                By.xpath("//select[@id='question_form_subject']//option[@value='Tech Issue']"));
        subjectSelectOption.click();
        Thread.sleep(2000);
        WebElement messageTextarea = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']"));
        messageTextarea.sendKeys(messageTextareaText);

        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(
                By.xpath("//form[@id='new_question_form']//input[@value='Submit']"));
        submitButton.click();
        Thread.sleep(2000);
        WebElement actualResultCaptchaMessage = driver.findElement(
                By.xpath("//form[@id='new_question_form']//div[contains(text(), 'reCAPTCHA verification failed')]"));

        Assert.assertEquals(actualResultCaptchaMessage.getText(), expectedResultErrorMessage);

        driver.quit();
    }

//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
// 4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Ignore
    @Test
    public void testErrorEmptyEmail () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String messageTextareaText = "Hello";
        String expectedResultErrorMessageEmptyEmail = "can't be blank";

        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();
        WebElement askAQuestionSubmenu = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        askAQuestionSubmenu.click();

        String mainWindow = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(mainWindow)) {
                driver.switchTo().window(winHandle);
            }
        }

        Thread.sleep(3000);
        WebElement subjectSelectField = driver.findElement(
                By.xpath("//form[@id='new_question_form']//select[@id='question_form_subject']"));
        subjectSelectField.click();
        Thread.sleep(2000);
        WebElement subjectSelectOption = driver.findElement(
                By.xpath("//select[@id='question_form_subject']//option[@value='Tech Issue']"));
        subjectSelectOption.click();
        Thread.sleep(2000);
        WebElement messageTextarea = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']"));
        messageTextarea.sendKeys(messageTextareaText);

        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iframe);

        WebElement captcha = driver.findElement(
                By.xpath("//span[@id='recaptcha-anchor']"));
        captcha.click();

        Thread.sleep(3000);

        WebElement submitButton = driver.findElement(
                By.xpath("//form[@id='new_question_form']//input[@value='Submit']"));
        submitButton.click();

        WebElement actualResultErrorWhenEmptyEmail = driver.findElement(
                By.xpath("//span[contains(text(), \"can't be blank\")]"));

        Assert.assertEquals(actualResultErrorWhenEmptyEmail.getText(), expectedResultErrorMessageEmptyEmail);

        driver.quit();
    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//    3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testChangeFarenheitOnCelsiusWhenChoosingMetric () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultCelsius = "C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement farenheitMeasure = driver.findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Imperial: °F, mph']"));
        farenheitMeasure.click();

        Thread.sleep(1000);
        WebElement metricMeasure = driver.findElement(
                By.xpath("//div[@id='weather-widget']//div[text()='Metric: °C, m/s']"));
        metricMeasure.click();

        WebElement celsiusTemperatureTextInCity = driver.findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']"));
        String[] celsiusText = celsiusTemperatureTextInCity.getText().split("");

        Thread.sleep(1000);
        Assert.assertEquals(celsiusText[celsiusText.length - 1], expectedResultCelsius);

        driver.quit();
    }

//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//    Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testUrlAfterClickingLogo () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultUrl = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);
        WebElement logo = driver.findElement(By.xpath("//li[@class='logo']//img"));
        logo.click();
        Thread.sleep(5000);
        String actualResultUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.quit();
    }

//    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testFindRomeInUrlAndRomeInSearchFieldWhenEnteringRomeInInput () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityname = "Rome";
        driver.get(url);
        Thread.sleep(5000);

        WebElement searchField = driver.findElement(By.xpath("//input[@type='text']"));
        searchField.sendKeys(cityname + "\n");
        Thread.sleep(1000);

        WebElement searchString = driver.findElement(By.id("search_str"));
        String actualResultRomeInSearchField = searchString.getAttribute("value");

        Assert.assertTrue(driver.getCurrentUrl().contains(cityname));
        Assert.assertTrue(driver.getCurrentUrl().contains("find"));
        Assert.assertEquals(actualResultRomeInSearchField, cityname);

        driver.quit();
    }

    @Test
    public void test30OrangeButtonsWhenClickingApiPage () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        Thread.sleep(5000);

        WebElement apiPage = driver.findElement(By.linkText("API"));
        apiPage.click();
        Thread.sleep(1000);

        int orangeButtons = driver.findElements(By.xpath("//a[contains(@class, 'orange')]")).size();

        Assert.assertEquals(orangeButtons, 30);

        driver.quit();
    }
}
