package com.terminal.pages

import geb.Module
import geb.Page
import com.terminal.pages.StaticData

class CartRowCurrentCampaign extends Module {
	static content = {
		cell { $("td", it) }
		dataCampaign { cell(0).text() }
		nameCampaign  { cell(1).text() }
		startDataCampaign { cell(2).text() }
		endDataCampaign { cell(3).text() }
		sumCampaign  { cell(5).text() }
		modCampaign { cell(7).text() }
		startCampaignLink{ cell(7).find("div.line a", text:"оплатить и запустить") }
		statusCampaignLink{ cell(7).find("a", class:"moderation-comment-link") }
		statusCampaign { cell(6).text() }
	}
}


class UserCurrentCampaignPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/currentCompany"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		headText{ $("H1.company")}
		currentCampaignsLink{ $("div.user-menu li.item2 a")}
		createCompanyLink{ $("div.user-menu li.item1 a") }
		myPicturesLink { $("a", href: contains("userImages")) }
		statisticPageLink{ $("div.user-menu li.item4.statistic a")}
		logoLink{ $("div.span8.logo").children().children()}
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}
		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}

		dateFilterPane{ $("div.date-sort")}
		dateFromInput{ dateFilterPane.find("input", name: "from")}
		dateToInput{ dateFilterPane.find("input", name: "to")}
		applyDateFilterButon{ dateFilterPane.find("input", type: "button",id:"filterByDate")}

		tableCurrent{ $("div.user-table.current.tablesorter")}
		tbodyCurrent{ $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody")}
		cartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr").collect { module CartRowCurrentCampaign, it } }
		filtrOddcartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr.blue.odd.filtered").collect { module CartRowCurrentCampaign, it } }
		filtrEvencartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr.blue.even.filtered").collect { module CartRowCurrentCampaign, it } }
		filtrCartCurrentCampaign { tbodyCurrent.find("tr", class:contains("filtered"))}

		filtr{ $("tr.tablesorter-filter-row.tablesorter-ignoreRow")}
		filtrState{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(6)}
		filtrStartData{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(2)}
		filtrEndData{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(3)}
		filtrmModeration{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(7)}
		valueFiltrState{filtrState.getAttribute("value")}
		clickOutfix{ $("div.table-pager.tablesorter-pager").find("span", id:"table_pager_info") }		

		sort{ $("tr.tablesorter-headerRow")}
		sortStartData{ sort.find("th").getAt(2)}

		groupActionHolder{ $("div.group-action-holder.item-11")}
		moderationComment{ $("p#moderation-comment-text")}
		closeModeration{groupActionHolder.find("div",class:"close")}
	}
}