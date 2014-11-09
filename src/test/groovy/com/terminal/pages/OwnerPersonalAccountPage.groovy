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
    }
}
