package com.terminal.pages

import geb.Page

class DemoCreateCompanyStartCompanyPage extends Page {
	//todo: Добавить id на страницу
	static at = { title == StaticData.getPageTitle() }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		settingsLink{ $("div.user-menu li.item6 a")}
        myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
		
		topBar{ $("div.span4.navbar.singin.user-top")}
        userNameLink{ topBar.children()}
        logoutLink{ topBar.children().next()}

        payLink{ $("div.pay-link a")}

        breadcrumbs{ $("div.breadcrumbs.no-b")}
        infoBlock{ breadcrumbs.next()}
    }
 }
