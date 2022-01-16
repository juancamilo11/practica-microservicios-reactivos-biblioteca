package dev.j3c.mspractice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPublisherConfig {

    public static final String PROVIDED_RESOURCES_QUEUE = "provided.resources.queue";
    public static final String RETURNED_RESOURCES_QUEUE = "returned.resources.queue";

    public static final String EXCHANGE = "resources.loaning.exchange";

    public static final String PROVIDED_RESOURCES_ROUTING_KEY = "provided.resources.routing_key";
    public static final String RETURNED_RESOURCES_ROUTING_KEY = "returned.resources.routing_key";

    @Bean
    public Queue providedResourcesQueue() {
        return new Queue(PROVIDED_RESOURCES_QUEUE, true);
    }

    @Bean
    public Queue returnedResourcesQueue() {
        return new Queue(RETURNED_RESOURCES_QUEUE, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding providedResourcesToQueueBinding() {
        return BindingBuilder.bind(this.providedResourcesQueue()).to(this.topicExchange()).with(PROVIDED_RESOURCES_ROUTING_KEY);
    }

    @Bean
    public Binding returnedResourcesToQueueBinding() {
        return BindingBuilder.bind(this.providedResourcesQueue()).to(this.topicExchange()).with(RETURNED_RESOURCES_ROUTING_KEY);
    }

}
