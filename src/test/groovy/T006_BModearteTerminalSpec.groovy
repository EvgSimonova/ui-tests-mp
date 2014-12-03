

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateTerminalPage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData

class T006_BModerateTerminalSpec extends GebReportingSpec {
	//todo: Реализовать вызов метода для логина администратора
  	def "can get to adminLogin page and Login as admin"() {
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
		terminalAddressInput.displayed
        		
		when:
		StaticData.setTerminalAddress(terminalAddressInput)
		//terminalAddressInput<< "Клязьма"
		searchTerminalButton.click()
		
		then:
		at AdminModerateTerminalPage
		terminalAddressInput.displayed
		terminalsTable.displayed
		findedTerminalAddress.displayed
		findedTerminalNumberLink.displayed
		
		when:
		findedTerminalNumberLink.click()
		
		then:
		selectedTerminalInfo.displayed
				
		when:
		selectedTerminaleditButton.click()
		
		then:
		editTerminalDialog.displayed
		
		when:
		terminalModerateStatus = "true"
		
		then:
		editTerminalDialog.displayed
		saveTerminalButton.displayed
		
		when:
		saveTerminalButton.click()
		
		then:
		at AdminModerateTerminalPage

    }
	
}