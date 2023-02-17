
# Chat2Desk SDK Demo

This application demonstrates chat integration using the Chat2Desk Android SDK, allowing users to perform various chat operations with Chat2Desk service

## Features
The application supports the following features:
- Sending message
- Sending message with attachment
- Receiving incoming message
- Fetching message history from Chat2Desk
- Sending client data to the Chat2Desk
- Receiving operator info
- Saving message history and allowing users to add messages when there is no connection, followed by sending once a connection is re-established

## Getting started

To get started, you'll need an account on Chat2Desk and channel with Online Chat.

1. Retrieve the parameters from the widget settings:
- widget token
- server urls
2. Create the `apikey.properties` file on `app` folder with next content:
````
WIDGET_TOKEN="YOUR_WIDGET_TOKEN_HERE"  
BASE_HOST="YOUR_BASE_HOST_TOKEN_HERE"  
WS_HOST="YOUR_WS_HOST_TOKEN_HERE"  
STORAGE_HOST="YOUR_STORAGE_TOKEN_HERE"
````
3. Build and run app.

## Usage

###  Message list
<table>
<tr>
<td>Main</td>
<td>Select Attachment</td>
<td>Dropdown menu</td>
</tr>
<tr>
<td><img src="./screenshots/main.png" width=300></td>
<td><img src="./screenshots/select_attachment.png" width=300></td>
<td><img src="./screenshots/actions.png" width=300></td>
</tr>
</table>

## Useful links
1. [Using Chat2Desk SDK](https://chat2desk.atlassian.net/wiki/external/453247004/ZTdmYjQ3YzQ0MDFkNGU4MjhlOGIzZjlmYjQ1MmViYjE?atlOrigin=eyJpIjoiOTk2ZjdlOTdiNjg3NDY4YTk2YWU0NDg3MGVhNWI5MjIiLCJwIjoiYyJ9)
2. [SDK Reference](https://sdk.chat2desk.com)

##  Have a question
- create an issue
