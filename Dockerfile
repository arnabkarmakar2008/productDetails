FROM tomcat:latest
MAINTAINER "MyRetail Admin" "admin@axp.com"
RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY productDetails-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN touch /usr/local/tomcat/webapps/productDetails-0.0.1-SNAPSHOT.war.dodeploy
EXPOSE 8080
#Comment
#Comment for TestBranch
#Comment from productDetailsNew
#Comment from productDetails
ENTRYPOINT /usr/local/tomcat/bin/catalina.sh run
# 1 was here
