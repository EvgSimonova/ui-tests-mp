package com.terminal.pages

import geb.Page

class UserPersonalAccountPage extends Page {

    static at = { $("meta", name: "pageId").@content == "member:personalAccount" }

    static content = {
        createCompanyLink { $("div.user-menu li.item1 a") }
        myPicturesLink { $("a", href: contains("userImages")) }
        createLink { $("div.create-link a") }
		
		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}
		balanceLink{ $("div.user-menu li.item4 a")}
		
    }
}
