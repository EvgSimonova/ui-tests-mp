

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerBalancePage
import com.terminal.pages.OwnerCashoutRequestPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;
import geb.*
import java.lang.*


class T108_RechargeOwnerBalanceSpec extends GebReportingSpec {
	def "can get to settings page and withdrawal of the terminal owner"() {
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

	        then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{moneyLink.displayed}
        	
		when:
		moneyLink.click()
		
		then:
		waitFor{at OwnerBalancePage}
		waitFor{balanceBoxBottom.displayed}
		
		when:
		moneyButton.click()
		
		then:
		waitFor{balanceBox.displayed}
		
		when:
		moneyAmount << "10"
		btnRequest.click()
		
		then:
		waitFor{errorBlock.displayed}
		waitFor{errorBlock.text() == "Ошибка. Запрошенная сумма слишком велика."}
		waitFor{terminalsListLink.displayed}
		waitFor{settingsLink.displayed}
	}
	def "can get to settings page and a withdrawal more than the balance of the owner of the terminal"() {
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

	        then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{moneyLink.displayed}
        	
		when:
		moneyLink.click()
		
		then:
		waitFor{at OwnerBalancePage}
		waitFor{balanceBoxBottom.displayed}
		
		when:
		moneyButton.click()
		
		then:
		waitFor{balanceBox.displayed}
		
		when:
		moneyAmount << "100"
		
		then:
		waitFor{btnRequest.click()}
		waitFor{errorBlock.displayed}
		waitFor{errorBlock.text() == "Ошибка. Запрошенная сумма слишком велика."}
		waitFor{terminalsListLink.displayed}
		waitFor{settingsLink.displayed}
	}
	def "can get to settings page and a withdrawal balance is less than the owner of the terminal"() {
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

	        then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{moneyLink.displayed}
        	
		when:
		moneyLink.click()
		
		then:
		waitFor{at OwnerBalancePage}
		waitFor{balanceBoxBottom.displayed}
		
		when:
		moneyButton.click()
		
		then:
		waitFor{balanceBox.displayed}
		
		when:
		moneyAmount << "2"
		
		then:
		waitFor{btnRequest.click()}
		waitFor{sucessBlock.displayed}
		waitFor{sucessBlock.text() == "Операция прошла успешно."}
		waitFor{terminalsListLink.displayed}
		waitFor{settingsLink.displayed}
	}
	def "can get to settings page and Request to withdraw funds empty values ​​owner of the terminal"() {
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

	        then:
        	waitFor{at OwnerPersonalAccountPage}
		waitFor{moneyLink.displayed}
        	
		when:
		moneyLink.click()
		
		then:
		waitFor{at OwnerBalancePage}
		waitFor{balanceBoxBottom.displayed}
		
		when:
		moneyButton.click()
		
		then:
		waitFor{balanceBox.displayed}
		
		when:
		moneyAmount.displayed
		btnRequest.click()
		
		then:
		waitFor{at OwnerCashoutRequestPage}
		waitFor{errorPage.displayed}
		waitFor{headText.text() == "Внутренняя ошибка на сервере"}
		waitFor{openSupportLink.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{settingsLink.displayed}
	}
}