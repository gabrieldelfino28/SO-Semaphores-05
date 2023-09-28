package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ServerSim extends Thread {

    private int threadID;
    private Semaphore semaphore;
    private final Random r = new Random();

    public ServerSim(int threadID, Semaphore semaphore) {
        this.threadID = threadID;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        long calcTime = 0;
        long BDtime = 0;
        if (threadID % 3 == 1) {
            calcTime = r.nextInt(200,1000);
            BDtime = r.nextInt(1000);
        } else if (threadID % 3 == 2) {
            calcTime = r.nextInt(500,1500);
            BDtime = r.nextInt(1500);
        } else if (threadID % 3 == 0) {
            calcTime = r.nextInt(1000,2000);
            BDtime = r.nextInt(1500);
        }
        try {
            Calc(calcTime);
            semaphore.acquire();
            TransacaoBD(BDtime);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    private void Calc(long value) {
        try {
            System.out.println("Realizando Calculos...");
            sleep(value);
        } catch (Exception e) {

        }
        System.err.println("Calculo #" + threadID + " finalizado!");
    }

    private void TransacaoBD(long value) {
        try {
            System.out.println("Transação de Banco de Dados...");
            sleep(value);
        } catch (Exception e) {

        }
        System.err.println("Transação #" + threadID + " finalizada com sucesso!");
    }
}
