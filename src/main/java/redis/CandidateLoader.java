package redis;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import redis.entity.Candidate;

@Component
public class CandidateLoader {
	private final ReactiveRedisConnectionFactory factory;
	private final ReactiveRedisOperations<String, Candidate> candidateOps;

	public CandidateLoader(ReactiveRedisConnectionFactory factory,
			ReactiveRedisOperations<String, Candidate> candidateOps) {
		this.factory = factory;
		this.candidateOps = candidateOps;
	}

	@PostConstruct
	public void loadData() {

		factory.getReactiveConnection().serverCommands().flushAll()
				.thenMany(Flux.just("Jane Doe", "John Doe", "Will Smith")
						.map(name -> new Candidate(UUID.randomUUID().toString(), name, "Rest API"))
						.flatMap(candidate -> candidateOps.opsForValue().set(candidate.getId(), candidate)))
				.thenMany(candidateOps.keys("*").flatMap(candidateOps.opsForValue()::get))
				.subscribe(System.out::println);
	}
}
