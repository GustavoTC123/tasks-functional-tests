package test.functional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {

    public WebDriver retornarBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8001/tasks/");
        return driver;
    }

    @Test
    public void adiconarTarefaComSucesso() {
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
    public void adiconarTarefaSemDescricao() {
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
    public void adiconarTarefaComDataPassada() {
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
    public void adiconarTarefaSemData() {

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

}
