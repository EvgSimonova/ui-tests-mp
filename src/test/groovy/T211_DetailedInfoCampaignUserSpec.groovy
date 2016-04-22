import com.terminal.pages.*
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat

class T211_DetailedInfoCampaignUserSpec extends GebReportingSpec {

        private String nameCompany

        def "can get to campaings page and Detailed information about the campaign"() {
            when:
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

            then:
            waitFor { at UserCurrentCampaignPage }
            waitFor { tableCurrent.displayed }
            waitFor { tbodyCurrent.displayed }
            waitFor { createCompanyLink.displayed }

            if (Integer.valueOf(tbodyCurrent.getAttribute("childElementCount")) != 0 || cartCurrentCampaign.findAll{it.modCampaign.text() == "Пройдена оплатить и запустить"}.size() == 0) {

            } else {
                waitFor { cartCurrentCampaign.displayed }

                if (cartCurrentCampaign.findAll{it.modCampaign.text() == "Пройдена оплатить и запустить"}.size() != 0}) {
                when:
                cartCurrentCampaign.find {
                    it.modCampaign.text() == "Пройдена оплатить и запустить"
                }.detailCampaign.click()

            }





                }
            }
}
