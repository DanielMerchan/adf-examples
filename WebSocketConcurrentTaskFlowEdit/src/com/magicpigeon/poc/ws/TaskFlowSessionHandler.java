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

/**
 * Managed Bean for handling Sessions and User-Task Flow relationship
 * @author Daniel Merchan Garcia
 */
@ApplicationScoped
public class TaskFlowSessionHandler {

    /**
     * Map for keeping TF - List of Users working in the same TF
     */
    private Map<String, List<UserSessionWS>> tfSessionMap;

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
    public void initialize(UserSessionWS userSessionWS, String taskFlowId) {
        List<UserSessionWS> sessions = tfSessionMap.get(taskFlowId);
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        boolean userAlreadyEditing = sessions.stream().anyMatch(x -> x.equals(userSessionWS));
        if (!userAlreadyEditing) {
            sessions.add(userSessionWS);
            tfSessionMap.put(taskFlowId, sessions);
        }
        this.notifyUsers(taskFlowId);
    }

    /**
     * When a Task Flow is finalized, a message is sent with the Task Flow Id, User Id and who is finalizing its editing
     * @param userSessionWS
     * @param taskFlowId
     */
    public void finalize(UserSessionWS userSessionWS, String taskFlowId) {
        removeSession(userSessionWS, taskFlowId);
    }

    /**
     * Notify the users connected which are editing the same Task Flow. It sends a JSON back to the UI with the list of users and Task Flow
     * @param userSessionWS - User Session WS POJO
     * @param taskFlowId - Task Flow Id
     */
    private void notifyUsers(String taskFlowId) {
        List<UserSessionWS> sessionsInTaskFlow = tfSessionMap.get(taskFlowId);
        
        StringBuilder users = new StringBuilder();
        if (sessionsInTaskFlow != null && sessionsInTaskFlow.size() >1 ) {
            for (UserSessionWS s : sessionsInTaskFlow) {
                users.append(s.getUserId() + " ");
            }
            for (UserSessionWS s : sessionsInTaskFlow) {
                String usersToSend  = users.toString().replace(s.getUserId(),"");
                    JsonObject message = this.createConcurrentEditSessionMessage(usersToSend, taskFlowId);
                    this.sendToSession(s, taskFlowId, message);
            }
        }
    }


    /**
     * Create a JSON send to UI with the users editing the current task flow id
     * @param users
     * @param taskFlowId - Task Flow Id
     * @return JsonObject
     */
    private JsonObject createConcurrentEditSessionMessage(String users, String taskFlowId) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject editSessionMessage = provider.createObjectBuilder()
                                                .add("taskFlowId", taskFlowId)
                                                .add("userId", users)
                                                .build();
        return editSessionMessage;
    }

    /**
     * Auxiliar method to send a Stringfy JSON to the sesion
     * @param session - Session to communicate with
     * @param taskFlowId - Identifier of the Task Flow
     * @param message - JsonObject with the message
     */
    private void sendToSession(UserSessionWS userSessionWS, String taskFlowId, JsonObject message) {
        try {
            userSessionWS.getSession().getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
           this.removeSession(userSessionWS,taskFlowId);
        Logger.getLogger(TaskFlowSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Remvoe a session
     * @param userSessionWS
     * @param taskFlowId
     */
    private void removeSession(UserSessionWS userSessionWS, String taskFlowId) {
        List<UserSessionWS> sessions = tfSessionMap.get(taskFlowId);
        sessions.remove(userSessionWS);
        tfSessionMap.put(taskFlowId, sessions);
    }
}
