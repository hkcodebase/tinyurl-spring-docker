package com.hk.prj.tinyurl_api.repository;

import com.hk.prj.tinyurl_api.model.Url;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UrlRepository extends CassandraRepository<Url, String> {
}
