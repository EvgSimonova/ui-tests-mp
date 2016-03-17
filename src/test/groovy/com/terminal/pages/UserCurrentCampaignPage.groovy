package com.terminal.pages

import geb.Module
import geb.Page

class CartRowCurrentCampaign extends Module {
	static content = {
		cell { $("td", it) }
		dataCampaign { cell(0).text().toInteger() }
		nameCampaign  { cell(1).text() }
		sumCampaign  { cell(5).text() }
		modCampaign { cell(7).text() }
		startCampaignLink{ cell(7).find("div.line a", text:"оплатить и запустить") }
		statusCampaign { cell(6).text() }
	}
}


class UserCurrentCampaignPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/currentCompany"
	static at = { title == StaticData.getPageTitle()  }

    static content = {
		headText{ $("H1.company")}
		currentCampaignsLink{ $("div.user-menu li.item2 a")}
		createCompanyLink{ $("div.user-menu li.item1 a") }
		myPicturesLink { $("a", href: contains("userImages")) }

		dateFilterPane{ $("div.date-sort")}
		dateFromInput{ dateFilterPane.find("input", name: "from")}
		dateToInput{ dateFilterPane.find("input", name: "to")}
		applyDateFilterButon{ dateFilterPane.find("input", type: "button",id:"filterByDate")}
		
		statisticPageLink{ $("div.user-menu li.item4.statistic a")}

		tableCurrent{ $("div.user-table.current.tablesorter")}
		tbodyCurrent{ $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody")}
		cartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr").collect { module CartRowCurrentCampaign, it } }

		filtr{ $("tr.tablesorter-filter-row.tablesorter-ignoreRow")}
		filtrState{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(6)}
		filtrmModeration{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(7)}
		valueFiltrState{filtrState.getAttribute("value")}
		clickOutfix{ $("div.table-pager.tablesorter-pager").find("span", id:"table_pager_info") }
	}
}