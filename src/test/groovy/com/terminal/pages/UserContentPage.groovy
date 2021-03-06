package com.terminal.pages

import geb.Page

class UserContentPage extends Page {
    static url=StaticData.getServerName()+"member/userImages"
    static at = {title == "Mark project" }

    static content = {
        createCompanyLink { $("div.user-menu li.item1 a") }
        myPicturesLink { $("a", href: contains("userImages")) }
        createLink { $("div.create-link a") }
		
		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}
		balanceLink{ $("div.user-menu li.item4 a")}
		
		imageUploadForm{ $("form", id:"imageUploadForm")}
		fileNameSpan{imageUploadForm.children().children().children()}
		
		conentList{ $("div.tabbable")}
		contentListImg{ conentList.find("H3")}
		
    }
}
