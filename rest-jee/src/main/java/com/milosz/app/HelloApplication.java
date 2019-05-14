package com.milosz.app;

import com.milosz.rest.MovieRestService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class HelloApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public HelloApplication() {
        singletons.add(new MovieRestService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}