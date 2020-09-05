# Chat
Minimal back-end for your chat application in Spring Boot and Hibernate.

`POST /users/register/` to sign up

`POST /users/authenticate/` to sign in

`GET /users/chats/` to get chatrooms user has

`POST /users/chat/` with request body of usernames to create a chatroom with users

`GET /users/chat?id={id}` to get a specific chatroom

`GET /chatoom?id={id}` to get messages of a specific chatroom

Subscribe and receive messages `/receive/{chatroomId}`

_________________________

Don't forget to put "Authorization" header with Bearer {token} value in order to authorize.

