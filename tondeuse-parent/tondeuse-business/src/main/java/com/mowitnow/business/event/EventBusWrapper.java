package com.mowitnow.business.event;

import com.google.common.eventbus.EventBus;

public class EventBusWrapper {

	public static void post(Object o) {
		EVENT_BUS.post(o);
	}

	public static void register(Object o) {
		EVENT_BUS.register(o);
	}

	public static void unregister(Object o) {
		EVENT_BUS.unregister(o);
	}

	/** Bus d'évênements synchrone */
	private static EventBus	EVENT_BUS	= new EventBus();

}
