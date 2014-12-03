package com.terminal.pages

import java.text.SimpleDateFormat
import com.terminal.pages.StaticData
import geb.Page

class OwnerTerminalListPage extends OwnerPersonalAccountPage {
    //todo добавить id на страницу
	//todo добавить в переменные терминал над которым производятся действия
    static url = StaticData.getServerName()+"/terminal-company/owner/terminals" 
	 static at = { title == "Mark project" }
	
	
    static content = {
		addTerminalButton{ $("a.btn")}
		
		//for create new terminal group and for rename terminal group
		groupMenuButton{ $("div.group-menu")}
		createGroupLink{ $("div.group-menu li.item-1 a")}
		renameGroupLink {$("div.group-menu li.item-2 a")}
		
		createGroupHolder{ $("div.group-action-holder.item-1")}
		createGroupDialog{ createGroupHolder.children()}
		groupNameInput{ createGroupDialog.find("input", id: "newGroupName", type: "text")}
		createNewGroupButton{ createGroupDialog.find("input", class: "btn", type: "button")}
		
		renameGroupHolder{ $("div.group-action-holder.item-2")}
		renameGroupDialog{ renameGroupHolder.children()}
		renameGroupNameInput{renameGroupDialog.find("input", id: "editGroupName", type: "text")}
		renameGroupButton{ renameGroupDialog.find("input", class: "btn", type: "button")}
		
		groupList{ $("div.image-menu")}
		group1Link{ groupList.find("a", text: contains("Группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())))}
		renamedGroup1Link{ groupList.find("a", text: contains("Переименованная группа терминалов 1"))}
		
		//for add new terminal
		createTerminalHolder{ $("div.edit-holder.create")}
		createTerminalDialog{ createTerminalHolder.find("div.edit-box")}
		createTerminalForm{ createTerminalDialog.find("form", id: "newTerminal")}
		terminalAddressInput{ createTerminalForm.find("input", name:"address", type: "text")}
		findTerminalOnMapButton{ createTerminalForm.find("input", type: "button", class:"btn")}
		showMapLink{ createTerminalForm.find("a.open-map")}
		showMapDialog{ $("div.prev-map")}
		closeMapDialogLink{ showMapDialog.next()}
		terminalNameInput{ createTerminalForm.find("input", name:"name", type: "text")}
		terminalDescriptionTextarea{ createTerminalForm.find("textarea", name:"description", id: "description")}
		terminalGroupSelect{ createTerminalForm.find("select", name:"terminalGroup")}
		terminalTypeSelect{ createTerminalForm.find("select", name:"terminalType")}
		audienceAgeBeginInput{ createTerminalForm.find("input", type:"text", id:"amount")}
		audienceAgeEndInput{ createTerminalForm.find("input", type:"text", id:"amount1")}
		weekAudienceInput{ createTerminalForm.find("input", type:"text", id:"weekdayAudience")}
		weekendAudienceInput{ createTerminalForm.find("input", type:"text", id:"weekendAudience")}
		startWorkTimeInput{createTerminalForm.find("input", type:"text", id:"startWorkTime")}
		endWorkTimeInput{createTerminalForm.find("input", type:"text", id:"endWorkTime")}
		operationSystemInput{createTerminalForm.find("input", type:"text", id:"operationSystem")}
		costInput{createTerminalForm.find("input", type:"text", id:"cost")}
		saveTerminalButton{createTerminalForm.find("input", type:"submit", class:"btn")}
		
		terminalPane{ $("div.jspPane")}
		terminalName{ terminalPane.find("h3", text: contains("Клязьма"))}
		
		//for stop terminal
		stopTerminalLink{ terminalPane.find("a.pause")}
		stopTerminalHolder{ $("div.group-action-holder.item-6")}
		stopTerminalDialog{ stopTerminalHolder.find("div.group-action").find("div.title", text: "Остановить терминал")}
		acceptStopTerminalButton{ stopTerminalHolder.find("a.delete")}
		runTerminalLink{ terminalName.parent().parent().find("a.start")}
    }
}
