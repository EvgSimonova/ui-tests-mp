package com.terminal.pages

import geb.Page

class OwnerCashoutRequestPage extends Page {

    static url = StaticData.getServerName()+"/owner/cashout_request?moneyAmount="
	static at = { title == "real direct" }

    static content = {
        terminalsListLink { $("div.user-menu li.item1 a") }
        moneyLink { $("div.user-menu li.item2.money a") }
		settingsLink{ $("div.user-menu li.item5 a")}
		logoutLink{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		errorPage{ $("div.error-page") }
		headText{ $("H1.company")}
		openSupportLink{ errorPage.find("a", class:"open-support")}
    }
}
