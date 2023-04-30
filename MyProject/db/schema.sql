-- Таблица жанров
CREATE TABLE genre (
id INTEGER  AUTO_INCREMENT PRIMARY KEY,
genre_name VARCHAR(16) UNIQUE NOT NULL
);

-- Таблица фильмов
CREATE TABLE movie (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(64) UNIQUE NOT NULL,
genre_id INTEGER NOT NULL,
CONSTRAINT fk_movie_genre_id FOREIGN KEY (genre_id) REFERENCES genre(id) 
ON DELETE SET NULL 
ON UPDATE CASCADE
);

-- Таблица пользователей
CREATE TABLE users (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(32) UNIQUE NOT NULL
);

-- Таблица рейтинга
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

-- Выбор топа рейтинга фильмов
SELECT m.TITLE, AVG(r.rating_digit) AS avg_rating 
FROM MOVIE m 
INNER JOIN RATING r ON m.ID = r.MOVIE_ID 
INNER JOIN GENRE g  ON m.GENRE_ID  = g.ID 
GROUP BY m.TITLE
HAVING g.GENRE_NAME = ?
ORDER BY avg_rating DESC
LIMIT ?

-- Добавление оценки фильма по имени пользователя и названию фильма
INSERT INTO rating (user_id, movie_id, rating_digit)
VALUES (
(SELECT id FROM users WHERE user_name = ?),
(SELECT id FROM movie WHERE title = ?), ?);

-- Обновление оценки фильма по имени пользователя и названию фильма
UPDATE rating SET rating_digit = ? 
WHERE user_id = (SELECT id FROM users WHERE user_name = ?) 
AND movie_id = (SELECT id FROM movie WHERE title = ?)

