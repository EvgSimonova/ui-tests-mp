package com.terminal.pages

import geb.Page

class AddCampaingTerminalPage extends Page {
	//todo: Добавить id на страницу
    static url=StaticData.getServerName()+"member/createCompany/addTerminal"
	static at = { title == "real direct" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}

		imgContainer{ $("div.jp-container")}
		imageLi{ imgContainer.children().children().children().children()}
		
		addressFilterPane{ $("div.adres-search")}
		addressInput{ addressFilterPane.find("input", id: "pac-input")}
		
		adressDistanceCheckbox{ addressFilterPane.find("input", id: "distance_checkbox")}
		addressDistanceInput{ addressFilterPane.find("input", id: "distance_input")}

		mapCanvas{ addressFilterPane.find("div", id: "map_canvas")}		
		
		terminalsContainer{ $("div.jspPane")}
		terminalPane{ terminalsContainer.children().find("h3", text: contains("Second"))}

		allTerminalDiv{ $("div.all-check")}
		terminalsContainer{ $("div.jspPane")}
		terminalPane{ terminalsContainer.children().find("h3", text: contains("Second"))}
		
		rigthButtonsPane{ $("div.right-buttons")}
		addTerminalToCompanyDutton{ rigthButtonsPane.children()}
		
		middleTabs{ $("div.tabbable")}
		addedToCampaignTab{ middleTabs.children().find("li", text: "Добавленные в кампанию")}

		
		rigthButtons{ $("div.right-buttons")}
		addImageToCampaignBtn{ rigthButtons.children()}
		
		bottomButtons{ $("div.nav-box.short")}
		nextButton{ $("input.button.next-button")}

		
    }
 }
