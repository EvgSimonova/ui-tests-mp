package com.terminal.pages

import geb.Page

class AddCampaingParamsPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/addParams"
	static at = { title == "Mark project" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		containerdiv{ $("div.container-fluid")}
		paramsForm{ containerdiv.find("form", id:"addParams")}
		campaignNameInput{ paramsForm.find("input", id:"companyName")}
		campaignStartdateInput{ paramsForm.find("input", id:"startDate")}
		campaignStartTimeInput{ paramsForm.find("input", id:"startTime")}
		campaignEnddateInput{ paramsForm.find("input", id:"endDate")}
		campaignEndTimeInput{ paramsForm.find("input", id:"endTime")}
		
		rigthButtons{ $("div.right-buttons")}
		addImageToCampaignBtn{ rigthButtons.children()}
		
		bottomButtons{ $("div.nav-box.short")}
		nextButton{ bottomButtons.find("input", id: "submitButton")}

		
    }
 }
