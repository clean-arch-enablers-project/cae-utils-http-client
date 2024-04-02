# Welcome to the _cae-utils-http-client_ repository!
The _cae-util-http-client_ is a standalone library: you can use it in whatever application architecture you choose, with whatever framework of your preference. However, this is a part of the CAE ecosystem, designed to enable you to build REST API calls at your Adapters layer without having to compromise the whole application architecture getting coupled to some needy framework or library.

It is very simple to use the HTTP Client from CAE. Take a look:

1. First of all, add the dependency at the pom.xml:

```xml

<dependency>
  <groupId>com.clean-arch-enablers</groupId>
  <artifactId>http-client</artifactId>
  <version>${version}</version>
</dependency>

```

2. Use the main interface and provide its implementation at a centralized point:

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestStarterFactory {

    public static final HttpRequestStarter SINGLETON;

    static {
        SINGLETON = new HttpRequestStarterImplementation();
    }

}
```

3. Start modeling your request and send it, parameterizing its output type:

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRetrievementAPIClient {

    public static UserRetrievementAPIClientOutput execute(Long id, UseCaseExecutionCorrelation correlation){
        var httpRequestStarter = HttpRequestStarterFactory.SINGLETON;
        var endpoint = EnvVars.getUsersServiceHost().concat("/users/{userId}");
        var httpRequest = httpRequestStarter.startGetRequestFor(endpoint)
                .pathVariableOf("userId", id.toString())
                .queryParameterOf("detailed-output", "true")
                .headerOf("correlationId", correlation.toString())
                .handlerByHttpStatusCode(404, UserRetrievementAPIClient::handleNotFound)
                .handlerForAnyUnsuccessfulResponse(UserRetrievementAPIClient::handleUnexpectedProblem)
                .retrierByExceptionType(IOException.class, RetrierModel.withLimitOf(5))
                .retrierByHttpStatusCode(503, RetrierModel.withLimitOf(5))
                .finishBuildingModel();
        return httpRequest.sendRequestReturning(UserRetrievementAPIClientOutput.class);
    }

    private static void handleUnexpectedProblem(HttpResponse httpResponse) {...}

    private static void handleNotFound(HttpResponse httpResponse) {...}

    @Getter
    @Setter
    public static class UserRetrievementAPIClientOutput {...}

}
```

Simple, isn't it?

With only 12 lines of code the request was defined with path variable, query parameter, HTTP header, handler in case of 404 responses, handler in case of any response different than 2xx and 404 specifically, retry-policy in case of IOException, retry-policy in case of 503 responses and the output mapping.

All you got to do is to declare the dependency and use the library API.
