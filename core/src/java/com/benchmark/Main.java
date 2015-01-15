package com.benchmark;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.RestExpress;
import org.restexpress.pipeline.DefaultResponseHandler;

public class Main
{
    public static void main(String[] args)
    {
        RestExpress server = new RestExpress()
            .setName("Echo");

        server.uri("/echo", new Object()
        {
            @SuppressWarnings("unused")
            public void read(Request req, Response res, Object obj)
            {
            	final DefaultResponseHandler handler = (DefaultResponseHandler) obj;
            	System.out.println("oggetto passato come callback-test: ");
                String value = req.getHeader("echo");
                res.setContentType("text/xml");
                final String result;	
                if (value == null)
                {
                    result = "<http_test><error>no value specified</error></http_test>";
                }
                else
                {
                	result = String.format("<http_test><value>%s</value></http_test>", value);
                }
                
                new Thread(new Runnable() {
					
					@Override
					public void run() 
					{
						try {
							Thread.sleep(2000000);
							handler.sendResponse(result);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
                
            }
        })
        .method(HttpMethod.GET)
        .noSerialization();

        server.setExecutorThreadCount(0);
        System.out.println("Thread executor count="  +server.getExecutorThreadCount());
        server.bind(8000);
        server.awaitShutdown();
    }}
