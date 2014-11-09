package com.terminal.pages

import geb.Page

class LoginFailedPage extends MainPage {

    static at = { $("meta", name: "pageId").@content == "LoginFailed" }

    static content = {
        errorblock { $("div.errorblock") }
    }
}
