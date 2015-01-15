package org.restexpress.pipeline;

import org.jboss.netty.channel.ChannelHandlerContext;

public class DefaultResponseHandler {

	private DefaultRequestHandler req_handler;
	private ChannelHandlerContext ctx;
	private MessageContext context;
	
	public DefaultResponseHandler(DefaultRequestHandler req_handler, ChannelHandlerContext ctx, MessageContext context) 
	{
		this.req_handler = req_handler;
		this.ctx = ctx;
		this.context = context;
	}
	
	public void sendResponse(Object result)
	{
		this.req_handler.sendResponse(ctx, context, result);
	}
	
	

}
