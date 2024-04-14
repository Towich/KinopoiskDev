# <img src="https://github.com/Towich/KinopoiskDev/assets/100920758/3a98984a-3e1b-49d8-9aca-723475296189" width="64"> MobileCinema

**MobileCinema** - это мобильное приложение, которое помогает людям искать фильмы или сериалы для приятного времепрепровождения.

## Основной функционал (экран всех фильмов) 
1. Отображается список фильмов и сериалов
2. Реализована пагинация
3. Можно отфильтровать выдачу по:
    * жанру
    * стране производства
    * году выхода
    * возрастному рейтингу
    * по типу контента (сериал/фильм)
4. Поиск по названию фильма
5. Можно перейти на страницу фильма из выдачи
6. Сохранение истории поиска (последние 20 результатов)
7. Поиск осуществляется в момент когда с ввода последнего символа прошла 1 секунда (debounce)
8. Реализовано кэширование запросов и реализована возможность работы приложения в офлайн-моде
9. Реализована светлая и темная тема

## Основной функционал (экран фильма) 
1. Отображается информация о фильме или сериале, в том числе:
    * название фильма/сериала
    * описание
    * рейтинг
    * отзывы
    * постеры
2. Реализовано отображение заглушки в случае, если какой-то из списков пустой
3. При ошибке загрузки изображения по URL оно заменяется на заглушку
4. Реализована кнопка «назад», которая ведет на выдачу. Фильтры и номер страницы при этом сохраняются.
5. Реализована пагинация: 
    * для списка актеров (если их больше 10)
    * для списка сезонов и серий (если есть)
    * для отзывов пользователей
6. Постеры отображаются в виде «карусели»
7. Реализована светлая и темная тема

## Нефункциональные требования
1. Язык: Kotlin
2. Работа с сетью: Retrofit, OkHttp
3. Многопоточность: Kotlin Coroutines/Flow
4. View: Jetpack Compose
5. Сериализаторы: Kotlinx Serialization, Gson
6. Навигация: Jetpack Navigation
7. Хранение данных: SharedPreferences, Room
8. Архитектура: MVVM + UseCases
9. DI: Dagger
10. Загрузчик изображений: Coil
11. Tests: JUnit4, Mockito

## Скриншоты

### Экран всех фильмов
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/a3308050-2b4c-4966-886f-4d359b8417a2" width="190">
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/756e3cdb-36aa-4a1e-8657-cebf497dfae1" width="190">
<img src="https://github.com/Towich/MobileCinema/assets/100920758/82066920-9fa1-4645-a7c4-821312178651" width="190">
<img src="https://github.com/Towich/MobileCinema/assets/100920758/91f8ffc6-3c94-4baf-94fd-e99d9776ed27" width="190">

### Экран фильма
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/84ba8142-3a15-4778-861e-e5351bb73ae0" width="190">
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/91815f0b-42a8-4f8c-8f78-c5a9a1d929e0" width="190">
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/561abc8d-a1d8-4b4e-bc89-5f3ba57d765b" width="190">
<img src="https://github.com/Towich/KinopoiskDev/assets/100920758/414b797b-982a-45a1-a915-41472c9de7f2" width="190">

### Экран фильтров
<img src="https://github.com/Towich/MobileCinema/assets/100920758/6b631036-5844-413b-88f5-68578e490c66" width="190">
<img src="https://github.com/Towich/MobileCinema/assets/100920758/864b5682-2d33-4db1-9f54-24169526dbfb" width="190">
<img src="https://github.com/Towich/MobileCinema/assets/100920758/99479293-816f-44d2-89e2-aa0f8ec4fe4e" width="190">
<img src="https://github.com/Towich/MobileCinema/assets/100920758/edb4727c-be8d-4117-ada3-ded78a2e80a3" width="190">

## Дополнительная информация
В качестве источника информации использовалось [API Кинопоиска](https://api.kinopoisk.dev/documentation)

## Установка
1. Склонируйте репозиторий в вашу локальную папку: *git clone https://github.com/Towich/KinopoiskDev.git*
2. Откройте проект в Android Studio
3. Запустите приложение на эмуляторе или на устройстве
