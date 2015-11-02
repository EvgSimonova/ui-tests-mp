import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
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
		waitFor{at OwnerPersonalAccountPage}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}

		when:
		terminalsListLink.click()

		then:
		waitFor{at OwnerTerminalListPage}
		waitFor{addTerminalButton.displayed}


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
		waitFor{showMapDialog.displayed}

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
		waitFor{at OwnerTerminalListPage}
		waitFor{terminalName.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{settingsLink.displayed}
		waitFor{logoutLink.displayed}
		waitFor{addTerminalButton.displayed}

	}


}