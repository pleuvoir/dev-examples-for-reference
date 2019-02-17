package io.github.pleuvoir.redis;

import org.springframework.data.repository.CrudRepository;

import io.github.pleuvoir.redis.model.LiveCurrentItemCache;

public interface LiveCurrentItemRepository extends CrudRepository<LiveCurrentItemCache, String> {
}
