# CrocMyProject

Вариант 21. По заданной тематике сформировать список фильмов для просмотра
Реализовать приложение, формирующее списки фильмов (топ 5, топ 10, топ
100) для просмотра по определенному жанру. Приложение должно
поддерживать добавление оценок к фильмам пользователями. Пользователь
может поставить только одну оценку фильму и впоследствии ее изменить, если
необходимо. Жанры, фильмы, оценки, пользователи должны сохраняться в БД.
Реализовать импорт жанров и фильмов из CSV-файлов. Реализовать механизм
экспорта жанров, фильмов в XML-файл.

Запуск производить в IDEA

Запустить сервер - java -cp h2-2.1.214.jar org.h2.tools.Server

Логин: admin

Пароль: admin


# Структура классов и пакетов

ConnectionConfig - Путь до места хранения БД, логином и паролем для удобства

Genre - Жанры:
    
    - Genre - Plain Old Java Object

    - GenreDao - Data Access Object

    - GenreImpoerter - Импорт жанров в БД

Movie - Фильмы:
    
    - Movie - Plain Old Java Object

    - MovieDao - Data Access Object

    - MovieImpoerter - Импорт фильмов в БД

Rating - Рейтинг:
    
    - Rating - Plain Old Java Object

    - RatingDao - Data Access Object

Users - Пользователи:
    
    - Users - Plain Old Java Object
   
    - UsersDao - Data Access Object