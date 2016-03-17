package com.terminal.pages

import geb.Page

class DemoCreateCompanyCompanyInfoPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/member/createCompany/companyInfo"
	static at = { title == StaticData.getPageTitle() || title == "Оплата и запуск кампании"  }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
        balanceLink{ $("div.user-menu li.item5.payment a")}
        settingsLink{ $("div.user-menu li.item6 a")}

        topBar{ $("div.span4.navbar.singin.user-top")}
        userNameLink{ topBar.children()}
        logoutLink{ topBar.children().next()}
		
		//bottonButtons{ $("div.nav-box.short")}
		//createCampaignButton{ $("input.button.next-button")}

        errorBlockCampaign{ $("div.errorblock")}
        infoCampaign{ $("div.feature-column")}
        sumCampaign{ infoCampaign.children().children().next()}

        startBlock{ $("div.pay-title")}
    }
 }
