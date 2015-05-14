
import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.AddCampaingParamsPage
import com.terminal.pages.AddCampaingImagePage
import com.terminal.pages.AddCampaingTerminalPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat
import org.openqa.selenium.Keys


class T008_CreateCompanyAsUserSpec extends GebReportingSpec {

    def "create Company as user"() {
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
		createCompanyLink.displayed
		
		when:
		createCompanyLink.click()
		
		then:
		at AddCampaingParamsPage
		campaignNameInput.displayed
		campaignStartdateInput.displayed
		campaignStartTimeInput.displayed
		campaignEnddateInput.displayed
		campaignEndTimeInput.displayed
		nextButton.displayed
		
		when:
		campaignNameInput <<  "Тестовая кампания " +new SimpleDateFormat("dd.MM.yyyy").format(new Date())
		campaignStartdateInput << new SimpleDateFormat("dd.MM.yyyy").format(new Date())
		campaignStartTimeInput << "10:00:00"
		campaignEnddateInput << new SimpleDateFormat("dd.MM.yyyy").format(new Date()+5)
		campaignEndTimeInput << "23:00:00"
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
		at AddCampaingTerminalPage
		addressInput.displayed
		adressDistanceCheckbox.displayed
		
		when:
		addressInput << "Москва"
		addressInput << Keys.chord(Keys.ENTER)		
		
		then:
		addressInput.displayed
		adressDistanceCheckbox.displayed
		
		when:
		adressDistanceCheckbox.click()

		then:
		adressDistanceCheckbox.displayed		
		addressDistanceInput.displayed		
		
		when:
		addressDistanceInput << "100"
		addressInput << Keys.chord(Keys.ENTER)

		then:		
		adressDistanceCheckbox.displayed		
		addressDistanceInput.displayed		
		waitFor{addressInput.displayed}
		waitFor{mapCanvas.displayed}
		waitFor{terminalsContainer.displayed}
		waitFor{terminalPane.displayed}
		
		when:
		terminalPane.click()
		
		then:
		terminalsContainer.displayed
		terminalPane.displayed
		addTerminalToCompanyDutton.displayed
		
		when:
		addTerminalToCompanyDutton.click()
		
		then:
		terminalsContainer.displayed
		terminalPane.displayed
		addTerminalToCompanyDutton.displayed
		addedToCampaignTab.displayed
		
		
		
		
		
    }
}