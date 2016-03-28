package com.terminal.pages

import geb.Page

class DemoCreateCompanyCheckAndConfirmPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/checkAndConfirm"
	static at = { title == StaticData.getPageTitle() }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		
		bottomButtons{ $("div.nav-box.short")}
		//createCampaignButton{ bottomButtons.find("input.button.next-button")}
		createCampaignButton{ bottomButtons.children().next().next().children()}
		
    }
 }
