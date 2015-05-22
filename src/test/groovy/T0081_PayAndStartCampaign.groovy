
import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.CampaignListPage
import com.terminal.pages.DemoCreateCompanyStartCompanyPage
import geb.spock.GebReportingSpec

class T0081_PayAndStartCampaign extends GebReportingSpec {

    def "pay and start campaign from list of campaign"() {
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
		myCampaignsLink.displayed
		
		when:
		myCampaignsLink.click()
		
		then: 
		at CampaignListPage
		campaignsTable.displayed
		stateDiv.displayed
		/*
		when:
		payLink.click()
		
		then:
		at DemoCreateCompanyStartCompanyPage
		header.displayed
		*/
	
    }
}