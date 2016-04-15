package com.terminal.pages

import geb.Module
import geb.Page

class RowCurrentCampaign extends Module {
	static content = {
		cell { $("td", it) }
		dataCampaign { cell(0).text() }
		nameCampaign  { cell(1).text() }
		startDataCampaign { cell(2).text() }
		endDataCampaign { cell(3).text() }
		sumCampaign  { cell(5).text() }
		modCampaign { cell(7).text() }
		statusCampaignLink{ cell(7).find("a", class:"campaignModeration") }
		statusCampaign { cell(6).text() }
	}
}

class AdminModerateCampaignPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/admin/contentModeration"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		campaignModerationLink{ leftMenuBlock.find("a", text: "Модерация кампаний")}
		contentTable{ $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters")}
		firstRow{ contentTable.children().next().children()}
		campaignNamedLink{ firstRow.find("a", class:"campaignId")}
		cartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr").collect { module RowCurrentCampaign, it } }
		
		editBoxDialog{ $("div", class:"edit-holder adt", style:"display: block;") }
		moderationForm{ editBoxDialog.find("form", class: "form")}
		moderationComment{ moderationForm.find("textarea", name:"moderateComment")}
		moderatinoStatusSelect{ editBoxDialog.find("select", name:"moderateStatus")}
		saveButton{ editBoxDialog.find("input", class:"btn", value:"Сохранить")}
		closeButton{ editBoxDialog.find("div",class:"close")}
		
    }
}
