**Microservice registry**
=========================


I've got a task to create simple microservice registry. I should say that I didn't have any knowledge of micro services before this task.
After researching a little bit I've acquired information regarding decoupling applications and creating microservices (basically microservice can be any app which can contact other microservices in some way either by rest/messaging system) but that it not the scope of my task. 
In order to create micro service arhitecture one should have mechanism for registering/deregistering micro services and the way to do that is using the service discovery. 
**Service discovery paterns:**

 - Client side service discovery
 - Server side service discovery

#### **Client side Service Discovery** 
Client micro service should contact the registry and ask about address of needed service, then registry responds with address of service and then client contacts the needed service directly (one small note is that there are open source tools for this eg. Eureka). 
#### **Srever side service discovery** 
Client micro service  asks registry for a needed micro service and server makes sure to act as intermediory between cliend and worker micro service (also available online NGNX, AWS). I have implemented client side service discovery skeleton of micro service registry with methods for discovery and registration. Also please note that I've just started learning Scala and I am not so proficient with it but it looked like a straight forward way for creating http server.

When micro services are started they should contact registry and register, also when services are stopped they should unregister, I would also implement heartbeat on registry side to check if all registered services are reachible and disable/remove non working ones (also registry can be implemented to discover micro services but I don't like this approach).

#### **Observability of Micro services:**
Regarding the observability many diferent things could be done, usually the one would create aggregation of logfiles from each individual micro service, also some monitoring of system resources can be done. Also some realtime statistics for number of operations (AWS offers auto scaling for adding nesesary resources to the system, based on system resources metrics)
