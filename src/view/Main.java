package view;

import controller.ServerSim;

import java.util.concurrent.Semaphore;

public class Main {
    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        for(int id = 1; id < 22; id++) {
            Thread Tserver = new ServerSim(id,semaphore);
            Tserver.start();
        }
    }
}
