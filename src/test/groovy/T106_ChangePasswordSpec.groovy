

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

class T106_ChangePasswordSpec extends GebReportingSpec {
	def "can get to settings page and change the password to blank fields "() {
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
		passwordForm.displayed
		
		when:
		savePasswordButton.click()
		
		then:
		waitFor{errorNewPassword.displayed}
		waitFor{errorOldPassword.displayed}
		waitFor{errorNewPassword.text() == "Пароль не может быть пустым."}
		waitFor{errorOldPassword.text() == "Пароль не может быть пустым."}
	}
	def "can get to settings page and change the password to blank the old password "() {
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
		passwordForm.displayed
		
		when:
		newPasswordInput << "222222"
     		confirmPasswordInput << "222222" 	
		savePasswordButton.click()

		then:

		waitFor{errorOldPassword.displayed}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		waitFor{errorOldPassword.text() == "Пароль не может быть пустым."}
		
    	}

	def "can get to settings page and change the password to blank the new password"() {
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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "111111"
		confirmPasswordInput << "222222" 
		savePasswordButton.click()

        	then:
		waitFor{errorNewPassword.displayed}
		waitFor{errorConfirmPassword.displayed}
        	waitFor{errorNewPassword.text() == "Пароль не может быть пустым."}	
        	waitFor{errorConfirmPassword.text() == "Подтверждение должно совпадать с паролем."}
		
  	}
	
	def "can get to settings page and change the password to blank the confirm password"() {
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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222" 
		savePasswordButton.click()

		then:
		waitFor{errorConfirmPassword.displayed}
		waitFor{errorConfirmPassword.text() == "Подтверждение должно совпадать с паролем."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
	}
	def "can get to settings page and change the password to incorrect old password"() {
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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "123456"
		newPasswordInput << "222222"
		confirmPasswordInput << "222222" 	
		savePasswordButton.click()

		then:	
		waitFor{errorOldPassword.displayed}
		waitFor{errorOldPassword.text() == "Старый пароль введен неверно."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}

	}
	def "can get to settings page and change with mismatched the new password"() {
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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222"
		confirmPasswordInput << "111111" 
		savePasswordButton.click()

        	then:
        	waitFor{errorConfirmPassword.displayed}
        	waitFor{errorConfirmPassword.text() == "Подтверждение должно совпадать с паролем."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
  	  }

	def "can get to settings page and change only with old password"() {
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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		savePasswordButton.click()

        	then:
        	waitFor{errorNewPassword.displayed}
		waitFor{errorNewPassword.text() == "Пароль не может быть пустым."}
        	waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	}
	def "can get to settings page and change only with new password"() {
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
		passwordForm.displayed
		
		when:
		newPasswordInput << "111111"
		savePasswordButton.click()

        	then:
        	waitFor{errorOldPassword.displayed}
		waitFor{errorOldPassword.text() == "Пароль не может быть пустым."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
	    }
	def "can get to settings page and change only with confirm password"() {
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
		passwordForm.displayed
		
		when:
		confirmPasswordInput << "111111"
		savePasswordButton.click()

        	then:
		waitFor{errorOldPassword.displayed}
		waitFor{errorNewPassword.displayed}
		waitFor{errorConfirmPassword.displayed}
		waitFor{errorOldPassword.text() == "Пароль не может быть пустым."}
		waitFor{errorNewPassword.text() == "Пароль не может быть пустым."}
		waitFor{errorConfirmPassword.text() == "Подтверждение должно совпадать с паролем."}

	}
	def "can get to settings page and simple password at least 6 characters"() {
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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "22"
		confirmPasswordInput << "22" 
		savePasswordButton.click()

        	then:
        	waitFor{errorNewPassword.displayed}
        	waitFor{errorNewPassword.text() == "Пароль слишком простой. Должен быть не меньше 6 символов длиной и содержать хотя бы одну букву и цифру."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
}
    	def "can get to settings page and change the password"() {
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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222"
		confirmPasswordInput << "222222" 
		savePasswordButton.click()

        	then:
        	waitFor{errorNewPassword.displayed}
        	waitFor{errorNewPassword.text() == "Пароль успешно изменён."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
    }	
	
}

