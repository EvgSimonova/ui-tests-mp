
import com.terminal.pages.MainPage
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.AddCampaingImagePage
import com.terminal.pages.AddCampaingParamsPage
import com.terminal.pages.StaticData
import com.terminal.utils.*
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;
import geb.*
import java.lang.*
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.*
import java.text.SimpleDateFormat

class T007_CreateCompanySpec extends GebReportingSpec {

    def "create Company in Demo mode"() {
        when:
        to MainPage
        at MainPage
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        demoModeLink.click()

		then:
		waitFor{at AddCampaingParamsPage}
		waitFor{campaignNameInput.displayed}
		waitFor{campaignStartdateInput.displayed}
		waitFor{campaignStartTimeInput.displayed}
		waitFor{campaignEnddateInput.displayed}
		waitFor{campaignEndTimeInput.displayed}
		waitFor{userEmail.displayed}
		waitFor{recaptchaDiv.displayed}
		waitFor{iframe.displayed}
		withFrame(myFrame){
			waitFor{captchaCheckbox.displayed}
		}
		
		when:
		StaticData.setCampaingName(campaignNameInput)
		StaticData.setCampaignStartDate(campaignStartdateInput)
		StaticData.setCampaignEndDate(campaignEnddateInput)
		StaticData.setCampaignEndTime(campaignEndTimeInput)
		StaticData.setUserEmail(userEmail)
		withFrame(myFrame){
			captchaCheckbox.click()
		}
		
		
		
		then:
		waitFor{at AddCampaingParamsPage}
		waitFor{campaignNameInput.displayed}
		
		
		/*
		then:
        waitFor{at DemoCreateCompanyPage}
        waitFor{addressFilterLink.displayed}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		when:
		addressFilterLink.click()
				
		then:
		waitFor{at DemoCreateCompanyPage}
		waitFor{addressFilterPane.displayed}
		waitFor{addressInput.displayed}
		waitFor{adressDistanceCheckbox.displayed}
		waitFor{mapCanvas.displayed}
		
		when:
		addressInput << "Москва"
		addressInput << Keys.chord(Keys.ENTER)
		
		then:
		addressFilterPane.displayed
		addressInput.displayed
		mapCanvas.displayed
		adressDistanceCheckbox.displayed
		
		when: 
		adressDistanceCheckbox.click()
		
		then:
		addressInput.displayed
		addressDistanceInput.displayed
		adressDistanceCheckbox.displayed
		mapCanvas.displayed
		
		when:
		addressDistanceInput << "50"
		addressDistanceInput << Keys.chord(Keys.ENTER)
		
		then:
		waitFor{addressInput.displayed}
		waitFor{addressDistanceInput.displayed}
		waitFor{adressDistanceCheckbox.displayed}
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
		
		when:
		addedToCampaignTab.click()
		
		then:
		addedToCampaignTab.displayed
		bottomButtons.displayed
		nextButton.displayed
		
		when:
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

		*/
		
    }
}