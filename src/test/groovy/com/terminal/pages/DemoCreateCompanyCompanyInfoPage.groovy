package com.terminal.pages

import geb.Page

class DemoCreateCompanyCompanyInfoPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/member/createCompany/addTerminal"
	static at = { title == "real direct" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		
		bottonButtons{ $("div.nav-box.short")}
		createCampaignButton{ $("input.button.next-button")}
		
    }
 }
