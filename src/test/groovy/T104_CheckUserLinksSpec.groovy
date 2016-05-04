

import com.terminal.pages.MainPage
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.StaticData
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.UserCurrentCampaignPage
import com.terminal.pages.UserStatisticPage
import com.terminal.pages.UserBalancePage
import com.terminal.pages.UserSettingsPage
import com.terminal.pages.UserContentPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import java.text.SimpleDateFormat

class T104_CheckUserLinksSpec extends GebReportingSpec {

    def "login as owner and check all links in client cabinet"() {
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
        StaticData.setUserName(usernameInputOnLoginForm)
		StaticData.setUserPassword(passwordInputOnLoginForm)
        loginButton.click()

        then:
        at UserPersonalAccountPage
        createCompanyLink.displayed
        myPicturesLink.displayed
        createLink.displayed
		topBar.displayed;
		userNameLink.displayed
		logoutLink.displayed
		
		//check the username link
		when:
		userNameLink.click()
		
		then:
        createCompanyLink.displayed
        myPicturesLink.displayed
        createLink.displayed
		topBar.displayed;
		userNameLink.displayed
		logoutLink.displayed
		
		//check the create company link
		when:
		createCompanyLink.click()
		
		then:
		at DemoCreateCompanyPage
		currentCampaignsLink.displayed
		
		//check the current campaign page
		when:
		currentCampaignsLink.click()
		
		then:
		waitFor{at UserCurrentCampaignPage}
		waitFor{headText.displayed}
		waitFor{currentCampaignsLink.displayed}
		
		//check the balance page
		when:
		balanceLink.click()
		
		then:
		at UserBalancePage
		headText.displayed
		settingsLink.displayed
		addMoneyButton.displayed
		
		//check the settings page
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		headText.displayed
		passwordForm.displayed
		newPasswordInput.displayed
		confirmPasswordInput.displayed
		oldPasswordInput.displayed
		savePasswordButton.displayed
		contentLink.displayed
		
		when:
		contentLink.click()
		
		then:
		at UserContentPage
		logoutLink.displayed
		conentList.displayed
		
    }
}