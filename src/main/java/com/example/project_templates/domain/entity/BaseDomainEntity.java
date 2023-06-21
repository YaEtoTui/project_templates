package com.example.project_templates.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
@MappedSuperclass
public abstract class BaseDomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    Long id;

    @Transient
    List<Supplier<Object>> events = new LinkedList<>();

    @DomainEvents
    protected List<Object> domainEvents() {
        return events.stream()
                .map(Supplier::get)
                .collect(Collectors.toUnmodifiableList());
    }

    @AfterDomainEventPublication
    protected void afterDomainEventsPublication() {
        events.clear();
    }

    protected void registerEvent(Supplier<Object> event) {
        events.add(event);
    }

    public int hashCode() {
        return id.hashCode();
    }
}
