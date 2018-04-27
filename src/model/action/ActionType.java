package model.action;

/**
 * Defines types of action that an element is capable of executing
 * @author Softvision
 *
 */
public enum ActionType {
    MOVE,
    MOVE1,
    PAUSE,
    EAT,
    REPRODUCE, // defines a reproduce behavior that requires two individuals of the same type
    REPRODUCE_COPY; // defines a reproduce behavior that requires only one individual
}