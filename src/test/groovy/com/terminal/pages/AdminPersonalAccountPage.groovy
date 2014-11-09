package com.terminal.pages

import geb.Page

class AdminPersonalAccountPage extends Page {

	//todo: Добавить id на страницу
    static url="http://marc-project.ru:9090/terminal-company/admin"
	static at = { title == "Mark project" }

    static content = {
		logoutButton{ $("div.span4.navbar.singin.user-top").find("a", text: "Выйти")}
		leftMenuBlock{ $("div.container-fluid")}
		terminalModreationLink{ leftMenuBlock.children().children().children().children().children().children().find("a", text: contains("Модерация терминалов"))}
    }
}
