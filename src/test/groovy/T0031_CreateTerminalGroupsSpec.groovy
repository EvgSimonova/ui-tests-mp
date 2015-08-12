

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec


class T0031_CreateTerminalGroupsSpec extends GebReportingSpec {

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
        waitFor{at OwnerPersonalAccountPage}
        waitFor{terminalsListLink.displayed}
        waitFor{moneyLink.displayed}
		
		when:
		terminalsListLink.click()
		
		then:
		waitFor{at OwnerTerminalListPage}
		
		when:
		groupMenuButton.click()
		
		then:
		waitFor{createGroupLink.displayed}
		
		when:
		createGroupLink.click()
				
		then:
		waitFor{createGroupDialog.displayed}
		
		when:
		StaticData.setTerminalGroupName(groupNameInput)
		createNewGroupButton.click()
		driver.navigate().refresh();
		
		then:
		waitFor{group1Link.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{settingsLink.displayed}
		waitFor{logoutLink.displayed}
		waitFor{addTerminalButton.displayed}
		
		//Переименование группы
		when:
		terminalsListLink.click()
		
		then: 
		waitFor{at OwnerTerminalListPage}
		
		when:
		group1Link.click()
		
		then:
		waitFor{group1Link.displayed}
		waitFor{groupMenuButton.displayed}
		
		when:
		groupMenuButton.click()
		
		then:
		waitFor{renameGroupLink.displayed}
		
		when:
		renameGroupLink.click()
		
		then:
		waitFor{renameGroupDialog.displayed}
		waitFor{renameGroupNameInput.displayed}
		waitFor{renameGroupButton.displayed}
		
		when:
		StaticData.renameTerminalGroupName(renameGroupNameInput)
		renameGroupButton.click()
		driver.navigate().refresh();
		
		then:
		waitFor{renamedGroup1Link.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{settingsLink.displayed}
		waitFor{logoutLink.displayed}
		waitFor{addTerminalButton.displayed}
		
    }
	
}