package br.com.alvesdanilo.refatora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RefatoraApplication {

	public static void main(String[] args) {
		// PROGRAM: REFATORA_LGC (Lunar Guidance Computer)
		// PURPOSE: CODE REVIEW PLATFORM INITIALIZATION
		// PILOT:   DANILO DE F. ALVES

		// ; CLEAR ACCUMULATOR
		// XOR AX, AX
		System.out.println(">>> [INIT] PREPARING THRUSTERS...");

		// ; CHECK MEMORY BANKS (MAGIC NUMBER CHECK)
		// MOV EBX, 0xDEADBEEF
		System.out.println(">>> [CHECK] MEMORY INTEGRITY: 0xDEADBEEF [OK]");

		// ; LOAD SPRING BOOT KERNEL INTO STACK
		// PUSH CS
		// CALL SPRING_RUN
		SpringApplication.run(RefatoraApplication.class, args);

		// ; MISSION STATUS: GO FOR ORBIT
		// JMP 0xFFFF
		System.out.println("\n" +
				"  ____  _____ _____    _  _____ ___  ____      _    \n" +
				" |  _ \\| ____|  ___|  / \\|_   _/ _ \\|  _ \\    / \\   \n" +
				" | |_) |  _| | |_    / _ \\ | || | | | |_) |  / _ \\  \n" +
				" |  _ <| |___|  _|  / ___ \\| || |_| |  _ <  / ___ \\ \n" +
				" |_| \\_\\_____|_|   /_/   \\_\\_| \\___/|_| \\_\\/_/   \\_\\\n" +
				" ===================================================\n" +
				" >>> SYSTEM ONLINE.\n" +
				" >>> READY TO REFACTOR THE WORLD.\n");
	}

}