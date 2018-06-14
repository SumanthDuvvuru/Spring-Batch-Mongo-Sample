# Spring-Batch-Sample
Sample application to test spring batch capabilities

Major endpoint :  <br/>
/run    ---> which is used to trigger the batch job, for now it reads the data from the mongodb and saves it to an xml file<br/>

other endpoints: (Mongo curd operations)<br/>
/save   ---> saves a file to report collection in test DB<br/>
/getall    ---> gets all the documents in json string<br/>
/deleteall  ---> delets all the documents from colleciton<br/>
/delete/{id} ---> deletes based on id<br/><br/><br/>


When running for the first time hit the /save endpoint atleast once, so that report collection is created in test DB inside mongo.<br/>
Then when we hit the /run endpoint we get the data in mongo saved into a xml file named mongo.xml in xml folder of root.<br/><br/>

We can even add scheduling to our method, which hits mongo at a regular interval and copies that to our xml file.<br/>
