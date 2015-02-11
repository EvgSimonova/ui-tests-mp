package com.terminal.pages

import geb.Page

class AdminModerateTerminalPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin/terminals"
	static at = { title == "Mark project" }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		terminalModreationLink{ leftMenuBlock.children().children().children().children().children().children().find("a", text: contains("Модерация терминалов"))}
		middleZone{ $("div.span9")}
		
		terminalAddressInput{ middleZone.find("input", type:"text", name: "address")}
		searchTerminalButton{ middleZone.find("input", type:"submit")}
		
		terminalsTable{ $("table", id: "table", class: "sortable")}
		//todo: переписать работу с таблицами на модули http://www.gebish.org/manual/0.9.2/modules.html
		findedTerminalAddress{ terminalsTable.children().next().children().children().next().next().next()}
		findedTerminalNumberLink{ findedTerminalAddress.previous().children()}
		
		selectedTerminalInfo{ $("div.image-preview.terminal")}
		selectedTerminaleditButton{ selectedTerminalInfo.find("span.edit-btn")}
		
		editTerminalDialog{ $("div.edit-box")}
		
		terminalModerateStatus{ editTerminalDialog.find("select", name: "moderate")}
		saveTerminalButton{ editTerminalDialog.find("input", type: "submit", class: "btn")}
		
    }
}
