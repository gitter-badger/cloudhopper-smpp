package com.cloudhopper.smpp.channel;

/*
 * #%L
 * ch-smpp
 * %%
 * Copyright (C) 2009 - 2012 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Channel handler responsible for renaming the current thread, passing the
 * event upstream, then renaming the thread back after its done processing. This
 * handler should be the first one in the pipeline to make sure all handlers
 * after it have the correct thread name for proper logging.
 *
 * @author joelauer (twitter: @jjlauer or <a href="http://twitter.com/jjlauer" target=window>http://twitter.com/jjlauer</a>)
 */
//@ChannelHandler.Sharable
public class SmppSessionThreadRenamer extends ChannelHandlerAdapter implements ChannelInboundHandler {
    private static final Logger logger = LoggerFactory.getLogger(SmppSessionThreadRenamer.class);

    private String threadName;

    public SmppSessionThreadRenamer(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String value) {
        this.threadName = value;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelRegistered");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelRegistered();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelUnregistered");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelUnregistered();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelActive");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelActive();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelInactive");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelInactive();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	logger.debug("channelRead");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelRead(msg);
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelReadComplete");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelReadComplete();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	logger.debug("userEventTriggered");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireUserEventTriggered(evt);
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
	logger.debug("channelWritabilityChanged");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireChannelWritabilityChanged();
	Thread.currentThread().setName(currentThreadName);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	throws Exception {
	logger.debug("exceptionCaught");
	String currentThreadName = Thread.currentThread().getName();
        Thread.currentThread().setName(threadName);
	ctx.fireExceptionCaught(cause);
	Thread.currentThread().setName(currentThreadName);
    }

}
