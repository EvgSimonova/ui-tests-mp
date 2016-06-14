import com.terminal.pages.*
import email.ReadingYandexEmail
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class T010_CheckOwnerNotificationSpec extends GebReportingSpec {

    private String nameTerminal
    private String nameCompany
    private String sumCamp

    def "can get to settings page and Changing the status of moderation of content"() {
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        when:
        println("go to Main page")
        to MainPage
        def newMessages = []
        println("checking e-mail")
        ReadingYandexEmail.main(newMessages, StaticData.getOwner1Name(), StaticData.getOwner1PasswordEmail())

        then:
        waitFor {loginLink.displayed}

        int i = 1
        while ( i < 5 ) {
            if ( i == 1 ) {
                when:
                at MainPage
                loginLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginDialog.displayed }

                when:
                usernameInputOnLoginForm << StaticData.getOwner1Name()
                passwordInputOnLoginForm << StaticData.getOwner1Password()
                loginButton.click()

                then:
                waitFor { at OwnerPersonalAccountPage }
                waitFor { settingsLink.displayed }
            }

            when:
            println("go to Settings page")
            settingsLink.click()

            then:
            waitFor { at OwnerSettingsPage }
            waitFor { formBlockNotifications.displayed }
            waitFor { inputStatusModeration.displayed }
            waitFor { inputChangeNotifications.displayed }
            waitFor { emailOwner.displayed }

            if (emailOwner.value().length() == 0) {
                when:
                emailOwner << StaticData.getOwner1Name()

                then:
                waitFor { saveEmail.displayed }

                when:
                saveEmail.click()

                then:
                waitFor { at OwnerSettingsPage }
                waitFor { emailOwner.displayed }
                waitFor { emailOwner.value() == StaticData.getOwner1Name() }
                waitFor { formBlockNotifications.displayed }
                waitFor { inputStatusModeration.displayed }
                waitFor { inputChangeNotifications.displayed }
            }

            if (i == 1) {
                if (inputStatusModerationCheked == "true") {
                    when:
                    inputStatusModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    println("changed notifications for Status Moderation")
                    btnSaveNotifications.click()

                    then:
                    waitFor { formBlockNotifications.displayed }
                    waitFor { inputStatusModeration.displayed }
                    waitFor { inputStatusModeration.value() == false }
                    waitFor { terminalsListLink.displayed }
                }
                when:
                terminalsListLink.click()

                then:
                waitFor { at OwnerTerminalListPage }
                waitFor { addTerminalButton.displayed }

                when:
                println("Add new terminal")
                nameTerminal = "Тестовый автоматический терминал " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
                StaticData.AddNewTerminalOwnerSpec(driver, nameTerminal)
            } else if (i == 2) {
                if (inputStatusModerationCheked != "true") {

                    when:
                    inputStatusModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    println("changed notifications for Content")
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputStatusModeration.displayed }
                    waitFor { inputStatusModeration.value() == "true" }
                }
            } else if (i == 3) {
                if (inputChangeNotificationsCheked == "true") {

                    when:
                    inputChangeNotifications.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    println("changed notifications for Status Moderation")
                    btnSaveNotifications.click()

                    then:
                    waitFor { formBlockNotifications.displayed }
                    waitFor { inputChangeNotifications.displayed }
                    waitFor { inputChangeNotifications.value() == false }
                    waitFor { logoutLink.displayed }
                }
            } else if (i == 4) {
                if (inputChangeNotificationsCheked != "true") {

                    when:
                    inputChangeNotifications.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    println("changed notifications for Content")
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputChangeNotifications.displayed }
                    waitFor { inputChangeNotifications.value() == "true" }
                    waitFor { logoutLink.displayed }
                }
            }

            if ( i > 2) {
                when:
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

                when:
                at MainPage
                loginLink.click()

                then:
                waitFor { at MainPage }
                waitFor {
                    loginDialog.displayed
                }

                when:
                usernameInputOnLoginForm << StaticData.getUser1Name()
                passwordInputOnLoginForm << StaticData.getUser1Password()
                loginButton.click()

                then:
                waitFor { at UserPersonalAccountPage }
                waitFor { createCompanyLink.displayed }

                when:
                println("Creating Campaing start")
                createCompanyLink.click()

                then:
                waitFor { at AddCampaingParamsPage }
                waitFor { paramsForm.displayed }

                when:
                nameCompany = "Тестовая кампания " +new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
                println("Creating Campaign in progress")
                StaticData.CreatingTestCampaign(driver,"myImg.jpg",nameCompany)

                then:
                waitFor { at DemoCreateCompanyStartCompanyPage }
                waitFor { infoBlock.displayed}
                waitFor { logoutLink.displayed }

                when:
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

                when:
                println("Campaign moderation proccess")
                StaticData.ModerateCampaignSpec(driver, nameCompany, 0)

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

                when:
                loginLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginDialog.displayed }

                when:
                usernameInputOnLoginForm << StaticData.getUser1Name()
                passwordInputOnLoginForm << StaticData.getUser1Password()
                loginButton.click()

                then:
                waitFor { at UserPersonalAccountPage }
                waitFor { myCampaignsLink.displayed }

                when:
                myCampaignsLink.click()

                then:
                waitFor { at UserCurrentCampaignPage }
                waitFor { tableCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.size > 0 }

                waitFor { filtrmModeration.displayed }
                waitFor { clickOutfix.displayed }

                when:
                if (filtrmModeration.getAttribute("value") != "запустить") {
                    if (filtrmModeration.getAttribute("value") != "") {
                        filtrmModeration << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
                    }
                    filtrmModeration << "запустить"
                }
                clickOutfix.click()

                then:
                waitFor { tbodyCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.find({it.modCampaign == "Пройдена\n" + "оплатить и запустить"}).startCampaignLink.displayed }

                when:
                cartCurrentCampaign.find({it.modCampaign == "Пройдена\n" + "оплатить и запустить"}).startCampaignLink.click()

                then:
                waitFor { at DemoCreateCompanyStartCompanyPage }
                waitFor { infoBlock.displayed }
                waitFor { payLink.displayed }

                when:
                println("Start pay process")
                payLink.click()

                then:
                waitFor { at DemoCreateCompanyCompanyInfoPage}
                waitFor { balanceLink.displayed }
                sleep(3000)

                if (driver.title != "Оплата и запуск кампании") {
                    waitFor { infoBlock.displayed }
                    waitFor { sumCampaign.displayed }

                    when:
                    try {
                        sumCamp = Integer.toString(Integer.valueOf(sumCampaign.text().substring(0,sumCampaign.text().lastIndexOf(','))) + 1)
                    } catch (Exception e) {
                        sumCamp = Integer.toString(Integer.valueOf(sumCampaign.text().substring(0,sumCampaign.text().lastIndexOf('.'))) + 1)
                    }
                    balanceLink.click()

                    then:
                    waitFor { at UserBalancePage }
                    waitFor { outSumInput.displayed }

                    when:
                    println("Balance changing")
                    StaticData.BalanceCampaignSpec(driver,sumCamp)

                    then:
                    waitFor { at UserBalancePage }
                    waitFor { myCampaignsLink.displayed }

                    when:
                    myCampaignsLink.click()

                    then:
                    waitFor { at UserCurrentCampaignPage }
                    waitFor { tableCurrent.displayed }
                    waitFor { cartCurrentCampaign.displayed }
                    waitFor { cartCurrentCampaign.size > 0 }
                    waitFor { filtrmModeration.displayed }
                    waitFor { clickOutfix.displayed }

                    when:
                    if (filtrmModeration.getAttribute("value") != "запустить") {
                        if (filtrmModeration.getAttribute("value") != "") {
                            filtrmModeration << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
                        }
                        filtrmModeration << "запустить"
                    }
                    clickOutfix.click()

                    then:
                    waitFor { tbodyCurrent.displayed }
                    waitFor { cartCurrentCampaign.displayed }
                    waitFor { cartCurrentCampaign.find({it.modCampaign == "Пройдена\n" + "оплатить и запустить"}).startCampaignLink.displayed }

                    when:
                    cartCurrentCampaign.find({it.modCampaign == "Пройдена\n" + "оплатить и запустить"}).startCampaignLink.click()
                    newMessages = []
                    ReadingYandexEmail.main(newMessages, StaticData.getUser1Name(), StaticData.getUser1PasswordEmail())

                    then:
                    waitFor { at DemoCreateCompanyStartCompanyPage }
                    waitFor { infoBlock.displayed }
                    waitFor { payLink.displayed }

                    when:
                    println("Pay process started")
                    payLink.click()

                    then:
                    waitFor { at DemoCreateCompanyCompanyInfoPage}
                    waitFor { startBlock.displayed }

                } else {
                    waitFor { startBlock.displayed }
                }

                waitFor { startBlock.text() == "Поздравляем, ваша рекламная кампания оплачена и запущена!"}
                waitFor { logoutLink.displayed }
            }

            then:
            waitFor { logoutLink.displayed }

            when:
            logoutLink.click()

            then:
            waitFor { at MainPage }
            waitFor { loginLink.displayed }

            if ( i < 3) {
                when:
                println("Terminal moderation process")
                if ( i == 1 ) {
                    StaticData.ModerateTerminalSpec(driver,nameTerminal,1)
                } else {
                    StaticData.ModerateTerminalSpec(driver,nameTerminal,0)
                }

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }
            }

            when:
            loginLink.click()

            then:
            waitFor { at MainPage }
            waitFor { loginDialog.displayed }

            when:
            usernameInputOnLoginForm << StaticData.getOwner1Name()
            passwordInputOnLoginForm << StaticData.getOwner1Password()
            loginButton.click()

            if ( i > 2 ) {
                
            }

            then:
            waitFor { at OwnerPersonalAccountPage }
            waitFor { settingsLink.displayed }

            when:
            newMessages = []
            sleep(7000)
            println("checking e-mail")
            ReadingYandexEmail.main(newMessages, StaticData.getOwner1Name(), StaticData.getOwner1PasswordEmail())

            then:
            if ( i == 2  || i == 4 ) {
                waitFor { newMessages.size() > 0 }
                if ( i == 2 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации терминала измененен на Пройдена"}) }
                } else if ( i == 4 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации кампании измененен на Пройдена"}) }
                }
                i++
            } else {
                if ( i == 1 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации терминала измененен на Пройдена"}) == null }
                } else if ( i == 3 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации кампании измененен на Пройдена"}) == null }
                }
                i++
            }
        }
    }
}