
import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateImagePage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData

class T201_ModerateImageSpec extends GebReportingSpec {

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
		imageModerationLink.displayed
		
		when:
		imageModerationLink.click()
		
		then:
		at AdminModerateImagePage
		imageModerationLink.displayed
		contentTable.displayed
		firstRow.displayed
		imgIdLink.displayed
		
		when:
		imgIdLink.click()
		
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
		}catch(Exception e){
		 println e
		}
		
		then:
        moderationForm.displayed
		moderatinoStatusSelect.displayed
		saveButton.displayed
		
		when:
		saveButton.click()
		
		then:
		imageModerationLink.displayed
		contentTable.displayed
		firstRow.displayed
		imgIdLink.displayed

		
		
		
    }
}