

import com.terminal.pages.MainPage
import com.terminal.pages.UserPersonalAccountPage
import com.terminal.pages.FacebookLoginPage
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat

class T0011_RegisterSocialSpec extends GebReportingSpec {

    def "register as user facebook"() {
        when:
        to MainPage
        at MainPage
        registrationLink.click()

        then:
        waitFor{at MainPage}
        waitFor{registrationDialog.displayed}
        waitFor{form.displayed}
	//waitFor{fbRegisterButton.displayed}

        when:
        fbRegisterButton.click()

        then:
	if (waitFor{ at UserPersonalAccountPage}){
		waitFor{ createCompanyLink.displayed}
		waitFor{ myPicturesLink.displayed}
		waitFor{ userNameLink.displayed}
		waitFor{ logoutLink.displayed}
	}else if( waitFor{at FacebookLoginPage}){
		waitFor{emailInput.displayed}
		waitFor{passwordInput.displayed}
	}
    }
}
