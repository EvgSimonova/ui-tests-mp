package com.terminal.pages

import geb.Page

class OwnerPersonalAccountPage extends Page {

    static at = { $("meta", name: "pageId").@content == "owner:personalAccount" }

    static content = {
        terminalsListLink { $("div.user-menu li.item1 a") }
        moneyLink { $("div.user-menu li.item2.money a") }
		statisticLink { $("div.user-menu li.item3 a")}
		settingsLink{ $("div.user-menu li.item5 a")}
		logoutLink{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
  		
		helpLink { $("div.footer-menu").find("a", class:"open-support")}
		supportHolder{ $("div.support-holder")}
		supportBox{ $("div.support-box")}
		supportForm{ $("div.form")}
		supSubject{ supportForm.find("input", type:"text", name:"subject")}
		errorSubject{ $("div.subject.alert.alert-error") }
		errorTextarea{ $("div.text.alert.alert-error") }
		supTextarea{ errorTextarea.next().next() }
		sendBtn{ supportForm.find("input", type:"button") }
		sendtext{ $("div.sending") }
		sendAccept{ $("div.accept") }

	}
}
