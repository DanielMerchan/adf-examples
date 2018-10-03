package com.magicpigeon.poc.ws;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocket Endpoint for Managing Task Flow Initializations / Finalizations to inform other users about concurrency edits
 * @author Daniel Merchan Garcia
 */
@ApplicationScoped
@ServerEndpoint(value="/websocket/taskflow/{userId}/{taskFlowId}")
public final class TaskFlowWebSocketServer {

    /**
     * Inject the Task Flow Session Handler which holds the information about users and Task Flows
     */
    @Inject
    private TaskFlowSessionHandler tfSessionHandler;

    /**
     * Opening a WebSocket connection
     * @param session
     * @param config
     */
    @OnOpen
    public void open(@PathParam("userId") String userId, @PathParam("taskFlowId") String taskFlowId, Session session) {
        UserSessionWS userSessionWS = new UserSessionWS(session, userId);
        tfSessionHandler.initialize(userSessionWS, taskFlowId);

    }

    /**
     * Close a WebSocket connection
     * @param userId
     * @param taskFlowId
     * @param session
     */
    @OnClose
    public void close(@PathParam("userId") String userId, @PathParam("taskFlowId") String taskFlowId, Session session) {
        UserSessionWS userSessionWS = new UserSessionWS(session, userId);
        tfSessionHandler.finalize(userSessionWS, taskFlowId);
    }

    /**
     * On Error when communicating the WebSocket
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(TaskFlowWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    /**
     * Dummy process, only messages are send to the server to keep alive the WebSocket connection from the client
     * @param message - Json Message 
     * @param session - Session sending the message
     */
    @OnMessage
    public void handleMessage(String message, Session session) {
//        if (message != null && !"".equalsIgnoreCase(message)) {
//            try (JsonReader reader = Json.createReader(new StringReader(message))) {
//                JsonObject jsonMessage = reader.readObject();
//                final String taskFlowId = jsonMessage.getString("taskFlowId");
//                final String userId = jsonMessage.getString("userId"); 
//                final String method = jsonMessage.getString("method");
//                if ("initializer".equalsIgnoreCase(method)) {
//                    tfSessionHandler.initialize(session, userId, taskFlowId);
//                } 
//    
//                if ("finalizer".equals(method)) {
//                    tfSessionHandler.finalize(session, userId, taskFlowId);
//                }
//            }
//        }
    }
}
