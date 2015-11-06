package org.yarr.rejeex;

import com.google.common.collect.ImmutableSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RejeexApplication extends Application
{
    @Override
    public Set<Object> getSingletons() {
        return ImmutableSet.of(new RejeexService());
    }
}
