/**
 * Created by m on 02.11.15.
 */


import com.terminal.pages.*
import geb.spock.GebReportingSpec

class T301_AdminLockUnlockUserSpec extends GebReportingSpec {
    def "can get to adminLogin page and block user"() {

        when:
        to AdminLoginPage
        at AdminLoginPage

        then:
        waitFor{usernameInput.displayed}
        waitFor{passwordInput.displayed}
        waitFor{loginButton.displayed}

        when:
        StaticData.setAdminName(usernameInput)
        StaticData.setAdminPassword(passwordInput)
        loginButton.click()

        then:
        waitFor{at AdminPersonalAccountPage}
        waitFor{settingsRightUserLink.displayed}

        when:
        settingsRightUserLink.click()
        def someList = ["mihailov-ta+spam33@ya.ru","mihailov-ta+spam44@ya.ru"]

        then:
        waitFor{at AdminModerateUserPage}
        waitFor{tableUser.displayed}
        waitFor{cartItems.displayed}

        when:
        for ( emailValue in someList ) {
            def userCart = cartItems.find({ it.emailUser == emailValue})

            then:
            if (userCart != null) {
                if (userCart.statusUser == "Активен") {
                    waitFor { userCart.userDetailsLink.displayed }

                    when:
                    userCart.userDetailsLink.click()

                    then:
                    waitFor { editBoxUser.displayed }
                    waitFor { activeBlockUser.displayed }

                    when:
                    activeBlockUser.click()

                    then:
                    waitFor { groupAction.displayed }
                    waitFor { deleteBlockUser.displayed }

                    when:
                    deleteBlockUser.click()

                    then:
                    waitFor { at AdminModerateUserPage }
                    waitFor { tableUser.displayed }
                    waitFor { cartItems.find({ it.emailUser == emailValue }).statusUser == "Заблокирован" }
                    waitFor { logoutButton.displayed }

                    when:
                    logoutButton.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginLink.displayed }

                    when:
                    loginLink.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginDialog.displayed }

                    if (emailValue == "mihailov-ta+spam33@ya.ru") {
                        when:
                        StaticData.setUserName(usernameInputOnLoginForm)
                        StaticData.setUserPassword(passwordInputOnLoginForm)
                        loginButton.click()
                    } else {
                        when:
                        StaticData.setOwnerName(usernameInputOnLoginForm)
                        StaticData.setOwnerPassword(passwordInputOnLoginForm)
                        loginButton.click()
                    }

                    then:
                    waitFor { at LoginFailedPage }
                    waitFor { errorblock.displayed }
                    waitFor { errorblock.text() == "Неверное имя или пароль" }

                    when:
                    to AdminLoginPage
                    at AdminLoginPage

                    then:
                    waitFor { usernameInput.displayed }
                    waitFor { passwordInput.displayed }
                    waitFor { loginButton.displayed }

                    when:
                    StaticData.setAdminName(usernameInput)
                    StaticData.setAdminPassword(passwordInput)
                    loginButton.click()

                    then:
                    waitFor { at AdminPersonalAccountPage }
                    waitFor { settingsRightUserLink.displayed }

                    when:
                    settingsRightUserLink.click()

                    then:
                    waitFor { at AdminModerateUserPage }
                    waitFor { tableUser.displayed }
                    waitFor { cartItems.displayed }
                    waitFor { cartItems.find({ it.emailUser == emailValue }).userDetailsLink.displayed }

                    when:
                    cartItems.find({ it.emailUser == emailValue }).userDetailsLink.click()

                    then:
                    waitFor { editBoxUser.displayed }
                    waitFor { activeBlockUser.displayed }

                    when:
                    activeBlockUser.click()

                    then:
                    waitFor { groupAction.displayed }
                    waitFor { deleteBlockUser.displayed }

                    when:
                    deleteBlockUser.click()

                    then:
                    waitFor { at AdminModerateUserPage }
                    waitFor { tableUser.displayed }
                    waitFor { cartItems.find({ it.emailUser == emailValue }).statusUser == "Активен" }
                    waitFor { logoutButton.displayed }

                    when:
                    logoutButton.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginLink.displayed }

                    when:
                    loginLink.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginDialog.displayed }
                    if (emailValue == "mihailov-ta+spam33@ya.ru") {

                        when:
                        StaticData.setUserName(usernameInputOnLoginForm)
                        StaticData.setUserPassword(passwordInputOnLoginForm)
                        loginButton.click()

                        then:
                        waitFor { at UserPersonalAccountPage }
                        waitFor { settingsLink.displayed }
                        waitFor { logoutLink.displayed }

                    } else {

                        when:
                        StaticData.setOwnerName(usernameInputOnLoginForm)
                        StaticData.setOwnerPassword(passwordInputOnLoginForm)
                        loginButton.click()

                        then:
                        waitFor { at OwnerPersonalAccountPage }
                        waitFor { settingsLink.displayed }
                        waitFor { logoutLink.displayed }
                    }

                    when:
                    logoutLink.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginLink.displayed }

                    if (emailValue == "mihailov-ta+spam33@ya.ru") {

                        when:
                        to AdminLoginPage
                        at AdminLoginPage

                        then:
                        waitFor { usernameInput.displayed }
                        waitFor { passwordInput.displayed }
                        waitFor { loginButton.displayed }

                        when:
                        StaticData.setAdminName(usernameInput)
                        StaticData.setAdminPassword(passwordInput)
                        loginButton.click()

                        then:
                        waitFor { at AdminPersonalAccountPage }
                        waitFor { settingsRightUserLink.displayed }

                        when:
                        settingsRightUserLink.click()

                        then:
                        waitFor { at AdminModerateUserPage }
                        waitFor { tableUser.displayed }
                        waitFor { cartItems.displayed }
                        waitFor { logoutButton.displayed }
                    }
                } else {
                    if (emailValue == "mihailov-ta+spam44@ya.ru") {
                        waitFor { logoutButton.displayed }

                        when:
                        logoutButton.click()

                        then:
                        waitFor { at MainPage }
                        waitFor { loginDialog.displayed }

                    } else {
                        waitFor { logoutButton.displayed }
                    }
                }
            } else {
                if (emailValue == "mihailov-ta+spam44@ya.ru") {
                    waitFor { logoutButton.displayed }

                    when:
                    logoutButton.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginDialog.displayed }

                }
            }
        }

        then:
        waitFor { at MainPage }
        waitFor { loginLink.displayed }

    }
}
