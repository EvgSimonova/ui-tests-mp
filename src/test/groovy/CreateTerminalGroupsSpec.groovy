

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat

class CreateTerminalGroupsSpec extends GebReportingSpec {

    def "can get to OwnerTerminalListPage and create new group"() {
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
		
		when:
		groupMenuButton.click()
		
		then:
		createGroupLink.displayed
		
		when:
		createGroupLink.click()
		
		then:
		createGroupDialog.displayed
		
		when:
		groupNameInput << "Группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())
		createNewGroupButton.click()
		
		then:
		group1Link.displayed
		terminalsListLink.displayed
		moneyLink.displayed
		statisticLink.displayed
		settingsLink.displayed
		logoutLink.displayed
		addTerminalButton.displayed
		
		
    }
	
}