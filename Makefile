run-dist:
	./build/install/app/bin/app
run:
	./gradlew run --args='--spring.profiles.active=application-dev'
build:
	./gradlew clean build
test:
	./gradlew test
lint:
	./gradlew checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

.PHONY: build