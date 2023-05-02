package com.example.application.views.helloworld;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.jeasy.random.EasyRandom;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

public class PersonDataProvider extends AbstractBackEndDataProvider<Person, PersonFilter> {
    public static int LAZY_RECORD;
    private static List<Person> DATABASE;

    {
        DATABASE = new ArrayList<>(new EasyRandom().objects(Person.class, 100000).toList());

        LAZY_RECORD = (int) (DATABASE.size() * 0.9);

        DATABASE.get(0).setFirstName("First");
        DATABASE.get(LAZY_RECORD).setFirstName(String.valueOf(LAZY_RECORD));
    }

    @Override
    protected Stream<Person> fetchFromBackEnd(final Query<Person, PersonFilter> query) {

        System.err.println("calling backend ....");

        // A real app should use a real database or a service
        // to fetch, filter and sort data.
        Stream<Person> stream = this.DATABASE.stream();

        // Filtering
        if (query.getFilter().isPresent()) {
            stream = stream.filter(person -> query.getFilter().get().test(person));
        }

        // Sorting
        if (query.getSortOrders().size() > 0) {
            stream = stream.sorted(sortComparator(query.getSortOrders()));
        }

        // Pagination
        return stream.skip(query.getOffset()).limit(query.getLimit());
    }

    @Override
    protected int sizeInBackEnd(final Query<Person, PersonFilter> query) {
        return (int) this.fetchFromBackEnd(query).count();
    }

    private static Comparator<Person> sortComparator(final List<QuerySortOrder> sortOrders) {
        return sortOrders.stream().map(sortOrder -> {
            Comparator<Person> comparator = personFieldComparator(sortOrder.getSorted());

            if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                comparator = comparator.reversed();
            }

            return comparator;
        }).reduce(Comparator::thenComparing).orElse((p1, p2) -> 0);
    }

    private static Comparator<Person> personFieldComparator(final String sorted) {
        if ("name".equals(sorted)) {
            return Comparator.comparing(com.example.application.views.helloworld.Person::getFullName);
        } else if ("profession".equals(sorted)) {
            return Comparator.comparing(com.example.application.views.helloworld.Person::getProfession);
        }
        return (p1, p2) -> 0;
    }
}
