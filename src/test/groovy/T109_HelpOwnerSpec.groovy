

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;

class T109_HelpOwnerSpec extends GebReportingSpec {
	def "can get to main page and send a message to the technical support no subject"() {

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
		StaticData.setOwnerName(usernameInputOnLoginForm)
		StaticData.setOwnerPassword(passwordInputOnLoginForm)
        	loginButton.click()

	        then:
        	at OwnerPersonalAccountPage
		helpLink.displayed
        	
		when:
		helpLink.click()
		
		then:
		supportBox.displayed
		
		when:
		supSubject << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supTextarea << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supTextarea << "Тестовое сообщение от автотеста! :) smile"
		sendBtn.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorSubject.displayed}
		waitFor{sendAccept.displayed}
		waitFor{sendBtn.displayed}
		waitFor{errorSubject.text() == "Заполните тему обращения."}
		
	}
	def "can get to main page and send a message to the technical support no text"() {

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
		StaticData.setOwnerName(usernameInputOnLoginForm)
		StaticData.setOwnerPassword(passwordInputOnLoginForm)
        	loginButton.click()

	        then:
        	at OwnerPersonalAccountPage
		helpLink.displayed
        	
		when:
		helpLink.click()
		
		then:
		supportBox.displayed
		
		when:
		supSubject << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supTextarea << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supSubject << "Тест"
		sendBtn.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorTextarea.displayed}
		waitFor{sendAccept.displayed}
		waitFor{sendBtn.displayed}
		waitFor{errorTextarea.text() == "Заполните текст обращения."}
		
	}
	def "can get to main page and send a message to the technical support no text and Subject"() {

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
		StaticData.setOwnerName(usernameInputOnLoginForm)
		StaticData.setOwnerPassword(passwordInputOnLoginForm)
        	loginButton.click()

	        then:
        	at OwnerPersonalAccountPage
		helpLink.displayed
        	
		when:
		helpLink.click()
		
		then:
		supportBox.displayed
		
		when:
		supSubject << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supTextarea << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		sendBtn.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorTextarea.displayed}
		waitFor{errorSubject.displayed}
		waitFor{sendAccept.displayed}
		waitFor{sendBtn.displayed}
		waitFor{errorSubject.text() == "Заполните тему обращения."}
		waitFor{errorTextarea.text() == "Заполните текст обращения."}
		
	}
	def "can get to main page and send a message to the technical support"() {

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
		StaticData.setOwnerName(usernameInputOnLoginForm)
		StaticData.setOwnerPassword(passwordInputOnLoginForm)
        	loginButton.click()

	        then:
        	at OwnerPersonalAccountPage
		helpLink.displayed
        	
		when:
		helpLink.click()
		
		then:
		supportBox.displayed
		
		when:
		supSubject << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supTextarea << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		supSubject << "Проверка связи"
		supTextarea << "Тестовое сообщение от автотеста! :) smile"
		sendBtn.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{sendtext.displayed}

		when:
		waitFor{sendtext.text() == "Идёт отправка сообщения..."}
		
		then:
		waitFor{sendAccept.displayed}
		waitFor{sendAccept.text() == "Ваше обращение успешно отправлено.↵В ближайшее время мы свяжемся с Вами для решения вашей задачи.↵С уважением, команда Marc project.↵"}
		
	}
}