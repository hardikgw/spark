#!/bin/sh
docker run -it -p 8080:8080 -p 8081:8081 -v $(pwd)/spark-data:/spark-data --name=spark cithub/spark /bin/bash