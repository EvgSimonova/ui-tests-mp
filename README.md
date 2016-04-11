Состояние билда с тестами:[ ![Codeship Status for mptester2/ui-tests-mp](https://codeship.com/projects/69c00860-796d-0133-2e51-7addd7fed507/status?branch=develop)](https://codeship.com/projects/118715)

# Описание #
 
Тесты для веб-прилоежения marc-project


### Как пользоваться? ###

* Установить maven
* Скачать репозиторий git clone https://mptester2@bitbucket.org/mptester2/ui-tests-mp.git
* Скачать webdriver для браузера (для Chrome http://chromedriver.storage.googleapis.com/index.html, для других браузеров - вбиваем в google "<имя браузера> web driver" и ищем нужный
* Добавить в переменную Path (если вы на Windows, если на линуксе догадаетесь или загуглите =) ) путь к папке с web-драйверами
* Перейти в папку с репозиторием
* Запустить команду mvn clean install (она запустит на исполнение все тесты)
* Либо команду mvn -Dtest=<Имя теста> test (Она запсустит на исполнение конкретный тест <Имя теста>)


### С кем общаться если что-то не получается? ###

* Тимур Михайлов