package com.terminal.pages

class LoginFailedPage extends MainPage {

    static url = StaticData.getServerName()+"/loginfailed"
    static at = { title == "real direct" }
    //static at = { $("meta", name: "pageId").@content == "LoginFailed" }

    static content = {
        errorblock { $("div.errorblock") }
    }
}
