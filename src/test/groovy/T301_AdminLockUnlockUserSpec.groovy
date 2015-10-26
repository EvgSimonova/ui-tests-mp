

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec

class T301_AdminLockUnlockUserSpec extends GebReportingSpec {

  	def "can get to adminLogin page and block user"() {
        
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
	waitFor{settingsRightUserLink.displayed}

    }
}