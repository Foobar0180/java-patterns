package com.patterns.gamma.behavioral;

/*
* A pattern in which the object's behaviour is determined by its state. An object transitions
* from one state to another, triggered by a transition. A formalised construct which manages
* state and transitions state is called a state machine.
* */

class LightBulbState {
    void on(LightSwitch light) {
        System.out.println("Light is already on");
    }

    void off(LightSwitch light) {
        System.out.println("Light is already off");
    }
}

class OnState extends LightBulbState {
    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch light) {
        System.out.println("Switching the light off");
        light.setState(new OffState());
    }
}

class OffState extends  LightBulbState {
    public OffState() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch light) {
        System.out.println("Switching the light on");
        light.setState(new OnState());
    }
}

class LightSwitch {
    private LightBulbState state;

    public LightSwitch() {
        state = new OffState();
    }

    public void setState(LightBulbState state) {
        this.state = state;
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }
}

class ClassicStateMachineDemo {
    public static void main(String[] args) {
        LightSwitch light = new LightSwitch();
        light.on();
        light.off();
        light.off();
    }
}
