

import com.terminal.pages.*
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

import java.text.SimpleDateFormat

class T008_CreateCompanyAsUserSpec extends GebReportingSpec {

    def "create Company as user"() {
        when:
        to MainPage
        at MainPage
        loginLink.click()

        then:
        waitFor{at MainPage}
        waitFor {
            loginDialog.displayed
        }

        when:

        StaticData.setUserName(usernameInputOnLoginForm)
		StaticData.setUserPassword(passwordInputOnLoginForm)
        loginButton.click()

        then:
		waitFor{at UserPersonalAccountPage}
		waitFor{createCompanyLink.displayed}
		waitFor{myPicturesLink.displayed}
		waitFor{createLink.displayed}
		waitFor{createCompanyLink.displayed}
		
		when:
		createCompanyLink.click()
		
		then:
		waitFor{at AddCampaingParamsPage}
		waitFor{campaignNameInput.displayed}
		waitFor{campaignStartdateInput.displayed}
		waitFor{campaignStartTimeInput.displayed}
		waitFor{campaignEnddateInput.displayed}
		waitFor{campaignEndTimeInput.displayed}
		waitFor{campaignEndTimeAddOn.displayed}
		waitFor{nextButton.displayed}
		
		when:
		campaignNameInput <<  "Тестовая кампания " +new SimpleDateFormat("dd.MM.yyyyHH:mm:ss").format(new Date())
		campaignStartdateInput.click()
		campaignStartTimeInput.click()
		campaignStartTimeUp.click()
		campaignEnddateInput.click()
		campaignEndDateSelector.click()
		campaignEndTimeInput.click()
		campaignEndTimeAddOn.click()
		campaignEndTimeUp.click()
		nextButton.click()
		
		then:
		waitFor{at AddCampaingImagePage}
		waitFor{imageUploadForm.displayed}
		waitFor{uploadImgDiv.displayed}

		when:
		driver.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
		driver.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
		driver.executeScript("document.getElementById('multipartFile').removeAttribute('style');")
			
		then:
		waitFor{fileNameSpan.displayed}
		
		when:
		try{
			fileNameSpan << "D:\\MyFiles\\myImg.jpg"
		}catch(Exception e){
			println e
		}
		
		then:
		waitFor{uploadImgDiv.displayed}
		waitFor{imgContainer.displayed}
		waitFor{bottomButtons.displayed}
		waitFor{nextButton.displayed}
		waitFor{imageLi.displayed}
		
		when:
		imageLi.click()

		then:
		waitFor{imageLi.displayed}
		waitFor{bottomButtons.displayed}
		
		
		when:
		addImageToCampaignBtn.click()
	
		then:
		waitFor{uploadImgDiv.displayed}
		waitFor{imgContainer.displayed}
		waitFor{bottomButtons.displayed}
		waitFor{nextButton.displayed}
		waitFor{imageLi.displayed}
		
		when:
		nextButton.click()
		
		then:
		waitFor{at AddCampaingTerminalPage}
		waitFor{addressInput.displayed}
		//waitFor{adressDistanceCheckbox.displayed}
		
		when:
		addressInput << "Москва"
		addressInput << Keys.chord(Keys.ENTER)		
		
		then:
		//waitFor{addressInput.displayed}
		waitFor{adressDistanceCheckbox.displayed}
		
		when:
		adressDistanceCheckbox.click()

		then:
		//adressDistanceCheckbox.displayed
		waitFor{addressDistanceInput.displayed}
		
		when:
		addressDistanceInput << "100"
		//addressInput << Keys.chord(Keys.ENTER)
		addressDistanceInput << Keys.chord(Keys.ENTER)

		then:
		waitFor{adressDistanceCheckbox.displayed}
		waitFor{addressDistanceInput.displayed}
		waitFor{addressInput.displayed}
		waitFor{mapCanvas.displayed}
		waitFor{terminalsContainer.displayed}
		waitFor{terminalPane.displayed}
		waitFor{allTerminalDiv.displayed}
		
		when:
		//terminalPane.click()
		allTerminalDiv.click()
		
		then:
		waitFor{terminalsContainer.displayed}
		waitFor{terminalPane.displayed}
		waitFor{addTerminalToCompanyDutton.displayed}
		
		when:
		addTerminalToCompanyDutton.click()
		
		then:
		waitFor{terminalsContainer.displayed}
		waitFor{terminalPane.displayed}
		waitFor{addTerminalToCompanyDutton.displayed}
		waitFor{addedToCampaignTab.displayed}
		
		when:
		addedToCampaignTab.click()
		
		then:
		waitFor{addedToCampaignTab.displayed}
		waitFor{bottomButtons.displayed}
		waitFor{nextButton.displayed}
		
		when:
		nextButton.click()
		
		then:
		waitFor{at DemoCreateCompanyCheckAndConfirmPage}
		waitFor{ bottomButtons.displayed}
		waitFor{createCampaignButton.displayed}
		
		when:
		createCampaignButton.click()
		
		then:
		waitFor{at DemoCreateCompanyStartCompanyPage}
		waitFor{header.displayed}
		
		
		
		
		
		
    }
}