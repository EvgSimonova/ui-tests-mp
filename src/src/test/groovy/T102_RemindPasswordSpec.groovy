

import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import java.text.SimpleDateFormat

class T102_RemindPasswordSpec extends GebReportingSpec {

    def "can get to main page and remind a password"() {
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
        forgotPasswordLink.click()

        then:
        waitFor {
            forgotPasswordForm.displayed
        }
		
		when:
		forgotPasswordEmail << "mihailov-ta+spam99@ya.ru"
		forgotPasswordButton.click()

		then:
		waitFor {
            feedbackInfo.displayed
        }
    }
	
}