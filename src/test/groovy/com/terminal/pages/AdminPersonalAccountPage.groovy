package com.terminal.pages

import geb.Page

class AdminPersonalAccountPage extends Page {

	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		
		leftMenuBlock{ $("div.container-fluid")}
		
		terminalModreationLink{ leftMenuBlock.children().children().children().children().children().children().find("a", text: contains("Модерация терминалов"))}
		
		imageModerationLink{ leftMenuBlock.find("a", text: "Картинки")}
		campaignModerationLink{ leftMenuBlock.find("a", text: "Модерация кампаний")}
		settingsRightUserLink{ leftMenuBlock.find("a", text: "Список пользователей системы")}
    }
}
