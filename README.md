# grabber
Агрегатор Java вакансий\
[![Build Status](https://www.travis-ci.com/KirillReal/job4j_grabber.svg?branch=main)](https://travis-ci.org/KirillReal/job4j_grubber)
[![codecov](https://codecov.io/gh/KirillReal/job4j_grabber/branch/main/graph/badge.svg?token=HV3GKKWA2H)](https://codecov.io/gh/KirillReal/job4j_grabber)

Техническое задание\
Приложение парсит сайты с вакансиями. Первый сайт будет sql.ru. В нем есть раздел job. Программа должна считывать все вакансии относящиеся к Java и записывать их в базу.

 * В проекте нужно использовать maven, travis, jacoco, checkstyle.
 * Приложение должно собираться в jar.
 * Система запускается по расписанию. Период запуска указывается в настройках - app.properties.
 * Доступ к интерфейсу будет через REST API.


