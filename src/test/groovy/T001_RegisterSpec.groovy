

import com.terminal.pages.MainPage
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat

class T001_RegisterSpec extends GebReportingSpec {

    def "register as user with password's autogeneration"() {
        when:
        println('Start')
        to MainPage
        at MainPage
        registrationLink.click()
        println('registration link clicked')

        then:
        waitFor{at MainPage}
        waitFor{registrationDialog.displayed}
        waitFor{form.displayed}
        println('at Main page and reg dialog displayed')

        when:
        println('start filling Form')
        generatePasswordCheckbox = true
        roleSelect = "user"
        usernameInput << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@ya.ru"
        registerButton.click()
        println('registration button clicked')

        then:
        waitFor{messageBox.displayed}
        waitFor{messageBox.text().trim() == "Вы были удачно зарегистрированы. Для входа на сайт проверьте вашу почту."}
        println('registration message displayed')
    }

    def "register as user with password"() {
        when:
        to MainPage
        at MainPage
        registrationLink.click()

        then:
        at MainPage
        registrationDialog.displayed
        form.displayed

        when:
        generatePasswordCheckbox = false
        roleSelect = "user"
        usernameInput << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@ya.ru"
        passwordInput << "Gnbws#456"
        confirmPasswordInput << "Gnbws#456"
        registerButton.click()

        then:
        waitFor{messageBox.displayed}
        waitFor{messageBox.text().trim() == "Вы были удачно зарегистрированы. Для входа на сайт проверьте вашу почту."}
    }

    def "register as owner with password's autogeneration"() {
        when:
        to MainPage
        at MainPage
        registrationLink.click()

        then:
        at MainPage
        registrationDialog.displayed
        form.displayed

        when:
        generatePasswordCheckbox = true
        roleSelect = "owner"
        usernameInput << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@ya.ru"
        registerButton.click()

        then:
        waitFor{messageBox.displayed}
        waitFor{messageBox.text().trim() == "Вы были удачно зарегистрированы. Для входа на сайт проверьте вашу почту."}
    }

    def "register as owner with password"() {
        when:
        to MainPage
        at MainPage
        registrationLink.click()

        then:
        at MainPage
        registrationDialog.displayed
        form.displayed

        when:
        generatePasswordCheckbox = false
        roleSelect = "owner"
        usernameInput << "mptestuser" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@ya.ru"
        passwordInput << "Gnbws#456"
        confirmPasswordInput << "Gnbws#456"
        registerButton.click()

        then:
        waitFor{messageBox.displayed}
        waitFor{messageBox.text().trim() == "Вы были удачно зарегистрированы. Для входа на сайт проверьте вашу почту."}
    }
}