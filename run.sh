#!/bin/bash
docker build -f trades/Dockerfile -t andromanko/trades .
docker build -f communication/Dockerfile -t andromanko/communication .
docker build -f payment/Dockerfile -t andromanko/payment .
docker-compose up --build -d
