FROM rabbitmq:3.11.8-management-alpine
#setup clustering
COPY .erlang.cookie ~
COPY rabbitmq.conf /config/
ENV RABBITMQ_CONFIG_FILE=/config/rabbitmq

#mirroring + auto-syncronization("ha-sync-mode":"automatic")
RUN rabbitmq-plugins enable rabbitmq_federation
#CMD rabbitmqctl set_policy ha-fed ".*" '{"federation-upstream-set":"all", "ha-sync-mode":"automatic", "ha-mode":"nodes", "ha-params":["rabbit@node1", "rabbit@node2"]}' --priority 1 --apply-to queues