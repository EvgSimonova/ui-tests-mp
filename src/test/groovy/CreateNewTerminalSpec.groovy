

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat

import org.openqa.selenium.Keys

class CreateNewTerminalSpec extends GebReportingSpec {

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
        usernameInputOnLoginForm << "mihailov-ta+spam44@ya.ru"
        passwordInputOnLoginForm << "123"
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
		createTerminalHolder.displayed
		createTerminalDialog.displayed
		createTerminalForm.displayed
			
		
		when:
		terminalAddressInput<< "Клязьма"
		findTerminalOnMapButton.click()
		showMapLink.click()
		
		then:
		//showMapHolder.displayed
		showMapDialog.displayed
		
		when:
		closeMapDialogLink.click()
		terminalNameInput<< "Новый терминал для тестирования 1"
		terminalDescriptionTextarea<< "Терминал находится нв первом этаже справа от входа. В форме кубика рубика, на каждой грани установлена одна плазма"
		//todo: добавить выбор группы
		terminalTypeSelect="1"
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
		statisticLink.displayed
		settingsLink.displayed
		logoutLink.displayed
		addTerminalButton.displayed		
		
    }
	
	
}