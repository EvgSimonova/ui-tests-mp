package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class OwnerBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/owner/balance"
	static at = { title == "Mark project" }

    static content = {
		terminalsListLink { $("div.user-menu li.item1 a") }
        	moneyLink { $("div.user-menu li.item2.money a") }
		sucessBlock{ $("div.successRegister")}
		errorBlock{ $("div.errorblock")}
		dateBlock{ $("div.date-sort")}
		balanceHolder{ $("div#edit.edit-holder.adt")}
		balanceBoxBottom{ $("div.balance-box.bottom")}
		moneyButton{ balanceBoxBottom.find("a", id:"money-button")}
		balanceBox{ balanceHolder.find("div.edit-box")}
		balanceForm{ balanceBox.find("form", class: "form")}
		moneyAmount{ balanceForm.find("input", type:"number", name:"moneyAmount")}
		btnRequest{ balanceForm.find("input", class: "btn", type:"submit")}
	}
}