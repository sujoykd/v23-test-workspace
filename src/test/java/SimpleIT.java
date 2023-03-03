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
        this.setDriver(new ChromeDriver());
        this.getDriver().get("http://localhost:8080/");
    }

    @Test
    public void selectRowTestBench() throws InterruptedException {
        final GridElement grid = this.$(GridElement.class).first();

        // select the first row
        grid.select(0);

        final var text = this.$(DivElement.class).id("testDiv");

        // Check that the div is populated with the correct text
        Assert.assertEquals("eOMtThyhVNLWUZNRcBaQKxI", text.getText());
    }

    @Test
    public void selectRowSelenium() throws InterruptedException {

        final var text = this.$(DivElement.class).id("testDiv");
        // Check that the div is populated with the correct text
        Assert.assertEquals("eOMtThyhVNLWUZNRcBaQKxI", text.getText());
    }

    @After
    public void tearDown() throws Exception {
        // close the browser instance when all tests are done
        this.getDriver().quit();
    }
}
