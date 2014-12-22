/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/

import org.openqa.selenium.firefox.FirefoxDriver
import com.terminal.pages.StaticData

driver = { new FirefoxDriver() }

baseUrl = StaticData.getServerName()
