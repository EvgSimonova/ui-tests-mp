package com.terminal.pages

import geb.Page
import geb.Module;

class CartRow extends Module {
    static content = {
        cell { $("td", it) }
        idUser { cell(0).text().toInteger() }
        userDetailsLink{ cell(0).find("a.user-details-link") }
        emailUser { cell(2).text() }
        roleUser { cell(3).text() }
        statusUser { cell(4).text() }
    }
}

class AdminModerateUserPage extends Page {

    static url=StaticData.getServerName()+"/admin/users"
    static at = { title == "Mark project" }

    static content = {
        logoutButton { $("div.span4.navbar.singin.user-top").find("a", text: "Выйти") }

        leftMenuBlock { $("div.container-fluid") }

        terminalModreationLink {
            leftMenuBlock.children().children().children().children().children().children().find("a", text: contains("Модерация терминалов"))
        }

        imageModerationLink { leftMenuBlock.find("a", text: "Картинки") }
        campaignModerationLink { leftMenuBlock.find("a", text: "Модерация кампаний") }

        settingsRightUserLink { leftMenuBlock.find("a", text: "Список пользователей системы") }
        tableUser { $("table#table.sortable.tablesorter.tablesorter-blue.hasFilters")}
        cartItems { $("table#table.sortable.tablesorter.tablesorter-blue.hasFilters tbody tr").collect { module CartRow, it } }
        quickFormSearch { $("form.quick-search-form")}
        inputSearch { quickFormSearch.find("input.search") }
        editBoxUser { $("div.span9").find("div", class: "edit-holder edit-user adt", style: "display: block;") }
        activeBlockUser { editBoxUser.find("a.admin-interface-links.block-user") }
        groupAction{ $("div.span9").find("div", class: "group-action-holder item-8", style: "display: block;") }
        deleteBlockUser { groupAction.find("a.delete.block-user-link")}
        noDeleteBlockUser { groupAction.find("a.delete")}
    }
}