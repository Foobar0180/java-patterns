package com.patterns.gamma.singletons;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertEquals;

interface Database {
    int getPopulation(String cityName);
}

class CitiesDatabase implements Database {
    private static Dictionary<String, Integer> cities = new Hashtable<>();
    private static int instanceCount = 0;
    private static final CitiesDatabase INSTANCE = new CitiesDatabase();

    private CitiesDatabase() {
        instanceCount++;
        System.out.println("Initializing database");

        try {
            String pathName = CitiesDatabase.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath();

            File file = new File(pathName);
            Path fullPath = Paths.get(file.getPath(), "cities.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(city -> cities.put(
                            city.get(0).trim(),
                            Integer.parseInt(city.get(1))
                    ));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static CitiesDatabase getInstance() {
        return INSTANCE;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public int getPopulation(String cityName) {
        return cities.get(cityName);
    }
}

class CitiesFinder {
    private Database database;

    public CitiesFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulations(List<String> cities) {
        int totalPopulation = 0;
        for (String city : cities) {
            totalPopulation += database.getPopulation(city);
        }
        return totalPopulation;
    }
}

class DummyDatabase implements Database {
    private static Dictionary<String, Integer> cities = new Hashtable<>();

    public DummyDatabase() {
        cities.put("Alpha", 1);
        cities.put("Beta", 2);
        cities.put("Gamma", 3);
    }

    @Override
    public int getPopulation(String cityName) {
        return cities.get(cityName);
    }
}

class CitiesDemo {
    @Test
    public void calculateTotalPopulationTest() {
        DummyDatabase database = new DummyDatabase();
        CitiesFinder finder = new CitiesFinder(database);
        assertEquals(4, finder.getTotalPopulations(
                List.of("Alpha", "Gamma")
        ));
    }
}
