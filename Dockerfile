FROM ubuntu
MAINTAINER hpatel@channelit.biz
USER root
ENV \
	SPARK_VER=2.0.2-bin-hadoop2.7
RUN apt-get update &&\
	apt-get install -y wget vim curl nodejs tar npm default-jdk sudo default-jdk ntp git &&\
	apt-get update &&\
	apt-get clean
RUN mkdir /usr/local/share/spark &&\
	curl http://d3kbcqa49mib13.cloudfront.net/spark-$SPARK_VER.tgz | tar xvz -C /usr/local/share/spark
RUN apt-get install -y python3
RUN touch /usr/local/share/spark/spark-$SPARK_VER/slaves &&\
	echo "localhost" >> usr/local/share/spark/spark-$SPARK_VER/conf/slaves
RUN	apt-get install -y python-numpy python-scipy python-matplotlib ipython ipython-notebook python-pandas python-sympy python-nose
RUN	apt-get install -y python-dev python-pip g++ libopenblas-dev
RUN pip install spaCy &&\
	python -m spacy.en.download all
RUN pip install pyLDAvis
RUN pip install gensim
RUN pip install nltk
RUN pip install bokeh
RUN pip install --upgrade pip
ENV \
	SPARK_MASTER_HOST=localhost \
	SPARK_MASTER_PORT=7077 \
	SPARK_MASTER_WEBUI_PORT=8080 \
	SPARK_LOCAL_DIRS=/spark-data/scratch \
	SPARK_WORKER_CORES=1 \
	SPARK_WORKER_PORT=7078 \
	SPARK_WORKER_WEBUI_PORT=8081
	PYSPARK_PYTHON=/usr/bin/python
EXPOSE 7077 7078 8080 8081
WORKDIR /usr/local/share/spark/spark-$SPARK_VER/sbin
RUN touch start.sh && \
	chmod 755 start.sh && \
	echo "#!/bin/sh" >> start.sh && \
	echo "./start-master.sh & ./start-slave.sh spark://localhost:7077 
CMD ["./start.sh"]