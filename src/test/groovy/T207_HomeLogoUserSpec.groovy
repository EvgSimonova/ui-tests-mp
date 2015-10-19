

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.UserBalancePage
import com.terminal.pages.UserContentPage
import com.terminal.pages.UserCurrentCampaignPage
import com.terminal.pages.UserSettingsPage
import com.terminal.pages.UserStatisticPage
import com.terminal.pages.AddCampaingParamsPage
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;
import geb.*
import java.lang.*


class T207_HomeLogoUserSpec extends GebReportingSpec {
	def "click on the logo and go to main page"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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

	        //to main page from personal account
		then:
        	waitFor{at UserPersonalAccountPage}
		waitFor{logoLink.displayed}
        	
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
		
		//to main page from create company page
		when:
		AccountPageLink.click()
				
		then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}

		when
		createCompanyLink.click()

		then:
		waitFor{at AddCampaingParamsPage}
		waitFor{myPicturesLink.displayed}
		waitFor{logoLink.displayed}

		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
		
		//to main page from money page
		when:
		AccountPageLink.click()
		
		then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{myPicturesLink.displayed}

		when
		myCampaignsLink.click()

		then:
		waitFor{at UserCurrentCampaignPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{logoLink.displayed}
		
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}

		//to main page from settings page
		when:
		AccountPageLink.click()
		
		then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{myPicturesLink.displayed}

		when
		myPicturesLink.click()

		then:
		waitFor{at UserContentPage}
		waitFor{myCampaignsLink.displayed}
		waitFor{myPicturesLink.displayed}
		waitFor{logoLink.displayed}
		
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}

		//to main page from moneyLink page
		when:
		AccountPageLink.click()
		
		then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{balanceLink.displayed}

		when
		balanceLink.click()

		then:
		waitFor{at UserBalancePage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{logoLink.displayed}
		
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
			
		when:
		AccountPageLink.click()
		
		then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myCampaignsLink.displayed}
		waitFor{settingsLink.displayed}

		when
		settingsLink.click()

		then:
		waitFor{at UserSettingsPage}
		waitFor{myCampaignsLink.displayed}
		waitFor{myPicturesLink.displayed}
		waitFor{logoLink.displayed}
		
		when:
		logoLink.click()
		
		then:
		waitFor{at MainPage}
		waitFor{AccountPageLink.displayed}
		waitFor{logoutLink.displayed}
	}
}