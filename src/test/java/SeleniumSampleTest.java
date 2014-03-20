package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumSampleTest {

	private static final String IE_DRIVER_SERVER_PATH  = "lib" + File.separator + "IEDriverServer_Win32_2.40.0";
	private static final String IE_DRIVER_SERVER_EXE   = IE_DRIVER_SERVER_PATH + File.separator + "IEDriverServer.exe";
	private static final String IE_DRIVER_EXE_PROPERTY = InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY;

	@Test
	public void veriflyWord() throws IOException {

		// IEのWebDriverまでのパスをシステムプロパティに設定する
		System.setProperty(IE_DRIVER_EXE_PROPERTY, IE_DRIVER_SERVER_EXE);

		WebDriver webDriver;

		// InterenetExplorerDriver を使用する場合、セキュリティー問題が発生して、「保護モードをすべてのゾーンで同じ値 (有効または無効) に設定する必要があります」というメッセージが表示される可能性があります。
		// org.openqa.selenium.remote.SessionNotFoundExceptionを解消するには、下記の設定でDesiredCapabilitiesインスタンスを作成し、そのインスタンスをInternetExplorerDriverのコンストラクタに渡します。
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
		desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		webDriver = new InternetExplorerDriver(desiredCapabilities);

		webDriver.get("http://localhost:8080/webapp/");

//		// 画面のキャプチャを生成
//		File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(file, new File("img/login_page.jpg"));
//		webDriver.findElement(By.name("userId")).sendKeys("000000");
//		webDriver.findElement(By.name("fn")).click();
//		webDriver.findElement(By.linkText("yahoo")).click();

		assertEquals("Hello World! in Tomcat 7.0", webDriver.findElement(By.id("hello")).getText());
	}

}
