package com.patterns.gamma.behavioral;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum PhoneState {
    OFF_HOOK,
    ON_HOOK,
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum StateTrigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

class HandmadeStateMachineDemo {
    private static Map<PhoneState, List<Pair<StateTrigger, PhoneState>>> rules = new HashMap<>();

    static {
        rules.put(PhoneState.OFF_HOOK, List.of(
           new Pair<>(StateTrigger.CALL_DIALED, PhoneState.CONNECTING),
           new Pair<>(StateTrigger.STOP_USING_PHONE, PhoneState.ON_HOOK)
        ));
        rules.put(PhoneState.CONNECTING, List.of(
                new Pair<>(StateTrigger.HUNG_UP, PhoneState.OFF_HOOK),
                new Pair<>(StateTrigger.CALL_CONNECTED, PhoneState.CONNECTED)
        ));
        rules.put(PhoneState.CONNECTED, List.of(
                new Pair<>(StateTrigger.LEFT_MESSAGE, PhoneState.OFF_HOOK),
                new Pair<>(StateTrigger.HUNG_UP, PhoneState.OFF_HOOK),
                new Pair<>(StateTrigger.PLACED_ON_HOLD, PhoneState.ON_HOLD)
        ));
        rules.put(PhoneState.ON_HOLD, List.of(
                new Pair<>(StateTrigger.TAKEN_OFF_HOLD, PhoneState.CONNECTED),
                new Pair<>(StateTrigger.HUNG_UP, PhoneState.OFF_HOOK)
        ));
    }

    private static PhoneState currentState = PhoneState.OFF_HOOK;
    private static PhoneState exitState = PhoneState.ON_HOOK;

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("The phone is currently " + currentState);

            for (int i = 0; i < rules.get(currentState).size(); ++i) {
                StateTrigger trigger = rules.get(currentState).get(i).getValue0();
                System.out.println("" + i + ". " + trigger);
            }

            boolean parseOk;
            int choice = 0;

            do {
                try {
                 System.out.println("Please enter your choice:");
                 choice = java.lang.Integer.parseInt(console.readLine());
                 parseOk = choice >= 0 && choice < rules.get(currentState).size();
                } catch (Exception e) {
                    parseOk = false;
                }
            } while (!parseOk);

            currentState = rules.get(currentState).get(choice).getValue1();

            if (currentState == exitState) break;
        }
        System.out.println("DONE!");
    }
}
