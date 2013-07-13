(ALPHA) NOT READY FOR PUBLIC RELEASE

blitline-java-client
====================

Reference Java Implementation for Blitline.com Image Processing SaaS


REQUIREMENTS
============
*The Apache Software Foundation: httpclient-4.2.5.jar*
*The Apache Software Foundation: httpcore-4.2.4.jar*
*com.googlecode.json-simple: simple-json-1.1.1.jar*

These jars are available in the lib directory. If you wish
to integrate this client with other libraries, go ahead, we would love
to hear from you and would be glad to add them to the 
Blitline library.


Installation and Use
====================
git clone git@github.com:blitline-dev/blitline-java-client.git

blitline-client.jar is in bin/ directory.


To Build By Hand
=======================
cd blitline-java-client

For Linux/Mac
```Shell
javac -d bin -cp lib/httpclient-4.2.5.jar:lib/httpcore-4.2.4.jar:lib/json-simple-1.1.1.jar com/blitline/client/*.java
```

For PC
```Shell
javac -d bin -cp lib/httpclient-4.2.5.jar;lib/httpcore-4.2.4.jar;lib/json-simple-1.1.1.jar com/blitline/client/*.java
```

jar cvf bin/blitline-client.jar bin/com/blitline/client/*.class

Example
========================


Usage
========================

There is only 2 calls in the Blitline client:

1. Submitting JSON to Blitline
2. Polling for results from Blitline

**Submitting JSON to Blitline:**

BlitlineClient.submitJsonToBlitline(json) take JSON as a string parameter. 

It's best to do all your thinking about Blitline jobs in terms of JSON. Build the jobs as JSON in text using tools like Blitline's GIST runner (http://www.blitline.com/docs/gist_runner?gist_id=3765044). Once you have the JSON for your jobs figured out as a big string, then you can just build it dynamically in your code and submit it via the BlitlineClient.submitJsonToBlitline call. The beauty of the JSON is that we can add to it at any time, expanding functionality without changing existing functionality. As such, it doesn't make sense to try to coerce it into a object or model, because we would need to update the Blitline libraries every time we made an addition.

This call will return a BlitlineResult object. This BlitlineResult object has two methods, getJobID and getImages. The jobID is used to track your job through the Blitline system, and the getImages is the list of images you submitted to Blitline.

**Polling for results from Blitline:**

BlitlineClient.longPoll(blitlineResult) takes a BlitlineResult as a parameter and waits for Blitline to notify you that your job has completed. Once completed, longPoll returns a BlitlinePostback which represents the data that Blitline returns to you about your job. This includes metadata and information about the image.

Blitline recommends that you only use longPoll for development purposes. Blitline recommends that use the postback (http://www.blitline.com/docs/postback) functionality provided by Blitline to notify your servers when a job has completed. 

Note: The BlitlinePostback is constructed with the same JSON that is returned to your server by Blitline, so you can use it server-side to recieve the Blitline postback when/if you implement postbacks on your server.

Please check out Blitline.com for further documentation about the Blitline service and if you have any questions please feel free to write us at support@blitline.com.


Licensing?
===========================

The project is licensed under the http://en.wikipedia.org/wiki/WTFPL license.


