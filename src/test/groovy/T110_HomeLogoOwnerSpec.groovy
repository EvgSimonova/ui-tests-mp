

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.OwnerBalancePage
import com.terminal.pages.OwnerSettingsPage
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;
import geb.*
import java.lang.*



class T110_HomeLogoOwnerSpec extends GebReportingSpec {
	def "click on the logo and go to main page"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        	when:
        	to MainPage
	        at MainPage
        	loginLink.click()

	        then:
        	at MainPage
	        waitFor {
        		loginDialog.displayed
	        	}

        	when:
		StaticData.setOwnerName(usernameInputOnLoginForm)
		StaticData.setOwnerPassword(passwordInputOnLoginForm)
        	loginButton.click()

 		//to main page from logo on terminals page
		then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{terminalsListLink.displayed}
		
		when:
		terminalsListLink.click()
		
		then:
		waitFor{at OwnerTerminalListPage}
		waitFor{logoLink.displayed}
        	
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
		
		//to main page from logo on balance page
		when:
		AccountPageLink.click()

	        then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{moneyLink.displayed}
        	
		when:
		moneyLink.click()
		
		then:
		waitFor{at OwnerBalancePage}
		waitFor{logoLink.displayed}
        	
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}

		//to main page from logo on settings page
		when:
		AccountPageLink.click()

		then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{settingsLink.displayed}
        	
		when:
		settingsLink.click()
		
		then:
		waitFor{at OwnerSettingsPage}
		waitFor{logoLink.displayed}
        	
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}

		//to main page from logo on personal account page
		when:
		AccountPageLink.click()

		then:
        	waitFor{at OwnerPersonalAccountPage}
        	waitFor{logoLink.displayed}
		
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
	}
}