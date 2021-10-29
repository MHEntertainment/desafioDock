package desafiodock1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;

public class testetorra {
	WebDriver driver;
	@Before
	public void iniciarDriver() {
		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.lojastorra.com.br/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@Test	
	public void ct01() { //Buscar produto.
		String termoDeBusca = "camisa";
		
		//mapear caixa de busca e inserir texto / Não é possivel mapear por id>random
		WebElement caixaDeBusca = driver.findElement(By.className("fulltext-search-box"));
		caixaDeBusca.sendKeys(termoDeBusca.toLowerCase());
		
		//realizar pesquisa
		WebElement enterBusca = 
				driver.findElement(By.className("ui-autocomplete-input"));
		enterBusca.sendKeys(Keys.ENTER);
		String resultado = 
				driver.findElement(By.className("productName")).getText();
		resultado = 
				resultado.toLowerCase();
		
		//validar resultado
		boolean resultadoTeste;
		if(resultado.contains(termoDeBusca)) {
			System.out.println("Teste OK");
			resultadoTeste = true;
		} else {
			System.out.println("Erro na busca");
			System.out.println("O resultado encontrado foi: " + resultado);
			resultadoTeste = false;
		}
		Assert.assertTrue(resultadoTeste);		
	}
	
	@Test	
	public void ct02() throws InterruptedException { //Ir para seção “calçados”.
		String termoDeBusca = "calçados";
		
		WebElement calcadosCateg = driver.findElement(By.id("ihttps://torratorra.vteximg.com.br/arquivos/ids/445634/Calç2.png?v=637689555713600000"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calcadosCateg);Thread.sleep(500);
		calcadosCateg.click();
		String categoria = driver.findElement(By.className("last")).getText();
		categoria = categoria.toLowerCase();

		boolean resultadoTeste;
		if(categoria.contains(termoDeBusca)) {
			System.out.println("Teste OK");
			System.out.println("O resultado encontrado foi: " + categoria);
			resultadoTeste = true;
		} else {
			System.out.println("Erro na busca");
			System.out.println("O resultado encontrado foi: " + categoria);
			resultadoTeste = false;
		}
		Assert.assertTrue(resultadoTeste);	
}

	@Test
	public void ct03() throws InterruptedException { //Filtrar por “Marca” de calçados.
		String termoDeFiltro = "activitta";
		
		WebElement calcadosCateg = driver.findElement(By.id("ihttps://torratorra.vteximg.com.br/arquivos/ids/445634/Calç2.png?v=637689555713600000"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calcadosCateg);Thread.sleep(500);
		calcadosCateg.click();
		WebElement calcadoFiltroMarca = driver.findElement(By.xpath("/html/body/main/div[3]/div/section/div/div[2]/div[2]/div/ul/li[1]/a/div[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calcadoFiltroMarca);Thread.sleep(500);
		driver.findElement(By.xpath("//span[@class='listFilter listFilterH5 Hide HideMarca Marca']//div[@class='btFilter']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//a[@title='Activitta']")).click();
		
		String resultadoFiltroMarca = driver.findElement(By.className("last")).getText();
		resultadoFiltroMarca = resultadoFiltroMarca.toLowerCase();
		boolean resultadoTeste;
		
		if(resultadoFiltroMarca.contains(termoDeFiltro)) {
			System.out.println("Teste OK");
			System.out.println("O resultado encontrado foi: " + resultadoFiltroMarca);
			resultadoTeste = true;
		} else {
			System.out.println("Erro na busca");
			System.out.println("O resultado encontrado foi: " + resultadoFiltroMarca);
			resultadoTeste = false;
		}
		Assert.assertTrue(resultadoTeste);	
	}
	
	@Test
	public void ct04() throws InterruptedException { //Enviar produto para “Minha Sacola”.
		WebElement masculinoCateg =
				driver.findElement(By.xpath("//img[@id='ihttps://torratorra.vteximg.com.br/arquivos/ids/446765/Categoria_Masc.png?v=637689769788300000']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", masculinoCateg);Thread.sleep(500);
		masculinoCateg.click();
		WebElement escolherItem = 
				driver.findElement(By.name("'Camiseta Manga Curta Estampa Animes Cinza Claro\n'"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", escolherItem);Thread.sleep(500);
			escolherItem.click();	
	}
	
	@Test
	public void ct05() throws InterruptedException { //Login de usuário inválido.		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement loginUsuario = driver.findElement(By.id("login"));
		loginUsuario.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vtexIdContainer")));
		WebElement entrarUsuario = driver.findElement(By.id("loginWithUserAndPasswordBtn"));
		entrarUsuario.click();
		Thread.sleep(500);
		driver.findElement(By.id("inputEmail")).sendKeys("teste@teste.teste");
		driver.findElement(By.id("inputPassword")).sendKeys("teste");
		driver.findElement(By.id("classicLoginBtn")).click();
	}
	
	@Test
	public void ctlist() {
		List<WebElement> listarMenu = 
				driver.findElements(By.xpath("/html/body/header/div[4]/div/div/div[2]/div"));
		ArrayList<String> linkText = 
				new ArrayList<String>();
		for(WebElement ele : listarMenu)
		{
			try {
				linkText.add(ele.getText());
			} catch (Exception e) {}
		}
		System.out.println("Lista:");
		for(String s : linkText) {
			System.out.println(s);
		}
	}
	
	@After
	public void encerrarDriver() {
		//driver.close();
		//driver.quit();
	}
}
