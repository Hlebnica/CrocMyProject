# CrocMyProject

Запуск производить в IDEA

Запустить сервер - java -cp h2-2.1.214.jar org.h2.tools.Server

Логин: admin

Пароль: admin

# Создание БД
CREATE TABLE genre (

id INTEGER  AUTO_INCREMENT PRIMARY KEY,

genre_name VARCHAR(16) UNIQUE NOT NULL

);


CREATE TABLE movie (

id INTEGER AUTO_INCREMENT PRIMARY KEY,

title VARCHAR(64) UNIQUE NOT NULL,

genre_id INTEGER NOT NULL,

CONSTRAINT fk_movie_genre_id FOREIGN KEY (genre_id) REFERENCES genre(id) 

ON DELETE SET NULL 

ON UPDATE CASCADE

);


CREATE TABLE users (

id INTEGER AUTO_INCREMENT PRIMARY KEY,

user_name VARCHAR(32) UNIQUE NOT NULL

);


CREATE TABLE rating (

id INTEGER PRIMARY KEY AUTO_INCREMENT,

user_id INTEGER NOT NULL,

movie_id INTEGER NOT NULL,

rating_digit INTEGER NOT NULL,

CONSTRAINT uk_rating_user_movie unique(user_id, movie_id),

CONSTRAINT ch_rating_rating_digit CHECK(rating_digit >= 0 AND rating_digit <= 10),

CONSTRAINT fk_movie_user_id FOREIGN KEY (user_id) REFERENCES users(id) 

ON DELETE CASCADE 

ON UPDATE CASCADE,

CONSTRAINT fk_movie_movie_id FOREIGN KEY (movie_id) REFERENCES movie(id) 

ON DELETE CASCADE 

ON UPDATE CASCADE

);


# Структура классов и пакетов

ConnectionConfig - Путь до места хранения БД, логином и паролем для удобства

Genre - Жанры:
    
    - Genre - Plain Old Java Object

    - GenreDao - Data Access Object

Movie - Фильмы:
    
    - Movie - Plain Old Java Object

    - MovieDao - Data Access Object

Rating - Рейтинг:
    
    - Rating - Plain Old Java Object

    - RatingDao - Data Access Object

Users - Пользователи:
    
    - Users - Plain Old Java Object
   
    - UsersDao - Data Access Object