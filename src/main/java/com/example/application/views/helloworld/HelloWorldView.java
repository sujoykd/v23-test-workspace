package com.example.application.views.helloworld;

import java.util.List;
import java.util.stream.IntStream;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public HelloWorldView() {
        this.prepareGrid();
    }

    private void datepickerTest() {
        final DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Meeting date and time");
        dateTimePicker.getElement().setAttribute("onkeypress", "return false;");
        dateTimePicker.getElement().getStyle().set("caret-color", "transparent");
        this.add(dateTimePicker);
    }

    private void binderTest() {
        this.setWidthFull();
        final TextField field = new TextField();
        field.setLabel("Name");
        field.setWidthFull();
        final Binder<HelloWorldView> binder = new Binder<>();
        binder.forField(field)
              .asRequired("Name is mandatory")
              .withValidator(name -> name.length() >= 3,
                             "Name must contain at least three characters. asdsd \n\n ssd")
              .withValidator(name -> !name.startsWith("s"),
                             "Name cannot start with s")
              .bind(HelloWorldView::getName, HelloWorldView::setName);

        this.add(field);
    }

    private void menuTest() {
        final Div div = new Div();
        div.setText("Move the splitter to see overflow feature");

        final MenuBar responsiveMenu = new MenuBar();

        for (final var button : this.buttons()) {
            final MenuItem menuItem = responsiveMenu.addItem(button);
            menuItem.addThemeNames("rockwell-ribbon");
            menuItem.getElement().getStyle().set("font-weight", "bold");
            menuItem.getElement().getClassList().add("execution-ribbon-menu-item");

            menuItem.addClickListener(e -> {
                System.out.println(e.getSource().getText());
            });
            menuItem.setEnabled(true);
        }

        final SplitLayout splitLayout = new SplitLayout(responsiveMenu, div);
        this.add(splitLayout);
    }

    private List<Div> buttons() {
        return IntStream.rangeClosed(65, 75).mapToObj(num -> String.valueOf((char) num)).map(Text::new).map(Div::new).toList();
    }

    private void prepareUpload() {
        final MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        final Upload upload = new Upload(buffer);
        final int maxFileSizeInBytes = 100 * 1024 * 1024; // 10MB
        upload.setMaxFileSize(maxFileSizeInBytes);

        upload.addSucceededListener(event -> {
            final String fileName = event.getFileName();
            System.out.println(fileName);
        });

        this.add(upload);
    }

    private void prepareGrid() {
        final Grid<Person> grid = new Grid<>(Person.class, false);
        grid.addColumn("firstName").setHeader("First name");
        grid.addColumn("lastName").setHeader("Last name");
        grid.addColumn("email").setHeader("Email");
        grid.addColumn("address").setHeader("Address");
        grid.addColumn("address.pincode").setHeader("PinCode");

        grid.setPageSize(50);

        final PersonFilter personFilter = new PersonFilter();
        final PersonDataProvider dataProvider = new PersonDataProvider();

        final ConfigurableFilterDataProvider<Person, Void, PersonFilter> filterDataProvider = dataProvider.withConfigurableFilter();
        filterDataProvider.setFilter(personFilter);
        grid.setItems(filterDataProvider);

        final var showSelection = new Div();
        showSelection.setId("testDiv");

        final var btnLazy = new Button("Go to " + PersonDataProvider.LAZY_RECORD, evt -> {
            grid.scrollToIndex(PersonDataProvider.LAZY_RECORD);
        });

        final var btnFirst = new Button("Go to 0", evt -> {
            grid.scrollToIndex(0);
        });

        final var section = new HorizontalLayout(btnFirst, btnLazy, showSelection);

        final var container = new VerticalLayout(section, grid);

        this.addAndExpand(container);
        this.setSizeFull();

        grid.addSelectionListener(event -> {
            event.getFirstSelectedItem()
                 .ifPresent(item -> showSelection.setText(item.getFirstName()));
        });
    }

}
