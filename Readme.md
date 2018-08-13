# Annictim

## development
### requirement
- apollo codegen

### how to setup
1. `npm install -g apollo`
2.

create `schema.json`

```
apollo-codegen download-schema https://api.annict.com/graphql --output schema.json -H "Authorization: bearer ${YOUR_AUTH_TOKEN}"
```

put `schema.jsona` to `app/src/main/graphql/com/okysoft/annictim/ApolloApi`

3.
create `api-key.properties`

```
clientID= YOUR_CLIENT_ID
clientKey= YOUR_CLIENT_KEY
```

puy `api-key.properties` to `app/`