package com.terminal.pages

import geb.Page

class DemoCreateCompanyStartCompanyPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/member/createCompany/startCompany"
	static at = { title == "Mark project" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		
		header{ $("h1.company")}
    }
 }
