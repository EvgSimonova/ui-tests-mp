package com.terminal.pages

import geb.Page

class MainPage extends Page {

    static at = { title == StaticData.getPageTitle() }

    static content = {

        messageBox { $("div.alert.alert-Success") }
	
	//login elements
        loginLink { $("a.sign") }
        loginDialog { $("div.sign-box") }
        closeDialogLink { loginDialog.find("div.close") }
        loginForm { loginDialog.find("form", name: "signInForm") }
        usernameInputOnLoginForm { loginForm.find("input", name: "j_username", type: "text") }
        passwordInputOnLoginForm { loginForm.find("input", name: "j_password", type: "password") }
        loginButton { loginForm.find("input", type: "submit") }

	//register elements
        registrationLink { $("a.reg") }
        registrationDialog { $("div.reg-box") }
        form { registrationDialog.find("form", name: "register_form") }
        usernameInput { form.find("input", name: "email", type: "text") }
        passwordInput { form.find("input", name: "password", type: "password") }
        confirmPasswordInput { form.find("input", name: "confirm_password", type: "password") }
        generatePasswordCheckbox { form.find("input", name: "generatePassword", type: "checkbox") }
        roleSelect { form.find("select", name: "role") }
        registerButton { form.find("input", type: "submit") }

	//social register block
	socialButtonsBlock{ registrationDialog.find("div.social")}
	fbRegisterButton{ socialButtonsBlock.children().children()}

		
		errorblock{ $("div.errorblock")}
		
		remindPasswordDialog{ $("div.password-box")}
		forgotPasswordLink{$("a.forgot-password")}
		forgotPasswordForm{remindPasswordDialog.find("form", name: "forgotPasswordForm")}
		forgotPasswordEmail{forgotPasswordForm.find("input", id: "forgot-password-email", type: "text")}
		forgotPasswordButton{forgotPasswordForm.find("input", type: "button")}
		feedbackInfo{ $("div.feedback").next()}
		
		demoModeLink{ $("a", text: "Попробовать сейчас.")}
	
	//Account page
	AccountPageLink{ $("div.span4.navbar.singin.user-top").children()}
	logoutLink{ AccountPageLink.next()}
    }
}
