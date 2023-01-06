package com.patterns.gamma.behavioral;

class Monster {
    public String name;
    public int attack, defense;

    public Monster(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}

class MonsterModifier {
    protected Monster monster;
    protected MonsterModifier next;

    public MonsterModifier(Monster monster) {
        this.monster = monster;
    }

    // Method chaining
    public void add(MonsterModifier modifier) {
        if (next != null) {
            next.add(modifier);
        }
        else {
            next = modifier;
        }
    }

    public void handle() {
        // Simply calls handle on the next pointer
        if (next != null) next.handle();
    }
}

class DoubleAttackModifier extends MonsterModifier {
    public DoubleAttackModifier(Monster monster) {
        super(monster);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + monster.name + "'s attack ability");
        monster.attack *= 2;
        // Call super.handle() as this is responsible for traversing the entire Chain of Responsibility.
        super.handle();
    }
}

class IncreaseDefensiveModifier extends MonsterModifier {
    public IncreaseDefensiveModifier(Monster monster) {
        super(monster);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + monster.name + "'s defensive ability");
        monster.defense += 3;
        super.handle();
    }
}

class CurseModifier extends MonsterModifier {
    public CurseModifier(Monster monster) {
        super(monster);
    }

    @Override
    public void handle() {
        // Do nothing here
        System.out.println("No bonus modifiers applied.");
    }
}

/*
 * A sequence of handlers processing an event (command or query) one after another, optionally having default
 * processing implementation, as well as, the ability to terminate the processing chain.
 */
class MethodChainDemo {
    public static void main(String[] args) {
        Monster ghoul = new Monster("Ghoul", 2, 2);
        System.out.println(ghoul);

        MonsterModifier root = new MonsterModifier(ghoul);
        root.add(new CurseModifier(ghoul)); // As this does not call super.handle() the method chains are cancelled
        root.add(new DoubleAttackModifier(ghoul));
        root.add(new IncreaseDefensiveModifier(ghoul));
        root.handle();

        System.out.println(ghoul);
    }
}
