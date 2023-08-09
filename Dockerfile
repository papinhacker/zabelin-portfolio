FROM openjdk:11
MAINTAINER girunir@gmail.com
# Stage 1. Building the project

WORKDIR /build
RUN mkdir -p /build
COPY . /build/

RUN apt-get update && apt-get install -y maven
RUN mvn clean -U package -DskipTests=true


FROM openjdk:11
# Stage 2. Local-testrunner container with the compiled project

ENV DEBIAN_FRONTEND=noninteractive

# Installing the required environment
RUN apt-get update &&\
    apt-get -yq install --fix-missing -y \
    curl net-tools awscli wget

# Installing Chrome
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb &&\
    dpkg -i google-chrome-stable_current_amd64.deb; apt-get -fy install

# Making dirs for compiled project
RUN mkdir -p /home/automation/src/main/resources/
RUN mkdir -p /home/automation/TestXML/
WORKDIR /home/automation

# Copying the nesessary files

# Compiled jar files
COPY --from=0 /build/target/*.jar /home/automation/
# Resources
COPY src/main/resources/ /home/automation/src/main/resources/
# License key
COPY sessionData /home/automation/
# XML Suites
COPY TestXML/ /home/automation/TestXML/
# Entrypoint
COPY scripts/bin/run.sh /home/automation/run.sh

# Execution permissions
RUN chmod 0777 /home/automation/run.sh

# Running tests
ENTRYPOINT ["/home/automation/run.sh"]