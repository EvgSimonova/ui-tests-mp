

import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerSettingsPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys


class T106_ChangePasswordSpec extends GebReportingSpec {
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
		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
		
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
		waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
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
		waitFor{errorOldPassword.text() == "������ ������ ����� �������."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}

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
        	waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
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
        	waitFor{errorNewPassword.text() == "������ �� ����� ���� ������."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
	
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
		waitFor{errorOldPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorNewPassword.text() == "������ �� ����� ���� ������."}
		waitFor{errorConfirmPassword.text() == "������������� ������ ��������� � �������."}

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
        	waitFor{errorOldPassword.displayed}
        	waitFor{errorOldPassword.text() == "������ ������� �������. ������ ���� �� ������ 6 �������� ������ � ��������� ���� �� ���� ����� � �����."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
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
        	waitFor{errorNewPassword.text() == "������ ������� ������."}
		waitFor{terminalsListLink.displayed}
		waitFor{moneyLink.displayed}
		
    }	
	
}

