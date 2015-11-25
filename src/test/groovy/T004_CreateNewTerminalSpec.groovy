
import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

import java.util.concurrent.TimeUnit

class T004_CreateNewTerminalSpec extends GebReportingSpec {

    def "can get to OwnerTerminalListPage and create new Terminal"() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
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

		//check for empty fields
		then:
		waitFor{createTerminalHolder.displayed}
		waitFor{createTerminalDialog.displayed}
		waitFor{createTerminalForm.displayed}
		waitFor{saveTerminalButton.displayed}

		when:
		saveTerminalButton.click()

		then:
		waitFor{createTerminalHolder.displayed}
		waitFor{createTerminalDialog.displayed}
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Координаты, Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Наименование, Описание, Режим работы, Операционная система, Адрес, введено(ы) неверно"}
		//check only filled address
		waitFor{terminalAddressInput.displayed}

		when:
		StaticData.setTerminalAddress(terminalAddressInput)
		findTerminalOnMapButton.click()
		showMapLink.click()

		then:
		waitFor{showMapDialog.displayed}
		waitFor{saveTerminalButton.displayed}

		when:
		closeMapDialogLink.click()
		//withAlert(wait: true) { saveTerminalButton.click() } == "необходимо ввести цену терминала"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Наименование, Описание, Режим работы, Операционная система, введено(ы) неверно"}

		//check only filled address and name
		when:
		StaticData.setTerminalName(terminalNameInput)
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Описание, Режим работы, Операционная система, введено(ы) неверно"}

		//check only filled address and name, description
		when:
		StaticData.setTerminalDescription(terminalDescriptionTextarea)
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{terminalGroupSelect.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//terminal group
		when:
		terminalGroupSelect.click()

		then:
		waitFor{terminalGroupSelectInactive.displayed}
		waitFor{terminalGroupSelectActive.displayed}

		when:
		if (terminalGroupSelectIndex == "0") {
			terminalGroupSelectActive.click()
		} else {
			terminalGroupSelectInactive.click()
		}

		then:
		waitFor {saveTerminalButton.displayed}

		when:
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{terminalGroupSelect.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}
		waitFor{terminalTypeSelect.displayed}

		//terminal type
		when:
		terminalTypeSelect.click()

		then:
		waitFor {terminalTypeSelectMonitor.displayed}
		waitFor {terminalTypeSelectSmartphone.displayed}
		waitFor {terminalTypeSelectATM.displayed}

		when:
		if (terminalTypeSelectIndex == '2') {
			terminalTypeSelectMonitor.click()
		} else {
			terminalTypeSelectSmartphone.click()
		}

		then:
		waitFor {saveTerminalButton.displayed}

		when:
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}
		waitFor{sliderLeft.displayed}
		waitFor{sliderRight.displayed}

		//the movement of the slider
		when:
		sliderLeft << Keys.ARROW_LEFT + Keys.ARROW_LEFT + Keys.ARROW_LEFT + Keys.ARROW_LEFT + Keys.ARROW_LEFT + Keys.ARROW_LEFT + Keys.ARROW_LEFT
		sliderRight << Keys.ARROW_RIGHT + Keys.ARROW_RIGHT + Keys.ARROW_RIGHT + Keys.ARROW_RIGHT + Keys.ARROW_RIGHT + Keys.ARROW_RIGHT + Keys.ARROW_RIGHT
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}
		waitFor{weekAudienceInput.displayed}

		//check outreach weekdays 1.the importance of outreach during the week
		when:
		weekAudienceInput << "999999999999999999999999999999999999999999999999999999999999999"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{weekAudienceInput.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в будние дни, Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//check outreach weekdays 2.an adequate value of outreach during the week
		when:
		weekAudienceInput << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		weekAudienceInput << "1000"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{weekendAudienceInput.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//test coverage on the weekend 1.the importance
		when:
		weekendAudienceInput << "999999999999999999999999999999999999999999999999999999999999999"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{weekendAudienceInput.displayed}
		waitFor{errorAlert.text() == "Охват аудитории в выходные дни, Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//test coverage on the weekend 2.an adequate value
		when:
		weekendAudienceInput << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		weekendAudienceInput << "23000"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{startWorkTimeInput.displayed}
		waitFor{errorAlert.text() == "Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//fill in the start time of the terminal
		when:
		startWorkTimeInput << "8:00"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{endWorkTimeInput.displayed}
		waitFor{errorAlert.text() == "Стоимоcть Размещения, Режим работы, Операционная система, введено(ы) неверно"}

		//fill in the end time of the terminal
		when:
		endWorkTimeInput << "23:00"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{operationSystemInput.displayed}
		waitFor{errorAlert.text() == "Стоимоcть Размещения, Операционная система, введено(ы) неверно"}

		//fill OS
		when:
		operationSystemInput << "Linux Mint"
		saveTerminalButton.click()

		then:
		waitFor{createTerminalForm.displayed}
		waitFor{errorAlert.displayed}
		waitFor{costInput.displayed}
		waitFor{errorAlert.text() == "Стоимоcть Размещения, введено(ы) неверно"}

		// The cost of accommodation
		when:
		audienceAgeBeginInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeBeginInput<< "3"
		audienceAgeEndInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeEndInput<< Keys.chord(Keys.BACK_SPACE)
		audienceAgeEndInput<< "83"
		costInput << "500"
		saveTerminalButton.click()

		then:
		waitFor{at OwnerTerminalListPage}
		//waitFor{terminalName.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{settingsLink.displayed}
		waitFor{logoutLink.displayed}
		waitFor{addTerminalButton.displayed}
		waitFor{terminalTable.displayed}

    }
	
	
}