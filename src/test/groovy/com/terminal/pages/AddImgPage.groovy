package com.terminal.pages

import geb.Page

class AddImgPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()
	static at = { title == "WBMP On-line Editor" }

    static content = {

		docBody{ $("div.body")}
		uplForm{ docBody.children().next().children().children().children().next().children() }
		fileInput{ uplForm.children().children()}
		uploadBtn{ fileInput.next()}
		
    }
 }
