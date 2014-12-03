import com.terminal.pages.MainPage
import com.terminal.pages.LoginFailedPage
import geb.spock.GebReportingSpec

class T101_LoginEmptyFields extends GebReportingSpec {

    def "can get to main page and login"() {
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
        at LoginFailed
        errorblock.displayed
        loginLink.displayed
        registrationLink.displayed
    }
}