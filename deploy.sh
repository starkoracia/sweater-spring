#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/ssh-frankfurt.pem \
target/sweater-spring-v1.jar \
ubuntu@ec2-18-197-199-34.eu-central-1.compute.amazonaws.com:/home/ubuntu/

echo 'Restart server...'

ssh -i ~/.ssh/ssh-frankfurt.pem -T ubuntu@ec2-18-197-199-34.eu-central-1.compute.amazonaws.com << EOF
pgrep java | xargs kill -9
echo 'killed process'
nohup java -jar sweater-spring-v1.jar > log.out 2>&1 &
EOF

echo 'Bye'
