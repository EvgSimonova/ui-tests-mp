package com.terminal.pages

import geb.Page

class AdminLoginPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"/admin"
	static at = { title == "real direct" }

    static content = {

        loginForm{ $("form", name: "signInForm")}
		usernameInput { loginForm.find("input", type:"text", name: "j_username") }
        passwordInput { loginForm.find("input", type: "password", name: "j_password") }
		loginButton{ loginForm.find("input", type: "submit", class: "button")}
    }
}
