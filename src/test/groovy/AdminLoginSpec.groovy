

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import geb.spock.GebReportingSpec

class AdminLoginSpec extends GebReportingSpec {

  	def "can get to adminLogin page and login as admin"() {
        when:
        to AdminLoginPage
        at AdminLoginPage

        then:
        usernameInput.displayed
		passwordInput.displayed
		loginButton.displayed

        when:
        usernameInput << "1@1.1"
        passwordInput << "111"
        loginButton.click()

        then:
        at AdminPersonalAccountPage
		logoutButton.displayed
    }
}