package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

@ApplicationScoped
public class Route extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {

        from(file("/tmp/camel-test"))
                .log("body received: ${body}");
    }
}