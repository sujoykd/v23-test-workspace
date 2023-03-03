package com.example.application.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("logout")
@AnonymousAllowed
public class Logout extends VerticalLayout {
    
    public Logout() {
        super();
        add(new Span("Thank you!"));
    }
    
}
