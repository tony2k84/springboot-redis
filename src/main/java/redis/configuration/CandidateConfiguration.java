package redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.entity.Candidate;

@Configuration
public class CandidateConfiguration {
  @Bean
  ReactiveRedisOperations<String, Candidate> redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<Candidate> serializer = new Jackson2JsonRedisSerializer<>(Candidate.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Candidate> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Candidate> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

}
