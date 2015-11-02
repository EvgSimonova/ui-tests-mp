package com.terminal.pages

import geb.Page

class UserContentPage extends Page {
    static url=StaticData.getServerName()+"/member/userImages"
    static at = {title == "Mark project" }

    static content = {
        	createCompanyLink { $("div.user-menu li.item1 a") }
        	myPicturesLink { $("a", href: contains("userImages")) }
        	createLink { $("div.create-link a") }
		logoLink{ $("div.span8.logo").children().children()}
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		settingsLink{ $("div.user-menu li.item6 a")}

		
		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}
		
		imageUploadForm{ $("form", id:"imageUploadForm")}
		fileNameSpan{imageUploadForm.children().children().children()}
		
		//Image User
		conentList{ $("div.tabbable")}
		contentListImg{ conentList.find("H3")}
		nameImage{ $("span.image-desc-name.editable.editable-click")}
		editableInput{ $("div.editable-input")}
		editableBtn{ $("div.editable-buttons")}
		nameInput{ editableInput.find("input", class:"input-medium")}
		btnPrimaryImage{ editableBtn.find("button", type:"submit", class:"btn btn-primary editable-submit")}
		clearEditable{ $("span.editable-clear-x")}
		errorNameImage{ $("div.editable-error-block.help-block")}
		allImageList{ $("ul#all-images")}
		firstImage{ $("ul#all-images li:first-child")}
		activImage{ allImageList.find("li", class:"active")}
		nameActiveImage{ activImage.find("H3")}
		deleteImage{ firstImage.find("a", class:"close")}
		nameFirstImage{ firstImage.find("H3")}
		LiImage{ $("ul#all-images li").max { it.getAttribute("data-image-id") }} 
		nameSearchInput{ $("div.term-list-header").find("input", type:"search", class:"search")}
		deleteForm{ $("div.group-action")}
		btnYes{ deleteForm.find("a", class:"delete action")}
		numberImages{ $("div.image-menu label#total-content-counter a").children()}
		
    }
}
