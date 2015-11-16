import com.terminal.pages.*
import geb.spock.GebReportingSpec
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriverException

import java.util.concurrent.TimeUnit

class T208_RenameUserContantSpec extends GebReportingSpec {
	def "can get to contant page and change the name of the picture"() {

		when:
		to MainPage
	    at MainPage
        loginLink.click()

	    then:
        waitFor{at MainPage}
	    waitFor{loginDialog.displayed}

        when:
		StaticData.setUserName(usernameInputOnLoginForm)
		StaticData.setUserPassword(passwordInputOnLoginForm)
        loginButton.click()

	    then:
        waitFor{at UserPersonalAccountPage}
		waitFor{myPicturesLink.displayed}
    	
		when:
		myPicturesLink.click()
		// file1 - png, file2 - gif, file3 - animated gif, file4 - bmp, file5 - mp4, file6 - mov, file7 - a 500MB file, file8 - exe extension in the jpg / gif / bmp / ​​mp4 / mov
		def someMap = ["file1":"E:\\MyFiles\\file1.png", "file2":"E:\\MyFiles\\file2.gif", "file3":"E:\\MyFiles\\file3.gif", "file4":"E:\\MyFiles\\file4.bmp", "file5":"E:\\MyFiles\\file5.mp4", "file6":"E:\\MyFiles\\file6.mov", "file7":"E:\\MyFiles\\file7.mp4", "file8":"E:\\MyFiles\\file8.jpg", "file9":"E:\\MyFiles\\file9.gif", "file10":"E:\\MyFiles\\file10.bmp", "file11":"E:\\MyFiles\\file11.mp4", "file12":"E:\\MyFiles\\file12.mov"]
		def someList = ["file1","file2","file3","file4","file5","file6","file7","file8","file9","file10","file11","file12"]
		def lastImages = 0

		then:
		waitFor{at UserContentPage}
		waitFor{numberImages.displayed}
		waitFor{conentList.displayed}
		waitFor{btnDropbox.displayed}

		//For available images
		if (numberImages.text() != "0"){
			waitFor{firstImage.displayed}
			waitFor{LiImage.displayed}

			when:
			lastImages = LiImage.getAttribute("data-image-id")
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
			waitFor{activImage.displayed}
			waitFor{nameActiveImage == "User_Page222"}
		}

		//add new images
		when:
		driver.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
		driver.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
		driver.executeScript("document.getElementById('multipartFile').removeAttribute('style');")

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
			waitFor(300){at UserContentPage}
			waitFor(300){conentList.displayed}
			waitFor(300){allImageList.displayed}
			waitFor(300){contentListImg.displayed}

			//Irregular files
			if (someList.indexOf(picture) > 5) {
				waitFor{filErrorWrapper.displayed}
				waitFor{filErrorWrapper.text() == "Загружаемый файл имеет некорректный формат или превышает допустимый размер."}
			} else {

				when:
				try {
					contentListImg.click()
				} catch (StaleElementReferenceException e) {
					println e
				}

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { nameSearchInput.displayed }
				waitFor { LiImage.displayed }
				waitFor { nameLiImage == (someMap.get(picture)).substring(someMap.get(picture).lastIndexOf('\\') + 1) }
			}
		}

		when:
		nameSearchInput << "file3"

		then:
		waitFor{at UserContentPage}
		waitFor{conentList.displayed}
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
		nameInput << "File_Big"
		btnPrimaryImage.click()

		then:
		waitFor{at UserContentPage}
		waitFor{nameImage.displayed}
		waitFor{nameImage.text() == "File_Big"}
		waitFor{nameSearchInput.displayed}

		when:
		nameSearchInput << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE

		then:
		waitFor{activImage.displayed}
		waitFor{nameActiveImage == "File_Big"}
		waitFor{numberImages.displayed}

		when:
		numberImages.click()

		then:
		waitFor{conentList.displayed}
		waitFor{LiImage.displayed}
		waitFor{imageUploadForm.displayed}

		//Remove Add pictures
		for ( int i = 0; i < 6; i++ ) {
			if (numberImages.text() != "0" && lastImages != LiImage.getAttribute("data-image-id")) {

				when:
				LiImage.click()

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { deleteImage.displayed }

				when:
				deleteImage.click()

				then:
				waitFor { deleteForm.displayed }
				waitFor { btnYes.displayed }

				when:
				btnYes.click()

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { numberImages.displayed }
				waitFor { btnDropbox.displayed }
				if (numberImages.text() != "0") {
					waitFor { LiImage.displayed }
				}
			}
		}

		//dropbox
		for ( filename in someList ) {

			when:
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			withNewWindow({ btnDropbox.click()}, close: false, wait: true ) { assert title == 'Войти в Dropbox' || title == 'Sign in Dropbox' || title == 'Dropbox Chooser'

				if ( filename == someList[0] ) {
					then:
					waitFor { at DropboxPage }
					waitFor { formAuthorization.displayed }
					waitFor { btnLogin.displayed }

					when:
					StaticData.setUserEmailDropbox(emailUser)
					StaticData.setUserPasswordDropbox(passwordUser)
					btnLogin.click()
				}

				then:
				waitFor(300) { title == 'Dropbox Chooser' }
				waitFor { at DropboxPage }
				waitFor { fileListPersonal.displayed }
				waitFor { inputSearch.displayed }

				when:
				inputSearch << (someMap.get(filename)).substring(someMap.get(filename).lastIndexOf('\\') + 1)
				driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

				then:
				waitFor(300) { searchFilePersonal.displayed }
				waitFor { searchChooser.displayed }
				waitFor { filePersonal.displayed }

				when:
				filePersonal.click()

				then:
				waitFor { searchFilePersonal.displayed }
				waitFor { activeFilePersonal.displayed }
				waitFor { btnPanel.displayed }
				waitFor { btnChoose.displayed }

				when:
				driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
				try {
					btnChoose.click()
				} catch (StaleElementReferenceException e) {
					println e
				}
			}

			then:
			waitFor(300) {at UserContentPage}
			waitFor(300) {conentList.displayed}
			waitFor(300) {allImageList.displayed}
			waitFor(300) {contentListImg.displayed}

			if (someList.indexOf(filename) > 5) {
				waitFor{filErrorWrapper.displayed}
				waitFor{numberImages.displayed}
				waitFor{filErrorWrapper.text() == "Загружаемый файл имеет некорректный формат или превышает допустимый размер."}

				when:
				numberImages.click()

				then:
				waitFor{btnDropbox.displayed}
				waitFor{LiImage.displayed}

			} else {

				when:
				try {
					contentListImg.click()
				} catch (StaleElementReferenceException e) {
					println e
				}

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { contentListImg.displayed }
				waitFor { nameSearchInput.displayed }
				waitFor(300) { LiImage.displayed }
				waitFor { nameLiImage == (someMap.get(filename)).substring(someMap.get(filename).lastIndexOf('\\') + 1) }
				waitFor { btnDropbox.displayed }
			}
		}

		//Remove Add pictures
		for ( int i = 0; i < 6; i++ ) {
			if (numberImages.text() != "0" && lastImages != LiImage.getAttribute("data-image-id")) {

				when:
				LiImage.click()

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { deleteImage.displayed }

				when:
				deleteImage.click()

				then:
				waitFor { deleteForm.displayed }
				waitFor { btnYes.displayed }

				when:
				btnYes.click()

				then:
				waitFor { at UserContentPage }
				waitFor { conentList.displayed }
				waitFor { numberImages.displayed }
				waitFor { btnDropbox.displayed }
				if (numberImages.text() != "0") {
					waitFor { LiImage.displayed }
				}
			}
		}
	}	
}