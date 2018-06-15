package com.magicpigeon.poc.ws;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import javax.enterprise.context.ApplicationScoped;

import javax.json.JsonObject;

import javax.json.spi.JsonProvider;

import javax.websocket.Session;

/**
 * Managed Bean for handling Sessions and User-Task Flow relationship
 * @author Daniel Merchan Garcia
 */
@ApplicationScoped
public class TaskFlowSessionHandler {

    /**
     * Map for keeping TF - List of Users working in the same TF
     */
    private Map<String, List<Session>> tfSessionMap;

    /**
     * Initializes the TaskFlowSessionHandler
     */
    @PostConstruct
    public void init() {
        tfSessionMap = new ConcurrentHashMap<>();
    }

    /**
     * When a Task Flow is initialize, a message is sent with the Task Flow Id, User Id of who is opening the TF.
     * Register the new user and notify the user if someone is already working in this TF.
     * @param session - WebSocket Session information
     * @param userId - Identifier of the user
     * @param taskFlowId - Identifier of the Task Flow
     */
    public void initialize(Session session, String userId, String taskFlowId) {
        List<Session> sessions = tfSessionMap.get(taskFlowId);
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        boolean userAlreadyEditing = sessions.stream().anyMatch(x -> x.getId().equals(session.getId()));
        if (!userAlreadyEditing) {
            sessions.add(session);
            tfSessionMap.put(taskFlowId, sessions);
        }
        this.notifyUsers(session, userId, taskFlowId);
    }

    /**
     * When a Task Flow is finalized, a message is sent with the Task Flow Id, User Id and who is finalizing its editing
     * @param session
     * @param userId
     * @param taskFlowId
     */
    public void finalize(Session session, String taskFlowId) {
        removeSession(session, taskFlowId);
    }

    /**
     * Notify the users connected which are editing the same Task Flow. It sends a JSON back to the UI with the list of users and Task Flow
     * @param session - Session
     * @param userId - User Id
     * @param taskFlowId - Task Flow Id
     */
    private void notifyUsers(Session session, String userId, String taskFlowId) {
        List<Session> sessionsInTaskFlow = tfSessionMap.get(taskFlowId);
        for (Session s : sessionsInTaskFlow) {
            if (!s.getId().equalsIgnoreCase(session.getId())) {
                System.out.println("NOTIFYING: " + s.getId());
                JsonObject message = this.createConcurrentEditSessionMessage(userId, taskFlowId);
                this.sendToSession(s, taskFlowId, message);
            }
        }
    }


    /**
     * Create a JSON send to UI with the users editing the current task flow id
     * @param user
     * @param taskFlowId - Task Flow Id
     * @return JsonObject
     */
    private JsonObject createConcurrentEditSessionMessage(String user, String taskFlowId) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject editSessionMessage = provider.createObjectBuilder()
                                                .add("taskFlowId", taskFlowId)
                                                .add("userId", user)
                                                .build();
        return editSessionMessage;
    }

    /**
     * Auxiliar method to send a Stringfy JSON to the sesion
     * @param session - Session to communicate with
     * @param taskFlowId - Identifier of the Task Flow
     * @param message - JsonObject with the message
     */
    private void sendToSession(Session session, String taskFlowId, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
           this.removeSession(session,taskFlowId);
        Logger.getLogger(TaskFlowSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Remvoe a session
     * @param session
     * @param taskFlowId
     */
    private void removeSession(Session session, String taskFlowId) {
        List<Session> sessions = tfSessionMap.get(taskFlowId);
        sessions.remove(session);
        tfSessionMap.put(taskFlowId, sessions);
    }
}
