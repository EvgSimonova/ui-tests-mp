

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import com.terminal.pages.UserContentPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriverException

import java.util.concurrent.TimeUnit

class T208_RenameUserContantSpec extends GebReportingSpec {
	def "can get to contant page and change the name of the picture"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
			waitFor{myPicturesLink.displayed}
    	
		when:
		myPicturesLink.click()

		then:
		waitFor{at UserContentPage}
		waitFor{numberImages.displayed}
		waitFor{conentList.displayed}

		//For available images
		if (numberImages.text() != "0"){
			waitFor{firstImage.displayed}
			
			when:
			firstImage.click()
					
			then:
			waitFor{nameImage.displayed}

			when:
			nameImage.click()

			then:
			waitFor{editableInput.displayed}
			waitFor{editableBtn.displayed}
			waitFor{nameInput.displayed}
			waitFor{clearEditable.displayed}
			
			when:
			clearEditable.click()

			then:
			waitFor{nameInput.text() == ""}
			waitFor{btnPrimaryImage.displayed}

			//check blank name
			when:
			btnPrimaryImage.click()
			
			then:
			waitFor{errorNameImage.displayed}
			waitFor{errorNameImage.text() == "Имя не может быть пустым."}
			waitFor{nameInput.displayed}
			waitFor{btnPrimaryImage.displayed}

			//the name of more than 265 characters
			when:
			nameInput << "111111111111111111111111111111111111111111111111111111111112122233333333334556666666666677777777777555555555555555555555555555555555555666666666666666666666666666666668888888888888888888888888888999999999999999999999999999996666666666666666666666666666666644444444444444444444444447777777777777777777777755555555555555555555555555999999999999999999999999998888888888888888888888"
			btnPrimaryImage.click()

			then:
			waitFor{errorNameImage.displayed}
			waitFor{errorNameImage.text() == "Слишком длинное имя. Должно быть не более 255 символов."}
			waitFor{clearEditable.displayed}

			//a successful change of name
			when:
			clearEditable.click()

			then:
			waitFor{nameInput.displayed}
			waitFor{nameInput.text() == ""}
			waitFor{btnPrimaryImage.displayed}

			when:
			nameInput << "User_Page222"
			btnPrimaryImage.click()
			
			then:
			waitFor{nameImage.displayed}
			waitFor{allImageList.displayed}
			waitFor{nameActiveImage.displayed}
			waitFor{nameActiveImage.text() == "User_Page222"}
		}

		//add new images
		when:
		driver.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
		driver.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
		driver.executeScript("document.getElementById('multipartFile').removeAttribute('style');")
		def someMap = ["picture1":"E:\\MyFiles\\flower.jpg", "picture2":"E:\\MyFiles\\smile.jpg", "picture3":"E:\\MyFiles\\flower1.jpg"]
		def someList = ["picture1","picture2","picture3"]

		then:
		waitFor{fileNameSpan.displayed}

		for ( picture in someList ) {
			when:
			try{
				fileNameSpan << someMap.get(picture)
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
			waitFor{nameSearchInput.displayed}
		}

		when:
		nameSearchInput << "flower"

		then:
		waitFor{at UserContentPage}
		waitFor{conentList.displayed}
		waitFor{nameImage.displayed}

		when:
		nameImage.click()

		then:
		waitFor{nameFirstImage.displayed}
		waitFor{clearEditable.displayed}	

		when:
		clearEditable.click()

		then:
		waitFor{nameInput.text() == ""}
		waitFor{btnPrimaryImage.displayed}

		//check blank name
		when:
		btnPrimaryImage.click()
			
		then:
		waitFor{at UserContentPage}
		waitFor{errorNameImage.displayed}
		waitFor{errorNameImage.text() == "Имя не может быть пустым."}
		waitFor{nameInput.displayed}
		waitFor{btnPrimaryImage.displayed}

		//the name of more than 265 characters
		when:
		nameInput << "111111111111111111111111111111111111111111111111111111111112122233333333334556666666666677777777777555555555555555555555555555555555555666666666666666666666666666666668888888888888888888888888888999999999999999999999999999996666666666666666666666666666666644444444444444444444444447777777777777777777777755555555555555555555555555999999999999999999999999998888888888888888888888"
		btnPrimaryImage.click()

		then:
		waitFor{errorNameImage.displayed}
		waitFor{errorNameImage.text() == "Слишком длинное имя. Должно быть не более 255 символов."}
		waitFor{clearEditable.displayed}

		//a successful change of name
		when:
		clearEditable.click()

		then:
		waitFor{at UserContentPage}
		waitFor{nameInput.displayed}
		waitFor{nameInput.text() == ""}
		waitFor{btnPrimaryImage.displayed}

		when:
		nameInput << "Flower_Big"
		btnPrimaryImage.click()
			
		then:
		waitFor{at UserContentPage}
		waitFor{nameActiveImage.displayed}
		waitFor{nameSearchInput.displayed}
		waitFor{nameActiveImage.text() == "Flower_Big"}

		when:
		nameSearchInput << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE

		then:
		waitFor{conentList.displayed}	
		waitFor{LiImage.displayed}
	
		//Remove Add pictures
		for ( picture in someList ) {
			
			when:
			LiImage.click()

			then:
			waitFor{at UserContentPage}
			waitFor{conentList.displayed}	
			waitFor{deleteImage.displayed}
			
			when:
			deleteImage.click()
				
			then:
			waitFor{deleteForm.displayed}
			waitFor{btnYes.displayed}

			when:
			btnYes.click()
	
			then:
			waitFor{at UserContentPage}
			waitFor{conentList.displayed}	
			waitFor{numberImages.displayed}
			if (numberImages.text() != "0"){
				waitFor{LiImage.displayed}
			}
			
		}
	}	
}