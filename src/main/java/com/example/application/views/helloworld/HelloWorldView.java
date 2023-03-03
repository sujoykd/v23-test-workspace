package com.example.application.views.helloworld;

import org.jeasy.random.EasyRandom;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    public HelloWorldView() {
        final Grid<Person> grid = new Grid<>(Person.class, false);
        grid.addColumn("firstName").setHeader("First name");
        grid.addColumn("lastName").setHeader("Last name");
        grid.addColumn("email").setHeader("Email");
        grid.addColumn("address").setHeader("Address");
        grid.addColumn("address.pincode").setHeader("PinCode");

        final var people = new EasyRandom().objects(Person.class, 10).toList();
        grid.setItems(people);

        final var div = new Div();
        div.setId("testDiv");

        this.addAndExpand(grid);
        this.add(div);

        grid.addSelectionListener(event -> {
            event.getFirstSelectedItem()
                 .ifPresent(item -> div.setText(item.getFirstName()));
        });
    }

}
