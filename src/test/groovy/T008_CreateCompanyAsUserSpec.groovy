
import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.AddCampaingParamsPage
import com.terminal.pages.AddCampaingImagePage
import com.terminal.pages.AddCampaingTerminalPage
import com.terminal.pages.DemoCreateCompanyCheckAndConfirmPage
import com.terminal.pages.DemoCreateCompanyStartCompanyPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat
import org.openqa.selenium.Keys
import java.util.Date.*
import java.util.concurrent.TimeUnit


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
		campaignNameInput <<  "Тестовая кампания " +new SimpleDateFormat("dd.MM.yyyyHH:mm:ss").format(new Date())
		campaignStartdateInput << new SimpleDateFormat("dd/MM/yyyy").format(new Date((new Date()).getTime()+ TimeUnit.DAYS.toMillis(2)))
		campaignStartTimeInput << "10:00:00"
		campaignEnddateInput << new SimpleDateFormat("dd/MM/yyyy").format(new Date((new Date()).getTime()+ TimeUnit.DAYS.toMillis(3)))
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
		waitFor{allTerminalDiv.displayed}
		
		when:
		//terminalPane.click()
		allTerminalDiv.click()
		
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
		
		when:
		addedToCampaignTab.click()
		
		then:
		addedToCampaignTab.displayed
		bottomButtons.displayed
		nextButton.displayed
		
		when:
		nextButton.click()
		
		then:
		waitFor{at DemoCreateCompanyCheckAndConfirmPage}
		waitFor{createCampaignButton.displayed}
		
		when:
		createCampaignButton.click()
		
		then:
		waitFor{at DemoCreateCompanyStartCompanyPage}
		waitFor{header.displayed}
		
		
		
		
		
		
    }
}