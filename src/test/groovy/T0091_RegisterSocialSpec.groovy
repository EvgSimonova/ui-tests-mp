

import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.FacebookLoginPage
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat

class T0091_RegisterSocialSpec extends GebReportingSpec {

    def "register as facebook user"() {
        when:
        to MainPage
        at MainPage
        registrationLink.click()

        then:
        waitFor{at MainPage}
        waitFor{registrationDialog.displayed}
        waitFor{form.displayed}
	waitFor{fbRegisterButton.displayed}

        when:
        fbRegisterButton.click()
	
	//stackoverflow.com/questions/25368067/geb-spock-if-then-else-logic-how-to-check-for-a-record-and-do-one-thing-if
        then:
	waitFor{ at FacebookLoginPage}		
	waitFor{emailInput.displayed}
	waitFor{passwordInput.displayed}
	waitFor{loginbutton.displayed}

	when:
	emailInput << "project.mark@yandex.ru"
	passwordInput << "456Fl@br"
	loginbutton.click()

	then:
	at UserPersonalAccountPage
	userNameLink.displayed
	logoutLink.displayed
	createCompanyLink.displayed
	myPicturesLink.displayed
    }
}
