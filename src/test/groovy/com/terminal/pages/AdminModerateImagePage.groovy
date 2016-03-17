package com.terminal.pages

import geb.Module
import geb.Page

class CartRowImage extends Module {
	static content = {
		cell { $("td", it) }
		idImager { cell(0).text().toInteger() }
		ImageDetailsLink{ cell(0).find("a", class:"contentId") }
		dataCreate { cell(1).text() }
		emailUser { cell(3).text() }
		statusImage { cell(2).text() }
	}
}

class AdminModerateImagePage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin/contentModeration"
	static at = { title == StaticData.getPageTitle() }

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

		tableImage { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters")}
		cartImage { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr").collect { module CartRowImage, it } }

		editHolder{ $("div", class:"edit-holder adt", style:"display: block;")}
		formImage{ editHolder.find("form", class:"form")}
		ImageStatusSelect{ formImage.find("select", name:"moderateStatus")}
		ImageStatusSelectIndex{ ImageStatusSelect.getAttribute("selectedIndex") }
		ImageStatusTrue{ ImageStatusSelect.children()}
		ImageStatusFalse{ ImageStatusSelect.children().next()}
		ImageStatusProgress{ ImageStatusSelect.children().next().next()}
		commentModerate { formImage.find("textarea", name:"moderateComment") }
		saveModerate { formImage.find("input", class:"btn", type:"submit") }
		idSort { $("div.tablesorter-header-inner")}
    }
}
