package com.patterns.gamma.behavioral;

/*
* This demo uses the CoR, Observer, Mediator and Memento patterns.
* */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// Observer to notify on queries
class GameEvent<Args> {
    private int index = 0;
    private Map<java.lang.Integer, Consumer<Args>> handlers = new HashMap<java.lang.Integer, Consumer<Args>>();

    public int subscribe(Consumer<Args> handler) {
        int i = index;
        handlers.put(++index, handler);
        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void dispatch(Args args) {
        for (Consumer<Args> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;
    enum Argument {
        ATTACK, DEFENSE
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

// The game class will act as a mediator/broker
class Game {
    public GameEvent<Query> queries = new GameEvent<>();
}

class Creature {
    private Game game;
    public String creatureName;
    public int baseAttack, baseDefense;

    public Creature(Game game, String creatureName, int baseAttack, int baseDefense) {
        this.game = game;
        this.creatureName = creatureName;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        Query query = new Query(creatureName, Query.Argument.ATTACK, baseAttack);
        game.queries.dispatch(query);
        return query.result;
    }

    int getDefense() {
        Query query = new Query(creatureName, Query.Argument.DEFENSE, baseDefense);
        game.queries.dispatch(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "game=" + game +
                ", creatureName='" + creatureName + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class CreatureModifier {
    protected Game game;
    protected Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class TripleAttackModifier extends CreatureModifier
    implements AutoCloseable {
    private final int token;

    public TripleAttackModifier(Game game, Creature creature) {
        super(game, creature);

        token = game.queries.subscribe(query -> {
           if (query.creatureName.equals(creature.creatureName)
                   && query.argument == Query.Argument.ATTACK) {
               query.result *= 3;
           }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class DoubleDefenseModifier extends CreatureModifier {
    public DoubleDefenseModifier(Game game, Creature creature) {
        super(game, creature);

        game.queries.subscribe(query -> {
            if (query.creatureName.equals(creature.creatureName)
                    && query.argument == Query.Argument.DEFENSE) {
                query.result += 2;
            }
        });
    }
}

class BrokerChainDemo {
    public static void main(String[] args) {
        Game game = new Game();
        Creature alghoul = new Creature(game, "Alghoul", 2, 2);
        System.out.println(alghoul);

        DoubleDefenseModifier defense = new DoubleDefenseModifier(game, alghoul);
        TripleAttackModifier attack = new TripleAttackModifier(game, alghoul);

        try (attack) {
            System.out.println(alghoul);
        }

        System.out.println(alghoul);
    }
}
