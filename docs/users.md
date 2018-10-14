# Users API

A user represents the model for the real user in the system. It's used to identify and manage people.

### Required fields

* `username` is a string, keep it under 255 characters
* `password` is a string, keep it under 255 characters

## Create user

```
curl -X POST \
  https://parks-and-rec-app.herokuapp.com/parksrec/services/v1/createUser \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a7486f78-c4f6-468d-8f05-54d049be47c0' \
  -H 'token: 5b0d7678-1b91-47ab-82f4-e7f113aeba4c' \
  -d '{
   "id": null,
   "username": "TestUserE7",
   "password": "TestUserE7",
    "roles": [
       {
    	    "id": null,
           "role_id": 1,
           "user_id":null

       },
       {
           "id": null,
           "role_id": 2,
           "user_id":null
       }
   ]}'
```

## Update user

```
curl -X PUT \
  https://parks-and-rec-app.herokuapp.com/parksrec/services/v1/updateUser \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a7d03d69-a5e3-4832-8538-dd70dc9c15f3' \
  -H 'token: 5b0d7678-1b91-47ab-82f4-e7f113aeba4c' \
  -d '{
        "userId": 2,
        "username": "TestUserE7",
        "password": "TestUserE7-updated",
        "roles": [
            {
                "id": 3,
                "role_id": 1,
                "user_id": 2
            },
            {
                "id": 4,
                "role_id": 2,
                "user_id": 2
            }
        ]
    }'
```

4. Get user by name

```
curl -X GET \
  'https://parks-and-rec-app.herokuapp.com/parksrec/services/v1/getUserByName?userName=TestUserE7' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 38c97cd6-d1f0-4780-a1f1-dc3716a8b242' \
  -H 'token: 5b0d7678-1b91-47ab-82f4-e7f113aeba4c'
```
