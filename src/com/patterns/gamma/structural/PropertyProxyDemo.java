package com.patterns.gamma.structural;

class Property<T> {
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Property<?> property = (Property<?>) obj;

        return value != null ? value.equals(property.value) : property.value == null;
    }
}

class Monster {
    private Property<Integer> stamina = new Property<>(100);

    public void setStamina(int value) {
        stamina.setValue(value);
    }

    public int getStamina() {
        return stamina.getValue();
    }
}

class PropertyProxyDemo {
    public static void main(String[] args) {
        final Monster ghoul = new Monster();
        ghoul.setStamina(120);
    }
}
