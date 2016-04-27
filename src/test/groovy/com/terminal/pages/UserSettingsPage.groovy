package com.terminal.pages

import geb.Page
import com.terminal.pages.StaticData

class UserSettingsPage extends Page {
	//todo: Добавить id на страницу
    static url = StaticData.getServerName()+"/member/settings"
	static at = { title == StaticData.getPageTitle() }

    static content = {
		headText{ $("H1.company")}
		createCompanyLink { $("div.user-menu li.item1 a") }
		myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
		balanceLink{ $("div.user-menu li.item5.payment a")}
		contentLink{$("div.user-menu li.item3.content a")}

		topBar{ $("div.span4.navbar.singin.user-top")}
		userNameLink{ topBar.children()}
		logoutLink{ topBar.children().next()}		
		
		logoLink{ $("div.span8.logo").children().children()}
		myPicturesLink { $("a", href: contains("userImages")) }
		settingsLink{ $("div.user-menu li.item6 a")}

		settingBlock{ $("div.setting-block")}
		settingChange{ settingBlock.children().next().children().next()}
		nameUser{ settingChange.find("input", id:"name", type:"text")}
		phoneUser{ settingChange.find("input", id:"phone", type:"text")}
		saveSettingChange{ settingChange.find("input", type:"submit") }
		userTop{ $("div.span4.navbar.singin.user-top")}
		nameUserTop{ userTop.children()}
		emailWrapper{ settingChange.next()}
		emailUser{ emailWrapper.find("input", id:"subscriptionEmail", type:"text")}
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
		inputContentModeration{ formBlockNotifications.find("input", id: 'contentModerationStatusChangedNootification1')}
		inputContentModerationCheked{ inputContentModeration.getAttribute('checked')}
		inputCampaingModeration{ formBlockNotifications.find("input", id: 'campaignModerationStatusChangedNotification1')}
		inputCampaingModerationCheked{ inputCampaingModeration.getAttribute('checked')}
		inputCampaignStatus{ formBlockNotifications.find("input", id: 'campaignStatusChangedNotification1')}
		inputCampaignStatusCheked{ inputCampaignStatus.getAttribute('checked')}
		inputBalanceReplenished{ formBlockNotifications.find("input", id: 'balanceReplenishedNotification1')}
		inputBalanceReplenishedCheked{ inputBalanceReplenished.getAttribute('checked')}
		btnSaveNotifications{ formBlockNotifications.find("input", type: "submit", class: "button")}
		
	}
}