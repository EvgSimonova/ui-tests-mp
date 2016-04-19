package com.terminal.pages
import org.openqa.selenium.By
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


def static getServerName(){
	//return "http://real-directt.ru:9090/terminal-company"
	return "http://terminal-company.herokuapp.com/"
	//return "http://webcab.de/woe.htm"

}

def static setUserName(input){
	input << "mihailov-ta+spam33@ya.ru"
}

def static setUserPassword(input){
	input << "111111"
}

def static setOwnerName(input){
	input << "mihailov-ta+spam44@ya.ru"
}

def static setOwnerPassword(input){
	input << "111111"
}


def static setAdminName(input){
	input << "1@1.1"
}

def static setAdminPassword(input){
	input << "111111"
}


def static setTerminalAddress(input){
	input << "Клязьма"
}


def static setTerminalName(input){
	input << "Новый терминал для тестирования 1"
}

def static setTerminalDescription(input){
	input << "Терминал находится нв первом этаже справа от входа. В форме кубика рубика, на каждой грани установлена одна плазма"
}

def static setTerminalGroupName(input){
	input<< "Группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())
}

def static renameTerminalGroupName(input){
	input<< "Переименованная группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())
}


def static setCampaingName(input){
	input << "Тестовая кампания "+new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date())
}

def static setCampaignStartDate(input){
	input <<  new SimpleDateFormat("dd.MM.yyyy").format(new Date((new Date()).getTime()+ TimeUnit.DAYS.toMillis(2)))
}

def static setCampaignStartTime(input){
	input << "10:00"
}

def static setCampaignEndDate(input){
	input <<  new SimpleDateFormat("dd.MM.yyyy").format(new Date((new Date()).getTime()+ TimeUnit.DAYS.toMillis(3)))
}

def static setCampaignEndTime(input){
	input << "23:00"
}

def static setUserEmail(input){
	input << "mihailov-ta@ya.ru"
}

def static setUserImage(input){
	input << "D:\\MyFiles\\flower.jpg"
}

def static setUserEmailDropbox(input){
	input << "Mihailov-ta+spam2@ya.ru"
}

def static setUserPasswordDropbox(input){
	input << "Lhjg<jrc909"
}

def static getUser1Name(){
	return "symonova-eu-reklama@yandex.ru"
}

def static getUser1PasswordEmail(){
	return "9638520741"
}

def static getUser1Password(){
	return "111111"
}

def static getOwner1Name(){
	return "simonova-eu-owner@yandex.ru"
}

def static getOwner1PasswordEmail(){
	return "7418520963"
}

def static getOwner1Password(){
	return "111111"
}

def static getPageTitle() {
	return ("real direct")
}

def static getDirImage() {
	return (new File(".").getAbsolutePath().replace(".","") + File.separatorChar + "images" + File.separatorChar)
}

def static getSupportMail() {
	return ("support@real-direct.ru")
}

def static waitVisibaly(elt,driverThis) {
	new WebDriverWait(driverThis, 160).until(ExpectedConditions.visibilityOf(elt))
}

def static waitPresenceOfAll(elt,driverThis) {
	new WebDriverWait(driverThis, 160).until(ExpectedConditions.presenceOfAllElementsLocatedBy(elt))
}

def static LiVisibaly(idLi,driverThis) {
	if (driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idLi)}']")).displayed == false) {
		driverThis.findElement(By.id("scrollbarY")).sendKeys(Keys.HOME)
		int LiImage = (Integer.valueOf(driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idLi)}']")).getAttribute("offsetTop")))
		int raz = 81
		while (driverThis.currentUrl.startsWith("http://terminal-company.herokuapp.com/member/userImages") || driverThis.currentUrl.startsWith("http://terminal-company.herokuapp.com/member/createCompany/addImage")) {
			if (driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idLi)}']")).displayed || LiImage <= raz) {
				break
			} else {
				driverThis.findElement(By.id("scrollbarY")).sendKeys(Keys.PAGE_DOWN)
				raz = raz + 82*8
			}
		}
	}
	driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idLi)}']")).click()
}

def static downloadPictures(driverThis,nameImage) {
	WebDriverWait wait = new WebDriverWait(driverThis, 300)
	driverThis.currentUrl == "http://terminal-company.herokuapp.com/member/userImages"
	waitVisibaly(driverThis.findElement(By.id("total-content-counter")), driverThis)
	if (driverThis.findElements(By.id("multipartFile")).size() == 0){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("multipartFile")))
	}
	driverThis.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
	driverThis.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
	driverThis.executeScript("document.getElementById('multipartFile').removeAttribute('style');")
	if (driverThis.findElement(By.id("total-content-counter")).text != "Загруженные изображения (0)") {
		driverThis.findElement(By.id("all-images")).findElement(By.tagName("li")).displayed
	}
	int idOld
	def pictureSheet = driverThis.findElement(By.id("all-images"))
	def ourPistures = pictureSheet.findElements(By.tagName("li"))
	if (ourPistures.size() > 0) {
		def ourPisture = ourPistures.max { Integer.valueOf(it.getAttribute("data-image-id")) }
		idOld = Integer.valueOf(ourPisture.getAttribute("data-image-id"))
	} else { idOld = 0 }

	try {
		driverThis.findElement(By.id("multipartFile")).with {
			clear()
			sendKeys(getDirImage() + nameImage)
		}

	} catch (WebDriverException e) {
		println e

	} catch (ElementNotVisibleException e) {
		println e
	}

	try {
		if (driverThis.findElements(By.id("fancybox-loading")).size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("fancybox-loading")))
		}
	} catch (e) {}

	//waitVisibaly(driverThis.findElement(By.id("all-images")),driverThis)
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-image-id = '${Integer.toString(idOld + 1)}']")))

	if (driverThis.findElement(By.id("all-images")).findElements(By.tagName("li")).size() > 9 ) {
		LiVisibaly(idOld+1,driverThis)
	} else {
		driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idOld+1)}']")).click()
		driverThis.findElement(By.xpath("//li[@class = 'active']")).displayed
	}
	def newLi = driverThis.findElement(By.id("all-images")).findElements(By.tagName("LI"))
	def Limax = newLi.max{Integer.valueOf(it.getAttribute("data-image-id"))}
	assert Limax.getAttribute("data-image-id") == Integer.toString(idOld + 1)
}

def static PicturesDropbox(driverThis,nameImage) {
	WebDriverWait wait = new WebDriverWait(driverThis, 300)
	driverThis.currentUrl == "http://terminal-company.herokuapp.com/member/userImages"
	waitVisibaly(driverThis.findElement(By.id("total-content-counter")), driverThis)
	if (driverThis.findElements(By.className("dropin-btn-status")).size() == 0){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropin-btn-status")))
	}

	if (driverThis.findElement(By.id("total-content-counter")).text != "Загруженные изображения (0)") {
		driverThis.findElement(By.id("all-images")).findElement(By.tagName("li")).displayed
	}
	int idOld
	def pictureSheet = driverThis.findElement(By.id("all-images"))
	def ourPistures = pictureSheet.findElements(By.tagName("li"))
	if (ourPistures.size() > 0) {
		def ourPisture = ourPistures.max { Integer.valueOf(it.getAttribute("data-image-id")) }
		idOld = Integer.valueOf(ourPisture.getAttribute("data-image-id"))
	} else { idOld = 0 }

	try {
		String winHandleBefore = driverThis.getWindowHandle()
		driverThis.findElement(By.className("dropin-btn-status")).click()
		for(String winHandle:driverThis.getWindowHandles()){
			if (driverThis.title == "Sign into Dropbox" || driverThis.title == "Войти в Dropbox" ) {
				driverThis.switchTo().window(winHandle)
				assert driverThis.currentUrl.startsWith("https://www.dropbox.com/chooser?origin=")
				waitVisibaly(driverThis.findElement(By.className('clearfix credentials-form login-form'), driverThis))
				driverThis.findElement(By.xpath("//input[@name = 'login_email']")).with {
					clear()
					sendKeys("Mihailov-ta+spam2@ya.ru")
				}
				driverThis.findElement(By.xpath("//input[@name = 'login_password']")).with {
					clear()
					sendKeys("Lhjg<jrc909")
				}
				driverThis.findElement(By.xpath("//button[@textContent = 'ВойтиПродолжить']")).click()
			}
			waitVisibaly(driverThis.findElement(By.id("recent-file-list")),driverThis)
			driverThis.findElement(By.className("text-input-input autofocus")).with {
				clear()
				sendKeys(nameImage)
			}
			waitVisibaly(driverThis.findElement(By.id("search-file-list")),driverThis)
			def searchOurLi = driverThis.findElement(By.id('search-file-list"'))
			searchOurLi.findElement(By.tagName("LI")).click()
			searchOurLi.displayed
			assert driverThis.findElement(By.xpath("//li[@class = 'dropin-file  selectable selected']"))
			driverThis.findElement(By.id("select-btn")).click()
			driverThis.close()
			driverThis.switchTo().window(winHandleBefore)
		}

	} catch (WebDriverException e) {
		println e

	}

	try {
		if (driverThis.findElements(By.id("fancybox-loading")).size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("fancybox-loading")))
		}
	} catch (e) {}

	//waitVisibaly(driverThis.findElement(By.id("all-images")),driverThis)
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-image-id = '${Integer.toString(idOld + 1)}']")))

	if (driverThis.findElement(By.id("all-images")).findElements(By.tagName("li")).size() > 9 ) {
		LiVisibaly(idOld+1,driverThis)
	} else {
		driverThis.findElement(By.xpath("//li[@data-image-id = '${Integer.toString(idOld+1)}']")).click()
		driverThis.findElement(By.xpath("//li[@class = 'active']")).displayed
	}
	def newLi = driverThis.findElement(By.id("all-images")).findElements(By.tagName("LI"))
	def Limax = newLi.max{Integer.valueOf(it.getAttribute("data-image-id"))}
	assert Limax.getAttribute("data-image-id") == Integer.toString(idOld + 1)
}

def static ModerateContentSpec(driverThis,idImager,selectIndex) {
	driverThis.get(getServerName()+"/loginAdmin")
	driverThis.findElement(By.name("j_username")).with {
		clear()
		sendKeys("1@1.1")
	}
	driverThis.findElement(By.name("j_password")).with {
		clear()
		sendKeys("111111")
	}
	driverThis.findElement(By.cssSelector("input.button")).click()
	waitVisibaly(driverThis.findElement(By.linkText("Картинки")),driverThis)
	assert getServerName() + 'admin/' == driverThis.currentUrl
	driverThis.findElement(By.linkText("Картинки")).click()
	waitPresenceOfAll(By.tagName("tr"),driverThis)
	assert getServerName() + 'admin/contentModeration' == driverThis.currentUrl
	if (driverThis.findElements(By.tagName("tr")).size() > 5){
		def searchTr = driverThis.findElement(By.xpath("//tr[@class=\"tablesorter-filter-row tablesorter-ignoreRow\"]"))
		searchTr.findElements(By.tagName("INPUT")).find{ it.getAttribute('data-column') == "0"}.with {
			clear()
			sendKeys(idImager)
		}
	}
	driverThis.findElement(By.linkText(idImager)).click()
	waitPresenceOfAll(By.xpath("//div[@class=\'edit-holder adt\']"),driverThis)
	def ourContent = driverThis.findElements(By.xpath("//div[@class=\'edit-holder adt\']")).find{ it.getAttribute('style').contains("display: block") }
	assert ourContent.findElement(By.name('moderateStatus'))
	ourContent.findElement(By.name('moderateStatus')).click()
	new Select(ourContent.findElement(By.name("moderateStatus"))).selectByIndex(selectIndex)
	if (selectIndex == 0) {
		assert 'TRUE' == ourContent.findElement(By.name("moderateStatus")).getAttribute('value')
		ourContent.findElement(By.name('moderateComment')).with {
			clear()
			sendKeys("Тестовая модерация картинки пройдена")
		}
	} else {
		assert 'FALSE' == ourContent.findElement(By.name("moderateStatus")).getAttribute('value')
		ourContent.findElement(By.name('moderateComment')).with {
			clear()
			sendKeys("Тестовая модерация картинки не пройдена")
		}
	}
	ourContent.findElement(By.cssSelector("input.btn")).click()
	waitPresenceOfAll(By.tagName("tr"),driverThis)
	def ourmod = driverThis.findElements(By.tagName("tr")).find{it.text.startsWith(idImager + " ")}.findElements(By.tagName('td')).getAt(2)
	if (selectIndex == 0) {
		assert "Пройдена" == ourmod.getText()
	} else {
		assert "Не пройдена" == ourmod.getText()
	}
	driverThis.findElement(By.linkText("Выйти")).click()
	waitVisibaly(driverThis.findElement(By.className("sign")),driverThis)
}

def static CreatingTestCampaign(driverThis,nameImage,nameCompany) {
	WebDriverWait wait = new WebDriverWait(driverThis, 300)
	driverThis.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driverThis.currentUrl == getServerName() + "member/createCompany/addParams"
	driverThis.findElement(By.id("companyName")).with {
		clear()
		sendKeys(nameCompany)
	}
	driverThis.findElement(By.id("startDate")).click()
	waitPresenceOfAll(By.xpath("//td[@class=\'day\']"),driverThis)
	def ourDayIndex = driverThis.findElement(By.xpath("//td[@class=\'day active\']")).text
	driverThis.findElements(By.xpath("//td[@class=\'day\']")).find{it.text == Integer.toString(Integer.valueOf(ourDayIndex) + 1)}.click()
	driverThis.findElement(By.id("startTime")).click()
	driverThis.findElement(By.id("endDate")).click()
	waitPresenceOfAll(By.xpath("//td[@class=\'day new\']"),driverThis)
	def newday = driverThis.findElements(By.xpath("//td[@class=\'day new\']"))
	newday.get(newday.size()-1).click()
	driverThis.findElement(By.id("endTime")).click()
		/*def endTime = driverThis.findElements(By.xpath("//div[@class=\'bootstrap-datetimepicker-widget dropdown-menu pull-right\']")).find{ it.getAttribute('style').contains("display: block")}
		def endTimeOur = endTime.findElements(By.tagName("a")).find{ it.getAttribute('data-action').contains("incrementHours")}
		endTimeOur.click()
		endTimeOur.click()
		endTimeOur.click()
		endTimeOur.click()*/
	if (driverThis.findElements(By.id("submitButton")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitButton")))
	}
	driverThis.findElement(By.id("submitButton")).click()
	waitPresenceOfAll(By.tagName("LI"), driverThis)
	assert (driverThis.currentUrl.startsWith(getServerName() + 'member/createCompany/addImage') && (driverThis.currentUrl.endsWith('isOwnerWithoutEmail=false') || driverThis.currentUrl.endsWith('isOwnerWithoutEmail=true')))
	waitVisibaly(driverThis.findElement(By.xpath("//label[@id=\"moderation-passed-counter\"]")),driverThis)
	downloadPictures(driverThis,nameImage)
	def allImages = driverThis.findElement(By.id("all-images"))
	assert allImages.findElement(By.xpath("//li[@class=\"active\"]"))
	driverThis.findElement(By.className("right-buttons")).findElement(By.tagName("INPUT")).click()
	if (driverThis.findElements(By.className("grey-image")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("grey-image")))
	}
	def newAllImages = driverThis.findElement(By.xpath("//ul[@id=\"all-images\"]"))
	assert newAllImages.findElement(By.xpath("//li[@class=\"grey-image\"]"))
	driverThis.findElement(By.className("nav-box")).findElement(By.tagName("INPUT")).click()
	waitVisibaly(driverThis.findElement(By.id("all-terminals")),driverThis)
	waitPresenceOfAll(By.tagName("LI"),driverThis)
	assert getServerName() + 'member/createCompany/addTerminal' == driverThis.currentUrl
	def field = driverThis.findElement(By.id("all-terminals"))
	assert 0 < field.findElements(By.tagName('li')).size()
	field.findElements(By.tagName('li')).find{ it.text.contains("Количество свободных слотов: 0") == false}.click()
	waitVisibaly(driverThis.findElement(By.className("active")),driverThis)
	assert driverThis.findElement(By.xpath("//li[@class=\"active\"]"))
	driverThis.findElement(By.className("right-buttons")).findElement(By.tagName("INPUT")).click()
	waitPresenceOfAll(By.tagName("LI"),driverThis)
	if (driverThis.findElements(By.className("grey-terminal")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("grey-terminal")))
	}
	def newAllTerminals = driverThis.findElement(By.xpath("//ul[@id=\"all-terminals\"]"))
	assert newAllTerminals.findElement(By.xpath("//li[@class=\"grey-terminal\"]"))
	driverThis.findElement(By.xpath("//input[@value=\"далее\"]")).click()
	if (driverThis.findElements(By.tagName("create-block")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("create-block")))
	}
	driverThis.findElement(By.className("left")).displayed
	assert getServerName() + 'member/createCompany/checkAndConfirm' == driverThis.currentUrl
	driverThis.findElement(By.id("submitButton")).displayed
	driverThis.findElement(By.id("submitButton")).click()
	waitVisibaly(driverThis.findElement(By.linkText("Выйти")),driverThis)
	assert driverThis.currentUrl.startsWith(getServerName() + 'member/createCompany/startCompany')
	assert driverThis.findElement(By.xpath("//div[@class=\"alert alert-info\"]")).getText() == "Ваша кампания отправлена на модерацию. О результатах мы оповестим вас по e-mail."
}

def static ModerateCampaignSpec(driverThis,nameCompany,selectIndex) {
	driverThis.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driverThis.get(getServerName()+"/loginAdmin")
	driverThis.findElement(By.name("j_username")).with {
		clear()
		sendKeys("1@1.1")
	}
	driverThis.findElement(By.name("j_password")).with {
		clear()
		sendKeys("111111")
	}
	driverThis.findElement(By.cssSelector("input.button")).click()
	waitVisibaly(driverThis.findElement(By.linkText("Модерация кампаний")),driverThis)
	assert getServerName() + 'admin/' == driverThis.currentUrl
	driverThis.findElement(By.linkText("Модерация кампаний")).click()
	waitPresenceOfAll(By.tagName("tr"),driverThis)
	assert getServerName() + 'admin/campaignModeration' == driverThis.currentUrl
	if (driverThis.findElement(By.tagName("TBODY")).findElements(By.tagName("tr")).size() > 5){
		def searchTr = driverThis.findElement(By.xpath("//tr[@class=\"tablesorter-filter-row tablesorter-ignoreRow\"]"))
		searchTr.findElements(By.tagName("INPUT")).find{ it.getAttribute('data-column') == "1"}.with {
			clear()
			sendKeys(nameCompany)
		}
	}
	driverThis.findElement(By.tagName("TBODY")).displayed
	def ourtrs = driverThis.findElement(By.tagName("TBODY")).findElements(By.tagName("tr")).find{it.text.contains(nameCompany)}
	assert ourtrs.findElements(By.tagName('td'))
	def ourtd = ourtrs.findElements(By.tagName('td')).getAt(7)
	waitVisibaly(ourtd.findElement(By.xpath("//a[@class=\"campaignModeration\"]")))	
	ourtd.findElement(By.xpath("//a[@class=\"campaignModeration\"]")).click()
	waitPresenceOfAll(By.xpath("//div[@class=\'edit-holder adt\']"),driverThis)
	def ourCompany = driverThis.findElements(By.xpath("//div[@class=\'edit-holder adt\']")).find{ it.getAttribute('style').contains("display: block") }
	assert ourCompany.findElement(By.name('moderateStatus'))
	ourCompany.findElement(By.name('moderateStatus')).click()
	waitVisibaly(ourCompany.findElement(By.name("moderateStatus")),driverThis)
	new Select(ourCompany.findElement(By.name("moderateStatus"))).selectByIndex(selectIndex)
	if (selectIndex == 0) {
		assert 'TRUE' == ourCompany.findElement(By.name("moderateStatus")).getAttribute('value')
		ourCompany.findElement(By.name('moderateComment')).with {
			clear()
			sendKeys("Тестовая модерация кампании пройдена")
		}
	} else {
		assert 'FALSE' == ourCompany.findElement(By.name("moderateStatus")).getAttribute('value')
		ourCompany.findElement(By.name('moderateComment')).with {
			clear()
			sendKeys("Тестовая модерация кампании не пройдена")
		}
	}
	ourCompany.findElement(By.cssSelector("input.btn")).click()
	waitVisibaly(driverThis.findElement(By.tagName("tr")),driverThis)
	def ourmod = driverThis.findElements(By.tagName("tr")).find{it.text.contains(nameCompany)}.findElements(By.tagName('td')).getAt(7)
	if (selectIndex == 0) {
		assert "Пройдена" == ourmod.getText()
	} else {
		assert "Не пройдена" == ourmod.getText()
	}
	driverThis.findElement(By.linkText("Выйти")).click()
	waitVisibaly(driverThis.findElement(By.className("sign")),driverThis)
}

def static BalanceCampaignSpec(driverThis,sumCompany) {
	WebDriverWait wait = new WebDriverWait(driverThis, 160)
	assert driverThis.currentUrl == getServerName() + "member/balans"
	driverThis.findElement(By.id("OutSum")).with {
		clear()
		sendKeys(sumCompany)
	}
	driverThis.findElement(By.name("addMoney")).click()
	if (driverThis.findElements(By.id("index-box")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("index-box")))
	}
	assert driverThis.currentUrl.startsWith("https://auth.robokassa.ru/Merchant/Index/")
	driverThis.findElement(By.xpath("//img[@class = \"rect2\"]")).click()
	if (driverThis.findElements(By.id("payment-params-box")).size() == 0) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-params")))
	}
	assert driverThis.currentUrl.startsWith("https://auth.robokassa.ru/Merchant/Payment/")
	driverThis.findElement(By.id("CardNumber")).with {
		clear()
		sendKeys('1234123412341234')
	}
	driverThis.findElement(By.id("CVC")).with {
		clear()
		sendKeys('123')
	}
	driverThis.findElement(By.id("EMail")).with {
		clear()
		sendKeys(getUser1Name())
	}
	driverThis.findElement(By.id("BSubmit")).click()
	waitVisibaly(driverThis.findElement(By.id('state-buttons')),driverThis)
	assert driverThis.currentUrl.startsWith('https://auth.robokassa.ru/Merchant/State/')
	driverThis.findElement(By.id("state-buttons")).findElements(By.tagName("a")).find{it.text.contains("Успешная оплата")}.click()
	waitVisibaly(driverThis.findElement(By.className("mail")),driverThis)
	assert driverThis.currentUrl.startsWith('https://auth.robokassa.ru/Merchant/State/Done')
	driverThis.findElements(By.className("rk-button")).find{it.getAttribute("value") == "Вернуться в магазин"}.click()
	waitVisibaly(driverThis.findElement(By.xpath("//div[@class=\"alert alert-success\"]")),driverThis)
	assert getServerName() + "member/balans" == driverThis.currentUrl
	assert driverThis.findElement(By.xpath("//div[@class=\"alert alert-success\"]")).getText() == "Уважаемый " + getUser1Name() + ", пополнение баланса на сумму " + sumCompany + ",00 прошло успешно!"
	def sumText = driverThis.findElement(By.className("balance-box")).findElement(By.tagName("SPAN")).getText()
	assert Float.valueOf(sumText.substring(0,sumText.indexOf(' ')).replace(",", ".")) >= Float.valueOf(sumCompany)

}

/*if (driverThis.findElements(By.id("multipartFile")).size() == 0){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("multipartFile")))
	}
	driverThis.executeScript("document.getElementById('imageUploadForm').children[0].removeAttribute('class');")
	driverThis.executeScript("document.getElementById('uniform-multipartFile').removeAttribute('class');")
	driverThis.executeScript("document.getElementById('multipartFile').removeAttribute('style');")

	try {
		driverThis.findElement(By.id("multipartFile")).with {
			clear()
			sendKeys(getDirImage() + nameImage)
		}

	} catch (WebDriverException e) {
		println e

	} catch (ElementNotVisibleException e) {
		println e
	}*/