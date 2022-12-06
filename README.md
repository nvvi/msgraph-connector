# msgraph-connector

[![CI Build](https://github.com/axonivy-market/msgraph-connector/actions/workflows/ci.yml/badge.svg)](https://github.com/axonivy-market/msgraph-connector/actions/workflows/ci.yml)

Read our docs:

- Azure App [Setup](msgraph-connector-product/setup.md)
- Graph [Connector](msgraph-connector-product/products/msgraph-connector/README.md)
- Graph [Calendar](msgraph-connector-product/products/msgraph-calendar/README.md)
- Graph [Mail](msgraph-connector-product/products/msgraph-mail/README.md)
- Graph [ToDo](msgraph-connector-product/products/msgraph-todo/README.md)
- Graph [Chat](msgraph-connector-product/products/msgraph-teams/README.md)

## Developer Guidelines

The Microsoft Graph universe is wide, if you use this connector and its demos as starting point
for your custom graph based solution, you may appreciate the following developer help.

### Explore

Microsoft's [Graph-Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer) let's you fire requests against the Graph APIs with a simple convenient UI. This is a good starting point, to find relevant resources for your custom Graph API use-case. Here you can conveniently grant permissions to simulate a call, without actually configuring your Azure App.

A selection of frequent API calls can be gleaned from Microsoft's [API-Reference](https://learn.microsoft.com/en-us/graph/api/overview?view=graph-rest-1.0).

### API Slicing

The rest-client definition within the msgraph-connector project limits the OpenAPI
support as close as possible to cover the shown demos. For the fact that:

- the full client is slow to generate: ~17 MB OpenAPI spec (takes around 60 seconds)
- the final client will be large too and causing delays: SCM, deployment, update, ...
- inscription masks will be slow to use ... and validation too
- small api-subset clients are much easier to use.

Ultimately, Microsoft recons that their APIs are huge: 
and offers a nice slicing [Graph-api-explorer](https://graphexplorerapi.azurewebsites.net/openapi/operations?style=Plain&graphVersion=v1.0) to get sub-sets of the API in a consistent state.

The API for the client can be reduced either with:

- operationIds: https://graphexplorerapi.azurewebsites.net/openapi?operationIds=me.sendMail&openApiVersion=2&graphVersion=v1.0&format=yaml&style=Plain
- or tags: https://graphexplorerapi.azurewebsites.net/openapi?tags=me.user,me.calendar,users.calendar&openApiVersion=2&graphVersion=v1.0&format=json

Unfortunately these reduce methods can not be combined. Which would often be handy to merge /me functions with specific read ops.

Sometimes slicing is still hard often the resulting client is still kind of large.
Therefore you may consider introducing multiple rest-clients, e.g. one for each graph product you use.

However, once you got the selection of required resources, proceed by regenerating the rest-client 
with this OpenAPI spec URI.
