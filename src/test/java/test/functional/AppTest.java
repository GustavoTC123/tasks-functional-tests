package test.functional;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppTest {

    public WebDriver retornarBrowser() throws Exception {
        // WebDriver driver = new ChromeDriver();

        DesiredCapabilities cap = DesiredCapabilities.chrome();

        WebDriver driver = new RemoteWebDriver(new
        URL("http://192.168.1.15:4444/wd/hub"), cap);

        driver.navigate().to("http://192.168.1.15:8001/tasks/");

        return driver;
    }

    @Test
    public void adiconarTarefaComSucesso() throws Exception {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        WebDriver driver = retornarBrowser();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Tarefa de teste(Funcional)");

            driver.findElement(By.id("dueDate")).sendKeys(dataFormatada);

            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Success!", mensagem);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void adiconarTarefaSemDescricao() throws Exception {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        WebDriver driver = retornarBrowser();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("dueDate")).sendKeys(dataFormatada);

            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the task description", mensagem);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void adiconarTarefaComDataPassada() throws Exception {
        LocalDate data = LocalDate.now();
        data = data.minusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        WebDriver driver = retornarBrowser();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Tarefa de teste(Funcional)");

            driver.findElement(By.id("dueDate")).sendKeys(dataFormatada);

            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Due date must not be in past", mensagem);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void adiconarTarefaSemData() throws Exception {

        WebDriver driver = retornarBrowser();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("Tarefa de teste(Funcional)");

            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the due date", mensagem);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void removerTarefaComSucesso() throws Exception {

        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        WebDriver driver = retornarBrowser();

        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Tarefa de teste(Funcional)");
            driver.findElement(By.id("dueDate")).sendKeys(dataFormatada);
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);
            
            driver.findElement(By.linkText("Remove")).click();
            String mensagemRemocao = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagemRemocao);

        } catch (Exception e) {
            Assert.fail();
        }
    }
}
