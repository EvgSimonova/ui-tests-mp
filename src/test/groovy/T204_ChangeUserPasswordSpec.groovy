

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.UserSettingsPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit;


class T204_ChangeUserPasswordSpec extends GebReportingSpec {
	def "can get to settings page and change the password to blank fields "() {

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
		passwordForm.displayed
		
		when:
		savePasswordButton.click()
		
		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorNewPassword.displayed}
		waitFor{errorOldPassword.displayed}
		waitFor{errorNewPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
	}
	def "can get to settings page and change the password to blank the old password "() {

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
		passwordForm.displayed
		
		when:
		newPasswordInput << "222222"
     		confirmPasswordInput << "222222" 	
		savePasswordButton.click()

		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorOldPassword.displayed}
		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
    	}

	def "can get to settings page and change the password to blank the new password"() {

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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "111111"
		confirmPasswordInput << "222222" 
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorOldPassword.displayed}
		waitFor{errorConfirmPassword.displayed}
        	waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}	
        	waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		
  	}
	
	def "can get to settings page and change the password to blank the confirm password"() {

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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222" 
		savePasswordButton.click()

		then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorConfirmPassword.displayed}
		waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
	}
	def "can get to settings page and change the password to incorrect old password"() {

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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "123456"
		newPasswordInput << "222222"
		confirmPasswordInput << "222222" 	
		savePasswordButton.click()

		then:	
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorOldPassword.displayed}
		waitFor{errorOldPassword.text() == "������ ������ ����� �������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
	}
	
	def "can get to settings page and change with mismatched the new password"() {

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
		passwordForm.displayed
				
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222"
		confirmPasswordInput << "111111" 
		savePasswordButton.click()


        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        	waitFor{errorConfirmPassword.displayed}
        	waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
  	  }

	def "can get to settings page and change only with old password"() {

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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        	waitFor{errorNewPassword.displayed}
        	waitFor{errorNewPassword.text() == "������ �� ����� ���� ������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
	}

	def "can get to settings page and change only with new password"() {

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
		passwordForm.displayed
		
		when:
		newPasswordInput << "111111"
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        	waitFor{errorOldPassword.displayed}
		waitFor{errorConfirmPassword.displayed}
  		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		
	}
	def "can get to settings page and change only with confirm password"() {

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
		passwordForm.displayed
		
		when:
		confirmPasswordInput << "111111"
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		waitFor{errorOldPassword.displayed}
		waitFor{errorNewPassword.displayed}
		waitFor{errorConfirmPassword.displayed}
		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorNewPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}

	}
	def "can get to settings page and change the password"() {

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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "222222"
		confirmPasswordInput << "222222" 
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        	waitFor{errorNewPassword.displayed}
        	waitFor{errorNewPassword.text() == "������ ������� ������."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
		
	}
	def "can get to settings page and simple password at least 6 characters"() {

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
		passwordForm.displayed
		
		when:
		oldPasswordInput << "111111"
		newPasswordInput << "22"
		confirmPasswordInput << "22" 
		savePasswordButton.click()

        	then:
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        	waitFor{errorOldPassword.displayed}
        	waitFor{errorOldPassword.text() == "������ ������� �������. ������ ���� �� ������ 6 �������� ������ � ��������� ���� �� ���� ����� � �����."}
		waitFor{balanceLink.displayed}
		waitFor{contentLink.displayed}
		
	}	

}