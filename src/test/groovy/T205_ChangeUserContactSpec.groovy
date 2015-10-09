

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.UserSettingsPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;


class T205_ChangeUserContactSpec extends GebReportingSpec {
	def "can get to settings page and change the name and phone of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		nameUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		nameUser << "Тимур"
		phoneUser << "84552545689"
		saveSettingChange.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{nameUserTop.displayed}
		waitFor{nameUserTop.text() == "Тимур"}
		waitFor{nameUser.text() == "Тимур"}
		waitFor{phoneUser.text() == "84552545689"}
	}
	def "can get to settings page and deleting a name in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		nameUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneUser << "123-45-66"
		saveSettingChange.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{nameUserTop.displayed}
		waitFor{nameUserTop.text() == usernameInputOnLoginForm}
		waitFor{nameUser.text() == ""}
		waitFor{phoneUser.text() == "123-45-66"}
	}
	def "can get to settings page and deleting a phone in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		nameUser << "Тимур"
		phoneUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveSettingChange.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{nameUserTop.displayed}
		waitFor{nameUserTop.text() == "Тимур"}
		waitFor{nameUser.text() == "Тимур"}
		waitFor{phoneUser.text() == ""}
	}
	def "can get to settings page and validity a phone in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		phoneUser << "8(455)4564565"
		saveSettingChange.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{phoneUser.text() == "8&#40;455&#41;4564565"}
		waitFor{contentLink.displayed}
		waitFor{balanceLink.displayed}

	}
	def "can get to settings page and deleting the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		nameUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveSettingChange.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{nameUserTop.displayed}
		waitFor{nameUserTop.text() == usernameInputOnLoginForm}
		waitFor{nameUser.text() == ""}
		waitFor{phoneUser.text() == ""}
	}
	def "can get to settings page and email change in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		emailUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		emailUser << "123@ya.ru"
		saveEmail.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{emailUser == "123@ya.ru"}
		waitFor{contentLink.displayed}
		waitFor{balanceLink.displayed}
	}
	def "can get to settings page and delete email in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		emailUser << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveEmail.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{contentLink.displayed}
		waitFor{balanceLink.displayed}
		waitFor{emailUser.text() == ""}
		
	}
	def "can get to settings page and validity email in the contact of the user of the terminal"() {

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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at UserSettingsPage
		settingChange.displayed
		
		when:
		emailUser << "test"
		saveEmail.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{subscriptionEmail.displayed}
		waitFor{contentLink.displayed}
		waitFor{balanceLink.displayed}
		waitFor{subscriptionEmail.text() == "Введите валидный email адрес"}
		
	}
}