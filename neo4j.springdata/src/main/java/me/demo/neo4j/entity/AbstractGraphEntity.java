package me.demo.neo4j.entity;

import org.springframework.data.neo4j.annotation.GraphId;

public abstract class AbstractGraphEntity {
	@GraphId
	private Long id;

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (id == null || obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		return id.equals(((AbstractGraphEntity) obj).id);

	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
}
