

import com.terminal.pages.MainPage
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.StaticData
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.UserCurrentCampaignPage
import com.terminal.pages.UserStatisticPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import java.text.SimpleDateFormat

class T104_CheckuserLinksSpec extends GebReportingSpec {

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
		createCompanyLink.displayed
		topBar.displayed;
		userNameLink.displayed
		logoutLink.displayed
		
		//check the create company link
		when:
		createCompanyLink.click()
		
		then:
		at DemoCreateCompanyPage
		addressFilterLink.displayed
		currentCampaignsLink.displayed
		
		//check the current campaign page
		when:
		currentCampaignsLink.click()
		
		then:
		at UserCurrentCampaignPage
		headText.displayed
		currentCampaignsLink.displayed
		dateFilterPane.displayed
		dateFromInput.displayed
		dateToInput.displayed
		applyDateFilterButon.displayed
		
		//check the statistic page
		when:
		statisticPageLink.click() 
		
		then:
		at UserStatisticPage
		headText.displayed
    }
}