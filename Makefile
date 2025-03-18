# Makefile

.PHONY: all clean install

all: install

clean:
	mvn clean

install: clean
	@export $(shell grep -v '^#' .env | xargs) && mvn install $(shell grep -v '^#' .env | sed 's/^/-D/')
