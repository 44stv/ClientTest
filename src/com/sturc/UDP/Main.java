package com.sturc.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] receiveBuffer = new byte[50];
                packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                datagramSocket.receive(packet);
                System.out.println("Text received is: " + new String(receiveBuffer, 0, packet.getLength()));

            } while (!echoString.equals("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("Socket timed out.");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
