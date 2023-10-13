package org.eclipse.rest.api.services;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.eclipse.rest.api.models.User;

import jakarta.inject.Singleton;

@Singleton
public class UserService {
	public static int counter = 0;
	public static AtomicInteger realCounter = new AtomicInteger(0);
 	private static Logger logger = Logger.getLogger(UserService.class.getName());

    public User get(String name) {
        if ((name == null) || name.trim().isEmpty())  {
			name = "world";
		}
		int id = counter++;
		int idReal = realCounter.incrementAndGet();
		logger.info(String.format("ID: %s", id));
		logger.info(String.format("ID: %s", idReal));
        return new User(name, "ok", Integer.valueOf(id));
    }
}
