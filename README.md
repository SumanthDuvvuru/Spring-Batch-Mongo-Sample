# Spring-Batch-Sample
Sample application to test spring batch capabilities

Major endpoint :  
/run    ---> which is used to trigger the batch job, for now it reads the data from the mongodb and saves it to an xml file

other endpoints: (Mongo curd operations)
/save   ---> saves a file to report collection in test DB
/getall    ---> gets all the documents in json string
/deleteall  ---> delets all the documents from colleciton
/delete/{id} ---> deletes based on id


When running for the first time hit the /save endpoint atleast once, so that report collection is created in test DB inside mongo.
Then when we hit the /run endpoint we get the data in mongo saved into a xml file named mongo.xml in xml folder of root.

We can even add scheduling to our method, which hits mongo at a regular interval and copies that to our xml file.
