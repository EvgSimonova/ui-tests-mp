

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerBalancePage
import com.terminal.pages.OwnerCashoutRequestPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys


class T108_RechargeOwnerBalanceSpec extends GebReportingSpec {
	def "can get to settings page and withdrawal of the terminal owner"() {

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
        	at OwnerPersonalAccountPage
		moneyLink.displayed
        	
		when:
		moneyLink.click()
		
		then:
		at OwnerBalancePage
		balanceBoxBottom.displayed
		
		when:
		moneyButton.click()
		
		then:
		balanceBox.displayed
		
		when:
		moneyAmount << "10"
		
		then:
		btnRequest.click()
		waitFor{errorBlock.displayed}
		waitFor{errorBlock.text() == "Ошибка. Запрошенная сумма слишком велика."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and a withdrawal more than the balance of the owner of the terminal"() {

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
        	at OwnerPersonalAccountPage
		moneyLink.displayed
        	
		when:
		moneyLink.click()
		
		then:
		at OwnerBalancePage
		balanceBoxBottom.displayed
		
		when:
		moneyButton.click()
		
		then:
		balanceBox.displayed
		
		when:
		moneyAmount << "100"
		
		then:
		btnRequest.click()
		waitFor{errorBlock.displayed}
		waitFor{errorBlock.text() == "Ошибка. Запрошенная сумма слишком велика."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and a withdrawal balance is less than the owner of the terminal"() {

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
        	at OwnerPersonalAccountPage
		moneyLink.displayed
        	
		when:
		moneyLink.click()
		
		then:
		at OwnerBalancePage
		balanceBoxBottom.displayed
		
		when:
		moneyButton.click()
		
		then:
		balanceBox.displayed
		
		when:
		moneyAmount << "2"
		
		then:
		btnRequest.click()
		waitFor{sucessBlock.displayed}
		waitFor{sucessBlock.text() == "Операция прошла успешно."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and Request to withdraw funds empty values ​​owner of the terminal"() {

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
        	at OwnerPersonalAccountPage
		moneyLink.displayed
        	
		when:
		moneyLink.click()
		
		then:
		at OwnerBalancePage
		balanceBoxBottom.displayed
		
		when:
		moneyButton.click()
		
		then:
		balanceBox.displayed
		
		when:
		moneyAmount.displayed
		btnRequest.click()
		
		then:
		waitFor{at OwnerCashoutRequestPage}
		waitFor{errorPage.displayed}
		waitFor{headText.text() == "Внутренняя ошибка на сервере"}
		waitFor{openSupportLink.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
}