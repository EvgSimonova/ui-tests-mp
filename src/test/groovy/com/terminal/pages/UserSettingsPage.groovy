package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserSettingsPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/balans"
	static at = { title == "Mark project" }

    static content = {
		headText{ $("H1.company")}
		
		passwordForm{ $("form", id:"terminalUser")}
		newPasswordInput{ passwordForm.find("input", id:"newPassword", type:"password")}
		confirmPasswordInput{ passwordForm.find("input", id:"confirmPassword", type:"password")}
		oldPasswordInput{ passwordForm.find("input", id:"oldPassword", type:"password")}
		savePasswordButton{ passwordForm.find("input", class:"button", type:"submit")}
	}
}