package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class OwnerBalancePage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/owner/balance"
	static at = { title == "Mark project" }

    static content = {

		dateBlock{ $("div.date-sort")}
		
	}
}