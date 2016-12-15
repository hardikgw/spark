FROM ubuntu
MAINTAINER hpatel@channelit.biz
USER root
ENV \
	SPARK_VER=2.0.2-bin-hadoop2.7
RUN apt-get update &&\
	apt-get install -y wget vim curl nodejs tar npm default-jdk sudo default-jdk ntp git openssh-server &&\
	apt-get update &&\
	apt-get clean
RUN mkdir /usr/local/share/spark &&\
	curl http://d3kbcqa49mib13.cloudfront.net/spark-$SPARK_VER.tgz | tar xvz -C /usr/local/share/spark
RUN apt-get install -y python3
RUN touch usr/local/share/spark/spark-$SPARK_VER/slaves &&\
	echo "localhost" >> usr/local/share/spark/spark-$SPARK_VER/slaves
ENV \
	SPARK_MASTER_HOST=localhost \
	SPARK_MASTER_PORT=7077 \
	SPARK_MASTER_WEBUI_PORT=8080 \
	SPARK_LOCAL_DIRS=/spark-data/scratch \
	SPARK_WORKER_CORES=1 \
	SPARK_WORKER_PORT=7078 \
	SPARK_WORKER_WEBUI_PORT=8081
EXPOSE 7077 7078 8080 8081 22