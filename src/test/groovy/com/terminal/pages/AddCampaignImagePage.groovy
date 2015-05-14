package com.terminal.pages

import geb.Page

class AddCampaingImagePage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/addImage"
	static at = { title == "Mark project" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		imageUploadForm{ $("form", id:"imageUploadForm")}
		uploadImgDiv{ imageUploadForm.children().children()}
		fileNameSpan{imageUploadForm.children().children().children()}
		fileInput{ imageUploadForm.multipartFile}
		spanBtnName{ imageUploadForm.children().children().children().next().next()}
		
		uniForm{ $("div.group-action-holder.item-3").next().next().next().next().next().next().next().next().next()}
		
		imgContainer{ $("div.jp-container")}
		imageLi{ imgContainer.children().children().children().children()}
		
		rigthButtons{ $("div.right-buttons")}
		addImageToCampaignBtn{ rigthButtons.children()}
		
		bottomButtons{ $("div.nav-box")}
		nextButton{ bottomButtons.children().next().next()}

		
    }
 }
