FROM ubuntu:16.04

RUN apt-get update && \
    apt-get install ca-certificates curl openssh-client software-properties-common -y --no-install-recommends && \
    apt-add-repository ppa:ansible/ansible && \
    apt-get update && \
    apt-get install ansible -y