# <center>IBM MQ Kafka </center>


## Docker
Criar imagem local: docker build -t mq-to-kafka:1.0 .</br>

docker-compose down</br>
docker-compose build</br>
docker-compose up -d

Use as credenciais padrão: admin / admin.

docker run -p 8080:8080 -p 1414:1414 -p 9443:9443 -e LICENSE=accept mq-to-kafka:1.0

## MQ Channel
Channel name: DEV.ADMIN.SVRCONN</br>
Channel type: Server</br>
Conn name: localhost(5000)</br>
Transmission queue: MY.QUEUE.1
Local address: LocalAddress=localhost(5000)

