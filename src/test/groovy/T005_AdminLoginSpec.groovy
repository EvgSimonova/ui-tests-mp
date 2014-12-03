

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec

class T005_AdminLoginSpec extends GebReportingSpec {

  	def "can get to adminLogin page and login as admin"() {
        when:
        to AdminLoginPage
        at AdminLoginPage

        then:
        usernameInput.displayed
		passwordInput.displayed
		loginButton.displayed

        when:
		StaticData.setAdminName(usernameInput)
		StaticData.setAdminPassword(passwordInput)
        loginButton.click()

        then:
        at AdminPersonalAccountPage
		logoutButton.displayed
    }
}