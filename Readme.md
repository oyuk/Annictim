# Annictim

[Annict](https://annict.jp/)の非公式Androidアプリです

Home | Record | Profile
---- | ---- | ----
<img src="/images/home.png" width="300"> | <img src="/images/record.png" width="300"> | <img src="/images/profile.png" width="300">

Detail |
---- |
<img src="/images/detail.png" width="300"> | 


## development
## How to setup
create `local.properties`

```
clientID=YOUR_CLIENT_ID
clientKey=YOUR_CLIENT_KEY
```

put `local.properties` to root directory.

### Create schema.json
```
apollo-codegen download-schema https://api.annict.com/graphql --output infra/src/main/graphql/com/okysoft/infra/schema.json --header "Authorization: Bearer YOUR_OUTH_TOKEN"
```
