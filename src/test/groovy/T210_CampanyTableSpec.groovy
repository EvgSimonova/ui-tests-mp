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
    private def sizeTable
    private def sizeFiltr

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
                waitFor { cartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.size > 0 }
            }
        }
        waitFor { sortStartData.displayed }

        when:
        sortStartData.click()
        sortStartData.click()

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { cartCurrentCampaign.displayed }

        when:
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm")
        String sDate = sdf.format(new Date(System.currentTimeMillis()))
        cartCurrentCampaign.each {
            sDate.compareTo(it.startDataCampaign) != 1
            sDate = it.startDataCampaign
        }

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { tableCurrent.displayed }
        waitFor { cartCurrentCampaign.displayed }
        waitFor { filtrStartData.displayed }

        when:
        sizeTable = cartCurrentCampaign.size()
        String searchDataStart = cartCurrentCampaign[3].startDataCampaign
        filtrStartData << searchDataStart
        sizeFiltr = cartCurrentCampaign.findAll{it.startDataCampaign.equals(searchDataStart)}.size()

        then:
        waitFor { at UserCurrentCampaignPage }
        waitFor { filtrCartCurrentCampaign.displayed }
        waitFor { sizeFiltr == sizeTable - filtrCartCurrentCampaign.size()}

        while (filtrStartData.value() != "") {

            when:
            filtrStartData << Keys.BACK_SPACE

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { filtr.displayed }
            waitFor { filtrStartData.displayed }
            if (cartCurrentCampaign.findAll{it.startDataCampaign.contains(filtrStartData.value())}.size() == sizeTable) {
                when:
                filtrStartData << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE

                then:
                waitFor { filtrStartData.displayed }
                break
            } else {
                //waitFor { filtrCartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.findAll{it.startDataCampaign.contains(filtrStartData.value())}.size() == sizeTable - filtrCartCurrentCampaign.size() }
            }
        }

        waitFor { filtrEndData.displayed }
        waitFor { cartCurrentCampaign.displayed }

        when:
        String searchDataEnd = cartCurrentCampaign[3].endDataCampaign
        filtrEndData << searchDataEnd
        sizeFiltr = cartCurrentCampaign.findAll{it.endDataCampaign.equals(searchDataEnd)}.size()

        then:
        waitFor { at UserCurrentCampaignPage }
        //waitFor { filtrCartCurrentCampaign.displayed }
        waitFor { sizeFiltr == sizeTable - filtrCartCurrentCampaign.size() }

        while (filtrEndData.value() != "") {

            when:
            filtrEndData << Keys.BACK_SPACE

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { filtr.displayed }
            waitFor { filtrEndData.displayed }
            if ( cartCurrentCampaign.findAll{it.endDataCampaign.contains(filtrEndData.value())}.size() == sizeTable) {
                when:
                filtrEndData << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE

                then:
                waitFor { filtrEndData.displayed }
                break
            } else {
                //waitFor { filtrCartCurrentCampaign.displayed }
                waitFor { cartCurrentCampaign.findAll{it.endDataCampaign.contains(filtrEndData.value())}.size() == sizeTable - filtrCartCurrentCampaign.size() }
            }
        }

        if (cartCurrentCampaign.findAll{it.statusCampaignLink.text() == "В процессе"}.size() != 0) {
            when:
            auditCompany = cartCurrentCampaign.find { it.modCampaign == "В процессе" }.nameCampaign

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
            if (cartCurrentCampaign.findAll { it.statusCampaignLink.text() == "Пройдена" }.size() != 0) {
                auditCompany = cartCurrentCampaign.find { it.statusCampaignLink.text() == "Пройдена" }.nameCampaign
            } else {
                auditCompany = cartCurrentCampaign.find { it.statusCampaignLink.text() == "Не пройдена"}.nameCampaign
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
        waitFor { cartCurrentCampaign.displayed }

        when:
        cartCurrentCampaign.find { it.nameCampaign == auditCompany }.statusCampaignLink.click()

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
