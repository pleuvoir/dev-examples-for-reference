package io.github.pleuvoir.redis;

import org.springframework.data.repository.CrudRepository;

import io.github.pleuvoir.redis.model.LiveItemFinishCache;

public interface LiveItemFinishRepository extends CrudRepository<LiveItemFinishCache, String> {
}
