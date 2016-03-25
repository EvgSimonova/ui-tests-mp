package com.terminal.pages

import geb.Page

class AddCampaingParamsPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/addParams"
	static at = { title == StaticData.getPageTitle() }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		containerdiv{ $("div.container-fluid")}
		paramsForm{ containerdiv.find("form", id:"addParams")}
		campaignNameInput{ paramsForm.find("input", id:"companyName")}
		campaignStartdateInput{ paramsForm.find("input", id:"startDate")}
		campaignStartTimeChooser{ $("div.bootstrap-datetimepicker-widget.dropdown-menu").next()}
		campaignStartTimeUp{ campaignStartTimeChooser.find("a", class:"btn")}
		campaignStartTimeInput{ paramsForm.find("input", id:"startTime")}
		
		campaignEnddateInput{ paramsForm.find("input", id:"endDate")}
		campaignEndDateChooser{ $("div.bootstrap-datetimepicker-widget.dropdown-menu").next().next()}
		campaignEndDateSelector{ campaignEndDateChooser.find("td.day.new")}
		campaignEndTimeInput{ paramsForm.find("input", id:"endTime")}
		
		userEmail{ paramsForm.find("input", id:"newEmail") }
		
		recaptchaDiv{ $("div.g-recaptcha")}
		iframe{ recaptchaDiv.children().children().children()}
		
		myFrame(page: FramePage){iframe}		
		
		rigthButtons{ paramsForm.find("div.right-buttons")}
		addImageToCampaignBtn{ rigthButtons.children()}
		
		bottomButtons{ $("div.nav-box.short")}
		nextButton{ bottomButtons.find("input", id: "submitButton")}

		logoLink{ $("div.span8.logo").children().children()}
        	createCompanyLink { $("div.user-menu li.item1 a") }
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
        	myPicturesLink { $("a", href: contains("userImages")) }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}

		
    }
	

 }
 
 class FramePage extends Page{
	static content={
		captchaCheckbox{ $("div.recaptcha-checkbox-checkmark")}
		
	}
 }
