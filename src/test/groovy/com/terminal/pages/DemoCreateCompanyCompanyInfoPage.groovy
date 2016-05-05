package com.terminal.pages

import geb.Page

class DemoCreateCompanyCompanyInfoPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/member/createCompany/companyInfo"
	static at = { title == StaticData.getPageTitle() || title == "Оплата и запуск кампании" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}

        balanceLink{ $("div.user-menu li.item5.payment a")}
        settingsLink{ $("div.user-menu li.item6 a")}
        myCampaignsLink { $("div.user-menu li.item2.campaigns a") }

        topBar{ $("div.span4.navbar.singin.user-top")}
        userNameLink{ topBar.children()}
        logoutLink{ topBar.children().next()}

        breadcrumbs{ $("div.breadcrumbs.no-b")}
        infoBlock{ breadcrumbs.next()}
        infoCampaign{ $("div.feature-column")}
        sumCampaign{ infoCampaign.children().children().next()}

        startBlock{ $("div.pay-title")}
    }
 }
