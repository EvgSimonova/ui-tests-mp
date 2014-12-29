package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/balans"
	static at = { title == "Mark project" }

    static content = {
		headText{ $("H1.company")}
		currentCampaignsLink{ $("div.user-menu li.item2 a")}
		
		roboBox{ $("div.robo-box")}
		roboBoxForm{ roboBox.find("form", id:"robokassa")}
		outsumInput{ roboBoxForm.find("input", id:"outsum")}
		addMoneyButton{ roboBoxForm.find("input", name:"addMoney")}
		
		statisticPageLink{ $("div.user-menu li.item3 a")}
		balancePageLink{  $("div.user-menu li.item4 a")}
	}
}