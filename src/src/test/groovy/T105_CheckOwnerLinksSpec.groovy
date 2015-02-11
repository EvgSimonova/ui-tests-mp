

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.OwnerBalancePage



class T105_CheckOwnerLinksSpec extends GebReportingSpec {

    def "login as owner and check all links in the cabinet"() {
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
		terminalsListLink.displayed
		
		//check owner terminal list page
		when:
		terminalsListLink.click()

		then:
		at OwnerTerminalListPage
		addTerminalButton.displayed
		groupMenuButton.displayed
		balancePageLink.displayed
		
		//check owner balance page
		when:
		balancePageLink.click()
		
		then:
		at OwnerBalancePage
		dateBlock.displayed
    }
}