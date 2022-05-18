# Запуск Appium mobile tests

## Запуск в browserstack
* Создать файл с настройками:
> ```src/test/resources/property/browserstack.properties```
>
* Добавить данные:
>```
>loginWP=***** -логин от аккаунта Wikipedia
>passwordWP=***** -пароль от аккаунта Wikipedia
>deviceName=Google Pixel 3
>platformVersion=9.0
>user=bsuse***** - взять из browserstack
>key=3Sjd********** - взять из browserstack
>app=bs://16f7f4a9fdc91a1b1dc98de925d3c3714065f283
>url=http://hub.browserstack.com/wd/hub
>```
* Запуск тестов
> ```./gradlew clean test -Ddevice=browserstack```
## Запуск в emulator
* Создать файл с настройками:
> ```src/test/resources/property/emulator.properties```
* Добавить данные:
>```
>loginWP=***** -логин от аккаунта Wikipedia
>passwordWP=***** -пароль от аккаунта Wikipedia
>deviceName=******** - @DefaultValue("Pixel_4")
>platformName=Android
>platformVersion=**** -  @DefaultValue("11.0")
>app=https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk?raw=true
>url=http://localhost:4723/wd/hub
>```
* Запустить Appium на 4723 порту
* Запуск тестов
> ```./gradlew clean test -Ddevice=emulator```

## Запуск в реальном девайсе
* Создать файл с настройками:
> ```src/test/resources/property/real.properties```
>
* Узнать deviceName через adb:
>Команда: ```adb devices```
* Добавить данные:
>```
>loginWP=***** -логин от аккаунта Wikipedia
>passwordWP=***** -пароль от аккаунта Wikipedia
>deviceName=******** - deviceName из adb
>platformName=Android
>platformVersion=**** -  platformVersion версия из настроек смартфона
>app=https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk?raw=true
>url=http://localhost:4723/wd/hub
>```
* Запустить Appium на 4723 порту
* Запуск тестов
> ```./gradlew clean test -Ddevice=real```
>  