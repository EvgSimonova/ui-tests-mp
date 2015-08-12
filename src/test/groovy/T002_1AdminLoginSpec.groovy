

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec

class T002_1AdminLoginSpec extends GebReportingSpec {

  	def adminLogin() {
        when:
        to AdminLoginPage
        at AdminLoginPage

        then:
        waitFor{usernameInput.displayed}
		waitFor{passwordInput.displayed}
		waitFor{loginButton.displayed}

        when:
		StaticData.setAdminName(usernameInput)
		StaticData.setAdminPassword(passwordInput)
        loginButton.click()

        then:
        waitFor{at AdminPersonalAccountPage}
		waitFor{logoutButton.displayed}
		waitFor{terminalModreationLink.displayed}
		waitFor{imageModerationLink.displayed}
		waitFor{campaignModerationLink.displayed}
    }
}