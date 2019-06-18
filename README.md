#DoorDashLite

This is lite version of DoorDash application, which displays the list of available restaurant. It is based on the MVVM architecture pattern. It also use Google architectural components such as LiveData and ViewModel to manage the data in an intermediate state of activity.

Restaurant Fragment makes the call through Restaurant modelView to get the information from the repository using the DoorDashService, which manage the network operation.

It assumes the connectivity is always there and doesn't handle the loss of connectivity gracefully. Currently it uses the static lat and lng to fetch the restaurant information.
