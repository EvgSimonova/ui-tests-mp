package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserStatisticPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/statistic"
	static at = { title == "real direct" }

    static content = {
		headText{ $("H1.company")}
		
		balanceLink{ $("div.user-menu li.item5.payment a")}
				
	}
}