import com.terminal.pages.*
import email.ReadingYandexEmail
import geb.spock.GebReportingSpec
import org.codehaus.groovy.runtime.dgmimpl.arrays.ArrayMetaMethod
import org.openqa.selenium.Keys

import java.text.SimpleDateFormat

class T211_DetailedInfoCampaignUserSpec extends GebReportingSpec {

        private String nameCompany
        private int i

        def "can get to campaings page and Detailed information about the campaign"() {
            when:
            i = 1
            to MainPage
            at MainPage
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
            ArrayList detCampaign = new ArrayList()

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { tableCurrent.displayed }
            waitFor { tbodyCurrent.displayed }
            waitFor { createCompanyLink.displayed }

            while (i < 4) {
                // Create a new campaign and check the details
                if (i == 1) {
                    when:
                    createCompanyLink.click()

                    then:
                    waitFor { at AddCampaingParamsPage }
                    waitFor { paramsForm.displayed }

                    when:
                    nameCompany = "Тестовая кампания " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
                    StaticData.CreatingTestCampaign(driver, "myImg.jpg", nameCompany)

                    then:
                    waitFor { at DemoCreateCompanyStartCompanyPage }
                    waitFor { infoBlock.displayed }
                    waitFor { myCampaignsLink.displayed }

                    when:
                    myCampaignsLink.click()

                    then:
                    waitFor { at UserCurrentCampaignPage }
                    waitFor { tableCurrent.displayed }
                    waitFor { tbodyCurrent.displayed }
                    waitFor { cartCurrentCampaign.displayed }

                } else {
                    when:
                    if ( i == 2 ) {
                        nameCompany = cartCurrentCampaign.find{it.modCampaign == "В процессе"}.nameCampaign
                    } else {
                        nameCompany = cartCurrentCampaign.find { it.modCampaign == "Не пройдена" }.nameCampaign
                    }
                    logoutLink.click()

                    then:
                    waitFor { at MainPage }
                    waitFor { loginLink.displayed }

                    when:
                    if ( i == 2 ) {
                        StaticData.ModerateCampaignSpec(driver, nameCompany, 1)
                    } else {
                        StaticData.ModerateCampaignSpec(driver, nameCompany, 0)
                    }

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
                }

                when:
                detCampaign[0] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.dataCampaign
                detCampaign[1] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.nameCampaign
                detCampaign[2] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.startDataCampaign
                detCampaign[3] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.endDataCampaign
                detCampaign[4] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.numberCampaign
                detCampaign[5] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.sumCampaign
                detCampaign[6] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.statusCampaign
                detCampaign[7] = cartCurrentCampaign.find{it.nameCampaign == nameCompany}.modCampaign

                then:
                waitFor { at UserCurrentCampaignPage }
                waitFor { cartCurrentCampaign.displayed }

                when:
                cartCurrentCampaign.find{it.nameCampaign == nameCompany}.detailCampaign.click()

                then:
                waitFor { at UserDetailCampaignPage }
                waitFor { tableCurrent.displayed }
                waitFor { tbodyCurrent.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.size() == 1 }
                waitFor { allTerminals.displayed }
                waitFor { liList.displayed }
                waitFor { cartCurrentCampaign[0].terminalCampaign == Integer.toString(liList.size()) }
                waitFor { cartCurrentCampaign[0].dataCampaign == detCampaign[0] }
                waitFor { cartCurrentCampaign[0].nameCampaign == detCampaign[1] }
                waitFor { cartCurrentCampaign[0].startDataCampaign == detCampaign[2] }
                waitFor { cartCurrentCampaign[0].endDataCampaign == detCampaign[3] }
                waitFor { cartCurrentCampaign[0].terminalCampaign == detCampaign[4] }
                waitFor { cartCurrentCampaign[0].sumCampaign == detCampaign[5] }
                waitFor { cartCurrentCampaign[0].statusCampaign == detCampaign[6] }
                waitFor { cartCurrentCampaign[0].modCampaign == detCampaign[7] }

                when:
                cartCurrentCampaign[0].imageCampaign.click()

                then:
                waitFor { imageRealCampaign.displayed }
                waitFor { closeImageReal.displayed }

                when:
                closeImageReal.click()

                then:
                waitFor { at UserDetailCampaignPage }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { allTerminals.displayed }
                waitFor { firstImage.displayed }

                when:
                firstImage.click()

                then:
                waitFor { allTerminals.displayed }
                waitFor { activImage.displayed }
                waitFor { terminalInfo.displayed }
                //map comparison
                waitFor { lookMap.displayed }
                //status comparison
                waitFor { terminalStatus.displayed }
                waitFor { terminalCost.displayed }
                waitFor { terminalNumber.displayed }
                waitFor { cartCurrentCampaign.displayed }
                waitFor { firstImage.displayed }
                waitFor { firstImageP.displayed }
                waitFor { terminalInfo.displayed }
                waitFor { nameTrminalInfo.displayed }
                waitFor { nameFirstImage.displayed }
                //name comparison
                waitFor { (nameFirstImage.text()).substring(0,nameFirstImage.text().indexOf(" ")) == nameTrminalInfo.text() }
                //cost comparison
                waitFor { (terminalCost.text()).substring(0,terminalCost.text().indexOf("/")) == (firstImageP[2].text()).substring(0,firstImageP[2].text().indexOf("/")) }
                 //description comparison
                waitFor { terminalDesc.displayed }
                waitFor { terminalDesc.text() == firstImageP[0].text()  }

                if ( i != 3) {
                    waitFor { !cartCurrentCampaign[0].modCampaign.contains("оплатить и запустить") }
                }

                when:
                //cross-country comparisons
                String stroka
                String stroka1
                stroka = firstImageP[1].text().substring(14,firstImageP[1].text().length()-9)
                stroka1 = "будние " + stroka.substring(0,stroka.indexOf("/")+1) + "выходные " + stroka.substring(stroka.indexOf("/")+1)

                then:
                waitFor { terminalNumber.text() == firstImageP[1].text().replace(stroka,stroka1)}


                i++
            }

            when:
            cartCurrentCampaign[0].startCampaignLink.click()

            then:
            waitFor { at DemoCreateCompanyStartCompanyPage }
            waitFor { infoBlock.displayed }
            waitFor { infoBlock.text() == "Ваша кампания успешно прошла модерацию. Нажмите кнопку \"Оплатить и запустить\"."}
            waitFor { payLink.displayed }

            when:
            payLink.click()

            then:
            waitFor { at DemoCreateCompanyCompanyInfoPage}
            waitFor { balanceLink.displayed }
            waitFor { infoBlock.displayed }
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

            then:
            waitFor { at DemoCreateCompanyStartCompanyPage }
            waitFor { infoBlock.displayed }
            waitFor { payLink.displayed }

            when:
            payLink.click()

            then:
            waitFor { at DemoCreateCompanyCompanyInfoPage}
            waitFor { startBlock.displayed }
            waitFor { startBlock.text() == "Поздравляем, ваша рекламная кампания оплачена и запущена!"}
            waitFor { settingsLink.displayed }
            waitFor { logoutLink.displayed }

            when:
            logoutLink.click()

            then:
            waitFor { at MainPage }
            waitFor { loginLink.displayed }

        }
}
