package com.magicpigeon.poc.ws;

import java.util.Objects;

import javax.websocket.Session;

/**
 * POJO Class for keeping WebSocket Session and User Identifier association
 * @author Daniel Merchan Garcia
 */
public final class UserSessionWS {

    /**
     * WebSocket Session
     */
    private Session session;

    /**
     * User identifier, in adf #{securityContext.userName}
     */
    private String userId;

    /**
     * Constructs a UserSession based on the WebSocekt Session and the User Identifier
     * @param session
     * @param userId
     */
    public UserSessionWS(Session session, String userId) {
        this.session = session;
        this.userId = userId;
    }

    /**
     * Set WebSocket Session
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Get WebSocket Session
     * @return Session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Set the User Identifier
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get the User Identifier
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Override equals
     * @param o - Other UserSessionWS object
     * @return true if the objects are the same
     */
    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof UserSessionWS)) {
            return false;
        }
        UserSessionWS user = (UserSessionWS) o;
        boolean a = Objects.equals(userId, user.userId) && Objects.equals(session, user.session);
        System.out.println("Equals userId: " + userId + " " + user.userId + " " + Objects.equals(userId, user.userId));
        System.out.println("Equals session: " + session + " " + user.session + " " + Objects.equals(session, user.session));
        System.out.println("Equals: " + a);
        return Objects.equals(userId, user.userId) && Objects.equals(session, user.session);
    }

    /**
     * Overide Hashcode
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, session);
    }
}
