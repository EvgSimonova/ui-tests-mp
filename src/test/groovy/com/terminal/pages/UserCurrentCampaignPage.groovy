package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserCurrentCampaignPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/currentCompany"
	static at = { title == "Mark project" }

    static content = {
		headText{ $("H1.company")}
		currentCampaignsLink{ $("div.user-menu li.item2 a")}
		
		dateFilterPane{ $("div.date-sort")}
		dateFromInput{ dateFilterPane.find("input", name: "from")}
		dateToInput{ dateFilterPane.find("input", name: "to")}
		applyDateFilterButon{ dateFilterPane.find("input", type: "button",id:"filterByDate")}
		
		statisticPageLink{ $("div.user-menu li.item4.statistic a")}
	}
}