package org.example.app.commands;

import org.hibernate.Session;

public interface Command {
    void execute(Session session);
}
