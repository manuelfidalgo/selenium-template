FROM markhobson/maven-chrome
USER root


WORKDIR /home

#CMD google-chrome -version
CMD pwd && ls -lat && mvn clean test && "mvn allure:serve"
