package com.terminal.pages

import geb.Page

class AdminModerateImagePage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin/contentModeration"
	static at = { title == "real direct" }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		imageModerationLink{ leftMenuBlock.find("a", text: "Картинки")}
		contentTable{ $("table.tablesorter.sortable.tablesorter-blue.hasFilters")}
		firstRow{ contentTable.children().next().next()}
		imgIdLink{ firstRow.find("a.contentId")}
		
		editBoxDialog{ $("div.edit-box")}
		moderationForm{ editBoxDialog.find("form", class: "form")}
		moderatinoStatusSelect{ editBoxDialog.find("select", name:"moderateStatus")}
		saveButton{ editBoxDialog.find("input", class:"btn", value:"Сохранить")}
		
    }
}
