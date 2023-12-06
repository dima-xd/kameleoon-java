# Kameleoon REST API

## User endpoints:

### Register a new user

```http request
POST /api/users/register
```

RequestBody
```json
{
"name": "name",
"email": "test@example.com",
"password": "password"
}
```

## Quote endpoints:

### Create a new quote

```http request
POST /api/quotes/create
```

RequestBody
```json
{
"content": "example content"
}
```
RequestParam
`userId` - ID of the User who creates a Quote

### Update the quote

```http request
PUT /api/quotes/update/{quoteId}
```
RequestBody
```json
{
"content": "updated content"
}
```
PathVariable
`quoteId` - ID of the Quote

### Delete the quote

```http request
DELETE /api/quotes/delete/{quoteId}
```
PathVariable
`quoteId` - ID of the Quote

### Get all quotes

```http request
GET /api/quotes
```

### Get random quote

```http request
GET /api/quotes/random
```

### Get top 10 quotes

```http request
GET /api/quotes/top
```

### Get worst 10 quotes

```http request
GET /api/quotes/worst
```

### Get specific quote details

```http request
GET /api/quotes/{quoteId}
```
PathVariable
`quoteId` - ID of the Quote

## Vote endpoints:

### Like the quote

```http request
POST /api/vote/{quoteId}/up
```
PathVariable
`quoteId` - ID of the Quote

### Dislike the quote

```http request
POST /api/vote/{quoteId}/down
```
PathVariable
`quoteId` - ID of the Quote

### Get the evolution of the votes over time

```http request
POST /api/vote/{quoteId}/evolution
```
PathVariable
`quoteId` - ID of the Quote
