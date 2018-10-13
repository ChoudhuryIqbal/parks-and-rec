# Authentication API

Parks and Rec uses a simple token based authentication if the username and password is recognized by the system.

### Required fields

* `username` is a string, keep it under 255 characters
* `password` is a string, used to authenticate and validate

## Login

```
curl -X POST \
  https://parks-and-rec-app.herokuapp.com/parksrec/services/v1/login \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cacc889d-5cb4-47f8-8e17-3c7b47fe4e07' \
  -H 'token: test' \
  -d '{
    "username": "Admin",
    "password": "Admin"

}'
```
