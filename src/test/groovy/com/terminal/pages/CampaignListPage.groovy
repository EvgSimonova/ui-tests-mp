package com.terminal.pages

import geb.Page

class CampaignListPage extends Page {

    static at = { title == "real direct" }
	static url = StaticData.getServerName()+"/member/currentCompany"

    static content = {
        campaignsTable { $("div.user-table.current.tablesorter")}
		stateDiv{ $("div.stateDiv350")}
		payLink{ stateDiv.next().children().children()}
		
    }
}
