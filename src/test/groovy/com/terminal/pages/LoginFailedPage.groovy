package com.terminal.pages

class LoginFailedPage extends MainPage {

    static url = StaticData.getServerName()+"/loginfailed"
    static at = { title == "Mark project" }
    //static at = { $("meta", name: "pageId").@content == "LoginFailed" }

    static content = {
        errorblock { $("div.errorblock") }
    }
}
