package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;


public class GCDMain {

        public static void main(String[] args) {

            Logger logger = LogManager.getLogger(MemberManager.class.getName());

                logger.info("Starting.");
                int a, b;
                Scanner in = new Scanner(System.in);
                System.out.println("Compute GCD for a/b. Enter a and b:");
                a = in.nextInt();
                b = in.nextInt();
                in.close();
                logger.debug(String.format("a=%d, b=%d", a, b));
                if (b != 0) {
                    while( a != b) {
                        if (a > b)
                            a = a - b;
                        else
                            b = b - a;
                    }
                    System.out.println("GCD=" + a);
                } else {
                    logger.error("Denominator is zero!");
                }
                logger.info("Ended.");
            }

        }


