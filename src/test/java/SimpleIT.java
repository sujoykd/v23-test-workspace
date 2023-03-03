import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.TestBenchTestCase;

public class SimpleIT extends TestBenchTestCase {

    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Sujoy/Softwares/chromedriver_win32/chromedriver.exe");
    }

    @Before
    public void setup() throws Exception {
        // Create a new browser instance
        this.setDriver(new ChromeDriver());
        // Open the application
        this.getDriver().get("http://localhost:8080/");
    }

    @Test
    public void selectRowTestBench() throws InterruptedException {
        // Find the first button (<vaadin-button>) on the page
        final GridElement grid = this.$(GridElement.class).first();

        // Click it
        grid.select(0);

        final var text = this.$(DivElement.class).id("testDiv");
        // Check the the value of the button is "Clicked"
        Assert.assertEquals("eOMtThyhVNLWUZNRcBaQKxI", text.getText());
    }

    @Test
    public void selectRowSelenium() throws InterruptedException {

        final var text = this.$(DivElement.class).id("testDiv");
        // Check the the value of the button is "Clicked"
        Assert.assertEquals("eOMtThyhVNLWUZNRcBaQKxI", text.getText());
    }

    @After
    public void tearDown() throws Exception {
        // close the browser instance when all tests are done
        this.getDriver().quit();
    }
}
