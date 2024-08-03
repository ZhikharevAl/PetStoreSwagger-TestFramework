# Используем официальный образ Java
FROM openjdk:21-slim

# Устанавливаем необходимые инструменты
RUN apt-get update && apt-get install -y curl unzip

# Устанавливаем Gradle
ENV GRADLE_VERSION 8.9
RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv gradle-${GRADLE_VERSION} /opt/gradle
ENV PATH=$PATH:/opt/gradle/bin

# Устанавливаем Allure
ENV ALLURE_VERSION 2.30.0
RUN curl -o allure-${ALLURE_VERSION}.zip -Ls \
    https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/${ALLURE_VERSION}/allure-commandline-${ALLURE_VERSION}.zip \
    && unzip allure-${ALLURE_VERSION}.zip -d /opt/ \
    && rm allure-${ALLURE_VERSION}.zip

ENV PATH=$PATH:/opt/allure-${ALLURE_VERSION}/bin

# Создаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Даем права на выполнение gradlew
RUN chmod +x ./gradlew

# Запускаем тесты
RUN ./gradlew clean test

# Генерируем отчет Allure
RUN ./gradlew allureReport

# Экспонируем порт для Allure отчета
EXPOSE 8080

# Команда для запуска Allure сервера
CMD ["allure", "serve", "-h", "0.0.0.0", "-p", "8080", "/app/build/allure-results"]