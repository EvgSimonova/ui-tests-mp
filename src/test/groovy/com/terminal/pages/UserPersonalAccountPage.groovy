package com.terminal.pages

import geb.Page

class UserPersonalAccountPage extends Page {

    static at = { $("meta", name: "pageId").@content == "member:personalAccount" }

    static content = {
        	createCompanyLink { $("div.user-menu li.item1 a") }
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
        	myPicturesLink { $("a", href: contains("userImages")) }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}
        	createLink { $("div.create-link a") }
		
		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}
		
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
