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

		terminalsTable{ middleZone.find("table", id: "table")}
		
		//terminalAddressInput{ middleZone.find("tr", //class:"tablesorter-headerRow").next().children().next().next().children()}
		
		terminalAddressInput{middleZone.find("input", type: "search", "data-column": "2")}
		searchTerminalButton{ middleZone.find("input", type:"submit")}
		
		
		//todo: переписать работу с таблицами на модули http://www.gebish.org/manual/0.9.2/modules.html
		//findedTerminalAddress{ }
		findedTerminalNumberLink{terminalsTable.find("tr", class:"odd").children().next().children()}
		
		selectedTerminalInfo{ $("div.image-preview.terminal")}
		selectedTerminaleditButton{ $("span.edit-btn")}
		
		editTerminalDialog{ $("div.edit-box")}
		
		terminalStatus{ editTerminalDialog.find("select", name: "terminalStatus")}
		terminalModerateStatus{ editTerminalDialog.find("select", name: "moderate")}
		saveTerminalButton{ editTerminalDialog.find("input", type: "submit", class: "btn")}
		
		inactivatedTerminalStatus{ findedTerminalAddress.next()}
		
    }
}
