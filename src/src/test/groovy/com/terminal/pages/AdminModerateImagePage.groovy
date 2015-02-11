package com.terminal.pages

import geb.Page

class AdminModerateImagePage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin/contentModeration"
	static at = { title == "Mark project" }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		imageModerationLink{ leftMenuBlock.find("a", text: "Картинки")}
		contentTable{ $("table.sortable")}
		
		
    }
}
