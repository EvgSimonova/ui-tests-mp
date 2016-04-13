import com.terminal.pages.*
import email.ReadingYandexEmail
import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriverException

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class T209_CheckUserNotificationSpec extends GebReportingSpec {

    private String idImage
    private String nameCompany

    def "can get to settings page and Changing the status of moderation of content"() {
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        //Changing the status of moderation of content = true and verification notification mail
        when:
        to MainPage
        def newMessages = []
        ReadingYandexEmail.main(newMessages, StaticData.getUser1Name(), StaticData.getUser1PasswordEmail())
        nameCompany = ""
        idImage = "0"

        then:
        waitFor {loginLink.displayed}

        int i = 1
        while ( i < 9 ) {
            if ( i < 4 ) {
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
                waitFor { settingsLink.displayed }
            }

            when:
            settingsLink.click()

            then:
            waitFor { at UserSettingsPage }
            waitFor { formBlockNotifications.displayed }
            waitFor { inputContentModeration.displayed }
            waitFor { inputCampaingModeration.displayed }
            waitFor { inputCampaignStatus.displayed }
            waitFor { inputBalanceReplenished.displayed }
            waitFor { emailUser.displayed }

            if ( emailUser.value().length() == 0 ) {
                when:
                emailUser << StaticData.getUser1Name()

                then:
                waitFor { saveEmail.displayed }

                when:
                saveEmail.click()

                then:
                waitFor { emailUser.displayed }
                waitFor { emailUser.value() == StaticData.getUser1Name() }
                waitFor { formBlockNotifications.displayed }
                waitFor { inputContentModeration.displayed }
                waitFor { inputCampaingModeration.displayed }
                waitFor { inputCampaignStatus.displayed }
                waitFor { inputBalanceReplenished.displayed }
            }

            if ( i == 1 ) {
                if (inputContentModerationCheked == "true") {

                    when:
                    inputContentModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { formBlockNotifications.displayed }
                    waitFor { inputContentModeration.displayed }
                    waitFor { inputContentModeration.value() == false }
                    waitFor { contentLink.displayed }
                }
            } else if ( i == 2 ) {
                if (inputContentModerationCheked != "true") {

                    when:
                    inputContentModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputContentModeration.displayed }
                    waitFor { inputContentModeration.value() == "true" }
                    waitFor { logoutLink.displayed }
                } else {
                    waitFor { logoutLink.displayed }
                }

                when:
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }
            } else if ( i == 3 ) {
                if (inputCampaingModerationCheked == "true") {

                    when:
                    inputCampaingModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputCampaingModeration.displayed }
                    waitFor { inputCampaingModeration.value() == false }
                    waitFor { createCompanyLink.displayed }
                }
            } else if ( i == 4 ) {
                if (inputCampaingModerationCheked != "true") {

                    when:
                    inputCampaingModeration.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputCampaingModeration.displayed }
                    waitFor { inputCampaingModeration.value() == "true" }
                    waitFor { createCompanyLink.displayed }
                }
                waitFor { logoutLink.displayed }

                when:
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

                when:
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
                waitFor { settingsLink.displayed }
                sleep(5000)

            } else if ( i == 5 ) {
                if (inputBalanceReplenishedCheked == "true") {

                    when:
                    inputBalanceReplenished.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputBalanceReplenished.displayed }
                    waitFor { inputBalanceReplenished.value() == false }
                    waitFor { balanceLink.displayed }
                }
            } else if ( i == 6 ) {
                if (inputBalanceReplenishedCheked != "true") {

                    when:
                    inputBalanceReplenished.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputBalanceReplenished.displayed }
                    waitFor { inputBalanceReplenished.value() == "true" }
                    waitFor { balanceLink.displayed }
                }
            } else if ( i == 7 ) {
                if (inputCampaignStatusCheked == "true") {

                    when:
                    inputCampaignStatus.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputCampaignStatus.displayed }
                    waitFor { inputCampaignStatus.value() == false }
                    waitFor { myCampaignsLink.displayed }
                }
            } else if ( i == 8 ) {
                if (inputCampaignStatusCheked != "true") {

                    when:
                    inputCampaignStatus.click()

                    then:
                    waitFor { btnSaveNotifications.displayed }

                    when:
                    btnSaveNotifications.click()

                    then:
                    waitFor { inputCampaignStatus.displayed }
                    waitFor { inputCampaignStatus.value() == "true" }
                    waitFor { myCampaignsLink.displayed }
                }
            }

            if ( i == 1 ) {
                when:
                contentLink.click()

                //add image to moderation
                then:
                waitFor { at UserContentPage }
                waitFor { numberImages.displayed }

                when:
                def endIdImage
                if (numberImages.text() != "0") {
                    endIdImage = idLiImage
                } else {
                    endIdImage = "0"
                }

                then:
                waitFor { at UserContentPage }
                waitFor { conentList.displayed }

                when:
                StaticData.downloadPictures(driver,"myImg.jpg")

                then:
                waitFor { at UserContentPage }
                waitFor { conentList.displayed }
                waitFor { nameSearchInput.displayed }
                waitFor { allImageList.displayed }
                waitFor { LiImage.displayed }
                /*if (Integer.valueOf(idLiImage) > 9) {
                    try {
                        waitFor { LiImage.displayed }
                    } catch (Exception e) {
                        waitFor { firstImage.displayed }

                        when:
                        firstImage.click()
                        int raz = (Integer.valueOf(offsetLiImage) + 1) / 82

                        then:
                        waitFor { liList.displayed }
                        waitFor { jspScrollable.displayed }

                        when:
                        driver.findElement(By.id("scrollbarY")).sendKeys(Keys.HOME)
                        while ( 9 <= raz) {
                            driver.findElement(By.id("scrollbarY")).sendKeys(Keys.PAGE_DOWN)
                            raz = raz - 9
                        }
                        waitFor { LiImage.displayed }
                    }
                } else{
                    waitFor { LiImage.displayed }
                }*/
                //waitFor { nameLiImage == "myImg.jpg" }
                waitFor { idLiImage != endIdImage }
                waitFor { logoutLink.displayed }

                when:
                idImage = idLiImage
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

            } else if ( i == 3) {
                when:
                createCompanyLink.click()

                then:
                waitFor { at AddCampaingParamsPage }
                waitFor { paramsForm.displayed }

                when:
                nameCompany = "Тестовая кампания " +new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
                StaticData.CreatingTestCampaign(driver,"myImg.jpg",nameCompany)

                then:
                waitFor { at DemoCreateCompanyStartCompanyPage }
                waitFor { warningBlock.displayed}
                waitFor { logoutLink.displayed }

                when:
                logoutLink.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }

                when:
                StaticData.ModerateCampaignSpec(driver, nameCompany, 1)

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
                waitFor { settingsLink.displayed}
            } else if ( i == 5 || i == 6 ) {
                when:
                balanceLink.click()

                then:
                waitFor { at UserBalancePage }
                waitFor { outSumInput.displayed }
                waitFor { addMoneyButton.displayed }

                when:
                StaticData.BalanceCampaignSpec(driver,"10")

                then:
                waitFor { at UserBalancePage }
                waitFor { settingsLink.displayed }
                sleep(3000)

            } else if ( i == 7 || i == 8  ) {
                when:
                myCampaignsLink.click()
                sleep(3000)

                then:
                waitFor { at UserCurrentCampaignPage }
                waitFor { tableCurrent.displayed }
                waitFor { tbodyCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }

                if (Integer.valueOf(tbodyCurrent.getAttribute("childElementCount")) == 0 || cartCurrentCampaign.findAll({it.modCampaign == "Пройдена\n" + "оплатить и запустить"}).size() == 0) {
                    waitFor { createCompanyLink.displayed }

                    when:
                    createCompanyLink.click()

                    then:
                    waitFor { at AddCampaingParamsPage }
                    waitFor { paramsForm.displayed }

                    when:
                    nameCompany = "Тестовая кампания " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
                    StaticData.CreatingTestCampaign(driver,"myImg.jpg",nameCompany)

                    then:
                    waitFor { at DemoCreateCompanyStartCompanyPage }
                    waitFor { warningBlock.displayed }
                    if ( warningBlock.text() == "Ваша кампания сейчас находится на модерации." ) {
                        waitFor { logoutLink.displayed }

                        when:
                        logoutLink.click()

                        then:
                        waitFor { at MainPage }
                        waitFor { loginLink.displayed }

                        when:
                        StaticData.ModerateCampaignSpec(driver, nameCompany, 0)

                        then:
                        waitFor { at MainPage }
                        waitFor { loginLink.displayed }

                        when:
                        newMessages = []
                        ReadingYandexEmail.main(newMessages, StaticData.getUser1Name(), StaticData.getUser1PasswordEmail())
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
                    }
                    waitFor { myCampaignsLink.displayed }

                    when:
                    myCampaignsLink.click()

                    then:
                    waitFor { at UserCurrentCampaignPage }
                    waitFor { tableCurrent.displayed }
                    waitFor { cartCurrentCampaign.displayed }
                    waitFor { cartCurrentCampaign.size > 0 }
                } else {
                    waitFor { cartCurrentCampaign.displayed }
                }
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
                payLink.click()

                then:
                waitFor { at DemoCreateCompanyCompanyInfoPage}
                waitFor { balanceLink.displayed }
                sleep(3000)

                if (driver.title != "Оплата и запуск кампании") {
                    waitFor { errorBlockCampaign.displayed }
                    waitFor { sumCampaign.displayed }

                    when:
                    def sumCamp = Integer.toString(Integer.valueOf(sumCampaign.text().substring(0,sumCampaign.text().lastIndexOf(','))) + 1)
                    balanceLink.click()

                    then:
                    waitFor { at UserBalancePage }
                    waitFor { outSumInput.displayed }

                    when:
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
                    payLink.click()

                    then:
                    waitFor { at DemoCreateCompanyCompanyInfoPage}
                    waitFor { startBlock.displayed }

                } else {
                    waitFor { startBlock.displayed }
                }

                waitFor { startBlock.text() == "Поздравляем, ваша рекламная кампания оплачена и запущена!"}
                waitFor { settingsLink.displayed }
                sleep(5000)

                if ( i == 8 ) {
                    waitFor { logoutLink.displayed }

                    when:
                    logoutLink.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginLink.displayed }
                }
            }

            if ( i < 3) {
                //approval pictures administrator
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
                waitFor { imageModerationLink.displayed }
                waitFor { campaignModerationLink.displayed }


                when:
                imageModerationLink.click()
                sleep(3000)

                then:
                waitFor { at AdminModerateImagePage }
                waitFor { tableImage.displayed }
                waitFor { cartImage.displayed }
                waitFor { idSort.displayed }

                when:
                if (Integer.valueOf(idImage) > 5) {
                    idSort.click()
                    idSort.click()
                }
                def imageCart = cartImage.find({
                    it.emailUser == StaticData.getUser1Name() && it.idImager == Integer.valueOf(idImage)
                })

                then:
                waitFor { imageCart.ImageDetailsLink.displayed }

                when:
                imageCart.ImageDetailsLink.click()

                then:
                waitFor { formImage.displayed }
                waitFor { ImageStatusSelect.displayed }

                when:
                ImageStatusSelect.click()

                then:
                waitFor { ImageStatusTrue.displayed }
                waitFor { ImageStatusFalse.displayed }
                waitFor { ImageStatusProgress.displayed }

                when:
                if ( i == 1 ) {
                    ImageStatusFalse.click()
                } else {
                    ImageStatusTrue.click()
                }

                then:
                waitFor { commentModerate.displayed }
                waitFor { saveModerate.displayed }

                when:
                if ( i == 1 ) {
                    commentModerate << "Тестовая модерация картинки."
                }
                saveModerate.click()

                then:
                waitFor { at AdminModerateImagePage }
                waitFor { idSort.displayed }
                waitFor { tableImage.displayed }

                when:
                if (Integer.valueOf(idImage) > 5) {
                    idSort.click()
                }
                idSort.click()

                then:
                waitFor { cartImage.displayed }
                waitFor { logoutButton.displayed }
                if ( i == 1 ) {
                    waitFor {
                        cartImage.find({
                            it.emailUser == StaticData.getUser1Name() && it.idImager == Integer.valueOf(idImage)
                        }).statusImage == "Не пройдена"
                    }
                } else {
                    waitFor {
                        cartImage.find({
                            it.emailUser == StaticData.getUser1Name() && it.idImager == Integer.valueOf(idImage)
                        }).statusImage == "Пройдена"
                    }
                    sleep(3000)
                }

                //get a letter with acknowledgment of moderation pictures
                when:
                logoutButton.click()

                then:
                waitFor { at MainPage }
                waitFor { loginLink.displayed }
            }

            when:
            newMessages = []
            sleep(7000)
            ReadingYandexEmail.main(newMessages, StaticData.getUser1Name(), StaticData.getUser1PasswordEmail())

            then:
            if ( i == 2  || i == 4 || i == 6 || i == 8) {
                waitFor { newMessages.size() > 0 }
                if ( i == 2 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации контента измененен на \"Пройдена\""}) }
                } else if ( i == 4 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации кампании измененен на Пройдена"}) }
                } else if ( i == 6 ) {
                    waitFor { newMessages.find({it.subject == "Информация о платеже"}) }
                } else if ( i == 8 ) {
                    waitFor { newMessages.find({it.subject == "Статус рекламной кампании изменен на Запущена"}) }
                }
                i++
            } else {
                if ( i == 1 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации контента измененен на \"Пройдена\""}) == null }
                } else if ( i == 3 ) {
                    waitFor { newMessages.find({it.subject == "Статус модерации кампании измененен на Пройдена"}) == null }
                } else if ( i == 5 ) {
                    waitFor { newMessages.find({it.subject == "Информация о платеже"}) == null }
                } else if ( i == 7 ) {
                    waitFor { newMessages.find({it.subject == "Статус рекламной кампании изменен на Запущена"}) == null }
                }
                i++
            }
        }
    }
}