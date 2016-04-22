package com.terminal.pages

import geb.Page

class UserDetailCampaignPage  extends Page {
    static at = { title == StaticData.getPageTitle() }

    static content = {
        headText{ $("H1.company")}
        currentCampaignsLink{ $("div.user-menu li.item2 a")}

        createCompanyLink{ $("div.user-menu li.item1 a") }
        myPicturesLink { $("a", href: contains("userImages")) }

        dateFilterPane{ $("div.date-sort")}
        dateFromInput{ dateFilterPane.find("input", name: "from")}
        dateToInput{ dateFilterPane.find("input", name: "to")}
        applyDateFilterButon{ dateFilterPane.find("input", type: "button",id:"filterByDate")}

        statisticPageLink{ $("div.user-menu li.item4.statistic a")}

        logoLink{ $("div.span8.logo").children().children()}
        createCompanyLink { $("div.user-menu li.item1 a") }
        myCampaignsLink { $("div.user-menu li.item2.campaigns a") }
        myPicturesLink { $("a", href: contains("userImages")) }
        balanceLink{ $("div.user-menu li.item5.payment a")}
        settingsLink{ $("div.user-menu li.item6 a")}

        tableCurrent{ $("div.user-table.current.tablesorter")}
        tbodyCurrent{ $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody")}
        cartCurrentCampaign { $("table#table.tablesorter.sortable.tablesorter-blue.hasFilters tbody tr").collect { module CartRowCurrentCampaign, it } }

        filtr{ $("tr.tablesorter-filter-row.tablesorter-ignoreRow")}
        filtrState{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(6)}
        filtrmModeration{filtr.find ("input", type:"search", class:"tablesorter-filter").getAt(7)}
        valueFiltrState{filtrState.getAttribute("value")}
        clickOutfix{ $("div.table-pager.tablesorter-pager").find("span", id:"table_pager_info") }

    }
}
