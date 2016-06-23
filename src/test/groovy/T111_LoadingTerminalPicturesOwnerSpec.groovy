import com.terminal.pages.MainPage
import com.terminal.pages.OwnerPersonalAccountPage
import com.terminal.pages.OwnerTerminalListPage
import com.terminal.pages.StaticData
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

import java.awt.AWTException
import java.awt.Robot
import java.awt.event.KeyEvent
import java.awt.im.InputContext
import java.text.SimpleDateFormat

class T111_LoadingTerminalPicturesOwnerSpec  extends GebReportingSpec {

    private Locale ru = new Locale("ru", "RU")
    private Locale en = new Locale("en", "US")
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
        filtrName << Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
        filtrName << nameTerminal

        then:
        waitFor { cartCurrentTerminal.displayed }

        when:
        cartCurrentTerminal.find{it.nameTerminal == nameTerminal}.detailTerminal.click()

        then:
        waitFor { activeTerminal.displayed }
        waitFor { panLoading.displayed }

        when:
        btnLoading.click()
        Robot rob = new Robot()
        InputContext InCon = InputContext.getInstance()
        if (InCon.getLocale().getLanguage().equals("ru")) {
            rob.delay(300)
            rob.keyPress(KeyEvent.VK_SHIFT)
            rob.keyPress(KeyEvent.VK_ALT)
            rob.keyRelease(KeyEvent.VK_SHIFT)
            rob.keyRelease(KeyEvent.VK_ALT)
            rob.delay(300)
        }
        String fileName = StaticData.getDirImage() + "file1.png"
        for (char symvol : fileName.toCharArray()) {
             boolean needShiftPress = Character.isUpperCase(symvol) && Character.isLetter(symvol)
            if (needShiftPress) {
                rob.keyPress(KeyEvent.VK_SHIFT)
            }
            int event = KeyEvent.getExtendedKeyCodeForChar(symvol.hashCode())
            
            try {
                if (symvol.toString() == ":") {
                    rob.delay(300)
                    try {
                        rob.delay(300)
                        rob.keyPress(KeyEvent.VK_SHIFT)
                        rob.keyPress(KeyEvent.VK_ALT)
                        rob.delay(300)
                        rob.keyRelease(KeyEvent.VK_SHIFT)
                        rob.keyRelease(KeyEvent.VK_ALT)
                        rob.delay(300)
                        rob.keyPress(KeyEvent.VK_SHIFT)
                        rob.keyPress(KeyEvent.VK_6)
                        rob.delay(300)
                        rob.keyRelease(KeyEvent.VK_6)
                        rob.keyRelease(KeyEvent.VK_SHIFT)
                        rob.delay(300)
                        rob.keyPress(KeyEvent.VK_SHIFT)
                        rob.keyPress(KeyEvent.VK_ALT)
                        rob.delay(300)
                        rob.keyRelease(KeyEvent.VK_SHIFT)
                        rob.keyRelease(KeyEvent.VK_ALT)
                        rob.delay(300)
                    }catch (AWTException e) {
                        println(e.printStackTrace())
                    }
                    
                } else if(symvol.toString() == ".") {
                    rob.delay(300)
                    rob.keyPress(KeyEvent.VK_SHIFT)
                    rob.keyPress(KeyEvent.VK_ALT)
                    rob.keyRelease(KeyEvent.VK_SHIFT)
                    rob.keyRelease(KeyEvent.VK_ALT)
                    rob.delay(300)
                    rob.keyPress(event)
                    rob.keyRelease(event)
                    rob.delay(300)
                    rob.keyPress(KeyEvent.VK_SHIFT)
                    rob.keyPress(KeyEvent.VK_ALT)
                    rob.keyRelease(KeyEvent.VK_SHIFT)
                    rob.keyRelease(KeyEvent.VK_ALT)
                    rob.delay(300)
                    //rob.keyPress(KeyEvent.VK_PERIOD)
                    //rob.keyRelease(KeyEvent.VK_PERIOD)
                } else {
                    rob.keyPress(event)
                }
            } catch (Exception e) {}
            if (needShiftPress) {
                    rob.keyRelease(KeyEvent.VK_SHIFT)
            }
        }
        rob.keyPress(KeyEvent.VK_ENTER)

        then:
        waitFor { activeTerminal.displayed }
        waitFor { }

        }
}
