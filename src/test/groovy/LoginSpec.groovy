

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat

class LoginSpec extends GebReportingSpec {

    def "can get to main page and login with empty fields"() {
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
        loginButton.click()

        then:
        at MainPage
        errblock.displayed
    }    
	
	def "can get to main page and login with empty login"() {
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
		passwordInputOnLoginForm << "123"
        loginButton.click()

        then:
        at MainPage
        errblock.displayed
    }    
	
	def "can get to main page and login with empty password"() {
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
		usernameInputOnLoginForm << "timurShneider1@ya.ru"
        loginButton.click()

        then:
        at MainPage
        errblock.displayed
    }    
	
	def "can get to main page and login with unused login and empty password"() {
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
		usernameInputOnLoginForm << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
        loginButton.click()

        then:
        at MainPage
        errblock.displayed
    }   

	def "can get to main page and login with unused login and unused password"() {
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
		usernameInputOnLoginForm << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
		passwordInputOnLoginForm << "mppassword" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
        loginButton.click()

        then:
        at MainPage
        errblock.displayed
    }    	
	
	def "can get to main page and login as user"() {
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
        usernameInputOnLoginForm << "mihailov-ta+spam33@ya.ru"
        passwordInputOnLoginForm << "123"
        loginButton.click()

        then:
        at UserPersonalAccountPage
        createCompanyLink.displayed
        myPicturesLink.displayed
        createLink.displayed
    }

    def "can get to main page and login as owner"() {
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
        usernameInputOnLoginForm << "mihailov-ta+spam44@ya.ru"
        passwordInputOnLoginForm << "123"
        loginButton.click()

        then:
        at OwnerPersonalAccountPage
        terminalsListLink.displayed
        moneyLink.displayed
    }
}