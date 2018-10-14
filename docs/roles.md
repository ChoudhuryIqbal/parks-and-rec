# Roles API

A role is associated to the user and determines what authorizations the user has.

## Get Roles

curl -X GET \
  http://localhost:8080/parksrec/services/v1/getRoles \
  -H 'token: f88352c5-60c5-4ae1-a7a0-fc60fe93fead' \
  -H 'Cache-Control: no-cache' \

