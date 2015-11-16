package com.terminal.pages

import geb.Page

class DropboxPage extends Page {
    static url = "https://www.dropbox.com/chooser?origin=http%3A%2F%2Fterminal-company.herokuapp.com&app_key=2izqqcwp477g1vp&link_type=direct&trigger=button&multiselect=false&extensions=.jpg%20.png%20.gif%20.bmp%20.mp4%20.mov&folderselect=false&iframe=false&version=2"
    static at = { title == "Войти в Dropbox" || title == "Dropbox Chooser" || title == "Sign in Dropbox"}

    static content = {
        RightPanel{ $("div#right-gray")}
        formAuthorization{ $("form.clearfix.credentials-form.login-form")}
        emailUser{ formAuthorization.find("input", class:"text-input-input autofocus", type:"email", name:"login_email")}
        passwordUser{ formAuthorization.find("input", class:"password-input text-input-input", type:"password", name:"login_password")}
        btnLogin{ formAuthorization.find("button", type:"submit", class:"login-button button-primary")}
        fileListPersonal{ $("div#chooser-main div.recent ul#recent-file-list.file-list")}
        inputSearch{ $("div#searchbar.text-input.label-hidden").find("input", class:"text-input-input autofocus", type:"text")}
        searchFilePersonal{ $("div#chooser-main div.search ul#search-file-list.file-list")}
        searchChooser{ $("div#chooser.search")}
        filePersonal{ searchFilePersonal.children()}
        activeFilePersonal{ searchFilePersonal.find("li", class:"dropin-file selectable selected")}
        btnPanel{ $("div#chooser-footer") }
        btnChoose{ btnPanel.find("button", class:"button-primary")}
    }
}
