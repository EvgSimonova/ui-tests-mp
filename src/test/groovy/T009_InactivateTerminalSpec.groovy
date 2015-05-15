

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateTerminalPage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData
import org.openqa.selenium.ElementNotVisibleException

class T009_InactivateTerminalSpec extends GebReportingSpec {
  	def "can get to adminLogin page and inactivate a Terminal"() {
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
  	
        when:
		terminalModreationLink.click()

        then:
		at AdminModerateTerminalPage
		waitFor{at AdminModerateTerminalPage}
		waitFor{terminalAddressInput.displayed}
		waitFor{terminalsTable.displayed}
		waitFor{findedTerminalAddress.displayed}
		waitFor{findedTerminalNumberLink.displayed}
		
		when:
		findedTerminalNumberLink.click()
		
		then:
		selectedTerminalInfo.displayed
				
		when:
		selectedTerminaleditButton.click()
		
		then:
		editTerminalDialog.displayed
		
		when:
		try{
			terminalStatus = "INACTIVE"
		}catch(ElementNotVisibleException e){
			println e
		}	
		
		then:
		editTerminalDialog.displayed
		saveTerminalButton.displayed
		
		when:
		saveTerminalButton.click()
		
		then:
		at AdminModerateTerminalPage
		inactivatedTerminalStatus.displayed
		inactivatedTerminalStatus.text()=='INACTIVE'

    }
	
}