package pangpang.controller.member.암호화.test;


import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule{
	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("++ Repeat task : " + LocalTime.now());
           
        };
        int initialDelay = 2;
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now() );
        executor.scheduleAtFixedRate(
            runnable, initialDelay, delay, TimeUnit.SECONDS);
		
        
        
        
		
		
	}

}
