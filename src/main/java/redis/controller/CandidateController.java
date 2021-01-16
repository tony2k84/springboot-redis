package redis.controller;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import redis.entity.Candidate;

@RestController
public class CandidateController {
  private final ReactiveRedisOperations<String, Candidate> candidateOps;

  CandidateController(ReactiveRedisOperations<String, Candidate> candidateOps) {
    this.candidateOps = candidateOps;
  }

  @GetMapping("/candidates")
  public Flux<Candidate> all() {
    return candidateOps.keys("*").flatMap(candidateOps.opsForValue()::get);
  }
}
