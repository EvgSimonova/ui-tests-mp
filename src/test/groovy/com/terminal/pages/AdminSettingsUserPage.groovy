package com.terminal.pages

import geb.Page

class AdminSettingsUserPage extends Page {
	
	static url=StaticData.getServerName()+"/admin/users"
	static at = { title == "Mark project" }
	
	static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		terminalModreationLink{ leftMenuBlock.children().children().children().children().children().children().find("a", text: contains("Модерация терминалов"))}
		settingsRightUserLink{ leftMenuBlock.find("a", text: "Список пользователей системы")}
		middleZone{ $("div.span9")}

		terminalsTable{ middleZone.find("table", id: "table")}

		//terminalAddressInput{ middleZone.find("tr", //class:"tablesorter-headerRow").next().children().next().next().children()}
		
		UserInput{middleZone.find("input", type: "search", "data-column": "3")}
		searchUserButton{ middleZone.find("input", type:"submit")}
		
		
		//todo: переписать работу с таблицами на модули http://www.gebish.org/manual/0.9.2/modules.html
		//findedTerminalAddress{ }
		findedUserNumberLink{terminalsTable.find("tr", class:"odd").children().next().children()}
		
		selectedUserSettingsInfo{ $("div.edit-box")}
		blockUserButton{ $("span.admin-block-status")}
		unblockUserButton{ $("a.admin-interface-links.block-user")}
		
		editTerminalDialog{ $("div.edit-box")}
		
		terminalStatus{ editTerminalDialog.find("select", name: "terminalStatus")}
		terminalModerateStatus{ editTerminalDialog.find("select", name: "moderate")}
		saveTerminalButton{ editTerminalDialog.find("input", type: "submit", class: "btn")}
		
		inactivatedTerminalStatus{ findedTerminalAddress.next()}
    }
}
