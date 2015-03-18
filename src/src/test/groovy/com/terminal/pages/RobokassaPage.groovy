package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class RobokassaPage extends Page {
    static url = "http://test.robokassa.ru/ReturnResults.aspx?Culture=ru"
	static at = { title == "http://test.robokassa.ru/ReturnResults.aspx?Culture=ru" }

    static content = {
		sucessUrl{ $("input", value:"http://terminal-company.herokuapp.com/member/balans")}
		sucessButton{ $("input", name:'ctl00$CPHMain$CToSuccessAndFailUrl$CReturnToShopSuccess$BReturn')}
		
		failUrl{ $("input", value:"http://terminal-company.herokuapp.com/member/balans")}	
		failButton{ $("input", name:'ctl00$CPHMain$CToSuccessAndFailUrl$CReturnToShopFail$BReturn')}
	}
}