package com.terminal.pages

import geb.Page

class DemoCreateCompanyPage extends Page {
	//todo: Добавить id на страницу
    static url="http://marc-project.ru:9090/terminal-company/member/createCompany/addTerminal"
	static at = { title == "Mark project" }

    static content = {

        loginLink{ $("a.sign")}
		registerLink{ $("a.reg")}
		
		groupsBlock{ $("div.groups")}
		addressFilterLink{ groupsBlock.find("li", id: "map_tab")}
		
		addressFilterPane{ $("div.adres-search")}
		addressInput{ addressFilterPane.find("input", id: "pac-input")}
		
		adressDistanceCheckbox{ addressFilterPane.find("input", id: "distance_checkbox")}
		addressDistanceInput{ addressFilterPane.find("input", id: "distance_input")}
		
		mapCanvas{ addressFilterPane.find("div", id: "map_canvas")}
		
		terminalsContainer{ $("div.jspPane")}
		terminalPane{ terminalsContainer.children().find("h3", text: contains("Second"))}
		
		rigthButtonsPane{ $("div.right-buttons")}
		addTerminalToCompanyDutton{ rigthButtonsPane.children()}
		
		middleTabs{ $("div.tabbable")}
		addedToCampaignTab{ middleTabs.children().find("li", text: "Добавленные в компанию")}
		
		bottomButtons{ $("div.nav-box.short")}
		nextButton{ bottomButtons.find("input", type: "button", class: "button.next-button")}
		
		
    }
 }
