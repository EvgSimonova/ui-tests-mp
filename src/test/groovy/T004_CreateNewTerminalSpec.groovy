

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat
import com.terminal.pages.StaticData

import org.openqa.selenium.Keys

class T004_CreateNewTerminalSpec extends GebReportingSpec {

    def "can get to OwnerTerminalListPage and create new Terminal"() {
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
		addTerminalButton.click()
		
		then:
		waitFor{createTerminalHolder.displayed}
		waitFor{createTerminalDialog.displayed}
		waitFor{createTerminalForm.displayed}
		waitFor{terminalAddressInput.displayed}
			
		
		when:
		StaticData.setTerminalAddress(terminalAddressInput)
		findTerminalOnMapButton.click()
		showMapLink.click()
		
		then:
		showMapDialog.displayed
		
		when:
		closeMapDialogLink.click()
		StaticData.setTerminalName(terminalNameInput)
		StaticData.setTerminalDescription(terminalDescriptionTextarea)
		//todo: добавить выбор группы
		audienceAgeBeginInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeBeginInput<< "3"
		audienceAgeEndInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeEndInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeEndInput<< "83"
		//todo: добавить движение ползунками возраста
		
		weekAudienceInput<< "1000"
		weekendAudienceInput<< "23000"
		startWorkTimeInput<< "8:00"
		endWorkTimeInput<< "23:00"
		operationSystemInput<< "Linux Mint"
		costInput<< "500"
		saveTerminalButton.click()
		
		then:
		at OwnerTerminalListPage
		terminalName.displayed
		terminalsListLink.displayed
		moneyLink.displayed
		settingsLink.displayed
		logoutLink.displayed
		addTerminalButton.displayed		
		
    }
	
	
}