package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/balans"
	static at = { title == "Баланс" }

    static content = {
		headText{ $("H1.company")}
		
		settingsLink{ $("div.user-menu li.item6 a")}
		
		robokassaForm{ $("form", id:"robokassa")}
		outSumInput{ robokassaForm.find("input", id:"outsum")}
		addMoneyButton{ robokassaForm.find("input", type:"button", name:"addMoney")}
		sucessBlock{ $("div.successblock")}
		errorBlock{ $("div.errorblock")}

		logoLink{ $("div.span8.logo").children().children()}
        	createCompanyLink { $("div.user-menu li.item1 a") }
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
        	myPicturesLink { $("a", href: contains("userImages")) }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}

		

	}
}