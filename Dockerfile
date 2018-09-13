FROM ubuntu
RUN apt-get update \
&& apt-get install -y git gradle \
&& git clone https://github.com/Deazushka/ServerSpring.git \
&& cd ServerSpring/ \
&& gradle build \
&& cd build/libs \
ENTRYPOINT ["java", "-jar", "/usr/bin/gs-gradle-0.1.0.jar"]
