FROM node:17-alpine3.14
LABEL maintainer="Avijit GuhaBiswas <guhabiswas.avijit@gmail.com>"
LABEL name="admin/openjdk-alpine-hfds"

RUN apk add bash\
    && apk add openjdk11\
    && adduser --disabled-password admin \
	&& echo 'admin ALL=(ALL) NOPASSWD:ALL' >>/etc/sudoers
	

USER admin
WORKDIR /home/admin/JavaBinaries
	
ENV SHELL=/bin/bash \
	USER=admin
COPY target/webhdfs-0.0.1-SNAPSHOT.jar webhdfs-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar", "webhdfs-0.0.1-SNAPSHOT.jar","/geeks"]