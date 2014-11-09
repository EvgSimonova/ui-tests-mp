package com.terminal.pages

import geb.Page

class UserPersonalAccountPage extends Page {

    static at = { $("meta", name: "pageId").@content == "member:personalAccount" }

    static content = {
        createCompanyLink { $("div.user-menu li.item1 a") }
        myPicturesLink { $("a", href: contains("userImages")) }
        createLink { $("div.create-link a") }
    }
}
