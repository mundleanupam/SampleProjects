package Other;

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.assertTrue;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
    /**
     * Takes a list of available countries and returns a list of its elements re-ordered.
     *
     * The new order of the elements is as follows:
     *
     * First the special countries in their original order from the special countries list.
     * Then the list of available countries, sorted alphabetically by name.
     *
     * Not all of the special countries may appear in the list of available countries.
     *
     * Available: United States, Mexico, Canada, Costa Rica, Bermuda, Belgium
     * Special: Canada, Mexico, France, Belgium
     * Result: Canada, Mexico, Belgium, Bermuda, Costa Rica, United States
     */
    public class Sorter implements ICountrySorter {
        public List<Country> sortCountries(List<Country> availableCountries, List<Country> specialCountries) {
            return null;
        }
    }

    public class Country {
        private String name;

        public Country(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public interface ICountrySorter {
        public List<Country> sortCountries(List<Country> availableCountries, List<Country> specialCountries);
    }

    /*** TESTS ***/
    private List<Country> availableCountries;
    private List<Country> specialCountries;

    private final Country specialA = new Country("Special A");
    private final Country specialB = new Country("Special B");
    private final Country specialC = new Country("Special C");
    private final Country specialD = new Country("Special D");
    private final Country specialE = new Country("Special E");

    private final Country countryA = new Country("Country A");
    private final Country countryB = new Country("Country B");
    private final Country countryC = new Country("Country C");
    private final Country countryD = new Country("Country D");
    private final Country countryE = new Country("Country E");

    private final List<Country> nonSpecialCountriesSorted = Arrays.asList(countryA, countryB, countryC, countryD, countryE);

    @Before
    public  void setUp() throws Exception {
        availableCountries = new ArrayList<Country>();

        availableCountries.add(countryE);
        availableCountries.add(specialD);
        availableCountries.add(countryA);
        availableCountries.add(countryC);
        availableCountries.add(specialB);
        availableCountries.add(countryB);
        availableCountries.add(specialA);
        availableCountries.add(countryD);

        specialCountries = new ArrayList<Country>();
        specialCountries.add(specialA);
        specialCountries.add(specialC);
        specialCountries.add(specialD);
        specialCountries.add(specialB);
        specialCountries.add(specialE);
    }

    @Test
    public void testSort() {
        ICountrySorter countrySorter = sorter();

        List<Country> countries = countrySorter.sortCountries(availableCountries, specialCountries);
        String temp = countries.get(0).getName();
        assertTrue(countries.get(0).getName().equals(specialA.getName()));
        assertTrue(countries.get(1).getName().equals(specialD.getName()));
        assertTrue(countries.get(2).getName().equals(specialB.getName()));

        checkNonSpecialCountries(countries.subList(3, countries.size()));
    }

    @Test
    public void testNoSpecialCountries() {
        ICountrySorter countrySorter = sorter();
        List<Country> availableCountries = nonSpecialCountriesListReversed();

        List<Country> countries = countrySorter.sortCountries(availableCountries, new ArrayList<Country>());
        checkNonSpecialCountries(countries);

        countries = countrySorter.sortCountries(availableCountries, null);
        checkNonSpecialCountries(countries);
    }

    @Test
    public void testNoAvailableCountries() {
        ICountrySorter countrySorter = sorter();
        List<Country> countries = countrySorter.sortCountries(new ArrayList(), specialCountries);
        assertTrue(countries == null || countries.size() == 0);

        countries = countrySorter.sortCountries(null, specialCountries);
        assertTrue(countries == null || countries.size() == 0);
    }

    private List<Country> nonSpecialCountriesListReversed() {
        List<Country> reversed = new ArrayList(nonSpecialCountriesSorted);
        Collections.reverse(reversed);
        return reversed;
    }

    private void checkNonSpecialCountries(List<Country> nonSpecialCountriesToCheck) {
        for (int i=0; i < nonSpecialCountriesSorted.size(); i++) {
            assertTrue(nonSpecialCountriesToCheck.get(i).getName().equals(nonSpecialCountriesSorted.get(i).getName()));
        }
    }

    private ICountrySorter sorter() {
        return new Sorter();
    }

    public static void main(String[] args) {
        JUnitCore.main("Other.Solution");
        //JUnitCore jUnitCore = new JUnitCore();
        //Result result = jUnitCore.run(Solution.class);
        //System.out.println(result.toString());
    }
}



