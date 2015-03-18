package com.terminal.pages

import geb.Page

class AddCampaingParamsPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/addParam"
	static at = { title == "Mark project" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		containerdiv{ $("div.container-fluid")}
		paramsForm{ containerdiv.find("form", id:"addParams")}
		campaignNameInput{ paramsForm.find("input", id:"companyName")}
		
		rigthButtons{ $("div.right-buttons")}
		addImageToCampaignBtn{ rigthButtons.children()}
		
		bottomButtons{ $("div.nav-box")}
		nextButton{ bottomButtons.children().next()}

		
    }
 }
