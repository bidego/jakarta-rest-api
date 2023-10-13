
run:
	mvn clean package && docker build . -t jakarta-rest-api && docker run -p 8080:8080 jakarta-rest-api

test-stress:
	ab -n1000 -s10 http://localhost:8080/user\?name\=carlo
