package com.terminal.pages
import java.text.SimpleDateFormat


def static getServerName(){
	//return "http://real-directt.ru:9090/terminal-company"
	return "http://terminal-company.herokuapp.com/"
	//return "http://webcab.de/woe.htm"

}

def static setUserName(input){
	input << "mihailov-ta+spam33@ya.ru"
}

def static setUserPassword(input){
	input << "111111"
}

def static setOwnerName(input){
	input << "mihailov-ta+spam44@ya.ru"
}

def static setOwnerPassword(input){
	input << "111111"
}


def static setAdminName(input){
	input << "1@1.1"
}

def static setAdminPassword(input){
	input << "111"
}


def static setTerminalAddress(input){
	input << "Клязьма"
}


def static setTerminalName(input){
	input << "Новый терминал для тестирования 1"
}

def static setTerminalDescription(input){
	input << "Терминал находится нв первом этаже справа от входа. В форме кубика рубика, на каждой грани установлена одна плазма"
}

def static setTerminalGroupName(input){
	input<< "Группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())
}

def static renameTerminalGroupName(input){
	input<< "Переименованная группа терминалов 1 - "+new SimpleDateFormat("yyyyMMdd").format(new Date())
}