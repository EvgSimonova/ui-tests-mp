package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class OwnerBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/owner/balance"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		terminalsListLink { $("div.user-menu li.item1 a") }
		moneyLink { $("div.user-menu li.item2.money a") }
		settingsLink{ $("div.user-menu li.item5 a")}
		logoLink{ $("div.span8.logo").children().children()}
		logoutLink{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		sucessBlock{ $("div.successRegister")}
		errorBlock{ $("div.errorblock")}
		dateBlock{ $("div.date-sort")}
		balanceHolder{ $("div#edit.edit-holder.adt")}
		balanceBoxBottom{ $("div.balance-box.bottom")}
		sumBalanceBox{ balanceBoxBottom.find("span")}
		moneyButton{ balanceBoxBottom.find("a", id:"money-button")}
		balanceBox{ balanceHolder.find("div.edit-box")}
		balanceForm{ balanceBox.find("form", class: "form")}
		moneyAmount{ balanceForm.find("input", type:"number", name:"moneyAmount")}
		btnRequest{ balanceForm.find("input", class: "btn", type:"submit")}
		alertSms{ $("div.alert.alert-success")}
	}
}