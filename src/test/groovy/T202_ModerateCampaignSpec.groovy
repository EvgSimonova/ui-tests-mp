
import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateCampaignPage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData
import org.openqa.selenium.ElementNotVisibleException

class T202_ModerateCampaignSpec extends GebReportingSpec {

    def "Moderate image"() {
        when:
        to AdminLoginPage
        at AdminLoginPage

        then:
        usernameInput.displayed
		passwordInput.displayed
		loginButton.displayed

        when:
		StaticData.setAdminName(usernameInput)
		StaticData.setAdminPassword(passwordInput)
        loginButton.click()

        then:
        at AdminPersonalAccountPage
		campaignModerationLink.displayed
		
		when:
		campaignModerationLink.click()
		
		then:
		at AdminModerateCampaignPage
		campaignModerationLink.displayed
		contentTable.displayed
		firstRow.displayed
		campaignNamedLink.displayed
		
		when:
		campaignNamedLink.click()
		
		then:
        //waitFor{moderationForm.displayed}
		waitFor{moderatinoStatusSelect.displayed}
		waitFor{saveButton.displayed}
		
		when:
		moderatinoStatusSelect.click()
		
		then:
		moderatinoStatusSelect.displayed
		
		when:
		try{moderatinoStatusSelect="TRUE"
		}catch(ElementNotVisibleException e){
		 println e
		}
		
		then:
        moderationForm.displayed
		moderatinoStatusSelect.displayed
		saveButton.displayed
		
		when:
		saveButton.click()
		
		then:
		campaignModerationLink.displayed
		contentTable.displayed
		firstRow.displayed
		campaignNamedLink.displayed

		
		
		
    }
}