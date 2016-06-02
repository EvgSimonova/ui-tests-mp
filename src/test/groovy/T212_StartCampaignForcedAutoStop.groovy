import com.terminal.pages.AddCampaingParamsPage
import com.terminal.pages.DemoCreateCompanyCompanyInfoPage
import com.terminal.pages.DemoCreateCompanyStartCompanyPage
import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import com.terminal.pages.UserBalancePage
import com.terminal.pages.UserCurrentCampaignPage
import com.terminal.pages.UserPersonalAccountPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

import java.text.SimpleDateFormat

class T212_StartCampaignForcedAutoStop  extends GebReportingSpec {

    private String nameCompany
    private String sumCamp

    def "can get to campaings page and start and stop campaign"() {
        when:
        println("go to Main page")
        to MainPage
        at MainPage
        nameCompany = ""
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
        for (int i = 1; i < 3; i++) {
            waitFor { createCompanyLink.displayed }

            when:
            println("Creating Campaing start")
            createCompanyLink.click()

            then:
            waitFor { at AddCampaingParamsPage }
            waitFor { paramsForm.displayed }

            when:
            nameCompany = "Тестовая кампания " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
            println("Creating Campaign in progress")
            StaticData.CreatingTestCampaignToday(driver, "myImg.jpg", nameCompany)

            then:
            waitFor { at DemoCreateCompanyStartCompanyPage }
            waitFor { infoBlock.displayed }
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
            println("go to Campaigns page")
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

            if (driver.title != "Оплата и запуск кампании") {
                waitFor { infoBlock.displayed }
                waitFor { sumCampaign.displayed }

                when:
                String payCamp
                try {
                    payCamp = sumCampaign.text().substring(0,sumCampaign.text().lastIndexOf('.'))
                } catch (e) {
                    payCamp = sumCampaign.text().substring(0,sumCampaign.text().lastIndexOf(','))
                }
                sumCamp = Integer.toString(Integer.valueOf(payCamp) + 1)
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
            waitFor { settingsLink.displayed }
            waitFor { myCampaignsLink.displayed }

            when:
            println("go to Campaigns page")
            myCampaignsLink.click()

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { tableCurrent.displayed }
            waitFor { cartCurrentCampaign.displayed }
            waitFor { filtrmModeration.displayed }
            waitFor { filtrNameCampaign.displayed }


            when:
            if (filtrmModeration.getAttribute("value") != "") {
                filtrmModeration << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
            }
            if (filtrNameCampaign.getAttribute("value") != "") {
                filtrNameCampaign << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
            }
            filtrNameCampaign << "Остановить"
            sleep(3000)

            then:
            waitFor { cartCurrentCampaign.find({it.nameCampaign.contains(nameCompany)}).statusCampaign == "Запущена" }

            if ( i == 1 ) {
                when:
                println("Campaign suspension process")
                cartCurrentCampaign.find({it.nameCampaign.contains(nameCompany)}).stopCapmaign.click()
                driver.switchTo().alert().accept()

                then:
                waitFor { at UserCurrentCampaignPage }
                waitFor { tableCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.find({it.nameCampaign == nameCompany}).statusCampaign == "Приостановлена" }

            } else {
                sleep(480000)
                waitFor { tableCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.find({it.nameCampaign == nameCompany}).statusCampaign == "Завершена" }

            }
        }
        waitFor { logoutLink.displayed }

        when:
        logoutLink.click()

        then:
        waitFor { at MainPage }
        waitFor { loginLink.displayed }
    }
}
