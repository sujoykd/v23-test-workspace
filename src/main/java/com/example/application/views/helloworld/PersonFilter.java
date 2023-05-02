package com.example.application.views.helloworld;

public class PersonFilter {
    private String searchTerm;

    public void setSearchTerm(final String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public boolean test(final Person person) {
        final boolean matchesFullName = this.matches(person.getFullName(), this.searchTerm);
        final boolean matchesProfession = this.matches(person.getProfession(), this.searchTerm);
        return matchesFullName || matchesProfession;
    }

    private boolean matches(final String value, final String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
