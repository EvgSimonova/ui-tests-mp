
import com.terminal.pages.StaticData
import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.UserBalancePage
import com.terminal.pages.RobokassaPage
import geb.spock.GebReportingSpec

class T203_RechargeUserBalanceSpec extends GebReportingSpec {

  	def RechargeUserBalanceSucess() {
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
        StaticData.setUserName(usernameInputOnLoginForm)
		StaticData.setUserPassword(passwordInputOnLoginForm)
        loginButton.click()
		
		then:
		at UserPersonalAccountPage
		createCompanyLink.displayed
		myPicturesLink.displayed
		createLink.displayed
		
		when:
		balanceLink.click()
		
		then:
		at UserBalancePage
		robokassaForm.displayed
		outSumInput.displayed
		addMoneyButton.displayed
		
		when:
		outSumInput << "1000"
		addMoneyButton.click()
		
		then:
		waitFor{at RobokassaPage}
		waitFor{sucessUrl.displayed}
		waitFor{sucessButton.displayed}
		
		when:
		sucessButton.click()
		
		then:
		at UserBalancePage
		robokassaForm.displayed
		outSumInput.displayed
		addMoneyButton.displayed
		sucessBlock.displayed
    }

  	def RechargeUserBalanceFail() {
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
        StaticData.setUserName(usernameInputOnLoginForm)
		StaticData.setUserPassword(passwordInputOnLoginForm)
        loginButton.click()
		
		then:
		at UserPersonalAccountPage
		createCompanyLink.displayed
		myPicturesLink.displayed
		createLink.displayed
		
		when:
		balanceLink.click()
		
		then:
		at UserBalancePage
		robokassaForm.displayed
		outSumInput.displayed
		addMoneyButton.displayed
		
		when:
		outSumInput << "1000"
		addMoneyButton.click()
		
		then:
		waitFor{at RobokassaPage}
		waitFor{sucessUrl.displayed}
		waitFor{failUrl.displayed}
		waitFor{sucessButton.displayed}
		waitFor{failButton.displayed}
		
		when:
		failButton.click()
		
		then:
		at UserBalancePage
		robokassaForm.displayed
		outSumInput.displayed
		addMoneyButton.displayed
		errorBlock.displayed
    }	
	
}