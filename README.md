# otus-project
Перед запуском тестов должен быть запущен docker
Запуск тестов осуществляется с укзанием параметров: имя браузера, версия браузера.
Для запуска тестов с локальной среды разработки небходимо в терминале указать:
mvn clean test -Dbrowser_name=$BROWSER_NAME -Dbrowser_version=$BROWSER_VERSION
, где $BROWSER_NAME = имя браузера, $BROWSER_VERSION = версия браузера.
Пример запуска в браузере chrome версии 89:
mvn clean test -Dbrowser_name=chrome -Dbrowser_version=89.0