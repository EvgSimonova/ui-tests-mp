

import com.terminal.pages.MainPage
import com.terminal.pages.AddImgPage
import com.terminal.pages.DemoCreateCompanyPage
import com.terminal.pages.AddCampaingImagePage
import com.terminal.utils.*
import geb.*
import java.lang.*
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import org.openqa.selenium.*
import java.text.SimpleDateFormat
import org.openqa.selenium.JavascriptExecutor

class T0071_CreateCompanySpec extends GebReportingSpec {

        def "create Company in Demo mode 1"() {
        when:
        to AddImgPage
        at AddImgPage
		driver.executeScript("document.getElementsByTagName('body')[0].style.display='none';")
		
		then:
		uploadBtn.displayed
		
		when:
        fileInput << "D:\\MyFiles\\myImg.jpg"
		uploadBtn.click()

        then:
		fileInput.displayed
    }
	
}