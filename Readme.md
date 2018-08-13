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

move `schema.jsona` to `app/src/main/graphql/com/okysoft/annictim/ApolloApi`
