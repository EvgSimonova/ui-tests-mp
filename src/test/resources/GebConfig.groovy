/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/


import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
//import org.openqa.selenium.ie.InternetExplorerDriver
import com.terminal.pages.StaticData

driver = { new FirefoxDriver() }
//driver = { new ChromeDriver() }
//driver = { new InternetExplorerDriver() }


baseUrl = StaticData.getServerName()
