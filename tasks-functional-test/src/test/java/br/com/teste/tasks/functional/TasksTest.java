package br.com.teste.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver prepare() throws MalformedURLException {
		
		//System.setProperty("webdriver.chrome.driver","C:\\Desenvolvimento\\Selenium\\chromedriver.exe");
		
		//Execuï¿½ï¿½o Local
		//WebDriver driver = new ChromeDriver();
		
		//Execuï¿½ï¿½o em Grid
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.29:4444/wd/hub"), cap);

		driver.navigate().to("http://192.168.15.29:8001/tasks");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}
	
	
	@Test
	public void salvarNovaTaskTest() throws MalformedURLException {
		
		WebDriver driver = prepare();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			driver.findElement(By.id("saveButton")).click();
			
			String result = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", result);
			
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricaoTest() throws MalformedURLException {
		
		WebDriver driver = prepare();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			driver.findElement(By.id("saveButton")).click();
			
			String result = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", result);
		
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDataTest() throws MalformedURLException {
		
		WebDriver driver = prepare();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
					
			driver.findElement(By.id("saveButton")).click();
			
			String result = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", result);
			
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarComDataPassadaTest() throws MalformedURLException {
		
		WebDriver driver = prepare();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			driver.findElement(By.id("saveButton")).click();
			
			String result = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", result);
		
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void deveRemoverComSucesso() throws MalformedURLException {
		
		WebDriver driver = prepare();
		
		try {
			//Nova Tarefa
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Test via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();
			
			String result = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", result);
			
			//Remoção
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			
			result = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", result);
		
		}finally {
			driver.quit();
		}
	}
}
