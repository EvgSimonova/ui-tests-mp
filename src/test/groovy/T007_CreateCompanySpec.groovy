

import com.terminal.pages.MainPage
import com.terminal.pages.DemoCreateCompanyPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import java.text.SimpleDateFormat

class T007_CreateCompanySpec extends GebReportingSpec {

    def "create Company in Demo mode"() {
        when:
        to MainPage
        at MainPage
        demoModeLink.click()

        then:
        at DemoCreateCompanyPage
        addressFilterLink.displayed

		when:
		addressFilterLink.click()
		
		then:
		addressFilterPane.displayed
		addressInput.displayed
		adressDistanceCheckbox.displayed
		mapCanvas.displayed
		
		when:
		addressInput << "Москва"
		addressInput << Keys.chord(Keys.ENTER)
		
		then:
		addressFilterPane.displayed
		addressInput.displayed
		mapCanvas.displayed
		adressDistanceCheckbox.displayed
		
		when: 
		adressDistanceCheckbox.click()
		
		then:
		addressInput.displayed
		addressDistanceInput.displayed
		adressDistanceCheckbox.displayed
		mapCanvas.displayed
		
		when:
		addressDistanceInput << "50"
		addressDistanceInput << Keys.chord(Keys.ENTER)
		
		then:
		addressInput.displayed
		addressDistanceInput.displayed
		adressDistanceCheckbox.displayed
		mapCanvas.displayed
		terminalsContainer.displayed
		terminalPane.displayed
		
		when:
		terminalPane.click()
		
		then:
		terminalsContainer.displayed
		terminalPane.displayed
		addTerminalToCompanyDutton.displayed
		
		when:
		addTerminalToCompanyDutton.click()
		
		then:
		terminalsContainer.displayed
		terminalPane.displayed
		addTerminalToCompanyDutton.displayed
		addedToCampaignTab.displayed
		
		when:
		addedToCampaignTab.click()
		
		then:
		addedToCampaignTab.displayed
		terminalsContainer.displayed
		terminalPane.displayed
		bottomButtons.displayed
		nextButton.displayed
		
		when:
		nextButton.click()
		
		then: 
		nextButton.displayed

		
    }
}