package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/balans"
	static at = { title == "Mark project" }

    static content = {
		headText{ $("H1.company")}
		
		settingsLink{ $("div.user-menu li.item5 a")}
		
		robokassaForm{ $("form", id:"robokassa")}
		outSumInput{ robokassaForm.find("input", id:"outsum")}
		addMoneyButton{ robokassaForm.find("input", type:"button", name:"addMoney")}
				
	}
}