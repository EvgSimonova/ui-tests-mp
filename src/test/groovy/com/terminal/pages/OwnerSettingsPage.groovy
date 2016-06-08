package com.terminal.pages

import geb.Page

class OwnerSettingsPage extends Page {
	
    static url = StaticData.getServerName()+"owner/settings"
	static at = { title == "Настройки"}

    static content = {
		terminalsListLink { $("div.user-menu li.item1 a") }
		moneyLink { $("div.user-menu li.item2.money a") }
		logoLink{ $("div.span8.logo").children().children()}
		settingBlock{ $("div.setting-block")}
		settingChange{ settingBlock.children().next().children().next()}
		nameOwner{ settingChange.find("input", id:"name", type:"text")}
		phoneOwner{ settingChange.find("input", id:"phone", type:"text")}
		saveSettingChange{ settingChange.find("input", type:"submit") }
		userTop{ $("div.span4.navbar.singin.user-top")}
		nameOwnerTop{ userTop.children()}
		emailWrapper{ settingChange.next()}
		emailOwner{ emailWrapper.find("input", id:"subscriptionEmail", type:"text")}
		saveEmail{ emailWrapper.find("input", type:"submit") }
		subscriptionEmail{ emailWrapper.find("span", id:"subscriptionEmail.errors", class:"error") }
		passwordForm{ settingBlock.children().next().next()}
		newPasswordInput{ passwordForm.find("input", id:"newPassword", type:"password")}
		errorNewPassword{ newPasswordInput.next()}
		confirmPasswordInput{ passwordForm.find("input", id:"confirmPassword", type:"password")}
		errorConfirmPassword{ confirmPasswordInput.next()}
		oldPasswordInput{ passwordForm.find("input", id:"password", type:"password")}
		errorOldPassword{ oldPasswordInput.next()}
		savePasswordButton{ passwordForm.find("input", type:"submit") }

		//alert
		formBlockNotifications{ $("div.notification-sett form#terminalUser")}
		inputStatusModeration{ formBlockNotifications.find("input", id: 'terminalModerationStatusChangedNotification1')}
		inputStatusModerationCheked{ inputStatusModeration.getAttribute('checked')}
		inputChangeNotifications{ formBlockNotifications.find("input", id: 'cashoutRequestNotification1')}
		inputChangeNotificationsCheked{ inputChangeNotifications.getAttribute('checked')}
		btnSaveNotifications{ formBlockNotifications.find("input", type: "submit", class: "button")}

	}
}