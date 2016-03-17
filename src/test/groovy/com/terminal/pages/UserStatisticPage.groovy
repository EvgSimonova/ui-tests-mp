package com.terminal.pages
import geb.Page

class UserStatisticPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/statistic"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		headText{ $("H1.company")}
		
		balanceLink{ $("div.user-menu li.item5.payment a")}
				
	}
}