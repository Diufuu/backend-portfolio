FROM ubuntu:focal

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

ARG ZULU_REPO_VER=1.0.0-3

RUN apt-get -qq update && \
    apt-get -qq -y --no-install-recommends install gnupg software-properties-common locales curl tzdata && \
    echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen && \
    locale-gen en_US.UTF-8 && \
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9 && \
    curl -sLO https://cdn.azul.com/zulu/bin/zulu-repo_${ZULU_REPO_VER}_all.deb && dpkg -i zulu-repo_${ZULU_REPO_VER}_all.deb && \
    apt-get -qq update && \
    apt-get -qq -y dist-upgrade && \
    mkdir -p /usr/share/man/man1 && \
    echo "Package: zulu17-*\nPin: version 17.0.5-*\nPin-Priority: 1001" > /etc/apt/preferences && \
    apt-get -qq -y --no-install-recommends install zulu17-jdk=17.0.5-* && \
    apt-get -qq -y purge gnupg software-properties-common curl && \
    apt -y autoremove && \
    rm -rf /var/lib/apt/lists/* zulu-repo_${ZULU_REPO_VER}_all.deb

ENV JAVA_HOME=/usr/lib/jvm/zulu17-ca-amd64
WORKDIR /

FROM openjdk:17.0.1-jdk-slim
EXPOSE 8080
COPY target/myportfolio-0.0.1-SNAPSHOT.jar /app/myportfolio.jar
CMD ["java", "-jar", "/app/myportfolio.jar"]