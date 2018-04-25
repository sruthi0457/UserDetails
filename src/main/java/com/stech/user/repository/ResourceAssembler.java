package com.stech.user.repository;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<Domain, Resource> {
    public abstract Resource toResource(Domain domain);

    public Collection<Resource> toResourceCollection(Collection<Domain> domain) {
        return domain.stream().map(o -> toResource(o)).collect(Collectors.toList());
    }
}
