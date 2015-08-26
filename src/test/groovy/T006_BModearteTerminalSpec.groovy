

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateTerminalPage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.Keys

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
		terminalAddressInput << Keys.chord(Keys.ENTER)
		
		then:
		waitFor{at AdminModerateTerminalPage}
		waitFor{terminalAddressInput.displayed}
		waitFor{terminalsTable.displayed}
		waitFor{findedTerminalNumberLink.displayed}
		
		when:
		findedTerminalNumberLink.click()
		
		then:
		waitFor{selectedTerminalInfo.displayed}
				
		when:
		selectedTerminaleditButton.click()
		
		then:
		waitFor{editTerminalDialog.displayed}
		
		when:
		try{
			terminalModerateStatus = "true"
		}catch(ElementNotVisibleException e){
			println e
		}	
		
		then:
		waitFor{editTerminalDialog.displayed}
		waitFor{saveTerminalButton.displayed}
		
		when:
		saveTerminalButton.click()
		
		then:
		waitFor{at AdminModerateTerminalPage}

    }
	
}