package com.terminal.pages

mport geb.Module
import geb.Page

class CartRowBalance extends Module {
	static content = {
		cell { $("td", it) }
		numberBalance { cell(0).text().toInteger() }
		dataBalance  { cell(1).text() }
		sumBalance  { cell(2).text() }
		tipBalance { cell(3).text() }
		statusBalance { cell(4).text() }
	}
}

class UserBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/balans"
	static at = { title == "Баланс" }

    static content = {
		headText{ $("H1.company")}
		
		settingsLink{ $("div.user-menu li.item6 a")}
		balanceLink{ $("div.user-menu li.item5.payment a")}
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }		
		
		robokassaForm{ $("form", id:"robokassa")}
		outSumInput{ robokassaForm.find("input", name:"OutSum")}
		addMoneyButton{ robokassaForm.find("input", type:"button", name:"addMoney")}
		sucessBlock{ $("div.successblock")}
		errorBlock{ $("div.errorblock")}

		cartBalance { $("table#table.sortable.tablesorter.tablesorter-blue.hasFilters tbody tr").collect { module CartRowBalance, it } }		
		
		logoLink{ $("div.span8.logo").children().children()}
        	createCompanyLink { $("div.user-menu li.item1 a") }
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
        	myPicturesLink { $("a", href: contains("userImages")) }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}

		

	}
}