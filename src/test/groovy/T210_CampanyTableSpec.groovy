import com.terminal.pages.AddCampaingParamsPage
import com.terminal.pages.AdminLoginPage
import com.terminal.pages.AdminModerateCampaignPage
import com.terminal.pages.AdminPersonalAccountPage
import com.terminal.pages.DemoCreateCompanyStartCompanyPage
import com.terminal.pages.MainPage
import com.terminal.pages.StaticData
import com.terminal.pages.UserCurrentCampaignPage
import com.terminal.pages.UserPersonalAccountPage
import email.ReadingYandexEmail
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

import java.text.SimpleDateFormat


class T210_CampanyTableSpec extends GebReportingSpec{

    private String nameCompany
    private String auditCompany
    private String commentModerate

    def "can get to campaings page and working with the advertiser's campaings table"() {
        when:
        to MainPage
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
        waitFor { myCampaignsLink.displayed }

        when:
        myCampaignsLink.click()

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { tbodyCurrent.displayed }

        if (Integer.valueOf(tbodyCurrent.getAttribute("childElementCount")) < 5) {
            while (Integer.valueOf(tbodyCurrent.getAttribute("childElementCount")) != 5) {
                waitFor { createCompanyLink.displayed }

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
                waitFor { myCampaignsLink.displayed }

                when:
                myCampaignsLink.click()

                then:
                waitFor { at UserCurrentCampaignPage }
                waitFor { tableCurrent.displayed }
                waitFor { cartRowCurrentCampaign.displayed }
                waitFor { cartRowCurrentCampaign.size > 0 }
            }
        }
        waitFor { sortStartData.displayed }

        when:
        sortStartData.click()
        sortStartData.click()

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { cartRowCurrentCampaign.displayed }

        when:
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm")
        String sDate = sdf.format(new Date(System.currentTimeMillis()))
        cartRowCurrentCampaign.each {
            sDate.compareTo(it.startDataCampaign) != 1
            sDate = it.startDataCampaign
        }

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { cartRowCurrentCampaign.displayed }
        waitFor { filtrStartData.displayed }

        when:
        String searchDataStart = cartRowCurrentCampaign[3].startDataCampaign
        filtrStartData << searchDataStart

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { cartRowCurrentCampaign.displayed }
        waitFor {
            cartRowCurrentCampaign.each {
                if (it.startDataCampaign != "") {
                    it.startDataCampaign == searchDataStart
                }
            }
        }

        while (filtrStartData.value() != "") {

            when:
            filtrStartData << Keys.BACK_SPACE

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { cartRowCurrentCampaign.displayed }
            waitFor {
                cartRowCurrentCampaign.each {
                    if (it.startDataCampaign != "") {
                        it.startDataCampaign.contains(filtrStartData.value())
                    }
                }
            }
            waitFor { filtrStartData.displayed }
            if (cartRowCurrentCampaign.findAll{it.startDataCampaign == ""}.size() == 0) {
                filtrStartData << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
                break
            }
        }

        waitFor { filtrEndData.displayed }

        when:
        String searchDataEnd = cartRowCurrentCampaign[3].endDataCampaign
        filtrEndData << searchDataEnd

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { cartRowCurrentCampaign.displayed }
        waitFor {
            cartRowCurrentCampaign.each {
                if (it.endDataCampaign != "") {
                    it.endDataCampaign == searchDataEnd
                }
            }
        }

        while (filtrEndData.value() != "") {

            when:
            filtrEndData << Keys.BACK_SPACE

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { cartRowCurrentCampaign.displayed }
            waitFor {
                cartRowCurrentCampaign.each {
                    if (it.endDataCampaign != "") {
                        it.endDataCampaign.contains(filtrEndData.value())
                    }
                }
            }
            waitFor { filtrEndData.displayed }
            if (cartRowCurrentCampaign.findAll{it.endDataCampaign == ""}.size() == 0) {
                filtrEndData << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
                break
            }
        }

        if (cartRowCurrentCampaign.findAll{it.statusCampaignLink.text() == "В процессе"}.size() != 0) {
            when:
            auditCompany = cartRowCurrentCampaign.find { it.modCampaign.text() == "В процессе" }.nameCampaign

            then:
            waitFor { logoutLink.displayed }

            when:
            logoutLink.click()

            then:
            waitFor { at MainPage }
            waitFor { loginLink.displayed }

            when:
            StaticData.ModerateCampaignSpec(driver, auditCompany, 0)
            commentModerate = 'Тестовая модерация кампании пройдена'

        } else {
            when:
            if (cartRowCurrentCampaign.findAll { it.statusCampaignLink.text() == "Пройдена" }.size() != 0) {
                auditCompany = cartRowCurrentCampaign.find { it.statusCampaignLink.text() == "Пройдена" }.nameCampaign
            } else {
                auditCompany = cartRowCurrentCampaign.find { it.statusCampaignLink.text() == "Не пройдена"}.nameCampaign
            }

            then:
            waitFor { logoutLink.displayed }

            when:
            logoutLink.click()

            then:
            waitFor { at MainPage }
            waitFor { loginLink.displayed }

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
            waitFor { campaignModerationLink.displayed }

            when:
            campaignModerationLink.click()

            then:
            waitFor { at AdminModerateCampaignPage }
            waitFor { cartCurrentCampaign.displayed }

            when:
            cartCurrentCampaign.find{it.nameCampaign == auditCompany}.statusCampaignLink.click()

            then:
            waitFor { editBoxDialog.displayed }
            waitFor { moderationComment.displayed }
            waitFor { closeButton.displayed }

            when:
            commentModerate = moderationComment.text()
            closeButton.click()

            then:
            waitFor { logoutButton.displayed }

            when:
            logoutButton.click()
        }

        then:
        waitFor { at MainPage }
        waitFor { loginLink.displayed }

        when:
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
        waitFor { myCampaignsLink.displayed }

        when:
        myCampaignsLink.click()

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { tbodyCurrent.displayed }
        waitFor { cartRowCurrentCampaign.displayed }

        when:
        cartRowCurrentCampaign.find { it.nameCampaign == auditCompany }.statusCampaignLink.click()

        then:
        waitFor { moderationComment.displayed }
        waitFor { moderationComment.text() == commentModerate }
        waitFor { closeModeration.displayed }

        when:
        closeModeration.click()

        then:
        waitFor { logoutLink.displayed }

        when:
        logoutLink.click()

        then:
        waitFor { at MainPage }
    }
}
