Simple Event-Driven System for Android
---
Inspired by the [event-driven architecture](https://en.wikipedia.org/wiki/Event-driven_architecture) of JavaScript, this project is aimed to facilitate the comprehension of EDA.

# Usage Preview
A set of API is implemented quite similar to [`Event`](https://developer.mozilla.org/en-US/docs/Web/API/Event/Event), [`EventTarget`](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget) and [`EventListener`](https://developer.mozilla.org/en-US/docs/Web/API/EventListener) in JavaScript.

## Create an [Event](app/src/main/java/com/jke/eventdrivensystem/models/Event.java)
```java
// Create a custom event
Event event = new Event();
event.type = /* type string */;
event.args = /* any object */;
// then dispatch this event somewhere.
```

## Add an [Event Listener](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventListener.java)
```java
// Create and instantiate an event target.
EventTarget target = new EventTarget() {};
// Create and implement an event listener.
EventListener listener = new EventListener() {
  @Override
  public void on(Event event) {
      switch(event.type) {
        case "event_type":
          // TODO: handle event typed of "event_type".
          break;
        default:
          break;
      }
  }
};
// Add event listeners
target.addEventListener("event_type", listener);
// Dispatch an event
target.dispatch(event);
```

## Remove an [Event Listener](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventListener.java)
```java
// Remove a specific event listener
target.removeEventListener("event_type", listener);
// Remove all listeners of a specific event
target.removeEventListener("event_type");
// Remove all listeners
target.removeEventListener();
```

# Implementation Preview
You can implement an event target from [`EventTarget`](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventTarget.java) or an event listener from [`EventListener`](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventListener.java).

## Implement an [Event Target](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventTarget.java)
```java
public class SampleEventTarget extends EventTarget {
  // TODO: dispatch an event somewhere.
  @Override
  public void dispatch(Event event) {
      // TODO: dispatch event.
  }
}
```

## Implement an [Event Listener](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventListener.java)
```java
public class SampleEventListener extends EventListener {
  @Override
  public void on(event) {
      switch (event.type) {
        case "event_type":
          // TODO: handle event typed of "event_type".
          break;
        default:
          break;
      }
  }
}
```

# Wrapper Preview
You can implement an event target wrapper from [`IEventTarget`](app/src/main/java/com/jke/eventdrivensystem/interfaces/IEventTarget.java) or an event listener wrapper from [`IEventHandler`](app/src/main/java/com/jke/eventdrivensystem/interfaces/IEventHandler.java).

## An [Event Target](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventTarget.java) Wrapper Implementing [`IEventTarget`](app/src/main/java/com/jke/eventdrivensystem/interfaces/IEventTarget.java)
```java
public class SampleEventTargetWrapper implements IEventTarget {
  private EventTarget mEventTarget = new EventTarget {
  };

  @Override
  public void addEventListener(String type, IEventHandler listener) {
    mEventTarget.addEventListener(type, listener);
  }

  @Override
  public void removeEventListener(String type, IEventHandler listener) {
    mEventTarget.removeEventListener(type, listener)
  }

  @Override
  public void removeEventListener(String type) {
    mEventTarget.removeEventListener(type);
  }

  @Override
  public void dispatch(Event event) {
    mEventTarget.dispatch(event);
  }
}
```

## An [Event Listener](app/src/main/java/com/jke/eventdrivensystem/abstracts/EventListener.java) Wrapper Implementing [`IEventHandler`](app/src/main/java/com/jke/eventdrivensystem/interfaces/IEventHandler.java) 
```java
public class SampleEventListenerWrapper implements IEventHandler {
  private EventListener mEventListener = new EventListener() {
    @Override
    public void on(Eevnt event) {
      switch (event.type) {
        case "event_type":
          // TODO: handle event typed of "event_type".
          break;
        default:
          break;
      }
    }
  };

  @Override
  public void invoke(Event event) {
    mEventListener.invoke(event);
  }

  @Override
  public void on(Event event) {
    mEventListener.on(event);
  }
}
```

License
---
[MIT](LICENSE).