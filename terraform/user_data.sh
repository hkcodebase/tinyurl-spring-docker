#!/bin/bash
sudo dnf update -y
# Remove old version if installed
#sudo dnf remove -y docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-engine

# Install dnf plugin
sudo dnf -y install dnf-plugins-core

# Add CentOS repository
sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

# Adjust release server version in the path as it will not match with Amazon Linux 2023
# so we'll hard code it to Alamlinux 9/Centos 9
sudo sed -i 's/$releasever/9/g' /etc/yum.repos.d/docker-ce.repo

# Install as usual
sudo dnf -y install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sleep 5s

# Enable the docker service
sudo systemctl enable --now docker

sleep 5s

#install git
sudo dnf -y install git

# Deploy App
cd /home/ec2-user
git clone https://github.com/hkcodebase/tinyurl-spring-docker.git app
cd app
git checkout develop

# Use full path or 'docker compose' (not docker-compose)
docker compose up -d