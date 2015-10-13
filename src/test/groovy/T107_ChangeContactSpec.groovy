

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerSettingsPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;
import geb.*
import java.lang.*


class T107_ChangeContactSpec extends GebReportingSpec {
	def "can get to settings page and change the name and phone of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		nameOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		nameOwner << "Timur"
		phoneOwner << "84552545689"
		saveSettingChange.click()
		
		then:
		waitFor{nameOwnerTop.displayed}
		waitFor{nameOwnerTop.text() == "Timur"}
		waitFor{nameOwner.value() == "Timur"}
		waitFor{phoneOwner.value() == "84552545689"}
	}
	def "can get to settings page and deleting a name in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		nameOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneOwner << "123-45-66"
		saveSettingChange.click()
		
		then:
		waitFor{nameOwner.value() == ""}
		waitFor{phoneOwner.value() == "123-45-66"}
		waitFor{nameOwnerTop.displayed}
		waitFor{nameOwnerTop.text() == "mihailov-ta+spam44@ya.ru"}

	}
	def "can get to settings page and deleting a phone in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		nameOwner << "Тимур"
		phoneOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveSettingChange.click()
		
		then:
		
		waitFor{nameOwnerTop.displayed}
		waitFor{nameOwnerTop.text() == "Тимур"}
		waitFor{nameOwner.value() == "Тимур"}
		waitFor{phoneOwner.value() == ""}
	}
	def "can get to settings page and validity a phone in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		phoneOwner << "8(455)4564565"
		saveSettingChange.click()
		
		then:
		
		waitFor{phoneOwner.value() == "8&#40;455&#41;4564565"}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and deleting the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		nameOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		phoneOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveSettingChange.click()
		
		then:
		
		waitFor{nameOwnerTop.displayed}
		waitFor{nameOwnerTop.text() == "mihailov-ta+spam44@ya.ru"}
		waitFor{nameOwner.value() == ""}
		waitFor{phoneOwner.value() == ""}
	}
	def "can get to settings page and email change in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		emailOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		emailOwner << "123@ya.ru"
		saveEmail.click()
		
		then:
		
		waitFor{emailOwner == "123@ya.ru"}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and delete email in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		emailOwner << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
		saveEmail.click()
		
		then:
		
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{emailOwner.text() == ""}
		
	}
	def "can get to settings page and validity email in the contact of the owner of the terminal"() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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
		settingsLink.displayed
        	
		when:
		settingsLink.click()
		
		then:
		at OwnerSettingsPage
		settingChange.displayed
		
		when:
		emailOwner << "test"
		saveEmail.click()
		
		then:
	
		waitFor{subscriptionEmail.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{subscriptionEmail.text() == "Введите валидный email адрес"}
		
	}
}