package com.terminal.pages

import geb.Page

class FacebookLoginPage extends Page {

    static at = { title == "Log into Facebook | Facebook" }

    static content = {

	//login elements
        emailInput { $("input", name: "email", type: "text") }
	passwordInput{ $("input", name: "pass", type: "password")}
	loginbutton{ $("input", type: "submit", name: "login")}
    }
}
