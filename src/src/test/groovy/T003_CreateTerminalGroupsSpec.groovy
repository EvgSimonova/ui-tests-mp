

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec


class T003_CreateTerminalGroupsSpec extends GebReportingSpec {

    def "can get to OwnerTerminalListPage create new group and rename it"() {
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
		
		when:
		groupMenuButton.click()
		
		then:
		createGroupLink.displayed
		
		when:
		createGroupLink.click()
				
		then:
		createGroupDialog.displayed
		
		when:
		StaticData.setTerminalGroupName(groupNameInput)
		createNewGroupButton.click()
		driver.navigate().refresh();
		
		then:
		group1Link.displayed
		terminalsListLink.displayed
		moneyLink.displayed
		statisticLink.displayed
		settingsLink.displayed
		logoutLink.displayed
		addTerminalButton.displayed
		
		//Переименование группы
		when:
		terminalsListLink.click()
		
		then: 
		at OwnerTerminalListPage
		
		when:
		group1Link.click()
		
		then:
		group1Link.displayed
		groupMenuButton.displayed
		
		when:
		groupMenuButton.click()
		
		then:
		renameGroupLink.displayed
		
		when:
		renameGroupLink.click()
		
		then:
		renameGroupDialog.displayed
		renameGroupNameInput.displayed
		renameGroupButton.displayed
		
		when:
		StaticData.renameTerminalGroupName(renameGroupNameInput)
		renameGroupButton.click()
		driver.navigate().refresh();
		
		then:
		renamedGroup1Link.displayed
		terminalsListLink.displayed
		moneyLink.displayed
		statisticLink.displayed
		settingsLink.displayed
		logoutLink.displayed
		addTerminalButton.displayed
		
    }
	
}