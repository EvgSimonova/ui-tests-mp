package com.terminal.pages

import geb.Module

import java.text.SimpleDateFormat

class CartRowCurrentTerminal extends Module {
	static content = {
		cell { $("td", it) }
		dataTerminal { cell(0).text() }
		numberTerminal  { cell(1).text() }
		detailTerminal { cell(1).find("a") }
		adressTerminal { cell(2).text() }
		statusTerminal { cell(3).text() }
		nameTerminal { cell(4).text() }
		sumTerminal  { cell(5).text() }
		modTerminal { cell(6).text() }
		commentTerminal { cell(7).text() }
	}
}

class OwnerTerminalListPage extends OwnerPersonalAccountPage {
	//todo добавить id на страницу
	//todo добавить в переменные терминал над которым производятся действия
	static url = StaticData.getServerName()+"/owner/terminals"
	static at = { title == StaticData.getPageTitle() }


	static content = {
		addTerminalButton{ $("a.btn")}
		terminalsListLink { $("div.user-menu li.item1 a") }
		moneyLink { $("div.user-menu li.item2.money a") }
		settingsLink{ $("div.user-menu li.item5 a")}
		logoutLink{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		terminalTable{ $("table#table.sortable.terminal-table.tablesorter.tablesorter-blue.hasFilters")}
		cartCurrentTerminal{ $("table#table.sortable.terminal-table.tablesorter.tablesorter-blue.hasFilters tbody tr").collect{ module CartRowCurrentTerminal, it } }
		filtr{ $("tr.tablesorter-filter-row.tablesorter-ignoreRow")}
		filtrName{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(4)}

		//for create new terminal group and for rename terminal group
		groupMenuButton{ $("div.group-menu")}
		createGroupLink{ $("div.group-menu li.item-1 a")}
		renameGroupLink {$("div.group-menu li.item-2 a")}
		balancePageLink{ $("div.user-menu li.item2.money a")}

		createGroupHolder{ $("div.group-action-holder.item-1")}
		createGroupDialog{ createGroupHolder.children()}
		groupNameInput{ createGroupDialog.find("input", id: "newGroupName", type: "text")}
		createNewGroupButton{ createGroupDialog.find("input", class: "btn", type: "button")}

		renameGroupHolder{ $("div.group-action-holder.item-2")}
		renameGroupDialog{ renameGroupHolder.children()}
		renameGroupNameInput{renameGroupDialog.find("input", id: "editGroupName", type: "text")}
		renameGroupButton{ renameGroupDialog.find("input", class: "btn", type: "button")}

		groupList{ $("div.image-menu")}
		group1Link{ groupList.find("a", text: contains("Группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())))}
		renamedGroup1Link{ groupList.find("a", text: contains("Переименованная группа терминалов 1"))}

		//for add new terminal
		createTerminalHolder{ $("div.edit-holder.create")}
		createTerminalDialog{ createTerminalHolder.find("div.edit-box")}
		createTerminalForm{ createTerminalDialog.find("form", id: "newTerminal")}
		terminalAddressInput{ createTerminalForm.find("input", name:"street", type: "text")}
		findTerminalOnMapButton{ createTerminalForm.find("input", type: "button", class:"btn")}
		showMapLink{ createTerminalForm.find("a.open-map")}
		showMapDialog{ $("div.prev-map")}
		closeMapDialogLink{ showMapDialog.next()}
		terminalNameInput{ createTerminalForm.find("input", name:"name", type: "text")}
		terminalDescriptionTextarea{ createTerminalForm.find("textarea", name:"description", id: "description")}
		terminalGroupSelect{ createTerminalForm.find("select", name:"terminalGroup")}
		terminalGroupSelectIndex{ terminalGroupSelect.getAttribute("selectedIndex")}
		terminalGroupSelectInactive{ terminalGroupSelect.children()}
		terminalGroupSelectActive{ terminalGroupSelect.children().next()}
		terminalTypeSelect{ createTerminalForm.find("select", name:"terminalType")}
		terminalTypeSelectIndex{ terminalTypeSelect.getAttribute("selectedIndex")}
		terminalTypeSelectMonitor{ terminalTypeSelect.children()}
		terminalTypeSelectATM{ terminalTypeSelect.children().next()}
		terminalTypeSelectSmartphone{ terminalTypeSelect.children().next().next()}
		sliderRange{ $("div#slider-range.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all")}
		sliderLeft{ sliderRange.children().next()}
		sliderRight{ sliderRange.children().next().next()}
		audienceAgeBeginInput{ createTerminalForm.find("input", type:"text", id:"amount")}
		audienceAgeEndInput{ createTerminalForm.find("input", type:"text", id:"amount1")}
		weekAudienceInput{ createTerminalForm.find("input", type:"text", id:"weekdayAudience")}
		weekendAudienceInput{ createTerminalForm.find("input", type:"text", id:"weekendAudience")}
		startWorkTimeInput{createTerminalForm.find("input", type:"text", id:"startWorkTime")}
		endWorkTimeInput{createTerminalForm.find("input", type:"text", id:"endWorkTime")}
		operationSystemInput{createTerminalForm.find("input", type:"text", id:"operationSystem")}
		costInput{createTerminalForm.find("input", type:"text", id:"cost")}
		saveTerminalButton{createTerminalForm.find("input", type:"button", class:"btn", id:"saveButton")}
		errorAlert{ createTerminalForm.find("div", class:"alert alert-error", id:"create_error")}

		terminalPane{ $("div.jspPane")}
		terminalName{ terminalPane.find("h3", text: contains("Moscow"))}

		//for stop terminal
		stopTerminalLink{ terminalPane.find("a.pause")}
		stopTerminalHolder{ $("div.group-action-holder.item-6")}
		stopTerminalDialog{ stopTerminalHolder.find("div.group-action").find("div.title", text: "Остановить терминал")}
		acceptStopTerminalButton{ stopTerminalHolder.find("a.delete")}
		runTerminalLink{ terminalName.parent().parent().find("a.start")}

		//for add picture to terminal
		bodyTerminal{ $("body")}
		activeTerminal{ bodyTerminal.find("div", class:"edit-holder adt", style:"display: block;")}
		idTerminal{activeTerminal.getAttribute("id")}
		panLoading{activeTerminal.find("div", class:"photo-box", data:startsWith("images"))}
		btnLoading{panLoading.find("input",class:"file")}
		giffka{ bodyTerminal.find("div", id:"fancybox-loading") }
		photoAll{ panLoading.find("a", class:"fancy_image") }
		deletePhoto{ photoAll.find("div", class:"close-image")}
		photoReal{ bodyTerminal.find("div", class:"fancybox-wrap fancybox-desktop fancybox-type-image fancybox-opened")}
		closePhotoReal{ bodyTerminal.find("a", class:"fancybox-item fancybox-close")}
		errorPhoto{ bodyTerminal.find("div",id:"errorWrapper")}
	}
}