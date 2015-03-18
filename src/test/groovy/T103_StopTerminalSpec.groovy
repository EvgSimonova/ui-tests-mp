

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat

import org.openqa.selenium.Keys

class T103_StopTerminalSpec extends GebReportingSpec {

    def "can get to OwnerTerminalListPage and stop a Terminal"() {
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
        moneyLink.displayed
		
		when:
		terminalsListLink.click()
		
		then:
		at OwnerTerminalListPage
		addTerminalButton.displayed
		
		
		when:
		stopTerminalLink.click()
		
		then:
		stopTerminalDialog.displayed
		
		when:
		acceptStopTerminalButton.click()
		
		then:
		runTerminalLink.displayed
		
    }
	
	
}