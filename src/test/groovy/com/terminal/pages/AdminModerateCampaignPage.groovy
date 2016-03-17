package com.terminal.pages

import geb.Page

class AdminModerateCampaignPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/admin/campaignModeration"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		campaignModerationLink{ leftMenuBlock.find("a", text: "Модерация кампаний")}
		contentTable{ $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters")}
		firstRow{ contentTable.children().next().children()}
		campaignNamedLink{ firstRow.find("a", class:"campaignId")}
		
		editBoxDialog{ $("div.edit-box")}
		moderationForm{ editBoxDialog.find("form", class: "form")}
		moderatinoStatusSelect{ editBoxDialog.find("select", name:"moderateStatus")}
		saveButton{ editBoxDialog.find("input", class:"btn", value:"Сохранить")}
		
    }
}
