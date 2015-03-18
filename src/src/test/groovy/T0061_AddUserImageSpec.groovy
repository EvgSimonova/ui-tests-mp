

import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateTerminalPage
import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.UserContentPage
import geb.spock.GebReportingSpec
import com.terminal.pages.StaticData
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.StaleElementReferenceException

class T0061_AddUserImageSpec extends GebReportingSpec {
    def "addUserContent"() {
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
        waitFor{at UserPersonalAccountPage}
        waitFor{createCompanyLink.displayed}
        waitFor{myPicturesLink.displayed}
        waitFor{createLink.displayed}
		waitFor{topBar.displayed}
		waitFor{userNameLink.displayed}
		waitFor{logoutLink.displayed}
		
		when:
		myPicturesLink.click()
		
		then:
		waitFor{at UserContentPage}
		
		when:
		driver.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
		driver.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
		driver.executeScript("document.getElementById('multipartFile').removeAttribute('style');")
			
		then:
		waitFor{fileNameSpan.displayed}
		
		when:
		try{
			fileNameSpan << "D:\\MyFiles\\myImg.jpg"
		}catch(WebDriverException e){
			println e
		}catch (ElementNotVisibleException e){
			println e
		}
		
		then:
		waitFor{at UserContentPage}
		waitFor{conentList.displayed}
		waitFor{contentListImg.displayed}
		
		when:
		try{
			contentListImg.click()
		}catch(StaleElementReferenceException e){
			println e
		}
		
		then:
		waitFor{at UserContentPage}
		waitFor{conentList.displayed}
		waitFor{contentListImg.displayed}
		
    }	
}