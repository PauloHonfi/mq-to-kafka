package br.com.phs.santander.mq.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jakarta.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@Configuration
public class MQConfig {

    @Value("${my.mq.queueManager}")
    private String queueManager;
    
    @Value("${my.mq.channel}")
    private String channel;
    
    @Value("${my.mq.host}")
    private String host;
    
    @Value("${my.mq.port}")
    private String port;
    
    @Value("${my.mq.user}")
    private String user;
    
    @Value("${my.mq.password}")
    private String password;
    
    @Value("${my.mq.mqcsp}")
    private String mqcsp;

    @Bean(name = "mqQueueConnectionFactorymy")
    public MQQueueConnectionFactory mqQueueConnectionFactorymy() {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(host);
        try {
            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setCCSID(1208);
            mqQueueConnectionFactory.setChannel(this.channel);
            mqQueueConnectionFactory.setPort(Integer.parseInt(this.port));
            mqQueueConnectionFactory.setQueueManager(this.queueManager);
            mqQueueConnectionFactory.put(WMQConstants.USER_AUTHENTICATION_MQCSP,Boolean.valueOf(this.mqcsp));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mqQueueConnectionFactory;
    }

    @Bean(name = "userCredentialsConnectionFactoryAdaptermy")
    public UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdaptermy(@Qualifier("mqQueueConnectionFactorymy") MQQueueConnectionFactory mqQueueConnectionFactory) {
        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        userCredentialsConnectionFactoryAdapter.setUsername(this.user);
        userCredentialsConnectionFactoryAdapter.setPassword(this.password);
        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(mqQueueConnectionFactory);
        return userCredentialsConnectionFactoryAdapter;
    }

    @Bean(name = "jmsOperationsmy")
    public JmsOperations jmsOperationsmy(@Qualifier("userCredentialsConnectionFactoryAdaptermy") UserCredentialsConnectionFactoryAdapter  userCredentialsConnectionFactoryAdapter) {
        JmsTemplate jmsTemplate = new JmsTemplate(userCredentialsConnectionFactoryAdapter);
        return jmsTemplate;
    }
}
