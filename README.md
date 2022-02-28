# Camel Metrics Issue

Camel RH Build 2.2.5 seems to build invalid `summary` metrics. 
* Works correctly with Quarkus Build 2.7.2 (latest)
* Does not work with Quarkus Build for Red Hat 2.2.5 (latest)

# Reproduce

    ./mvnw clean compile quarkus:dev
    echo "testfile" > /tmp/camel-test/myfile.txt
    curl localhost:8080/q/metrics

# Metics output
## Red Hat Build 2.2.5 (Invalid summary metric)

    # TYPE application_camel_exchange_processing_seconds summary
    application_camel_exchange_processing_seconds_count{camelContext="camel-1",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 2.0
    # TYPE application_camel_exchange_processing_seconds_sum gauge
    application_camel_exchange_processing_seconds_sum{camelContext="camel-1",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.018918516
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.5",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.75",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.95",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.98",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.99",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753
    application_camel_exchange_processing_seconds{camelContext="camel-1",quantile="0.999",endpointName="file:///tmp/camel-test",eventType="ExchangeCreatedEvent"} 0.012124753

## Quarkus Build 2.7.2 (correct summary metric)

    # TYPE application_camel_route_processing_seconds summary
    application_camel_route_processing_seconds_count{camelContext="camel-1",routeId="route1"} 1.0
    application_camel_route_processing_seconds_sum{camelContext="camel-1",routeId="route1"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.5"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.75"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.95"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.98"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.99"} 0.007587899
    application_camel_route_processing_seconds{routeId="route1",camelContext="camel-1",quantile="0.999"} 0.007587899
