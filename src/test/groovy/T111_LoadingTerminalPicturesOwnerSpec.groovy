import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec

import java.text.SimpleDateFormat

class T111_LoadingTerminalPicturesOwnerSpec  extends GebReportingSpec {

    private String nameTerminal

    def "can get to Terminal list page and Loading the Pictures of terminal"() {
        when:
        println("go to Main page and login")
        to MainPage

        then:
        waitFor {loginLink.displayed}

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
        waitFor { terminalsListLink.displayed }

        when:
        println("go to terminal list page")
        terminalsListLink.click()

        then:
        waitFor { at OwnerTerminalListPage }
        waitFor { addTerminalButton.displayed }

        when:
        println("Add new terminal")
        nameTerminal = "Тестовый автоматический терминал " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())
        StaticData.AddNewTerminalOwnerSpec(driver, nameTerminal)

        then:
        waitFor { at OwnerTerminalListPage }
        waitFor { cartCurrentTerminal.displayed }
        waitFor { filtrName.displayed }

        when:
        filtrName << nameTerminal

        then:
        waitFor { cartCurrentTerminal.displayed }

        when:
        cartCurrentTerminal.find{it.nameTerminal == nameTerminal}.detailTerminal.click()

        then:
        waitFor { activeTerminal.displayed }
        waitFor { btnLoadingPicture.displayed }

        when:
        btnLoadingPicture.click()

        then:
        waitFor { activeTerminal.displayed }


    }
}
