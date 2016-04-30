package com.terminal.pages

import geb.Module
import geb.Page

class CartRowDetaliCampaign extends Module {
    static content = {
        cell { $("td", it) }
        dataCampaign { cell(0).text() }
        nameCampaign  { cell(1).text() }
        startDataCampaign { cell(2).text() }
        endDataCampaign { cell(3).text() }
        terminalCampaign  { cell(4).text() }
        sumCampaign  { cell(5).text() }
        statusCampaign { cell(6).text() }
        modCampaign { cell(7).text() }
        startCampaignLink{ cell(7).find("div.line a", text:"оплатить и запустить") }
        statusCampaignLink{ cell(7).find("a", class:"moderation-comment-link") }
        auditoryCampaign { cell(8).text() }
        timeCampaign { cell(9).text() }
        imageCampaign { cell(10) }
    }
}

class UserDetailCampaignPage  extends Page {
    static at = { title == "Информация о рекламной кампании" }

    static content = {
        headText{ $("H1.company")}
        statisticPageLink{ $("div.user-menu li.item4.statistic a")}
        logoLink{ $("div.span8.logo").children().children()}
        createCompanyLink { $("div.user-menu li.item1 a") }
        myPicturesLink { $("a", href: contains("userImages")) }
        balanceLink{ $("div.user-menu li.item5.payment a")}
        settingsLink{ $("div.user-menu li.item6 a")}
        topBar{ $("div.span4.navbar.singin.user-top")}
        userNameLink{ topBar.children()}
        logoutLink{ topBar.children().next()}

        tableCurrent{ $("div.user-table.current")}
        tbodyCurrent{ $("table#table.sortable tbody")}
        cartCurrentCampaign { $("table#table.sortable tbody tr").collect { module CartRowDetaliCampaign, it } }

        // terminal info
        allTerminals{ $("ul#all-terminals")}
        terminalInfo{ $("div.terminal-info")}
        nameTrminalInfo{ terminalInfo.find("H3")}
        lookMap{ terminalInfo.find('a#context-map')}
        terminalStatus{ terminalInfo.find("p.terminal-status")}
        terminalCost{ terminalInfo.find("p.cost")}
        terminalNumber{ terminalInfo.find("p.number")}
        terminalDesc{ terminalInfo.find("p.description")}

        liList{ $("ul#all-terminals li")}
        firstImage{ $("ul#all-terminals li:first-child")}
        nameFirstImage{ firstImage.find("H3")}
        firstImageP{ firstImage.find("p")}
        activImage{ allTerminals.find("li", class:"active")}
        nameActiveImage{ activImage.find("H3")}

        imageRealCampaign{ $("img.fancybox-image")}
        closeImageReal{ $("a.fancybox-item.fancybox-close")}


    }
}
